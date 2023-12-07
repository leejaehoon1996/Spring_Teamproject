package com.example.demo.DTO;

import com.example.demo.entity.Run;
import com.example.demo.entity.Schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ScheduleDto {
	
	private Long idx;
	private String content;
	private String date;
	private String start_time;
	private String finish_time;
	private String username;
	

	public Schedule toEntity() {
		Schedule build = Schedule.builder()
				.idx(idx)
				.content(content)
				.date(date)
				.start_time(start_time)
				.finish_time(finish_time)
				.username(username)
				.build();
		return build;
	}
	
	@Builder
	public ScheduleDto(Long idx, String content , String date, String start_time, String finish_time, String running_km, String username) {
		this.idx = idx;
		this.content = content;
		this.date = date;
		this.start_time = start_time;
		this.finish_time = finish_time;
		this.username = username;
	}
}