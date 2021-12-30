# exampleRest

### **1. Description**
* **Rest**를 사용한 간단한 예제 프로그램
* **Client 프로그램으로 Postman을 사용**
* 실행 URL
  + **GET**
    - 전체 User 조회: http://localhost:8080/exampleRest/api/users
    - 특정 User 조회: http://localhost:8080/exampleRest/api/users/1
  + **POST**
    - http://localhost:8080/exampleRest/api/users
  + **PUT**
    - http://localhost:8080/exampleRest/api/users/1
  + **DELETE**
    - 전체 User 삭제: http://localhost:8080/exampleRest/api/users
    - 특정 User 삭제: http://localhost:8080/exampleRest/api/users/1


### **2. Environment**
* **Eclispe**
  + Spring Legacy Project
    - Template : Spring MVC 선택
* **Server**
  + Tomcat
  
  
### **3. Packages**
* **controller**
  + 사용자의 요청(request) 및 예외를 처리하는 Class
  + REST API를 처리
  + **GlobalExceptionController**, **HomeController**, **RestApiController**
* **model**
  + 애플리케이션에서 사용할 Model Class
  + **User**
* **service**
  + DAO Class를 따로 만들지 않고 Service Class에서 DAO 역할을 수행
  + 생성자를 통해 메모리 상에 User정보 저장
  + **UserService**
* **exception**
  + 예외 처리와 관련된 Class
  + **ErrorResponse**, **UserDuplicateException**, **UserNotFoundException**


### **4. 주요 Files**
* **pom.xml**
  + 프로젝트 기본 정보 및 Dependency 관리
  + 사용한 Dependency(Spring Legacy Project 기반)
    - spring-tx
    - jackson-databind
    - lombok
