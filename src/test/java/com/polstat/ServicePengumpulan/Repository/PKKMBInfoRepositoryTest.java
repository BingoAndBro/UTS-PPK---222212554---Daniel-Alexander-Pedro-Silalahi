//package com.polstat.ServicePengumpulan.Repository;
//
//import com.polstat.ServicePengumpulan.Entity.PKKMBInfo;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@ActiveProfiles("test")
//public class PKKMBInfoRepositoryTest {
//
//    @Autowired
//    private PKKMBInfoRepository pkkmbInfoRepository;
//
//    @BeforeEach
//    public void setUp() {
//        pkkmbInfoRepository.deleteAll();
//    }
//
//    @Test
//    public void testFindByTitle() {
//        PKKMBInfo info = PKKMBInfo.builder()
//                .title("PKKMB 2024")
//                .description("Detail PKKMB")
//                .eventDate(LocalDateTime.now().plusDays(10))
//                .location("Auditorium Kampus")
//                .build();
//        pkkmbInfoRepository.save(info);
//
//        Optional<PKKMBInfo> foundInfo = pkkmbInfoRepository.findByTitle("PKKMB 2024");
//        assertThat(foundInfo).isPresent();
//        assertThat(foundInfo.get().getLocation()).isEqualTo("Auditorium Kampus");
//    }
//}
