package chap03.spring.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap03.config.AppConfig;
import chap03.spring.dto.RegisterRequest;
import chap03.spring.exception.AlreadyExistingMemberException;
import chap03.spring.exception.IdPasswordNotMatchingException;
import chap03.spring.exception.MemberNotFoundException;
import chap03.spring.service.ChangePasswordService;
import chap03.spring.service.MemberInfoPrinter;
import chap03.spring.service.MemberListPrinter;
import chap03.spring.service.MemberRegisterService;
import chap03.spring.service.VersionPrinter;

public class MainForSpring {

	private static ApplicationContext ctx = null;

	public static void main(String[] args) throws IOException {
//		ctx = new GenericXmlApplicationContext("classpath:/appCtx.xml");
//		ctx = new GenericXmlApplicationContext("classpath:/conf1.xml", "classpath:/conf2.xml");
//		ctx = new GenericXmlApplicationContext("classpath:/conf2.xml");
		ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("명령어 입력");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다");
				break;
			}
			if (command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} else if (command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			} else if (command.equals("list")) {
				processListCommand();
				continue;
			} else if (command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
				continue;
			} else if (command.equals("version")) {
				processVersionCommand();
				continue;
			}
			printHelp();
		}
	}

	private static void processVersionCommand() {
		VersionPrinter versionPrinter = ctx.getBean(VersionPrinter.class);
		versionPrinter.print();
	}

	private static void processInfoCommand(String[] arg) {
		// 선택회원조회
		if(arg.length != 2) {
			printHelp();
			return;
		}
		String email = arg[1];
		MemberInfoPrinter infoPrinter = ctx.getBean(MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(email);
	}

	private static void processListCommand() {
		// 모든회원정보
		MemberListPrinter listPrinter = ctx.getBean(MemberListPrinter.class);
		listPrinter.printAll();
		
	}

	private static void processNewCommand(String[] arg) {
		// 회원가입
		if (arg.length != 5) {
			printHelp();
			return;
		}
		MemberRegisterService regSvc = ctx.getBean(MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);

		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치안함");
		}
		try {
			regSvc.register(req);
			System.out.println("등록했습니다. \n");
		} catch (AlreadyExistingMemberException e) {
			System.out.println("이미 존재하는 이메일");
		}
	}

	private static void processChangeCommand(String[] arg) {
		// 비밀번호변경
		if (arg.length != 4) {
			printHelp();
			return;
		}
		String email = arg[1];
		String password = arg[2];
		String newPassword = arg[3];

		ChangePasswordService changePwdSvc = ctx.getBean(ChangePasswordService.class);
		try {
			changePwdSvc.changePassword(email, password, newPassword);
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일");
		} catch (IdPasswordNotMatchingException e) {
			System.out.println("이메일과 암호가 일치하지 않음");
		}
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령어, 아래와 같이 사용법을 확인하기");
		System.out.println("명령어 사용법:");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println();

	}

}
