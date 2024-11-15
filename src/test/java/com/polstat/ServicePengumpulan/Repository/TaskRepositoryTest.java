//package com.polstat.ServicePengumpulan.Repository;
//
//import com.polstat.ServicePengumpulan.Entity.Task;
//import com.polstat.ServicePengumpulan.Entity.TaskStatus;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@ActiveProfiles("test")
//public class TaskRepositoryTest {
//
//    @Autowired
//    private TaskRepository taskRepository;
//
//    @BeforeEach
//    public void setUp() {
//        taskRepository.deleteAll();
//    }
//
//    @Test
//    public void testFindByStatus() {
//        Task task = Task.builder()
//                .title("Tugas Pertama")
//                .description("Deskripsi tugas pertama")
//                .dueDate(LocalDateTime.now().plusDays(5))
//                .status(TaskStatus.BELUM_KUMPUL)
//                .build();
//        taskRepository.save(task);
//
//        List<Task> tasks = taskRepository.findByStatus(TaskStatus.BELUM_KUMPUL);
//        assertThat(tasks).hasSize(1);
//        assertThat(tasks.get(0).getTitle()).isEqualTo("Tugas Pertama");
//    }
//}
