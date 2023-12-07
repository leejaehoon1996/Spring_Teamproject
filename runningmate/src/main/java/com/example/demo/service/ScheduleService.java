package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.example.demo.repository.ScheduleRepository;
import com.example.demo.DTO.ScheduleDto;
import com.example.demo.entity.Run;
import com.example.demo.entity.Schedule;

import jakarta.transaction.Transactional;


@Service
public class ScheduleService {

	private ScheduleRepository scheduleRepository;
	
	public ScheduleService(ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}
	
	@Transactional
	public Long savewrite(ScheduleDto scheduleDto) {
		return scheduleRepository.save(scheduleDto.toEntity()).getIdx();
	}
	
	@Transactional
	public ScheduleDto getWrite(Long idx) {
		Optional<Schedule> scheduleWrapper = scheduleRepository.findById(idx);
		Schedule schedule = scheduleWrapper.get();
		
			ScheduleDto scheduleDto = ScheduleDto.builder()
					.idx(schedule.getIdx())
					.content(schedule.getContent())
					.date(schedule.getDate())
					.start_time(schedule.getStart_time())
					.finish_time(schedule.getFinish_time())
					.username(schedule.getUsername())
					.build();
			
			return scheduleDto;
		}
		
		@Transactional 
		public void deleteWrite(Long idx) {
		scheduleRepository.deleteById(idx); 
		 }
		 
	
	private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 5; // 한 페이지에 존재하는 게시글 수
	
    @Transactional
    public List<ScheduleDto> getRunlist(Integer pageNum, String id) {
    	//String useremail = "1@naver.com";
    	//PageRequest pageRequest = PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "idx"));//
      Page<Schedule> page = scheduleRepository.findByUsername(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "idx")),id);
      //  Page<Schedule> page = scheduleRepository.findByUseremail(useremail, pageRequest); //이거는 변수에 선언해서 조회된 값만 페이징처리//
    	
    	
        List<Schedule> schedules = page.getContent();
        List<ScheduleDto> scheduleDtoList = new ArrayList<>();

        for(Schedule schedule : schedules) {
        	scheduleDtoList.add(this.convertEntityToDto(schedule));
		}
		return scheduleDtoList;
    }

   // @Transactional
   // public Long countByUseremail() {
   //	String useremail = "1@naver.com";//
   //    return scheduleRepository.countByUseremail(useremail);
   // }

    @Transactional
    public Long getBoardCount(String id) {
//    	String username = "1";//
        return scheduleRepository.countByUsername(id); //근데 이거는 변수에 선언해서 조회된 값만 페이징처리된게 했는데...일단 ㅇㅋ 괄호안에 username 나중에 추가해야함
    }
    public Integer[] getPageList(Integer curPageNum, String id) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount(id));
        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int) (Math.ceil((postsTotalCount / PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 현재 페이지가 마지막 페이지 번호를 초과하지 않도록 보정
        if (curPageNum > totalLastPageNum) {
            curPageNum = totalLastPageNum;
        }

        // 페이지 시작 번호 조정
        int startPage = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        int index = 0;
        for (int val = startPage; val <= blockLastPageNum; val++) {
            if (index >= BLOCK_PAGE_NUM_COUNT) {
                break;
            }
            pageList[index++] = val;
        }

        return pageList;
    }
    
    private ScheduleDto convertEntityToDto(Schedule schedule) {
        return ScheduleDto.builder()
                .idx(schedule.getIdx())
				.content(schedule.getContent())
				.date(schedule.getDate())
				.start_time(schedule.getStart_time())
				.finish_time(schedule.getFinish_time())
				.username(schedule.getUsername())
				.build();
    }
    
    
}


