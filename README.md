# 김영한 선생님의 [스프링 입문](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard) 요약 정리  


## 섹션 1 프로젝트 환경 설정
### 1. 프로젝트 생성

- spring initializr 사용해서 간단히 프로젝트 생성 가능
- gradle project로 생성 
- 프로젝트 구조 
  - gradle, gradle wrapper
  - main - test 폴더 분리되어 생성되는 것이 기본
    - test 코드의 중요성이 대두되어 test 폴더가 자동으로 생성된다. 
  - resources
    - java 코드 파일 제외한 나머지, 설정 파일 등 
  - build.gradle
    - gradle : 버전 설정, 라이브러리 추가를 위한 파일
- 프로젝트 실행
  - 기본적으로 main 메서드를 갖고 있는 ~Application 파일이 생성됨
  - 실행 로그: Tomcat started on port 8080
  - `@SpringBootApplication` 
    - 해당 애노테이션이 명시된 클래스 안에 main 메서드를 실행하면 내장된 웹서버인 톰캣과 스프링부트가 같이 올라온다.


### 2. 라이브러리 살펴보기 

- gradle, maven 같은 빌드 툴들은 의존관계를 관리 해준다.
- spring boot starter web를 추가하면 해당 라이브러리를 필요로 하는 라이브러리를 모두 추가해준다.

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
    - mockito: mock 라이브러리
    - assertj: 테스트 코드를 편하게 작성하게 도와주는 라이브러리
    - spring-test: 스프링 통합 테스트 지원


### 3. view 환경설정

- spring boot 가 제공하는 welcome page
  - resources/static/index.html을 기본 페이지로 사용
- 컨트롤러에서 string을 리턴하면 View resolver가 해당 이름을 가진 html 파일을 찾아서 리턴


### 4. 빌드하고 실행하기

```
콘솔로 이동
1. ./gradlew build
2. cd build/libs
3. java -jar hello-spring-0.0.1-SNAPSHOT.jar
4. 실행 확인
```
---

## 섹션2 스프링 웹 개발 기초
- 웹 개발을 크게 보면 1) html 같은 정적 컨텐츠 2) 타임리프 같은 mvc 패턴 3) 데이터만 내려주는 API

### 1. 정적 컨텐츠 static content
- 스프링 부트는 static 폴더에서 정적 컨텐츠를 찾아서 사용한다.
- 1)관련 컨트롤러가 있는지 확인하고 없으면 2)정적 컨텐츠에서 해당 요청을 찾아서 반환한다.


### 2. MVC와 템플릿 엔진
- MVC: Model - view - controller

- 과거에는 view, controller가 분리되어 있지 않았다.
  - view에서 모든걸 해결했다. 
  - Jsp - view 파일 안에서 db 접근, 로직 등 모든 것을 해결
- 관심사 분리, 역할과 책임
  - View의 역할 :  화면을 그리는 것
  - Controller의 역할 :  비즈니스 로직 담당

- View resolver가 해당 view와 model에 담긴 data로 html을 그려서 내려준다.

### 3. API
- @ResponseBody : http 요청에 대한 응답으로 body에 데이터를 넣어서 리턴한다는 어노테이션
  - Response body로 객체를 리턴하면 json으로 반환하는게 기본

- 요청을 받았는데 해당 컨트롤러에 @ResponseBody가 있으면 View resolver 대신 httpMessageConverter가 동작함
  - 기본 문자처리: stringHttpMessageConverter
  - 기본 객체처리: MappingJacson2HttpMessageConverter

--- 

## 섹션3 회원 관리 예제 - 백엔드 개발
### 1. 비즈니스 요구사항 정리
- 일반적인 웹 애플리케이션 계층 구조
  - Controller : 웹 MVC의 컨트롤러 역할
  - Service : 핵심 비즈니스 로직 구현
  - Repository : 데이터베이스에 접근, 도메인 객체를 Db에 저장하고 관리
  - Domain : 비즈니스 도메인 객체


### 2. 회원 도메인과 리포지토리 만들기
- 인터페이스 MemberRepository 생성
  - 메서드 추가   
    Save, 
    findById, 
    findByName, 
    findAll, 

- 클래스 MemoryMemberRepository 생성
  - DB 대신 map에 저장하고 long sequence 를 id로 이용


### 3. 회원 리포지토리 테스트 케이스 작성
- junit 프레임워크로 테스트 코드 작성
- MemoryMemberRepositoryTest 작성
- 테스트 assert문 사용하기

- @after each 테스트 케이스 종료 마다 실행되는 메서드
  - 여러 테스트를 한번에 실행시키면 메모리DB에 남아있는 데이터가 다른 테스트에 영향을 줄 수 있어서 메모리DB 초기화 시키는 용도로 사용

