import io.papermc.paperweight.userdev.ReobfArtifactConfiguration

plugins {
    id("java")
    id("maven-publish")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.8" // the latest version can be found on the Gradle Plugin Portal
}

// The Minecraft version we're currently building for
val minecraftVersion = "1.21.4"
// Version of CoreApi
val projectVersion = "1.0.0"

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
    withJavadocJar()
    withSourcesJar()
}

paperweight.reobfArtifactConfiguration = ReobfArtifactConfiguration.MOJANG_PRODUCTION

repositories {
    mavenCentral()
}

dependencies {
    // Paper
    paperweight.paperDevBundle("${minecraftVersion}-R0.1-SNAPSHOT")
}

tasks {
    processResources {
        expand("version" to projectVersion)
    }
    compileJava {
        options.release = 21
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
        options.overview("src/main/javadoc/overview.html")
        (options as StandardJavadocDocletOptions).links(
            "https://jd.papermc.io/paper/1.21.1/",
            "https://jd.advntr.dev/api/4.17.0/",
        )
        (options as StandardJavadocDocletOptions).docTitle("CoreAPI - $projectVersion")
    }
    jar {
        archiveFileName.set("CoreAPI-$projectVersion-$minecraftVersion.jar")
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.shanebeestudios"
            artifactId = project.name.lowercase()
            version = projectVersion
            from(components["java"])
        }
    }
}
