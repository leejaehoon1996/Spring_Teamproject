package com.example.demo.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//회원가입화면에서 넘어논 가입정보를 담는 객체
@Getter
@Setter
public class MemberDTO {
	private Long idx;
	private String username;
	private String userId;
	private String pw;
	private String addr;
	private String ph;
	
	@Builder
	public MemberDTO(Long idx, String username, String userId, String pw, String addr, String ph) {
		this.idx = idx;
		this.username = username;
		this.userId = userId;
		this.pw = pw;
		this.addr = addr;
		this.ph = ph;
	}
}
