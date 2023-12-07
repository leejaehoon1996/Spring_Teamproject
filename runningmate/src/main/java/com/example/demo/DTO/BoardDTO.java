package com.example.demo.DTO;

import java.time.LocalDateTime;

import com.example.demo.entity.Board;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	private Long idx;
	private int hit;
	private int invisable;
	private String content;
	private String title;
	private String username;
	private String useremail;
	private String regdate;
	private int count;
	
	public Board toEntity() {
		Board build = Board.builder()
				.idx(idx)
				.hit(hit)
				.invisable(1)
				.title(title)
				.content(content)
				.username(username)
				.useremail(useremail)
				.regdate(regdate)
				.build();
		return build;
	}
}
