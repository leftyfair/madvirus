package chap03.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("versionPrinter")
public class VersionPrinter {

	private int majorVersion;
	private int minorVersion;
	
	public VersionPrinter() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public VersionPrinter(@Value("4") int majorVersion, @Value(value = "1") int minorVersion) {
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
	}
	
	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}

	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

	public void print() {
		System.out.println("이 프로그램 버전은" + majorVersion + "." +minorVersion + "이다.\n\n");
	}
	
}
