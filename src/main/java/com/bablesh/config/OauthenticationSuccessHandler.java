package com.bablesh.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bablesh.entity.Providers;
import com.bablesh.entity.User;
import com.bablesh.helper.AppConstants;
import com.bablesh.repository.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OauthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepo userRepo;
    Logger logger = LoggerFactory.getLogger(OauthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("OauthenticationSuccessHandler.....");
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
        // logger.info(user.getName());
        // user.getAttributes().forEach((key, value)->{
            // logger.info("{} ::=> {}", key, value);
            // });
            // logger.info(user.getAttributes().toString());
            String email = user.getAttribute("email").toString();
            String name = user.getAttribute("name").toString();
            String picture = user.getAttribute("picture").toString();
            
            User user3 = userRepo.findByEmail(email).orElse(null);
            if (user3 == null) {
                // ^ create user and save database
                User user2 = new User();
                user2.setEmail(email);
                user2.setName(name);
                user2.setProfilePic(picture);
                user2.setPassword("password");
                user2.setUserId(UUID.randomUUID().toString());
                user2.setProvider(Providers.GOOGLE);
                user2.setEnabled(true);
                user2.setEmailVerified(true);
                user2.setProviderUserId(user.getName());
                user2.setRoleList(List.of(AppConstants.ROLE_USER));
                user2.setAbout("This account is created using Google...");
                
                userRepo.save(user2);
                logger.info("User Saved : " + email);
            }
            new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

}
