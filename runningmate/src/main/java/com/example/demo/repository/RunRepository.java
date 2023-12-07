package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.DTO.RunDto;
import com.example.demo.entity.Run;

public interface RunRepository extends JpaRepository<Run, Long>{
	
//	Page<Run> findByUsername(String username, PageRequest pageRequest);

//	long countByUsername(String username);

    @Query("SELECT COALESCE(SUM(r.runningkm), 0) FROM Run r WHERE r.username = :username")
    double getTotalRunningKm(@Param("username") String username);
	
    @Query(value = "SELECT COUNT(DISTINCT r.date) FROM Run r WHERE r.username = :username", nativeQuery = true)
    long getTotalDaysRun(@Param("username") String username);
    
	Page<Run> findByUsername(PageRequest of, String id);

	List<Run> findByUsername(String id);

	Long countByUsername(String username);
}
