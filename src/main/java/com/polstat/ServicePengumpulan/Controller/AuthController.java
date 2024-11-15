package com.polstat.ServicePengumpulan.Controller;

import com.polstat.ServicePengumpulan.DTO.AuthRequest;
import com.polstat.ServicePengumpulan.DTO.AuthResponse;
import com.polstat.ServicePengumpulan.DTO.UserDTO;
import com.polstat.ServicePengumpulan.Entity.UserRole;
import com.polstat.ServicePengumpulan.Service.AuthService;
import com.polstat.ServicePengumpulan.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth Controller", description = "Endpoints for authentication and user registration")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    private static final Set<String> VALID_ID_PANITIA = Set.of(
            "PANITIA2024_1", "PANITIA2024_2", "PANITIA2024_3", "PANITIA2024_4", "PANITIA2024_5"
    ); // Daftar ID Panitia yang valid untuk role ADMIN

    @Operation(
            summary = "Login",
            description = "Endpoint to login, accepting either 'nim' for SISWA or 'id_panitia' for ADMIN.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Authentication request containing identifier and password",
                    content = @Content(
                            schema = @Schema(implementation = AuthRequest.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful", content = @Content(
                    schema = @Schema(implementation = AuthResponse.class)
            )),
            @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content)
    })
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }

    @Operation(
            summary = "Register a new user",
            description = "Endpoint to register a new user. Validates that ADMIN users have a valid ID Panitia and SISWA users have a NIM."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getRole() == UserRole.ADMIN) {
            // Validasi bahwa ADMIN memiliki ID Panitia yang valid
            if (userDTO.getIdPanitia() == null || !VALID_ID_PANITIA.contains(userDTO.getIdPanitia())) {
                return "Invalid ID Panitia";
            }
        } else if (userDTO.getRole() == UserRole.SISWA) {
            // Validasi bahwa SISWA memiliki NIM yang valid
            if (userDTO.getNim() == null) {
                return "NIM is required for Siswa";
            }
        } else {
            return "Invalid role specified";
        }

        // Jika validasi berhasil, lanjutkan dengan registrasi user
        userService.registerUser(userDTO);
        return "User registered successfully";
    }
}
