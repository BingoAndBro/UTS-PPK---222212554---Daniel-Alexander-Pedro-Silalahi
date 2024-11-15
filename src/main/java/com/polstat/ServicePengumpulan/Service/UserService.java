package com.polstat.ServicePengumpulan.Service;

import com.polstat.ServicePengumpulan.DTO.UserDTO;
import com.polstat.ServicePengumpulan.Entity.User;
import com.polstat.ServicePengumpulan.Entity.UserRole;
import com.polstat.ServicePengumpulan.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
        public UserRole getUserRole(String nim) {
        User user = userRepository.findByNim(nim)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getRole();
    }

    public User getUserByNim(String nim) {
        return userRepository.findByNim(nim)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
       public User getUserByIdPanitia(String idPanitia) {
        return userRepository.findByIdPanitia(idPanitia)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void registerUser(UserDTO userDTO) {
        User user = new User();

        if (userDTO.getRole() == UserRole.ADMIN) {
            user.setIdPanitia(userDTO.getIdPanitia());
            user.setNim(null); // Clear nim for Admin
        } else if (userDTO.getRole() == UserRole.SISWA) {
            user.setNim(userDTO.getNim());
            user.setIdPanitia(null); // Clear idPanitia for Siswa
        }

        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());

        userRepository.save(user);
    }
}
