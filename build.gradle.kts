plugins {
    id("java")
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
dependencies {
    implementation("com.h2database:h2:2.1.214")  // H2 JDBC driver
}


tasks.test {
    useJUnitPlatform()
}