package com.work.community.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.work.community.entity.Profile;
import com.work.community.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProfileService {
	
	private final ProfileRepository profileRepository;

	/* public Profile findByPno(Integer pno) {
        return profileRepository.findByPno(pno);
    }*/
	
	/* public Profile findById(Integer pno) {
		Optional<Profile> findProfile = profileRepository.findById(pno);
		if (findProfile.isPresent()) { // 회원 정보가 있으면
			// entity -> dto 변환
			return findProfile.get(); // 정보를 가져와서 반환
		}else {
			throw new BootBoardException("회원 프로필을 찾을 수 없습니다.");
		}
	} */

	
}
