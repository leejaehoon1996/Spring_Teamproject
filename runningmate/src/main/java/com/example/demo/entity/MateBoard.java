package com.example.demo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MateBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob
	private String content;
	
	@ColumnDefault("0")
	private int hit;
	
	@GeneratedValue
	private String username;
	
	@GeneratedValue
	private String useremail;
	
	@ColumnDefault("1")
	private int invisable;
	
	private String local;
	
	private LocalDateTime reg_date;
	
	@Builder
	public MateBoard(Long idx, int hit, int invisable, String content, String title, String username, String useremail, String regdate, String local) {
		this.idx = idx;
		this.hit=hit;
		this.invisable=invisable;
		this.content=content;
		this.title=title;
		this.local=local;
		this.username = username;
		this.useremail = useremail;
		this.reg_date=LocalDateTime.now();
	}
	
}
