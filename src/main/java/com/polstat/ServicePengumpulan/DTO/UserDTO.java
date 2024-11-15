package com.polstat.ServicePengumpulan.DTO;

import com.polstat.ServicePengumpulan.Entity.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String nim;
     private String idPanitia;
    private String password;  // Menyertakan password agar DTO ini bisa digunakan juga untuk registrasi
    private UserRole role;
}
