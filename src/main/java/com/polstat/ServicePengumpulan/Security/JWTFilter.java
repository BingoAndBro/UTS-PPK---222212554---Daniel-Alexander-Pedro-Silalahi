package com.polstat.ServicePengumpulan.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        String identifier = null;
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            identifier = jwtUtil.extractIdentifier(token);
        }

        
      if (identifier != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        String role = jwtUtil.extractRole(token);

             // Tambahkan log untuk melihat role dan identifier saat ini
        System.out.println("Role: " + role + ", Identifier: " + identifier);
        
        // Allow access for `ADMIN` if identifier is "admin"
        if ((("SISWA".equals(role) && jwtUtil.validateToken(token, identifier)) ||
                ("ADMIN".equals(role) && jwtUtil.validateToken(token, "admin")))) {
            
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    identifier, null, authenticatedUserService.getAuthorities(role));
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
        chain.doFilter(request, response);
    }
}

