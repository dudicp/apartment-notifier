package com.patimer.apartment.model.setting;

import java.util.List;

public class MailSettingEntity
{
    private List<String> mailRecipients;
    private String mailFrom;
    private String mailgunUrl;
    private String mailgunApiKey;

    public MailSettingEntity(List<String> mailRecipients, String mailFrom, String mailgunUrl, String mailgunApiKey)
    {
        this.mailRecipients = mailRecipients;
        this.mailFrom = mailFrom;
        this.mailgunUrl = mailgunUrl;
        this.mailgunApiKey = mailgunApiKey;
    }

    public List<String> getMailRecipients()
    {
        return mailRecipients;
    }

    public String getMailFrom()
    {
        return mailFrom;
    }

    public String getMailgunUrl()
    {
        return mailgunUrl;
    }

    public String getMailgunApiKey()
    {
        return mailgunApiKey;
    }
}
