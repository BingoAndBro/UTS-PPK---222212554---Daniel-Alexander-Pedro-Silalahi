package com.polstat.ServicePengumpulan.Mapper;

import com.polstat.ServicePengumpulan.DTO.PKKMBInfoDTO;
import com.polstat.ServicePengumpulan.Entity.PKKMBInfo;
import org.springframework.stereotype.Component;

@Component
public class PKKMBInfoMapper {

    public PKKMBInfoDTO toDTO(PKKMBInfo pkkmbInfo) {
        return PKKMBInfoDTO.builder()
                .id(pkkmbInfo.getId())
                .title(pkkmbInfo.getTitle())
                .content(pkkmbInfo.getContent())
                .build();
    }

    public PKKMBInfo toEntity(PKKMBInfoDTO pkkmbInfoDTO) {
        return PKKMBInfo.builder()
                .title(pkkmbInfoDTO.getTitle())
                .content(pkkmbInfoDTO.getContent())
                .build();
    }
}
