package com.auth.server.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@Builder
@ConfigurationProperties(prefix ="app")
public class AppProperties {
    private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Auth {
        private String tokenLogoutSecret= "CBBDB1CA1572D92485DD03009C69D629";
        private String tokenSecret = "926D96C90030DD58429D2751AC1BDBBC";
        private long tokenExpirationMsec = 864000000;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static final class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }
}