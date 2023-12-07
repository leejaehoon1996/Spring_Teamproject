package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import com.example.demo.repository.RunRepository;


import com.example.demo.DTO.RunDto;
import com.example.demo.DTO.ScheduleDto;
import com.example.demo.entity.Run;
import com.example.demo.entity.Schedule;

import jakarta.transaction.Transactional;


@Service
public class RunService {

	private RunRepository runRepository;
	
	public RunService(RunRepository runRepository) {
		this.runRepository = runRepository;
	}
	
	@Transactional
	public Long savewrite(RunDto runDto) {
		return runRepository.save(runDto.toEntity()).getIdx();
	}
	
	@Transactional
	public RunDto getWrite(Long idx) {
		Optional<Run> runWrapper = runRepository.findById(idx);
		Run run = runWrapper.get();
		
			RunDto runDto = RunDto.builder()
					.idx(run.getIdx())
					.date(run.getDate())
					.location(run.getLocation())
					.time(run.getTime())
					.runningkm(run.getRunningkm())
					.username(run.getUsername())
					.build();
			
			return runDto;
		}
		
		@Transactional 
		public void deleteWrite(Long idx) {
		 runRepository.deleteById(idx); 
		 }
		 
	
	private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 5; // 한 페이지에 존재하는 게시글 수
	
    @Transactional
    public List<RunDto> getRunlist(String id, Integer pageNum) {
//    	String username = "1";//
//    	PageRequest pageRequest = PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "idx"));//
        Page<Run> page = runRepository.findByUsername(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "idx")),id);
//        Page<Run> page = runRepository.findByUsername(username, pageRequest); //이거는 변수에 선언해서 조회된 값만 페이징처리//
        
        List<Run> runs = page.getContent();
        List<RunDto> runDtoList = new ArrayList<>();
        for(Run run : runs) {
        	runDtoList.add(this.convertEntityToDto(run));
		}
        	
		return runDtoList;
    }

    @Transactional
    public Long getBoardCount(String id) {
//    	String username = "1";//
        return runRepository.countByUsername(id); //근데 이거는 변수에 선언해서 조회된 값만 페이징처리된게 했는데...일단 ㅇㅋ 괄호안에 username 나중에 추가해야함
    }

    public Integer[] getPageList(Integer curPageNum, String id) {
    	
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

// 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount(id));

// 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

// 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

// 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

// 페이지 번호 할당
        for (int val = curPageNum, i = 0; val <= blockLastPageNum; val++, i++) {
            pageList[i] = val;
        }

        return pageList;
    }
    
    
    private RunDto convertEntityToDto(Run run) {
        return RunDto.builder()
        	    .idx(run.getIdx())
    			.date(run.getDate())
    			.location(run.getLocation())
    			.time(run.getTime())
    			.runningkm(run.getRunningkm())
    			.username(run.getUsername())
    			.build();
    }
    
    public double getTotalRunningKm(String id) {
        return runRepository.getTotalRunningKm(id);
    }
  
    public long getTotalDaysRun(String id) {
        return runRepository.getTotalDaysRun(id);
    }
//
//	public double getTotalkm(String id) {
//		double total = 0;
//		 List<Run> runs = runRepository.findByUsername(id);
//	    
//	        for(Run run : runs) {
//	        	total += run.getRunningkm();
//
//			}
//		
//		
//		return total;
//	}
}

