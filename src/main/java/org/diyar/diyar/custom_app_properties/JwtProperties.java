package org.diyar.diyar.custom_app_properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;

    public void setSecret(String secret) { this.secret = secret; }

    public String getSecret() { return secret; }
}
