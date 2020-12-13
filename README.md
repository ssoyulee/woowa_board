# woowa_borad

### 환경설정 및 선행 조건
##### 포트 변경 필요 시 

	application.properties 파일 내 server.port 변경

##### 메일 설정

	1. 2차 인증
		구글 계정 관리 접속 (https://myaccount.google.com/security)
		좌측 탭에 보안 클릭
			2단계 인증 사용으로 변경
	2. 앱 비밀번호 
		구글 계정 관리 접속 (https://myaccount.google.com/security)
		좌측 탭에 보안 클릭
			앱 비밀번호 설정
				앱선택 : 기타(맞춤 이름)
				기기선택 : SMTP
	3. application.properties 파일 내 설정
		spring.mail.username={이메일주소}
		spring.mail.password={2.앱비밀번호에서 생성된 16자리 비밀번호}
		
### 빌드 방법
	mvnw clean
	mvnw install

### 실행 방법
	mvnw spring-boot:run
	java -Dfile.encoding=UTF-8 -jar target/board-0.0.1-SNAPSHOT.war
	
### 프로젝트 구조 (주요 패키지)
	- woowa.board.common	: 공통 파일
	- woowa.board.config 	: 설정 파일
		
	- woowa.board.admin 	: 어드민 페이지
	- woowa.board.board 	: 게시판 관련 파일
	- woowa.board.commnet 	: 댓글 관련 파일
	- woowa.board.hacker 	: 메인화면에 Hacker New List를 가져오는 파일
	- woowa.board.index 	: 메인 화면
	- woowa.board.noti	: 메일 전송 관련 파일
	- woowa.board.post	: 게시물 관련 파일
	- woowa.board.user	: 사용자 관련 파일

### swagger 
	http://localhost:{port}/swagger-ui.html
	ex) http://localhost:8080/swagger-ui.html