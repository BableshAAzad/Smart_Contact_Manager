package com.bablesh.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication) {
        AuthenticationPrincipal principal = (AuthenticationPrincipal) authentication.getPrincipal();
        if (principal instanceof OAuth2AuthenticatedPrincipal) {

            var oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            
            if (clientId.equalsIgnoreCase("google")) {
                // * sing with google
                System.out.println("Getting email form google");
            } else if (clientId.equalsIgnoreCase("github")) {
                // * sing with github
                System.out.println("Getting email form github");
            } else if (clientId.equalsIgnoreCase("facebook")) {
                // * sing with facebook
                System.out.println("Getting email form facebook");
            }
            return "";
        } else {
            // * sing with email and password
            System.out.println("Getting data from local database");
            return authentication.getName();
        }
    }
}
