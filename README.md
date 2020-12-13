# woowa_borad

### 환경 설정
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
	

### swagger 
	http://localhost:8080/swagger-ui.html