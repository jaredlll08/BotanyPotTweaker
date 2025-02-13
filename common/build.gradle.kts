import com.blamejared.botanypotstweaker.gradle.Versions

plugins {
    java
    id("net.neoforged.moddev.legacyforge")
    id("com.blamejared.botanypotstweaker.default")
}

legacyForge {
    mcpVersion = Versions.MINECRAFT
}

dependencies {
    compileOnly("org.spongepowered:mixin:0.8.5")
    val crt = "com.blamejared.crafttweaker:CraftTweaker-common-${Versions.MINECRAFT}:${Versions.CRAFTTWEAKER}";
    compileOnly(crt)
    compileOnly("net.darkhax.bookshelf:Bookshelf-Common-${Versions.MINECRAFT}:${Versions.BOOKSHELF}")
    compileOnly("net.darkhax.botanypots:BotanyPots-Common-${Versions.MINECRAFT}:${Versions.BOTANYPOTS}")
    annotationProcessor("com.blamejared.crafttweaker:Crafttweaker_Annotation_Processors:${Versions.CRAFTTWEAKER_ANNOTATION_PROCESSOR}")
    annotationProcessor(crt)
}
