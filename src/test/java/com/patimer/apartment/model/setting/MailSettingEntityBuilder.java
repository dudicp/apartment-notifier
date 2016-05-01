package com.patimer.apartment.model.setting;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MailSettingEntityBuilder
{
    private List<String> mailRecipients = Collections.singletonList("dudicp@gmail.com");
    private String mailFrom = "Mailgun Sandbox <postmaster@sandboxca73482de7734d1aa0d83d5d9cedcc96.mailgun.org>";
    private String mailgunUrl = "https://api.mailgun.net/v3/sandboxca73482de7734d1aa0d83d5d9cedcc96.mailgun.org/messages";
    private String mailgunApiKey = "key-" + UUID.randomUUID();


    public MailSettingEntityBuilder withMailRecipients(List<String> mailRecipients)
    {
        this.mailRecipients = mailRecipients;
        return this;
    }

    public MailSettingEntityBuilder withMailFrom(String mailFrom)
    {
        this.mailFrom = mailFrom;
        return this;
    }

    public MailSettingEntityBuilder withMailGunUrl(String mailgunUrl)
    {
        this.mailgunUrl = mailgunUrl;
        return this;
    }

    public MailSettingEntityBuilder withMailGunApiKey(String mailgunApiKey)
    {
        this.mailgunApiKey = mailgunApiKey;
        return this;
    }

    public MailSettingEntity build()
    {
        return new MailSettingEntity(mailRecipients, mailFrom, mailgunUrl, mailgunApiKey);
    }
}
