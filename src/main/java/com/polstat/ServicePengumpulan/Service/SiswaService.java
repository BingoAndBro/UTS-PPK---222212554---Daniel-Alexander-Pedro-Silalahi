package com.polstat.ServicePengumpulan.Service;

import com.polstat.ServicePengumpulan.DTO.PKKMBInfoDTO;
import com.polstat.ServicePengumpulan.DTO.TaskDTO;
import com.polstat.ServicePengumpulan.DTO.ChangePasswordRequest;
import com.polstat.ServicePengumpulan.Entity.Task;
import com.polstat.ServicePengumpulan.Entity.TaskStatus;
import com.polstat.ServicePengumpulan.Entity.User;
import com.polstat.ServicePengumpulan.Mapper.PKKMBInfoMapper;
import com.polstat.ServicePengumpulan.Mapper.TaskMapper;
import com.polstat.ServicePengumpulan.Repository.PKKMBInfoRepository;
import com.polstat.ServicePengumpulan.Repository.TaskRepository;
import com.polstat.ServicePengumpulan.Repository.UserRepository;
import com.polstat.ServicePengumpulan.Security.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SiswaService {

    @Autowired
    private PKKMBInfoRepository pkkmbInfoRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @Autowired
    private PKKMBInfoMapper pkkmbInfoMapper;

    @Autowired
    private TaskMapper taskMapper;
    
    @Autowired
    private UserRepository userRepository;
    
       @Autowired
    private PasswordEncoder passwordEncoder;
    
   private final String uploadDir = System.getProperty("user.dir") + "/uploads/";


    /**
     * Retrieves all PKKMB information.
     */
    public List<PKKMBInfoDTO> getPKKMBInfo() {
        return pkkmbInfoRepository.findAll().stream()
                .map(pkkmbInfoMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves tasks specifically assigned to a student based on their nim.
     */
    public List<TaskDTO> getTasksByNim(String nim) {
        List<Task> tasks = taskRepository.findByAssignedTo_Nim(nim);
        return tasks.stream()
                    .map(taskMapper::toDTO)
                    .collect(Collectors.toList());
    }

   
   public TaskDTO submitTask(Long taskId, MultipartFile file) {
    Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new RuntimeException("Task not found"));

    // Menggunakan path absolut untuk direktori `uploads`
    String uploadDir = System.getProperty("user.dir") + "/uploads/";
    File dir = new File(uploadDir);

    // Membuat direktori jika belum ada
    if (!dir.exists() && !dir.mkdirs()) {
        throw new RuntimeException("Failed to create upload directory");
    }

    // Menentukan path file lengkap dengan direktori
    String filePath = uploadDir + "task_" + taskId + "_" + file.getOriginalFilename();
    File destinationFile = new File(filePath);

    try {
        // Menyimpan file ke lokasi yang ditentukan
        file.transferTo(destinationFile);

        // Menyimpan path file dan menentukan status tugas berdasarkan due date
        task.setFilePath(filePath);
        Date currentDate = new Date();
        task.setSubmissionDate(currentDate);
        task.setStatus(currentDate.before(task.getDueDate()) ? TaskStatus.TEPAT_WAKTU : TaskStatus.TERLAMBAT);

        // Menyimpan task yang telah diperbarui ke repository
        taskRepository.save(task);
    } catch (IOException e) {
        throw new RuntimeException("Failed to save file", e);
    }

    return taskMapper.toDTO(task);
}
   
    public void changePassword(String nim, ChangePasswordRequest changePasswordRequest) {
        User user = userRepository.findByNim(nim)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Verifikasi password lama
        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Password lama salah.");
        }

        // Encode password baru dan simpan
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
    }

}
