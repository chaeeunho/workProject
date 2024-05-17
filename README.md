Spring Boot 기반 웹 서비스 개발 : 3WORK: WWW HEALTH CARE
![327441098-ab829309-177f-4785-8b27-2ef179ca7d54](https://github.com/chaeeunho/workProject/assets/145078937/8f72c1c7-5a07-4e17-84ca-23bb3ffacea4)


Spring Boot와 JPA를 활용한 건강 커뮤니티 사이트
📝 프로젝트 소개

바쁜 현대사회 속 현대인의 건강한 라이프스타일 촉진을 위한 정보 및 소통 서비스를 제공하는 사이트입니다.

⏰ 개발 기간

2024.01.29. ~ 2024.02.20.
🧑‍🤝‍🧑 담당 역할

최은호(팀장) : 프로젝트 총괄 디렉터 & 주소, 날씨 API연동 / DB(ERD)구성 작업

김정수(팀원) : 프로젝트 발표 / UI 디자인 / DB구성 작업 / 달력 API연동 / 유저 홈페이지, 프로필 수정 페이지 디자인(HTML, CSS) 및 기능구현, 회원가입 유효성 검사

임예은(팀원) : 프로젝트 계획서, 회의록 작성 및 발표자료 제작 / 대문, 메인, 로그인, 장바구니 페이지 디자인(HTML, CSS) 및 기능 구현 / 장바구니 기능 구현

고병진(팀원) : 지도 API연동, 노래, 뉴스, 아이템 정보 및 서브메뉴 페이지 디자인(HTML, CSS)

⚙️ 개발 환경 (JAVA / JDK 17)

image

💡 주요 기능

[메인페이지]

전체 디자인 설계
날씨, 지도 API 구현
비 로그인 시: 로그인, 아이디 및 비밀번호 찾기 버튼 출력 / 로그인 시 : 미니홈페이지로 이동, 위시리스트, 회원정보 수정, 로그아웃 버튼 출력
회원 리스트 및 검색 기능 구현
각종 컨텐츠(운동 정보, 헬스창 찾기, 건강뉴스, 추천 음악, 운동 기구) 리스트 구현
[회원 가입 페이지]

페이지 디자인(HTML, CSS) 구현
각 가입 정보 유효성 검사 구현
DTO와 JS를 활용한 유효성 검사 진행
ajax를 활용한 아이디, 닉네임 중복 검사 진행
API를 활용한 주소 검색 기능 구현
가입 완료 시 완료 페이지로 이동, DB에 저장
[아이디 찾기]

Optional 기능을 활용하여 이름과 연락처 DB에서 검색된 아이디, 가입일 출력
[비밀번호 찾기]

아이디, 이름 연락처 DB를 활용
임시 비밀번호 이메일로 발송
발송된 임시 비밀번호로 로그인 가능하도록 구현
[로그인 페이지]

가입시 저장된 아이디, 비밀번호를 통해 로그인 성공 시 메인페이지로 이동하도록 구현
[회원 검색 기능]

아이디를 기준으로 검색 시 해당 회원 검색 가능
검색키워드 미입력 시 회원 리스트가 출력되도록 구현
검색된 회원의 미니홈페이지로 이동 가능
[관리자 페이지]

회원이 해당 페이지 접근 시 “접근 불가 페이지입니다.” 문구 출력 및 메인으로 이동하도록 구현
각종 콘텐츠 페이지 (음악추천, 건강 뉴스 및 정보) 데이터 저장/삭제 가능
각 콘텐츠 화면 내 페이징처리 구현
회원 리스트 및 회원 정보 상세 보기, 탈퇴 기능 구현
[유저 홈페이지]

홈페이지 방문 시 방문자수 증가하도록 구현
프로필(이미지, 소개글) 구현
회원 정보 수정 가능(첨부파일 저장 포함)
BGM재생 기능 구현
방명록 작성, 수정, 삭제 기능 구현
방명록 삭제 및 수정은 회원당 자신이 작성한 방명록만 가능하도록 구현
방명록 페이징 처리 구현
게시판 ‘나의 건강일지’ 구현
‘Full Calendar’를 활용, 구글 API로 계정 연동하여 달력 구현
[유저 장바구니(위시리스트)]

관심 상품 담기 기능 구현
동일 제품 중복 추가 불가
관심 상품 삭제 기능 구현
📋 프로젝트 구조

해당 프로젝트의 구조는 아래와 같이 구성되어 있습니다.

메인 소스 디렉토리 구조

workProject\Code\3WORK\src : Java 소스 파일 및 정적 자료, 각종 페이지 html파일
workProject\Logo : 각종 컨텐츠 이미지파일
pom.xml : 프로젝트의 의존성 및 빌드 설정을 정의
application.properties: Spring Boot 애플리케이션 설정 파일.
* 상세 구현 화면 및 코드는 상단 '3WORK HEALTH CARE 화면 구현 자료'에서 확인 부탁드립니다.
