package com.example.demo.DTO;

import java.time.LocalDateTime;

import com.example.demo.entity.MateBoard;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MateBoardDTO2 {
	private Long idx;
	private int hit;
	private int invisable;
	private String content;
	private String title;
	private String username;
	private String regdate;
	
	public MateBoard toEntity() {
		MateBoard build = MateBoard.builder()
				.idx(idx)
				.hit(hit)
				.invisable(0)
				.title(title)
				.content(content)
				.username(username)
				.regdate(regdate)
				.build();
		return build;
	}
}
