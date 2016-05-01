package com.patimer.apartment.service.notificationService;

import com.patimer.apartment.model.ApartmentEntity;
import com.patimer.apartment.service.mail.MailMessage;
import com.patimer.apartment.service.mail.MailSender;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.jsoup.helper.Validate;

import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailNotificationServiceImpl implements NotificationService
{
    private MailSender mailSender;
    private String from;
    private List<String> recipients;
    private VelocityEngine velocityEngine;

    public MailNotificationServiceImpl(MailSender mailSender, String from, List<String> recipients)
    {
        Validate.notNull(mailSender);
        Validate.notEmpty(from);
        Validate.isTrue(recipients != null && recipients.size() > 0);

        this.mailSender = mailSender;
        this.from = from;
        this.recipients = recipients;
        initVelocity();
    }

    private void initVelocity()
    {
        this.velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
    }

    @Override
    public void notify(ApartmentEntity apartment)
    {
        notify(Collections.singletonList(apartment));
    }

    @Override
    public void notify(List<ApartmentEntity> apartments)
    {
        Validate.isTrue(apartments.size() > 0);

        String subject = "Found " + apartments.size() +" matching your search";

        Template template = velocityEngine.getTemplate("templates/apartmentNotificationTemplate.vm");
        VelocityContext context = new VelocityContext();
        context.put("apartments", apartments);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        MailMessage mailMessage = new MailMessage(from, recipients, subject, writer.toString());
        mailSender.send(mailMessage);
    }
}
