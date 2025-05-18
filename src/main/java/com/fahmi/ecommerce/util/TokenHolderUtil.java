package com.fahmi.ecommerce.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenHolderUtil {
    private final HttpServletRequest request;
    private final JwtUtil jwtUtil;

    public String getUsername() {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);

        return jwtUtil.extractUsername(token);
    }
}
