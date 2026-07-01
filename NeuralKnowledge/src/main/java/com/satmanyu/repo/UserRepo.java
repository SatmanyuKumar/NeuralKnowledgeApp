package com.satmanyu.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satmanyu.entity.DocumentChunk;
import com.satmanyu.entity.MyUser;

@Repository
public interface UserRepo extends JpaRepository<MyUser, String> {

	 
		// TODO Auto-generated method stub
	
	

}
