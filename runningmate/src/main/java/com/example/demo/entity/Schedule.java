package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

	@Id @GeneratedValue
	private Long idx;
	
	@Column(length = 255, nullable = false)
	private String content;
	
	@Column(length = 100, nullable = false)
	private String date;
	
	@Column(length = 100, nullable = false)
	private String start_time;
	
	@Column(length = 100, nullable = false)
	private String finish_time;
	
    @Column(length = 100, nullable = false)
	private String username;
	
    @Builder
	public Schedule(Long idx, String content, String date, String start_time, String finish_time, String username) {
		this.idx = idx;
		this.content = content;
		this.date = date;
		this.start_time = start_time;
		this.finish_time = finish_time;
		this.username = username;
	}
}
