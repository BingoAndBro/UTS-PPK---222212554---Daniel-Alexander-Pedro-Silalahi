package com.polstat.ServicePengumpulan.Controller;

import com.polstat.ServicePengumpulan.DTO.*;
import com.polstat.ServicePengumpulan.Entity.Task;
import com.polstat.ServicePengumpulan.Repository.TaskRepository;
import com.polstat.ServicePengumpulan.Service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin Controller", description = "Endpoints for managing users, tasks, and PKKMB information")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TaskRepository taskRepository;

    @Operation(summary = "Create a new user", description = "Endpoint to create a new user with role ADMIN or SISWA")
    @PostMapping("/user")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return adminService.createUser(userDTO);
    }

    @Operation(summary = "Update an existing user", description = "Endpoint to update user details by ID")
    @PutMapping("/user/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return adminService.updateUser(id, userDTO);
    }

    @Operation(summary = "Delete a user", description = "Endpoint to delete a user by ID")
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
    }

    @Operation(summary = "Get all users", description = "Retrieve a list of all users in the system")
    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return adminService.getAllUsers();
    }

    @Operation(summary = "Create a new task", description = "Endpoint to create a new task")
    @PostMapping("/task")
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        return adminService.createTask(taskDTO);
    }

    @Operation(summary = "Update a task", description = "Endpoint to update task details by ID")
    @PutMapping("/task/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return adminService.updateTask(id, taskDTO);
    }

    @Operation(summary = "Delete a task", description = "Endpoint to delete a task by ID")
    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable Long id) {
        adminService.deleteTask(id);
    }

    @Operation(summary = "Get all tasks", description = "Retrieve a list of all tasks in the system")
    @GetMapping("/tasks")
    public List<TaskDTO> getAllTasks() {
        return adminService.getAllTasks();
    }

    @Operation(summary = "Create new PKKMB information", description = "Endpoint to create new PKKMB information")
    @PostMapping("/pkkmbinfo")
    public PKKMBInfoDTO createPKKMBInfo(@RequestBody PKKMBInfoDTO pkkmbInfoDTO) {
        return adminService.createPKKMBInfo(pkkmbInfoDTO);
    }

    @Operation(summary = "Update PKKMB information", description = "Endpoint to update PKKMB information by ID")
    @PutMapping("/pkkmbinfo/{id}")
    public PKKMBInfoDTO updatePKKMBInfo(@PathVariable Long id, @RequestBody PKKMBInfoDTO pkkmbInfoDTO) {
        return adminService.updatePKKMBInfo(id, pkkmbInfoDTO);
    }

    @Operation(summary = "Delete PKKMB information", description = "Endpoint to delete PKKMB information by ID")
    @DeleteMapping("/pkkmbinfo/{id}")
    public void deletePKKMBInfo(@PathVariable Long id) {
        adminService.deletePKKMBInfo(id);
    }

    @Operation(summary = "Get all PKKMB information", description = "Retrieve a list of all PKKMB information")
    @GetMapping("/pkkmbinfos")
    public List<PKKMBInfoDTO> getAllPKKMBInfo() {
        return adminService.getAllPKKMBInfo();
    }

    @Operation(summary = "Download task file", description = "Endpoint to download a file submitted for a task by ID")
    @GetMapping("/task/{id}/file")
    public ResponseEntity<FileSystemResource> downloadTaskFile(@PathVariable Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        File file = new File(task.getFilePath());
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        FileSystemResource resource = new FileSystemResource(file);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
