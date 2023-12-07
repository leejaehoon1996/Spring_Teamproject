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
public class Run {

	@Id @GeneratedValue
	private Long idx;
	
	@Column(length = 100, nullable = false)
	private String date;
	
	@Column(length = 100, nullable = false)
	private String location;
	
	@Column(length = 100, nullable = false)
	private String time;
	
	@Column(nullable = false)
	private double runningkm;
	
    @Column(length = 100, nullable = false)
	private String username;
	
    @Builder
	public Run(Long idx, String date, String location, String time, double runningkm, String username) {
		this.idx = idx;
		this.date = date;
		this.location = location;
		this.time = time;
		this.runningkm = runningkm;
		this.username = username;
	}
}
