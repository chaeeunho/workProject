package com.work.community.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.work.community.dto.MusicDTO;
import com.work.community.entity.Music;
import com.work.community.repository.MusicRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MusicService {
		
	private final MusicRepository musicRepository;

	public void save(@Valid MusicDTO musicDTO) {
		// 변환 메서드 필요 -> 최종적으로 music로 들어가야 한다.
		Music music = Music.toSaveEntity(musicDTO);
		musicRepository.save(music);
	}

	// 페이지 처리 전, 목록 가져오기
	/* public List<MusicDTO> findAll() {
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
	} */

	// 노래 삭제
	public void deleteById(Integer mno) {
		musicRepository.deleteById(mno);
	}

	// 노래 목록, 페이지 처리
	public Page<MusicDTO> findListAll(Pageable pageable) {
		int page = pageable.getPageNumber() - 1; // db는 현재페이지보다 1 작아야함
		int pageSize = 10;
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "mno");
		
		Page<Music> musicList = musicRepository.findAll(pageable);
		
		log.info("musicList.isFirst()=" + musicList.isFirst());
		log.info("musicList.isLast()=" + musicList.isLast());
		log.info("musicList.getNumber()=" + musicList.getNumber());
		
		// 생성자 방식으로 musicDTOList 만들기
		Page<MusicDTO> musicDTOList = musicList.map(music -> 
		new MusicDTO(music.getMno(), music.getMname(), music.getMalbum(), music.getMsinger(), music.getMcategory()));
		
		return musicDTOList;
	}
	
}
