repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    maven {
        name = "tcoded-releases"
        url = uri("https://repo.tcoded.com/releases")
    }
}

dependencies {
    implementation(project(":common"))
    compileOnly("io.papermc.paper:paper-api:1.20-R0.1-SNAPSHOT")
    implementation("com.tcoded:FoliaLib:0.5.1")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}