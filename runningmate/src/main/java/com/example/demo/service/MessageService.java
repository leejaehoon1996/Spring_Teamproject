package com.example.demo.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.MessageDTO;
import com.example.demo.DTO.MessageDTO1;
import com.example.demo.entity.Message;
import com.example.demo.repository.MessageRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageService {
	
	private final MessageRepository messageRepository;
	
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	@Transactional
	public Long saveMessage(MessageDTO msgDTO) {
		
		return messageRepository.save(msgDTO.toEntity()).getIdx();
	}
	
	private static final int BLOCK_PAGE_NUM_COUNT = 5;
	private static final int PAGE_POST_COUNT = 5;

	@Transactional
	public List<MessageDTO> getReceiveList(String id, Integer pageNum) {
		
		Page<Message> page = messageRepository.findByReceivenameAndReadinvisiableOrderByIdxDesc(PageRequest.of(pageNum -1, PAGE_POST_COUNT), id, 1);
		
		List<Message> messages = page.getContent();
		List<MessageDTO> messageDto = new ArrayList<>();
		
		for(Message message : messages) {
			MessageDTO messageDTO = MessageDTO.builder()
					.idx(message.getIdx())
					.receiveemail(message.getReceiveemail())
					.receivename(message.getReceivename())
					.sendemail(message.getSendemail())
					.sendname(message.getSendname())
					.content(message.getContent())
					.regdate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(message.getRegdate()))
					.build();
			
			messageDto.add(messageDTO);
			
		}
		
		return messageDto;
	}
	
	@Transactional
	public List<MessageDTO> getSendList(String id, Integer pageNum) {
		
		Page<Message> page = messageRepository.findBySendnameAndSendinvisiableOrderByIdxDesc(PageRequest.of(pageNum -1, PAGE_POST_COUNT), id, 1);
		
		List<Message> messages = page.getContent();
		List<MessageDTO> messageDto = new ArrayList<>();
		
		for(Message message : messages) {
			MessageDTO messageDTO = MessageDTO.builder()
					.idx(message.getIdx())
					.receiveemail(message.getReceiveemail())
					.receivename(message.getReceivename())
					.sendemail(message.getSendemail())
					.sendname(message.getSendname())
					.content(message.getContent())
					.regdate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(message.getRegdate()))
					.build();
			
			messageDto.add(messageDTO);
			
		}
		
		return messageDto;
	}
	
	@Transactional
    public Long getBoardCount() {
   // 	String useremail = "1@naver.com";//
        return messageRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());
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
	public Long deleteMessage(Long idx) {
		Optional<Message> msgWrapper = messageRepository.findById(idx);
		
		Message message = msgWrapper.get();
		
		MessageDTO1 messageDTO = MessageDTO1.builder()
				.idx(message.getIdx())
				.content(message.getContent())
				.receiveemail(message.getReceiveemail())
				.receivename(message.getReceivename())
				.sendemail(message.getSendemail())
				.sendname(message.getSendname())
				.regdate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(message.getRegdate()))
				.readinvisiable(0)
				.sendinvisiable(message.getSendinvisiable())
				.build();
		
		return messageRepository.save(messageDTO.toEntity()).getIdx();
	}
	
	@Transactional
	public Long deleteSendMessage(Long idx) {
		Optional<Message> msgWrapper = messageRepository.findById(idx);
		
		Message message = msgWrapper.get();
		
		MessageDTO1 messageDTO = MessageDTO1.builder()
				.idx(message.getIdx())
				.content(message.getContent())
				.receiveemail(message.getReceiveemail())
				.receivename(message.getReceivename())
				.sendemail(message.getSendemail())
				.sendname(message.getSendname())
				.regdate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(message.getRegdate()))
				.readinvisiable(message.getReadinvisiable())
				.sendinvisiable(0)
				.build();
		
		return messageRepository.save(messageDTO.toEntity()).getIdx();
	}
}
