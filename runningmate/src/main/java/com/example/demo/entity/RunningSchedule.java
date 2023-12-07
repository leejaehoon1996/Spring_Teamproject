package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RunningSchedule {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idx;
	
	private String date;
	
	private String time;
	
	@Column(nullable=false, length=100)
	private String content;
	
	@GeneratedValue
	private String useremail;
}
