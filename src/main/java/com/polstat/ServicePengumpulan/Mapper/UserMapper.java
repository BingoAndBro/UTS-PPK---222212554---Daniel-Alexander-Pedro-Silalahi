package com.polstat.ServicePengumpulan.Mapper;

import com.polstat.ServicePengumpulan.DTO.UserDTO;
import com.polstat.ServicePengumpulan.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .nim(user.getNim())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public User toEntity(UserDTO userDTO) {
        return User.builder()
                .nim(userDTO.getNim())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .build();
    }
}
