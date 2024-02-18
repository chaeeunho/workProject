package com.work.community.service;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.work.community.dto.ItemDTO;
import com.work.community.entity.Item;
import com.work.community.repository.ItemRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ItemService {
   
   private final ItemRepository itemRepository;

   // 아이템 이미지 처리
   public void save(@Valid ItemDTO itemDTO, MultipartFile iimage) throws Exception {
      // 1. 파일을 서버에 저장하고, 
      if(!iimage.isEmpty()) { // 전달된 파일이 있으면
        UUID uuid = UUID.randomUUID();  // 무작위 아이디 생성(중복파일의 이름을 생성해줌)
        
        String filename = uuid + "_" + iimage.getOriginalFilename(); // 원본 파일
      // 1. 윈도우 : C:/3workfiles/item/
        // 2. 맥 : /Users/rim-yeeun/3workfiles/item/
        String filepath = "C:\\3workfiles\\item\\" + filename;
        
        // File 클래스 객체 생성
        File savedFile = new File(filepath); // upload 폴더에 저장
        iimage.transferTo(savedFile); 
      
        // 2. 파일 이름은 db에 저장
        itemDTO.setIfilename(filename);
        itemDTO.setIfilepath(filepath); // 파일 경로 설정함
      }
      // dto -> entity로 변환
      Item item = Item.toSaveEntity(itemDTO);
      // entity를 db에 저장
      itemRepository.save(item);
   }
   
   // 아이템 삭제
   public void deleteById(Integer ino) {
      itemRepository.deleteById(ino);
   }

   // 아이템 목록, 페이지 처리
   public Page<ItemDTO> findListAll(Pageable pageable) {
      int page = pageable.getPageNumber() - 1; // db는 현재페이지보다 1 작아야함
      int pageSize = 9;
      pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "ino");
      
      Page<Item> itemList = itemRepository.findAll(pageable);
      
      log.info("itemList.isFirst()=" + itemList.isFirst());
      log.info("itemList.isLast()=" + itemList.isLast());
      log.info("itemList.getNumber()=" + itemList.getNumber());
      
      // 생성자 방식으로 musicDTOList 만들기
      Page<ItemDTO> itemDTOList = itemList.map(item -> 
      new ItemDTO(item.getIno(), item.getIname(), item.getIcontent(), item.getIprice(), item.getIlink(), item.getIfilename(), item.getIfilepath()));
      
      return itemDTOList;
   }

   // 장바구니에서 사용
   public Item findById(Integer ino) {
      Optional<Item> findItem = itemRepository.findById(ino);
      ItemDTO itemDTO = ItemDTO.toSaveDTO(findItem.get());
      Item item = Item.toSaveEntity(itemDTO);
      return item;
   }
   
}