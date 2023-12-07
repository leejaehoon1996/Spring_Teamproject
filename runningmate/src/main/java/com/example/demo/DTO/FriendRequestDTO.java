package com.example.demo.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.entity.FriendRequest;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FriendRequestDTO {
	private Long idx;
	private String requestname;
	private String requestemail;
	private String useremail;
	private String username;
	private String location;
	private LocalDate reg_date;
	private LocalDate request_date;
	private int count;
	
	public FriendRequest toEntity() {
		FriendRequest build = FriendRequest.builder()
				.idx(idx)
				.request_email(requestemail).request_name(requestname)
				.user_email(useremail).user_name(username).location(location)
				.build();
		return build;
	}
	
	@Builder
	public FriendRequestDTO(Long idx, String requestname, String requestemail,String username, String useremail, String location, LocalDate request_date, int count) {
		this.idx = idx;
		this.requestemail = requestemail;
		this.requestname = requestname;
		this.location = location;
		this.username = username;
		this.useremail = useremail;
		this.reg_date = LocalDate.now();
		
		this.request_date = request_date;
		this.count = count;
	}
}
