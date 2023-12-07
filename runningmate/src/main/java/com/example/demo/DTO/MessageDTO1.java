package com.example.demo.DTO;

import java.security.Timestamp;
import java.time.LocalDateTime;

import com.example.demo.entity.Message;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class MessageDTO1 {
	private Long idx;
	private String content;
	private String receiveemail;
	private String receivename;
	private String sendemail;
	private String sendname;
	private String regdate;
	private int readinvisiable;
	private int sendinvisiable;
	
	public Message toEntity() {
		Message build = Message.builder()
				.idx(idx)
				.content(content)
				.receiveemail(receiveemail)
				.receivename(receivename)
				.sendemail(sendemail)
				.sendname(sendname)
				.readinvisiable(readinvisiable)
				.sendinvisiable(sendinvisiable)
				.build();
		
		return build;
	}
	
	@Builder
	public MessageDTO1(Long idx, String content, String receiveemail, String receivename, String sendemail, String sendname, String regdate, int readinvisiable, int sendinvisiable) {
		this.idx = idx;
		this.content = content;
		this.receiveemail = receiveemail;
		this.receivename = receivename;
		this.sendemail = sendemail;
		this.sendname = sendname;
		this.regdate = regdate;
		this.readinvisiable = readinvisiable;
		this.sendinvisiable = sendinvisiable;
	}
}
