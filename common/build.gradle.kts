import com.blamejared.botanypotstweaker.gradle.Versions

plugins {
    java
    id("org.spongepowered.gradle.vanilla") version "0.2.1-SNAPSHOT"
    id("com.blamejared.botanypotstweaker.default")
}

minecraft {
    version(Versions.MINECRAFT)
}


dependencies {
    compileOnly("org.spongepowered:mixin:0.8.5")
    val crt = "com.blamejared.crafttweaker:CraftTweaker-common-${Versions.MINECRAFT}:${Versions.CRAFTTWEAKER}"
    compileOnly(crt)
    annotationProcessor("com.blamejared.crafttweaker:Crafttweaker_Annotation_Processors:${Versions.CRAFTTWEAKER_ANNOTATION_PROCESSOR}")
    annotationProcessor(crt)
    compileOnly("net.darkhax.bookshelf:Bookshelf-Common-${Versions.MINECRAFT}:${Versions.BOOKSHELF}")
    compileOnly("net.darkhax.botanypots:BotanyPots-Common-${Versions.MINECRAFT}:${Versions.BOTANYPOTS}")
}
