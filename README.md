## 섹션1
### 1. 프로젝트 생성

- spring initializr 사용해서 간단히 프로젝트 생성 가능
- gradle project로 생성 
- 프로젝트 구조 
  - gradle, gradle wrapper
  - main - test 폴더 분리되어 생성 
    - test 코드가 중요하기 때문에 
  - resources
    - java 코드 파일 제외한 나머지, 설정 파일 등 
  - build.gradle
    - gradle : 버전 설정하고 라이브러리 땡겨오는 거다 정도 알면 됨
- 프로젝트 실행
  - 기본적으로 main 메서드를 갖고 있는 ~Application 파일이 생서됨
  - 실행 로그: Tomcat started on port 8080
  - `@SpringBootApplication` 애노테이션이 추가된 클래스 안에 있는 main 메서드를 실행하면
  톰캣이라는 웹서버를 내장하고 있어서 톰캣이라는 웹서버를 자체적으로 띄우면서 스프링부트가 같이 올라옴 


### 2. 라이브러리 살펴보기 

- gradle, maven 같은 빌드 툴들은 의존관계 관리를 해준다.
- spring boot starter web을 땡기면 해당 라이브러리를 필요로 하는 라이브러리를 다 땡겨온다

- 스프링부트 라이브러리 
  - spring-boot-starter-web
    - spring-boot-starter-tomcat: 톰캣 (웹서버)
    - spring-webmvc: 스프링 웹 MVC
  - spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View)
  - spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅
    - spring-boot
      - spring-core
    - spring-boot-starter-logging
      - logback, slf4j
  
- 테스트 라이브러리
  - spring-boot-starter-test
    - junit: 테스트 프레임워크
    - mockito: 목 라이브러리
    - assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
    - spring-test: 스프링 통합 테스트 지원


### 3. view 환경설정

- spring boot 가 제공하는 welcome page
  - resources/static/index.html을 기본 페이지로 사용
- 컨트롤러에서 string을 리턴하면 뷰 리졸버가 해당 이름을 가진 html 파일을 찾아서 리턴

### 4. 빌드하고 실행하기

콘솔로 이동
1. ./gradlew build
2. cd build/libs
3. java -jar hello-spring-0.0.1-SNAPSHOT.jar
4. 실행 확인


## 섹션2 스프링 웹 개발 기초
- 웹 개발을 크게 보면 1) html 같은 정적 컨텐츠 2) 타임리프 같은 mvc 패턴 3) 데이터만 내려주는 API

### 1. 정적 컨텐츠 static content
스프링 부트는 static 폴더에서 정적 컨텐츠를 찾아서 사용한다.
관련 컨트롤러가 있는지 확인하고 없으면 정적 컨텐츠에서 해당 요청을 찾아서 반환한다.


### 2. MVC와 템플릿 엔진
Model - view - controller

과거에는 view, controller가 분리되어 있지 않았다.
view에서 모든걸 해결했다. Jsp - view 파일 안에서 db 접근, 로직 등 모든 것을 해결
관심사 분리, 역할과 책임
View 화면을 그리는 것
Controller 비즈니스 로직 담당

View resolver가 해당 view와 model에 담긴 data로 html을 그려서 내려준다.

### 3. API
@ResponseBody : http에서 body에 리턴하는 데이터를 넣어주겠다는
Response body로 객체를 리턴하면 json으로 반환하는게 기본

요청을 받았는데 해당 컨트롤러에 @ResponseBody가 있으면
View resolver 대신 httpMessageConverter가 동작함
기본 문자처리: stringHttpMessageConverter
기본 객체처리: MappingJacson2HttpMessageConverter


## 섹션3 회원 관리 예제 - 백엔드 개발
### 1. 비즈니스 요구사항 정리
- 일반적인 웹 애플리케이션 계층 구조
  컨트롤러: 웹 MVC의 컨트롤러 역할
  서비스 : 핵심 비즈니스 로직 구현
  리포지토리: 데이터베이스에 접근, 도메인 객체를 Db에 저장하고 관리
  도메인: 비즈니스 도메인 객체


### 2. 회원 도메인과 리포지토리 만들기
인터페이스 member repository
Save
findById
findByName
findAll

클래스 memory member repository
map에 저장하고 long sequence 를 id로 이용


### 3. 회원 리포지토리 테스트 케이스 작성
junit 프레임워크로 테스트 코드
memoryMemberRepositoryTest 작성
테스트 assert문 사용하기

save()
findByName()
findAll()

@after each 테스트 케이스 종료 마다 실행되는 메서드
- 여러 테스트를 한번에 실행시키면 메모리DB에 남아있는 데이터가 다른 테스트에 영향을 줄 수 있어서 메모리DB 초기화 시키는 용도로 사용

TDD
테스트를 먼저 만들고 구현 클래스를 작성

- 테스트 코드 작성시 주의사항
테스트는 서로 종속되지 않고 각각 실행되어야 함
테스트 순서에 의존관계가 있으면 좋은 테스트가 아님 

### 4. 회원 서비스 개발
- repository : DB에 대한 단순한 작업
- service: 비즈니스 로직 포함 (ex. 회원 이름으로 중복 체크)

### 5. 회원 서비스 테스트 
- cmd shift T : 테스트 생성 단축키 
- 테스트 이름은 한글로 작성해도 괜찮다
- 테스트는 정상보다 예외가 더 중요

- @BeforeEach 

opt command / 주석

## 섹션4 스프링 빈과 의존관계
### 1. 컴포넌트 스캔과 자동 의존관계 설정 
### 2. 자바 코드로 직접 스프링 빈 등록하기 

















실무에서는 sout 대신 log를 써야한다.