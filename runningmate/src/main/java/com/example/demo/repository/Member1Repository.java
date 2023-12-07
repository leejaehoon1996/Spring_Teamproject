package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Member;

public interface Member1Repository extends JpaRepository<Member, Long>{

	Member findByUsername(String username);

}
