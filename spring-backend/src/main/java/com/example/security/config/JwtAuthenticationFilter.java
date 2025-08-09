package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Lấy token từ header
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // bỏ "Bearer "
            // 2. Lấy username từ token
            username = jwtTokenProvider.getUsernameFromToken(token);
        }

        // 3. Nếu có username và chưa được xác thực
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 4. Load user từ DB
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            // 5. Kiểm tra token hợp lệ
            if (jwtTokenProvider.validateToken(token, userDetails)) {
                // 6. Tạo authentication object
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                // 7. Gán vào context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 8. Cho request đi tiếp
        filterChain.doFilter(request, response);
    }
}
