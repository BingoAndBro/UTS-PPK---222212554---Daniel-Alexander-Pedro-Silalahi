package com.polstat.ServicePengumpulan.Repository;

import com.polstat.ServicePengumpulan.Entity.Task;
import com.polstat.ServicePengumpulan.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedTo(User user);
     List<Task> findByAssignedTo_Nim(String nim);  // Gunakan underscore untuk mengakses nim di entitas terkait
}
