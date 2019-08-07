import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinPluginVersion = "1.3.11"
	kotlin("plugin.jpa") version "$kotlinPluginVersion"
	id("org.springframework.boot") version "2.1.7.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("plugin.allopen") version "$kotlinPluginVersion"

	kotlin("jvm") version "1.2.71"
	kotlin("plugin.spring") version "1.2.71"

	kotlin("kapt") version "$kotlinPluginVersion"
}

group = "org.uhafactory.travle"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("com.mysema.querydsl:querydsl-jpa:3.6.3")
	kapt("com.mysema.querydsl:querydsl-apt:3.6.3:jpa")
	kapt("org.springframework.boot:spring-boot-configuration-processor")
	compileOnly("org.springframework.boot:spring-boot-configuration-processor")

	runtimeOnly("com.h2database:h2")
//	runtimeOnly("mysql:mysql-connector-java")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
	testImplementation("org.mockito:mockito-junit-jupiter:2.23.4")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
