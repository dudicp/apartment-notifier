package com.patimer.apartment.service.mail;

import com.patimer.apartment.model.ApartmentEntity;
import com.patimer.apartment.model.ApartmentEntityBuilder;
import com.patimer.apartment.service.notificationService.MailNotificationServiceImpl;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestMailNotificationService
{
    @Test
    public void testNotify()
    {
        // given
        String from = "test@gmail.com";
        List<String> recipients = Collections.singletonList("test@gmail.com");
        MailSender mailSender = Mockito.mock(MailSender.class);
        MailNotificationServiceImpl mailNotificationService =
            new MailNotificationServiceImpl(mailSender, from, recipients);

        ArgumentCaptor<MailMessage> mailMessageArgumentCapture = ArgumentCaptor.forClass(MailMessage.class);
        ApartmentEntity apartmentEntity1 = new ApartmentEntityBuilder().withAddress("Address1").build();
        ApartmentEntity apartmentEntity2 = new ApartmentEntityBuilder().withAddress("Address2").build();

        // when
        mailNotificationService.notify(Arrays.asList(apartmentEntity1, apartmentEntity2));
        Mockito.verify(mailSender).send(mailMessageArgumentCapture.capture());

        // then
        MailMessage mailMessage = mailMessageArgumentCapture.getValue();
        Assert.notNull(mailMessage.getText());
        Assert.isTrue(from.equals(mailMessage.getFrom()));
        Assert.isTrue(recipients.equals(mailMessage.getRecipient()));
        Assert.isTrue(mailMessage.getText().contains("Address1"));
        Assert.isTrue(mailMessage.getText().contains("Address2"));
    }

}
