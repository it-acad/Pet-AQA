package config;

import lombok.Data;

@Data
@YamlConfUrl(configUrl = "core.yaml")
public class CoreConfig {
    private String hostUrl;
    private String adminUiUrl;
    private String amadeusUrl;
    private String shoppingPath;
    private String petPath;
    private String petUrl;
    private String clientId;
    private String secretKey;
}
