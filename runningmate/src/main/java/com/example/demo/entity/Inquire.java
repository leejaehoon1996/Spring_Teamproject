package com.example.demo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Inquire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@Column(nullable=false, length=100)
	private String title;
	
	@Column(nullable=false, length=3000)
	private String content;
	
	@GeneratedValue
	private String useremail;
	
	@GeneratedValue
	private String username;
	
	@ColumnDefault("1")
	private int finish;
	
	private LocalDateTime reg_date;
	
	@Builder
	public Inquire(Long idx, String title, String content, String useremail, int finish, String username) {
		this.idx = idx;
		this.content = content;
		this.title = title;
		this.useremail = useremail;
		this.username = username;
		this.finish = finish;
		this.reg_date = LocalDateTime.now();
		
	}
}
