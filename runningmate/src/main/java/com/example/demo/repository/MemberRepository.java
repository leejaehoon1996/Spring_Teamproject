package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.example.demo.DTO.MemberDTO;
import com.example.demo.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	Member findByUserId(String userId);

	Optional<Member> findByUsername(String currentUsername);
	
	Long save(MemberDTO memberDto);
}
