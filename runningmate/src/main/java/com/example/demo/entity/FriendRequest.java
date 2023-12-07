package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class FriendRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@GeneratedValue
	private String requestname;
	
	@GeneratedValue
	private String requestemail;
	
	@GeneratedValue
	private String username;
	
	@GeneratedValue
	private String useremail;
	
	private String location;
	
	private LocalDate reg_date;
	
	@Builder
	public FriendRequest(Long idx, String request_name, String request_email, String user_name, String user_email, String location) {
		this.idx = idx;
		this.requestname = request_name;
		this.requestemail = request_email;
		this.username = user_name;
		this.useremail = user_email;
		this.location = location;
		this.reg_date = LocalDate.now();
	}
}
