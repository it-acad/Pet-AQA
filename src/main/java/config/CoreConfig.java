package config;

import lombok.Data;

@Data
@YamlConfUrl(configUrl = "core.yaml")
public class CoreConfig {
    private String hostUrl;
    private String adminUiUrl;
    private String amadeusUri;
    private String petUrl;
    private String clientId;
    private String secretKey;
}
