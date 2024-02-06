package com.work.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.work.community.dto.MusicDTO;
import com.work.community.entity.Music;
import com.work.community.repository.MusicRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MusicService {
	
	private final MusicRepository musicRepository;

	public void save(@Valid MusicDTO musicDTO) {
		// 변환 메서드 필요 -> 최종적으로 music로 들어가야 한다.
		Music music = Music.toSaveEntity(musicDTO);
		musicRepository.save(music);
	}

	public List<MusicDTO> findAll() {
		// DB에서 memberList를 가져와야 함
		List<Music> musicList = musicRepository.findAll(Sort.by(Sort.Direction.DESC, "mno"));
		// 빈 memberDTOList를 생성
		List<MusicDTO> musicDTOList = new ArrayList<>();
		// memberDTOList에 memberDTO를 채움
		for(Music music : musicList) {
			MusicDTO musicDTO = MusicDTO.toSaveDTO(music);
			musicDTOList.add(musicDTO);
		}
		return musicDTOList;
	}
	
}
