package com.example.demo.DTO;


import com.example.demo.entity.Run;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RunDto {
	
	private Long idx;
	private String date;
	private String location;
	private String time;
	private double runningkm;
	private String username;
	private double totalkm;
	

	public Run toEntity() {
		Run build = Run.builder()
				.idx(idx)
				.date(date)
				.location(location)
				.time(time)
				.runningkm(runningkm)
				.username(username)
				.build();
		return build;
	}
	
	@Builder
	public RunDto(Long idx, String date, String location, String time, double runningkm, String username, double totalkm) {
		this.idx = idx;
		this.date = date;
		this.location = location;
		this.time = time;
		this.runningkm = runningkm;
		this.username = username;
		this.totalkm = totalkm;
	}
}