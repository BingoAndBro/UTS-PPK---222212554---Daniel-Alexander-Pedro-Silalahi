package com.polstat.ServicePengumpulan.Controller;

import com.polstat.ServicePengumpulan.DTO.PKKMBInfoDTO;
import com.polstat.ServicePengumpulan.DTO.TaskDTO;
import com.polstat.ServicePengumpulan.DTO.ChangePasswordRequest;
import com.polstat.ServicePengumpulan.Security.AuthenticatedUserService;
import com.polstat.ServicePengumpulan.Service.SiswaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/siswa")
@Tag(name = "Siswa Controller", description = "Endpoints for mahasiswa to access tasks, PKKMB information, and change their password.")
public class SiswaController {

    @Autowired
    private SiswaService siswaService;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @Operation(summary = "Get PKKMB information", description = "Retrieve a list of all PKKMB information available to students.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of PKKMB information retrieved successfully",
                    content = @Content(schema = @Schema(implementation = PKKMBInfoDTO.class)))
    })
    @GetMapping("/pkkmbinfos")
    public List<PKKMBInfoDTO> getPKKMBInfo() {
        return siswaService.getPKKMBInfo();
    }

    @Operation(summary = "Get tasks by NIM", description = "Retrieve a list of tasks assigned to the student based on their NIM.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of tasks retrieved successfully",
                    content = @Content(schema = @Schema(implementation = TaskDTO.class))),
            @ApiResponse(responseCode = "403", description = "Access Denied: You can only access your own tasks.")
    })
    @GetMapping("/tasks/{nim}")
    public List<TaskDTO> getTasksByNim(@PathVariable String nim) {
        String currentUserNim = authenticatedUserService.getCurrentUserNim();
        if (!nim.equals(currentUserNim)) {
            throw new SecurityException("Access Denied: You can only access your own tasks.");
        }
        return siswaService.getTasksByNim(nim);
    }

    @Operation(summary = "Submit a task", description = "Submit a file for a specific task by task ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task submitted successfully",
                    content = @Content(schema = @Schema(implementation = TaskDTO.class))),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "500", description = "Failed to save file")
    })
    @PutMapping("/task/{id}/submit")
    public TaskDTO submitTask(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return siswaService.submitTask(id, file);
    }

    @Operation(summary = "Change password", description = "Change the password of the currently logged-in student.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password changed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid old password")
    })
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        String nim = authenticatedUserService.getCurrentUserNim(); 
        siswaService.changePassword(nim, changePasswordRequest);
        return ResponseEntity.ok("Password berhasil diubah.");
    }
}
