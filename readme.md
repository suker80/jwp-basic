#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
1. ContextLoadListener는 ServletconextListener를 구현을 하고 있다. contextInitialized 메서드는 컨텍스트가 초기화 될때 실행이 되는 메서드이다.
2. conetxtInitialized 메서드에서 "jwp.sql" sql 스크립트를 실행한다.
3. 스크립트가 실행되면서 데이터베이스 테이블을 생성을 한다.
4. sql 스크립트를 통해서 테이블들을 초기화한다.
5. 서블릿 컨테이너가 생성이 되면 서블릿들을 생성을 한다.
6. 프로젝트에는 서블릿이 DispatcherServlet 하나 밖에 없기 때문에 DispatcherServlet 만 생성이 된다.
7. DispatcherServlet은 서블릿 컨테이너에 의해 생성이 되며 라이프싸이클에 따라 init 메서드가 먼저 수행이 된다.
8. init 메서드에는 RequestMapping 인스턴스를 생성하는 코드가 있어서 요청들을 Mapping을 사전 설정에 따라 매핑하게 된다.
#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.

1. 서블릿 컨테이너로 요청이 들어온다.
2. 서블릿 컨테이너에서 url 매핑에따라 알맞은 서블릿을 배정을 한다.
3. 서블릿은 DispatcherServlet 하나 뿐이고 모든 경로가 허용이 되어있다.
4. DispathcerServlet에 요청이 들어오면 미리 초기화해놓은 RequestMapping 인스턴스에 현재 경로에 맞는 컨트롤러를 요청한다.
5. DispatcherServlet이 컨트롤러를 받으면 HttpServletRequest, HttpServletResponse 인스턴스를 넘겨 컨트롤러를 실행한다.
6. 컨트롤러를 실행하면 반환 값으로 ModelAndView 인스턴스를 반환한다.
7. 반환을 할 때 화면에 뿌릴 url 값을 ModelAndView 인스턴스에 넣는다.
8. ModelAndView 인스턴스에는 현재 컨트롤러가 실행하고 반환할 값들이 ModelAndView 클래스안에 model 필드에 추가가 되어있다.
9. ModelAndView 인스턴스의 View 인스턴스를 통해 model 값, HttpServletRequest , HttpServletResponse를 넘기게 된다.
10. View 인스턴스 안에서 forward 메서드를 통해 webcontent안에 있는 html을 찾게 된다.

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 
