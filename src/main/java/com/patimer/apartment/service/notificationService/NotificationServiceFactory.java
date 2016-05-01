package com.patimer.apartment.service.notificationService;

import com.patimer.apartment.model.setting.MailSettingEntity;
import com.patimer.apartment.service.mail.MailSender;
import com.patimer.apartment.service.mail.MailgunSandboxMailSenderImpl;
import org.apache.commons.lang.Validate;

public class NotificationServiceFactory
{
    public NotificationService create(NotificationServiceType notificationServiceType, MailSettingEntity mailSettingEntity)
    {
        Validate.notNull(notificationServiceType);

        switch (notificationServiceType)
        {
            case Email:
                return createMailNotificationService(mailSettingEntity);
            case Console:
                return createConsoleNotificationService();
            case SMS:
            default:
                throw new IllegalArgumentException("Unsupported notification service type: '" + notificationServiceType + "'.");
        }
    }

    private MailNotificationServiceImpl createMailNotificationService(MailSettingEntity mailSettingEntity)
    {
        MailSender mailSender = new MailgunSandboxMailSenderImpl(mailSettingEntity.getMailgunUrl(), mailSettingEntity.getMailgunApiKey());
        return new MailNotificationServiceImpl(mailSender, mailSettingEntity.getMailFrom(), mailSettingEntity.getMailRecipients());
    }

    private ConsoleNotificationServiceImpl createConsoleNotificationService()
    {
        return new ConsoleNotificationServiceImpl();
    }

}
