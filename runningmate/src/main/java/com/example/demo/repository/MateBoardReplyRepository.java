package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MateBoardReply;

public interface MateBoardReplyRepository extends JpaRepository<MateBoardReply, Long>{

	List<MateBoardReply> findByBoardidx(Long idx);

	void deleteByUsername(String username);

}
