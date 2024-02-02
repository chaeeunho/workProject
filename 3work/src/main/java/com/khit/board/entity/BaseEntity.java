package com.khit.board.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
	
	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createdDate;  //등록일
	
	@UpdateTimestamp
	@Column(insertable = false)
	private Timestamp updatedDate;  //수정일
}
