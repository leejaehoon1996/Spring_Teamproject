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
public class LocationBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob
	private String content;
	
	@ColumnDefault("0")
	private int hit;
	
	private String file_name;
	
	@GeneratedValue
	private String username;
	
	@GeneratedValue
	private String useremail;
	
	@ColumnDefault("1")
	private int invisable;
	
	private LocalDateTime reg_date;
}