- TDD : Test Driven Development
  - 테스트를 먼저 만들고 구현 클래스를 작성

- 테스트 코드 작성시 주의사항
  - 테스트는 서로 종속되지 않고 각각 실행되어야 함
  - 테스트 순서에 의존관계가 있으면 좋은 테스트가 아님 

### 4. 회원 서비스 개발
- repository : DB에 대한 단순한 작업
- service : 비즈니스 로직 포함 (ex. 회원 이름으로 중복 체크)

### 5. 회원 서비스 테스트
- 테스트 이름은 한글로 작성해도 괜찮다
- 테스트는 정상보다 예외가 더 중요
- @BeforeEach 


> cmd shift T : 테스트 생성 단축키  
> opt cmd / : 주석

--- 

## 섹션4 스프링 빈과 의존관계
### 1. 컴포넌트 스캔과 자동 의존관계 설정 
- 생성자에 `@Autowired`가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다. 이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection) 의존성 주입이라 한다.
- 스프링 빈을 등록하는 2가지 방법 
  - 컴포넌트 스캔과 자동 의존관계 설정 
  - 자바 코드로 직접 스프링 빈 동록하기 
- 컴포넌트 스캔 원리 
  - `@Component` 애노테이션이 있으면 스프링 빈으로 자동 등록된다.
  - `@Controller` `@Service` `@Repository` 는 `@Component`를 포함하고 있다. 
- 스프링은 스프링 컨테이너에 스프링 빈을 등록할때 싱글톤을 기본으로 한다. 따라서 같은 스프링 빈이면 모두 같은 인스턴스다.


### 2. 자바 코드로 직접 스프링 빈 등록하기
- `@Configuration` 어노테이션을 명시한 설정 클래스를 추가 
  - 해당 클래스에서 `@Bean`으로 직접 빈 등록할 수 있다. 

- DI에는 필드 주입, setter 주입, 생성자 주입 3가지가 있다. 의존관계가 실행중에 동적으로 변하는 경우가 거의 없으므로 생성자 주입을 권장
- 실무에서는 주로 컴포넌트 스캔을 사용
- 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록 
- `@Autowired` 스프링 빈으로 등록된 객체에만 동작한다.


> cmd p 메서드의 매개변수 조회 

---

## 섹션5 회원관리 예제 - 웹 MVC 개발 
### 회원 웹 기능 - 홈 화면 추가
### 회원 웹 기능 - 등록 
### 회원 웹 기능 - 조회
- @GetMapping 컨트롤러를 통해서 view를 호출한다. 
  - 컨트롤러가 정적 파일보다 우선 순위가 높다. 
- @PostMapping 컨트롤러를 통해서 데이터를 받는다. 
- thymeleaf에서 컨트롤러에서 받은 데이터를 동적으로 view를 생성한다.

---

## 섹션6 스프링 DB 접근 기술
### 1. H2 데이터베이스 설치

> ls -ltr 리눅스 날짜순 정렬

### 2. 순수 JDBC
고대의 방법

### 3. 스프링 통합 테스트 
- `@SpringBootTest`
  - 스프링 컨테이너와 테스트를 함께 실행한다.
- `@Transactional`
  - 테스트 케이스에 이 애노테이션이 있으면 테스트 시작 전에 트랜잭션을 시작하고 테스트 완료 후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다. 
  
### 3. 스프링 Jdbc Template
- 스프링 Jdbc Template과 MyBatis 같은 라이브러리는 JDBC API의 반복 코드를 대부분 제거해준다.

### 4. JPA
- JPA를 사용하면 객체 중심의 설계로 패러다임을 전환할 수 있다. 
- JPA를 사용하면 개발 생산성을 크게 높일 수 있다. 

### 5. 스프링 데이터 JPA
- 스프링 데이터 JPA는 생산성을 마법처럼! 
- JPA를 편리하게 사용하는 기술이므로 JPA 선행학습 후 스프링 데이터 JPA 사용하는 것을 추천한다. 

- 스프링 데이터 JPA 제공 기능
  - 인터페이스를 통한 기본적인 CRUD
  - `findByName()` 처럼 필드 이름만으로 조회 가능
  - 페이징 기능 

--- 
## 섹션7 AOP
### 1. AOP가 필요한 상황
- 비즈니스 로직이 아니면서 공통적으로 처리해야 하는 로직 

### 2. AOP 적용
- Aspect Oriented Programming
- 공통 관심 사항과 핵심 관심 사항을 분리
- 원하는 적용 대상을 선택할 수 있다. 
- 스프링 컨테이너에서 프록시를 생성해서 컨트롤러에서 프록시를 호출하고 `joinPoint.proceed()`에서 실제 객체를 호출한다.