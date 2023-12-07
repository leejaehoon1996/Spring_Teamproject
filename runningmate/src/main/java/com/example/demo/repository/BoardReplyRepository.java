package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BoardReply;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Long>{

	List<BoardReply> findByBoardidx(Long idx);

}
