package com.board.api;
//파일이 속한 위치를 말한 코드

//이 프로젝트는 여기서 시작하고, 이 아래에 있는 스프링 클래스들을 자동으로 찾아서 관리해라.

import org.springframework.boot.SpringApplication;
//스프링부트 앱을 실행하기 위해 필요한 기능을 가져오는 코드
import org.springframework.boot.autoconfigure.SpringBootApplication;
//밑의 어노테이션을 쓰기 위해 가져오는 코드

@SpringBootApplication
//스프링부트 프로젝트의 시작 클래스라는 뜻의 어노테이션
public class BoardApiApplication {
//public은 외부에서도 접근 가능하다는 뜻 공개된 클래스라는 뜻

	public static void main(String[] args) {
		//자바 프로그램이 시작될 때 가장 먼저 실행되는 함수 main 메서드라고 함
		//static은 객체를 따로 만들지 않아도 바로 실행할 수 있다는 뜻
		SpringApplication.run(BoardApiApplication.class, args);
		//스프링 부트 앱을 실제로 실행하는 코드 ./gradlew bootRun 하면 보통 실행되는 코드
		//BoardApiApplication 클래스를 기준으로 앱을 시작하라는 뜻
	}

}