package com.polstat.ServicePengumpulan.DTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String nim;
    private String password;
    private String idPanitia;
    
        public String getIdentifier() {
        return nim != null ? nim : idPanitia;
    }
}
