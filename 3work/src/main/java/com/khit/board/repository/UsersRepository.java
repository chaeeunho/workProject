package com.khit.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.board.entity.Users;

public interface UsersRepository extends JpaRepository<Users,Integer>{

	//select * from member where member_id = ?;
		Optional<Users> findByUid(String string);

}
