package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.ScheduleDto;
import com.example.demo.service.RunService;
import com.example.demo.service.ScheduleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping(value="/")
public class ScheduleController {
	
	private ScheduleService scheduleService;
	
	public ScheduleController(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	
	@GetMapping("schedule")
	public String schedule(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		List<ScheduleDto> scheduleDtoList = scheduleService.getRunlist(pageNum,currentUsername);
		Integer[] pageList = scheduleService.getPageList(pageNum,currentUsername);
		
		model.addAttribute("scheduleList", scheduleDtoList);
		model.addAttribute("pageList", pageList);
		
		return "schedule";
	}
	
	@GetMapping("schedule_write")
	public String schedule_write() {
		return "schedule_write";
	}
	
	@PostMapping("schedule_write")
	public String write(ScheduleDto scheduleDto) {
		scheduleService.savewrite(scheduleDto);
		return "redirect:/schedule";
	}
	
	@GetMapping("write/schedule_modify/{no}")
	public String edit(@PathVariable("no") Long idx, Model model) {
		ScheduleDto scheduleDto = scheduleService.getWrite(idx);
		
		model.addAttribute("scheduleDto", scheduleDto);
		return "schedule_modify";
	}
	
	@PutMapping("/schedule_modify/{no}")	
	public String update(ScheduleDto scheduleDto) {
		scheduleService.savewrite(scheduleDto);
		return "redirect:/schedule";
	}
	
	@GetMapping("schedule_write/delete/{no}")
	public String delete(@PathVariable("no") Long idx) { 
		scheduleService.deleteWrite(idx);
	    return "redirect:/schedule"; 
	}
	 
}
