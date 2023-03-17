plugins {
    id("java-library")
    id("maven-publish")
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    api(project(":omega-momiji-api"))
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
