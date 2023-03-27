package config;

import lombok.Data;

@Data
@YamlConfUrl(configUrl = "secret.yaml")
public class SecretConfig {
    private String clientId;
    private String secret;
}
