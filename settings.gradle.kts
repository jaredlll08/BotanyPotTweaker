
pluginManagement {
    repositories {
        maven("https://maven.blamejared.com")
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        maven("https://repo.spongepowered.org/repository/maven-public/") {
            name = "Sponge Snapshots"
        }
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "BotanyPotsTweaker"
include("common")
include("fabric")
include("forge")


if (file("CraftTweaker-Annotation-Processors").exists()) {
    includeBuild("CraftTweaker-Annotation-Processors") {
        dependencySubstitution {
            substitute(module("com.blamejared.crafttweaker:Crafttweaker_Annotation_Processors")).using(project(":"))
        }
    }
}