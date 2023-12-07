package com.example.demo.DTO;

import com.example.demo.entity.Board;
import com.example.demo.entity.BoardReply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardReplyDTO {
	private Long idx;
	private int invisable;
	private String content;
	private String username;
	private int boardidx;
	private String regdate;
	
	public BoardReply toEntity() {
		BoardReply boardreply = BoardReply.builder()
				.idx(idx)
				.invisable(invisable)
				.content(content)
				.username(username)
				.boardidx(boardidx)
				.regdate(regdate)
				.build();
		return boardreply;
	}
	
	
}
