package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.DTO.FriendRequestDTO;
import com.example.demo.entity.FriendRequest;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long>{

	Page<FriendRequest> findByUsernameOrderByRequestnameAsc(PageRequest pageRequest, String user_email);

	Optional<FriendRequest> findByUsernameAndRequestname(String username, String requestname);

}
