package com.polstat.ServicePengumpulan.Service;

import com.polstat.ServicePengumpulan.DTO.*;
import com.polstat.ServicePengumpulan.Entity.*;
import com.polstat.ServicePengumpulan.Exception.ResourceNotFoundException;
import com.polstat.ServicePengumpulan.Exception.UnauthorizedActionException;
import com.polstat.ServicePengumpulan.Mapper.*;
import com.polstat.ServicePengumpulan.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PKKMBInfoRepository pkkmbInfoRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private PKKMBInfoMapper pkkmbInfoMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDTO(userRepository.save(user));
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
        user.setNim(userDTO.getNim());
        user.setRole(userDTO.getRole());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Update password jika diperlukan
        return userMapper.toDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
        if (user.getRole() == UserRole.SISWA) {
            userRepository.delete(user);
        } else {
            throw new UnauthorizedActionException("Admins cannot delete another admin.");
        }
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        taskRepository.save(task);
        return taskMapper.toDTO(task);
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + id + " not found"));
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        return taskMapper.toDTO(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task with ID " + id + " not found");
        }
        taskRepository.deleteById(id);
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PKKMBInfoDTO createPKKMBInfo(PKKMBInfoDTO pkkmbInfoDTO) {
        PKKMBInfo pkkmbInfo = pkkmbInfoMapper.toEntity(pkkmbInfoDTO);
        return pkkmbInfoMapper.toDTO(pkkmbInfoRepository.save(pkkmbInfo));
    }

    public PKKMBInfoDTO updatePKKMBInfo(Long id, PKKMBInfoDTO pkkmbInfoDTO) {
        PKKMBInfo pkkmbInfo = pkkmbInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PKKMBInfo with ID " + id + " not found"));
        pkkmbInfo.setTitle(pkkmbInfoDTO.getTitle());
        pkkmbInfo.setContent(pkkmbInfoDTO.getContent());
        return pkkmbInfoMapper.toDTO(pkkmbInfoRepository.save(pkkmbInfo));
    }

    public void deletePKKMBInfo(Long id) {
        if (!pkkmbInfoRepository.existsById(id)) {
            throw new ResourceNotFoundException("PKKMBInfo with ID " + id + " not found");
        }
        pkkmbInfoRepository.deleteById(id);
    }

    public List<PKKMBInfoDTO> getAllPKKMBInfo() {
        return pkkmbInfoRepository.findAll().stream()
                .map(pkkmbInfoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
