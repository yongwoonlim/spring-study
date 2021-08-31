package com.yongwoonlim.jwttutorial.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtil {
    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    private SecurityUtil() {
    }

    public static Optional<String> getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String username = null;
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) principal;
            username = springSecurityUser.getUsername();
        } else if (principal instanceof String) {
            username = (String) principal;
        }

        return Optional.ofNullable(username);
    }
}
