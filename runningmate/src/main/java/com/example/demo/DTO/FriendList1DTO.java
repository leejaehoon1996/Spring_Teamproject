package com.example.demo.DTO;

import com.example.demo.entity.FriendList;

import lombok.*;

@NoArgsConstructor
@ToString
@Data
public class FriendList1DTO {
	private Long idx;
	private String friendname;
	private String friendemail;
	private String addr;
	private String myemail;
	private String myname;
	
	public FriendList toEntity() {
		FriendList build = FriendList.builder()
				.idx(idx)
				.friendname(myname).friendemail(myemail)
				.addr(addr)
				.myemail(friendemail).myname(friendname)
				.build();
		
		return build;
	}
	
	@Builder
	public FriendList1DTO(Long idx, Long requestidx,  String friendname, String friendemail, String myemail, String myname, String addr, int count) {
		this.idx = idx;
		this.friendname = myname;
		this.friendemail = myemail;
		this.myemail = friendemail;
		this.myname = friendname;
		this.addr = addr;
	}

}
