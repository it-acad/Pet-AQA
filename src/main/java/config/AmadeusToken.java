package config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class AmadeusToken {
    private String token;
    private long expiresIn;
    private long createdAt;
    private String raw;

    public AmadeusToken(String token, long expiresIn, String raw) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.createdAt = Instant.now().getEpochSecond();
        this.raw = raw;
    }
}