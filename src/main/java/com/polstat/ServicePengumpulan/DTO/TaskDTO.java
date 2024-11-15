// TaskDTO.java
package com.polstat.ServicePengumpulan.DTO;

import com.polstat.ServicePengumpulan.Entity.TaskStatus;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status; // Status of the task
    private Date dueDate; // Due date of the task
    private Long assignedToUserId; // ID of the user this task is assigned to
     private String filePath;       // Path of the file submitted by the student
    private Date submissionDate;   // Date of submission
}
