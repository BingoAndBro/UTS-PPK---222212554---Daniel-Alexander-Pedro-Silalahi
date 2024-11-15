package com.polstat.ServicePengumpulan.Service;

import com.polstat.ServicePengumpulan.DTO.AuthRequest;
import com.polstat.ServicePengumpulan.DTO.AuthResponse;
import com.polstat.ServicePengumpulan.Entity.User;
import com.polstat.ServicePengumpulan.Entity.UserRole;
import com.polstat.ServicePengumpulan.Repository.UserRepository;
import com.polstat.ServicePengumpulan.Security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(AuthRequest authRequest) {
        User user = userRepository.findByIdentifier(authRequest.getIdentifier()) // Adjust method to check both nim and id_panitia
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

            String identifier = user.getRole() == UserRole.ADMIN ? "admin" : authRequest.getIdentifier();
        String token = jwtUtil.generateToken(authRequest.getIdentifier(), user.getRole().name());
        return new AuthResponse(token);
    }
}
