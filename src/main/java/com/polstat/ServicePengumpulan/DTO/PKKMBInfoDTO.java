package com.polstat.ServicePengumpulan.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PKKMBInfoDTO {
    private Long id;
    private String title;
    private String content;
}
