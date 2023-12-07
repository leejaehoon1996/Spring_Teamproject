package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
public class FriendList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@GeneratedValue
	private String friendname;
	
	@GeneratedValue
	private String friendemail;
	
	private String addr;
	
	@GeneratedValue
	private String myemail;
	
	@GeneratedValue
	private String myname;
	
	@Builder
	public FriendList(Long idx, String friendname, String friendemail, String addr, String myemail, String myname) {
		this.idx = idx;
		this.friendname= friendname;
		this.friendemail = friendemail;
		this.addr = addr;
		this.myemail = myemail;
		this.myname = myname;
	}
	
}
