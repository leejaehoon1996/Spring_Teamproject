package com.example.demo.DTO;

import com.example.demo.entity.FriendList;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@ToString
@Data
public class FriendListDTO {
	private Long idx;
	private String friendname;
	private String friendemail;
	private String addr;
	private String myemail;
	private String myname;
	private Long requestidx;
	private int count;
	
	public FriendList toEntity() {
		FriendList build = FriendList.builder()
				.idx(idx)
				.friendname(friendname).friendemail(friendemail)
				.addr(addr)
				.myemail(myemail).myname(myname)
				.build();
	
		return build;
	}
	@Builder
	public FriendListDTO(Long idx, Long requestidx,  String friendname, String friendemail, String myemail, String myname, String addr, int count) {
		this.idx = idx;
		this.requestidx = requestidx;
		this.friendname = friendname;
		this.friendemail = friendemail;
		this.myemail = myemail;
		this.myname = myname;
		this.addr = addr;
		this.count = count;
	}
	
}
