//package com.polstat.ServicePengumpulan.Repository;
//
//import com.polstat.ServicePengumpulan.Entity.User;
//import com.polstat.ServicePengumpulan.Entity.UserRole;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@ActiveProfiles("test")
//public class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
////    @BeforeEach
////    public void setUp() {
////        userRepository.deleteAll();
////    }
//
//    @Test
//    public void testFindByNim() {
//        User user = User.builder()
//                .nim("12345")
//                .fullname("Test User")
//                .password("password")
//                .role(UserRole.SISWA)
//                .build();
//        userRepository.save(user);
//
//        Optional<User> foundUser = userRepository.findByNim("12345");
//        assertThat(foundUser).isPresent();
//        assertThat(foundUser.get().getFullname()).isEqualTo("Test User");
//    }
//}
