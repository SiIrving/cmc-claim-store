package uk.gov.hmcts.cmc.claimstore.config.properties.notifications;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
@ConfigurationProperties(prefix = "notifications")
public class NotificationsProperties {

    @NotEmpty
    private String govNotifyApiKey;

    @URL
    @NotEmpty
    private String frontendBaseUrl;

    @Valid
    private NotificationTemplates templates;

    public String getGovNotifyApiKey() {
        return govNotifyApiKey;
    }

    public void setGovNotifyApiKey(String govNotifyApiKey) {
        this.govNotifyApiKey = govNotifyApiKey;
    }

    public String getFrontendBaseUrl() {
        return frontendBaseUrl;
    }

    public void setFrontendBaseUrl(String frontendBaseUrl) {
        this.frontendBaseUrl = frontendBaseUrl;
    }

    public NotificationTemplates getTemplates() {
        return templates;
    }

    public void setTemplates(NotificationTemplates templates) {
        this.templates = templates;
    }

}
