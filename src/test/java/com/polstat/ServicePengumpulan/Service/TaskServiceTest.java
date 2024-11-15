//package com.polstat.ServicePengumpulan.Service;
//
//import com.polstat.ServicePengumpulan.DTO.TaskDTO;
//import com.polstat.ServicePengumpulan.Entity.Task;
//import com.polstat.ServicePengumpulan.Entity.TaskStatus;
//import com.polstat.ServicePengumpulan.Mapper.TaskMapper;
//import com.polstat.ServicePengumpulan.Repository.TaskRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//class TaskServiceTest {
//
//    @Mock
//    private TaskRepository taskRepository;
//
//    @Mock
//    private TaskMapper taskMapper;
//
//    @InjectMocks
//    private TaskService taskService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetAllTasks() {
//        // Data pengujian
//        Task task = Task.builder()
//                .id(1L)
//                .title("Tugas 1")
//                .description("Deskripsi Tugas 1")
//                .dueDate(LocalDateTime.now().plusDays(2))
//                .status(TaskStatus.BELUM_KUMPUL) // Menggunakan enum TaskStatus
//                .filePath("path/to/file")
//                .build();
//
//        TaskDTO taskDTO = TaskDTO.builder()
//                .id(1L)
//                .title("Tugas 1")
//                .description("Deskripsi Tugas 1")
//                .dueDate(task.getDueDate())
//                .status(TaskStatus.BELUM_KUMPUL) // Menggunakan enum TaskStatus di DTO
//                .filePath("path/to/file")
//                .build();
//
//        // Stubbing behavior
//        when(taskRepository.findAll()).thenReturn(List.of(task));
//        when(taskMapper.toTaskDTO(task)).thenReturn(taskDTO);
//
//        // Panggil metode yang diuji
//        List<TaskDTO> result = taskService.getAllTasks();
//
//        // Verifikasi hasil
//        assertThat(result).isNotEmpty();
//        assertThat(result.get(0).getStatus()).isEqualTo(TaskStatus.BELUM_KUMPUL);
//        assertThat(result.get(0).getTitle()).isEqualTo("Tugas 1");
//    }
//
//    @Test
//    void testGetTaskById() {
//        // Data pengujian
//        Long taskId = 1L;
//        Task task = Task.builder()
//                .id(taskId)
//                .title("Tugas 1")
//                .description("Deskripsi Tugas 1")
//                .dueDate(LocalDateTime.now().plusDays(2))
//                .status(TaskStatus.KUMPUL_TEPAT_WAKTU)
//                .filePath("path/to/file")
//                .build();
//
//        TaskDTO taskDTO = TaskDTO.builder()
//                .id(taskId)
//                .title("Tugas 1")
//                .description("Deskripsi Tugas 1")
//                .dueDate(task.getDueDate())
//                .status(TaskStatus.KUMPUL_TEPAT_WAKTU)
//                .filePath("path/to/file")
//                .build();
//
//        // Stubbing behavior
//        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
//        when(taskMapper.toTaskDTO(task)).thenReturn(taskDTO);
//
//        // Panggil metode yang diuji
//        Optional<TaskDTO> result = taskService.getTaskById(taskId);
//
//        // Verifikasi hasil
//        assertThat(result).isPresent();
//        assertThat(result.get().getStatus()).isEqualTo(TaskStatus.KUMPUL_TEPAT_WAKTU);
//        assertThat(result.get().getTitle()).isEqualTo("Tugas 1");
//    }
//}
