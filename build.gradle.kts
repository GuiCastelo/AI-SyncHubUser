import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.jetbrains.kotlin.jvm") version "1.9.23"
	id("org.jetbrains.kotlin.plugin.spring") version "1.9.23"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.9.23"
}

group = "guilherme.brito"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")

	//SWAGGER
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("org.postgresql:postgresql:42.7.2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.jar {
	enabled = false
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
