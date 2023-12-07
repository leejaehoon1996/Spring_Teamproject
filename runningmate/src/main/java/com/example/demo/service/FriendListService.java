package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.FriendList1DTO;
import com.example.demo.DTO.FriendListDTO;
import com.example.demo.DTO.FriendRequestDTO;
import com.example.demo.entity.FriendList;

import com.example.demo.repository.FriendListRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class FriendListService {
	
	private final FriendListRepository friendListRepository;
	
	public FriendListService(FriendListRepository friendListRepository) {
		this.friendListRepository = friendListRepository;
	}
	
	@Transactional
	public Long addFriend(FriendListDTO flDTO) {
		
		return friendListRepository.save(flDTO.toEntity()).getIdx();
	}
	
	@Transactional
	public Long addFriend1(FriendList1DTO fl1dto) {
		
		return friendListRepository.save(fl1dto.toEntity()).getIdx();
	}
	
	private static final int BLOCK_PAGE_NUM_COUNT = 5; //블럭에 존재하는 페이지 번호 수
	private static final int PAGE_POST_COUNT = 5; //한 페이지에 존재하는 게시글 수
	
	@Transactional
	public List<FriendListDTO> getFriendlist(String id,Integer pageNum) {
		Page<FriendList> page = friendListRepository.findByMynameOrderByFriendnameAsc(PageRequest.of(pageNum -1, PAGE_POST_COUNT), id);
		
		List<FriendList> friends = page.getContent(); 
		List<FriendListDTO> friendDtoList = new ArrayList<>();
		int count = 1;
		for(FriendList friendlist : friends) {
			
			FriendListDTO friendDto = FriendListDTO.builder()
					.idx(friendlist.getIdx())
					.friendname(friendlist.getFriendname())
					.addr(friendlist.getAddr())
					.friendemail(friendlist.getFriendemail())
					.count(count)
					.build();

			friendDtoList.add(friendDto);
			
			count++;
		}
		return friendDtoList;
	}
	
	@Transactional
    public Long getBoardCount(String myname) {
   // 	String useremail = "1@naver.com";//
        return friendListRepository.countByMyname(myname);
    }

    public Integer[] getPageList(String myname, Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount(myname));
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
	
	@Transactional 
	public void deleteFriend(Long idx) {
		Optional<FriendList> flWrapper = friendListRepository.findById(idx);
		FriendList friendList = flWrapper.get();
		
		String friendEmail = friendList.getFriendemail();
		String myEmail = friendList.getMyemail();
		
		friendListRepository.deleteByFriendemailAndMyemail(myEmail, friendEmail);
			
		friendListRepository.deleteById(idx); 
	 }


	public FriendListDTO getFriend(String id, String myid) {
		Optional<FriendList> listWrapper = friendListRepository.findByFriendemailAndMyemail(id, myid);

	    if (listWrapper.isPresent()) {
	        FriendList friendList = listWrapper.get();
	        FriendListDTO friendListDto = FriendListDTO.builder()
	                .friendemail(friendList.getFriendemail())
	                .build();
	        return friendListDto;
	    } else {
	        FriendListDTO friendListDto = FriendListDTO.builder()
	                .friendemail(null)
	                .build();
	        return friendListDto;
	    }
		
	}
	
}
