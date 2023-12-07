package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Message;


public interface MessageRepository extends JpaRepository<Message, Long> {

	Page<Message> findByReceivenameAndReadinvisiableOrderByIdxDesc(PageRequest request, String email, int ri);
	Page<Message> findBySendnameAndSendinvisiableOrderByIdxDesc(PageRequest request, String email, int si);
	void deleteByReceiveemail(String useremail);
	void deleteBySendemail(String useremail);

}
