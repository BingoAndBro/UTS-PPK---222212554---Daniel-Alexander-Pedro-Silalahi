package com.polstat.ServicePengumpulan.Repository;

import com.polstat.ServicePengumpulan.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNim(String nim);
    
    Optional<User> findByIdPanitia(String idPanitia);
    
    // Unified method for identifier (either nim or id_panitia)
    default Optional<User> findByIdentifier(String identifier) {
        Optional<User> user = findByNim(identifier);
        return user.isPresent() ? user : findByIdPanitia(identifier);
    }
}
