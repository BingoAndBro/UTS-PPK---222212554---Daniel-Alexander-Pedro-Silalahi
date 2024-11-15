//package com.polstat.ServicePengumpulan.Service;
//
//import com.polstat.ServicePengumpulan.DTO.PKKMBInfoDTO;
//import com.polstat.ServicePengumpulan.Entity.PKKMBInfo;
//import com.polstat.ServicePengumpulan.Mapper.PKKMBInfoMapper;
//import com.polstat.ServicePengumpulan.Repository.PKKMBInfoRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//public class PKKMBInfoServiceTest {
//
//    @Mock
//    private PKKMBInfoRepository pkkmbInfoRepository;
//
//    @Mock
//    private PKKMBInfoMapper pkkmbInfoMapper;
//
//    @InjectMocks
//    private PKKMBInfoService pkkmbInfoService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetPKKMBInfoById() {
//        // Buat objek PKKMBInfo untuk pengujian
//        PKKMBInfo info = PKKMBInfo.builder()
//                .id(1L)
//                .title("PKKMB Event")
//                .description("Event Description")
//                .eventDate(LocalDateTime.now().plusDays(2))
//                .location("Campus")
//                .build();
//
//        // Buat objek PKKMBInfoDTO
//        PKKMBInfoDTO infoDTO = PKKMBInfoDTO.builder()
//                .id(1L)
//                .title("PKKMB Event")
//                .description("Event Description")
//                .eventDate(LocalDateTime.now().plusDays(2))
//                .location("Campus")
//                .build();
//
//        // Stub repositori dan mapper
//        when(pkkmbInfoRepository.findById(1L)).thenReturn(Optional.of(info));
//        when(pkkmbInfoMapper.toPKKMBInfoDTO(info)).thenReturn(infoDTO);
//
//        // Panggil metode yang diuji
//        Optional<PKKMBInfoDTO> foundInfoDTO = pkkmbInfoService.getPKKMBInfoById(1L);
//
//        // Verifikasi hasil
//        assertThat(foundInfoDTO).isPresent();
//        assertThat(foundInfoDTO.get().getTitle()).isEqualTo("PKKMB Event");
//        assertThat(foundInfoDTO.get().getDescription()).isEqualTo("Event Description");
//    }
//}
