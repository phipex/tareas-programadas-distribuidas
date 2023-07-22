plugins {
	java
	id("org.springframework.boot") version "2.7.13"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	id ("com.google.cloud.tools.jib") version "3.3.2"
}

group = "co.com.ies.pruebas"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-aop
	implementation("org.springframework.boot:spring-boot-starter-aop:3.1.2")
	// https://mvnrepository.com/artifact/org.jobrunr/jobrunr-spring-boot-starter
	implementation("org.jobrunr:jobrunr-spring-boot-starter:5.3.3")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
