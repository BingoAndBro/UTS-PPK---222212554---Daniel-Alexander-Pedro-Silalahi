package com.polstat.ServicePengumpulan.Repository;

import com.polstat.ServicePengumpulan.Entity.PKKMBInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PKKMBInfoRepository extends JpaRepository<PKKMBInfo, Long> {
}
