package com.example.demo.DTO;

import com.example.demo.entity.MateBoard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MateBoardDTO {
	private Long idx;
	private int hit;
	private int invisable;
	private String content;
	private String title;
	private String username;
	private String useremail;
	private String regdate;
	private int count;
	private String local;
	
	public MateBoard toEntity() {
		MateBoard build = MateBoard.builder()
				.idx(idx)
				.hit(hit)
				.invisable(1)
				.title(title)
				.content(content)
				.username(username)
				.useremail(useremail)
				.regdate(regdate)
				.local(local)
				.build();
		return build;
	}
}