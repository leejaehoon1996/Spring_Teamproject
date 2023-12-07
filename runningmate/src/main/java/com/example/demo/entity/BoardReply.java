package com.example.demo.entity;

import java.security.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BoardReply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@Column(nullable = false, length = 100)
	private String content;
	
	private int boardidx;
	
	@GeneratedValue
	private String username;
	
	@ColumnDefault("1")
	private int invisable;
	
	@CreationTimestamp
	private LocalDateTime regdate;
	
	@Builder
	public BoardReply(Long idx, int invisable, String content, String username, int boardidx, String regdate) {
		this.idx = idx;
		this.invisable=1;
		this.content=content;
		this.username = username;
		this.boardidx = boardidx;
		this.regdate=LocalDateTime.now();
	}
}
