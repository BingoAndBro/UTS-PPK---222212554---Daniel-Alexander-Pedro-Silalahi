//package com.polstat.ServicePengumpulan.Service;
//
//import com.polstat.ServicePengumpulan.DTO.UserRegistrationDTO;
//import com.polstat.ServicePengumpulan.Entity.User;
//import com.polstat.ServicePengumpulan.Entity.UserRole;
//import com.polstat.ServicePengumpulan.Repository.UserRepository;
//import com.polstat.ServicePengumpulan.Mapper.UserMapper;
//import com.polstat.ServicePengumpulan.Mapper.UserRegistrationMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@ActiveProfiles("test")
//public class AdminServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private UserMapper userMapper;
//
//    @Mock
//    private UserRegistrationMapper userRegistrationMapper;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private AdminService adminService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testCreateStudentAccount() {
//        User adminUser = User.builder().role(UserRole.ADMIN).build();
//        UserRegistrationDTO registrationDTO = new UserRegistrationDTO("12345", "Test User", "password", "password");
//        User userEntity = User.builder().nim("12345").fullname("Test User").role(UserRole.SISWA).build();
//
//        when(userRegistrationMapper.toUser(registrationDTO)).thenReturn(userEntity);
//        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
//        when(userRepository.save(any(User.class))).thenReturn(userEntity);
//        when(userMapper.toUserDTO(any(User.class))).thenReturn(null);
//
//        adminService.createStudentAccount(registrationDTO, adminUser);
//
//        verify(userRepository, times(1)).save(userEntity);
//    }
//}
