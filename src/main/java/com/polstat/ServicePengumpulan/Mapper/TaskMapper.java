package com.polstat.ServicePengumpulan.Mapper;

import com.polstat.ServicePengumpulan.DTO.TaskDTO;
import com.polstat.ServicePengumpulan.Entity.Task;
import com.polstat.ServicePengumpulan.Entity.TaskStatus;
import com.polstat.ServicePengumpulan.Entity.User;
import com.polstat.ServicePengumpulan.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    @Autowired
    private UserRepository userRepository;

    public TaskDTO toDTO(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .dueDate(task.getDueDate())
                .assignedToUserId(task.getAssignedTo() != null ? task.getAssignedTo().getId() : null)
                .filePath(task.getFilePath())         // Include file path in DTO
                .submissionDate(task.getSubmissionDate())  // Include submission date in DTO
                .build();
    }

    public Task toEntity(TaskDTO taskDTO) {
        Task task = Task.builder()
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .status(taskDTO.getStatus() != null ? taskDTO.getStatus() : TaskStatus.BELUM_KUMPUL)
                .dueDate(taskDTO.getDueDate())
                .build();

        if (taskDTO.getAssignedToUserId() != null) {
            User assignedTo = userRepository.findById(taskDTO.getAssignedToUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            task.setAssignedTo(assignedTo);
        }

        // Note: filePath and submissionDate are set when the task is submitted
        task.setFilePath(taskDTO.getFilePath());
        task.setSubmissionDate(taskDTO.getSubmissionDate());

        return task;
    }
}
