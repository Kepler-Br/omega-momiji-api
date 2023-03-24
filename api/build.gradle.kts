plugins {
    id("java-library")
    id("maven-publish")
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-web")

    api("com.fasterxml.jackson.core:jackson-annotations")
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
