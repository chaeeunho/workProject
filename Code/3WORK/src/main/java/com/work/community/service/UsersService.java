package com.work.community.service;


import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.work.community.config.SecurityUser;
import com.work.community.dto.UsersDTO;
import com.work.community.entity.Role;
import com.work.community.entity.Users;
import com.work.community.exception.BootBoardException;
import com.work.community.repository.UsersRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class UsersService {
   
   private final UsersRepository usersRepository;
      
   private final PasswordEncoder pwEncoder;
   
   private final EmailService emailService;
   
      public Users login(Users users) {
         // db에서 아이디 조회
         Optional<Users> findUsers = 
                  usersRepository.findByUid(users.getUid());
         if(findUsers.isPresent()) {
            return findUsers.get();
         }else {
            return null;
         }
      }
      //회원 가입 처리
      public void save(UsersDTO usersDTO) {
         //1. 비밀번호 암호화
         //2. 권한 설정
         String encPW = pwEncoder.encode(usersDTO.getUpassword());
         usersDTO.setUpassword(encPW);
         usersDTO.setRole(Role.ADMIN);
         
         //dto -> entity 변환 메서드
         Users users = Users.toSaveEntity(usersDTO);
         usersRepository.save(users);
      }
      
      public List<UsersDTO> findAll() {
         //db에서 memberList를 가져옴
         List<Users> usersList = usersRepository.findAll();
         
         //비어있는 memberDTOList를 생성
         List<UsersDTO> usersDTOList = new ArrayList<>();
         
         //memberDTOList에 memberDTO를 저장함
         for(Users users : usersList) {
            UsersDTO usersDTO = UsersDTO.toSaveDTO(users);
            usersDTOList.add(usersDTO);
         }
         return usersDTOList;
      }
      
      public Users findById(Integer uno) {
         // db에서 member 엔티티를 꺼내옴
         Optional<Users> findUsers = usersRepository.findById(uno);
         if (findUsers.isPresent()) { // 회원 정보가 있으면
            // entity -> dto 변환
            return findUsers.get(); // 정보를 가져와서 반환
         }else {
            throw new BootBoardException("회원 페이지를 찾을 수 없습니다.");
         }
         
      }
      //회원 탈퇴
      public void deleteById(Integer uno) {
         usersRepository.deleteById(uno);
      }
      
       // 아이디의 일부를 포함하는 사용자 검색
       public List<UsersDTO> searchUsersByUid(String uid) {
           List<Users> usersList = usersRepository.findByUidContaining(uid);
           return usersList.stream()
                           .map(UsersDTO::toSaveDTO)
                           .collect(Collectors.toList());
       }

      //아이디 중복검사
      public String checkId(String uid) {
      Optional<Users> findUser = 
            usersRepository.findByUid(uid);
      if(findUser.isEmpty()) {
         return "OK";
      }else {
         return "NO";
      }
   }
      // 닉네임 중복검사
      public String checkNickname(String unickname) {
         Optional<Users> checkNickname = usersRepository.findByUnickname(unickname);
         if (checkNickname.isEmpty()) {
            return "OK";
         }else {
            return "NO";
         }
      }
      
      //회원 수정페이지
      public UsersDTO findByUid(SecurityUser principal) {
         Optional<Users> user =  
               usersRepository.findByUid(principal.getUsername());
         UsersDTO usersDTO = UsersDTO.toSaveDTO(user.get());
         return usersDTO;
      }

       //회원 수정 처리 
       public void update(UsersDTO usersDTO) { String encPW =
       pwEncoder.encode(usersDTO.getUpassword()); usersDTO.setUpassword(encPW);
       usersDTO.setRole(Role.MEMBER);
        
       Users users = Users.toSaveUpdate(usersDTO); usersRepository.save(users); }
       
      //회원 정보 수정(첨부파일 포함)
      public void saveFiles(UsersDTO usersDTO, MultipartFile uimage, MultipartFile bgmFile) throws Exception {
          String encPW = pwEncoder.encode(usersDTO.getUpassword());
          usersDTO.setUpassword(encPW);
          usersDTO.setRole(Role.MEMBER);
          
          Users users;
          if (!uimage.isEmpty()) {
              // 이미지 파일이 업로드된 경우 처리
              UUID uuid = UUID.randomUUID();
              String imageFilename = uuid + "_" + uimage.getOriginalFilename();
              String imageFilepath = "C:\\3workfiles\\profile\\" + imageFilename;
              File savedImageFile = new File(imageFilepath);
              uimage.transferTo(savedImageFile);
              
              usersDTO.setUfilename(imageFilename);
              usersDTO.setUfilepath(imageFilepath);
          } else {
              // 이미지 파일이 업로드되지 않은 경우 기존의 파일 정보를 유지
              usersDTO.setUfilename(findById(usersDTO.getUno()).getUfilename());
              usersDTO.setUfilepath(findById(usersDTO.getUno()).getUfilepath());
          }
          
          if (!bgmFile.isEmpty()) {
              // mp3 파일이 업로드된 경우 처리
              UUID uuid = UUID.randomUUID();
              String mp3Filename = uuid + "_" + bgmFile.getOriginalFilename();
              String mp3Filepath = "C:\\3workfiles\\mp3\\" + mp3Filename;
              File savedMp3File = new File(mp3Filepath);
              bgmFile.transferTo(savedMp3File);
              
              usersDTO.setBgmname(mp3Filename);
              usersDTO.setBgmpath(mp3Filepath);
          } else {
              // mp3 파일이 업로드되지 않은 경우 기존의 파일 정보를 유지
              usersDTO.setBgmname(findById(usersDTO.getUno()).getBgmname());
              usersDTO.setBgmpath(findById(usersDTO.getUno()).getBgmpath());
          }
          
          users = Users.toSaveUpdate(usersDTO);
          usersRepository.save(users);
         }
      
         //방문자수 증가
         @Transactional
         public void updateHits(Integer uno) {
            usersRepository.updateHits(uno);
         }
         
         //임시 비번 발송
         public boolean isUidAndUphoneAndUnameMatch(String uid, String uname, String uphone) {
            Users users = usersRepository.findByUidAndUnameAndUphone(uid, uname, uphone);
            return users != null;
         }

         //임시 비밀번호 생성, 이메일 전송
          public void sendTemporaryPassword(String uid){
              String temporaryPassword = generateTemporaryPassword();
              emailService.sendTemporaryPassword(uid, temporaryPassword);
          }
          
          public String generateTemporaryPassword(){
              String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
              int length = 10;
              StringBuilder temporaryPassword = new StringBuilder(length);
              SecureRandom random = new SecureRandom();
              for(int i=0; i<length; i++){
                  int randomIndex = random.nextInt(characters.length());
                  temporaryPassword.append(characters.charAt(randomIndex));
              }

              return temporaryPassword.toString();
          }
          //임시비밀번호 저장
          public UsersDTO findByUid2(String uid) {
              Users users = usersRepository.findByUid(uid).get();
              UsersDTO usersDTO = UsersDTO.toSaveDTO(users);
              return usersDTO;
          }
          
          //방문자 순위
          public List<UsersDTO> findAllUsersOrderByHitsDesc() {
              List<Users> users = usersRepository.findAllByOrderByHitsDesc();
              return users.stream().map(UsersDTO::toSaveDTO).collect(Collectors.toList());
          }

      }