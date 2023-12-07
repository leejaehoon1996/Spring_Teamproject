package com.example.demo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@GeneratedValue
	private String sendname;
	
	@GeneratedValue
	private String sendemail;
	
	@GeneratedValue
	private String receivename;
	
	@GeneratedValue
	private String receiveemail;
	
	@NonNull
	@Column(length = 3000)
	private String content;
	
	private LocalDateTime regdate;
	
	@ColumnDefault("1")
	private int readinvisiable;
	
	@ColumnDefault("1")
	private int sendinvisiable;
	
	@Builder
	public Message(Long idx, String receiveemail, String receivename, String content, String sendname, String sendemail, int readinvisiable, int sendinvisiable) {
		this.idx = idx;
		this.receivename= receivename;
		this.receiveemail = receiveemail;
		this.content = content;
		this.sendemail = sendemail;
		this.sendname = sendname;
		this.readinvisiable = readinvisiable;
		this.sendinvisiable = sendinvisiable;
		this.regdate = LocalDateTime.now();
	}

}
