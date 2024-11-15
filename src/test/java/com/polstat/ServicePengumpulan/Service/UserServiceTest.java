//package com.polstat.ServicePengumpulan.Service;
//
//import com.polstat.ServicePengumpulan.DTO.UserDTO;
//import com.polstat.ServicePengumpulan.Entity.User;
//import com.polstat.ServicePengumpulan.Entity.UserRole;
//import com.polstat.ServicePengumpulan.Mapper.UserMapper;
//import com.polstat.ServicePengumpulan.Repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private UserMapper userMapper;
//
//    @InjectMocks
//    private UserService userService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testFindUserByNim() {
//        // Data pengujian
//        String testNim = "12345";
//        User user = User.builder()
//                .id(1L)
//                .nim(testNim)
//                .fullname("Test User")
//                .password("password")
//                .role(UserRole.SISWA)
//                .build();
//
//        UserDTO userDTO = UserDTO.builder()
//                .id(1L)
//                .nim(testNim)
//                .fullname("Test User")
//                .role(UserRole.SISWA)
//                .build();
//
//        // Stubbing behavior
//        when(userRepository.findByNim(testNim)).thenReturn(Optional.of(user));
//        when(userMapper.toUserDTO(user)).thenReturn(userDTO);
//
//        // Panggil metode yang diuji
//        Optional<UserDTO> result = userService.findUserByNim(testNim);
//
//        // Verifikasi hasil
//        assertThat(result).isPresent();
//        assertThat(result.get().getNim()).isEqualTo(testNim);
//        assertThat(result.get().getFullname()).isEqualTo("Test User");
//    }
//}
