import com.blamejared.botanypotstweaker.gradle.Properties
import com.blamejared.botanypotstweaker.gradle.Versions
import com.blamejared.gradle.mod.utils.GMUtils
import net.darkhax.curseforgegradle.TaskPublishCurseForge
import net.darkhax.curseforgegradle.Constants as CFG_Constants

plugins {
    id("fabric-loom") version "1.5-SNAPSHOT"
    id("com.blamejared.botanypotstweaker.default")
    id("com.blamejared.botanypotstweaker.loader")
    id("com.modrinth.minotaur")
}

dependencies {
    minecraft("com.mojang:minecraft:${Versions.MINECRAFT}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${Versions.FABRIC_LOADER}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${Versions.FABRIC}")
    implementation(project(":common"))
    val crt = "com.blamejared.crafttweaker:CraftTweaker-fabric-${Versions.MINECRAFT}:${Versions.CRAFTTWEAKER}"
    modImplementation(crt)
    modImplementation("com.faux.fauxcustomentitydata:FauxCustomEntityData-fabric-${Versions.MINECRAFT}:${Versions.FAUX_CUSTOM_ENTITY_DATA}")
    modLocalRuntime("mezz.jei:jei-${Versions.MINECRAFT}-fabric:${Versions.JEI}")

    annotationProcessor("com.blamejared.crafttweaker:Crafttweaker_Annotation_Processors:${Versions.CRAFTTWEAKER_ANNOTATION_PROCESSOR}")
    annotationProcessor(crt)
    modImplementation("net.darkhax.bookshelf:Bookshelf-Fabric-${Versions.MINECRAFT}:${Versions.BOOKSHELF}")
    modImplementation("net.darkhax.botanypots:BotanyPots-Fabric-${Versions.MINECRAFT}:${Versions.BOTANYPOTS}")
}

loom {
    runs {
        named("client") {
            client()
            configName = "Fabric Client"
            ideConfigGenerated(true)
            runDir("run")
        }
    }
}

tasks.create<TaskPublishCurseForge>("publishCurseForge") {
    apiToken = GMUtils.locateProperty(project, "curseforgeApiToken")

    val mainFile = upload(Properties.CURSE_PROJECT_ID, file("${project.buildDir}/libs/${base.archivesName.get()}-$version.jar"))
    mainFile.changelogType = "markdown"
    mainFile.changelog = GMUtils.smallChangelog(project, Properties.GIT_REPO)
    mainFile.releaseType = CFG_Constants.RELEASE_TYPE_RELEASE
    mainFile.addJavaVersion("Java ${Versions.JAVA}")
    mainFile.addGameVersion(Versions.MINECRAFT)
    mainFile.addRequirement("crafttweaker")
    doLast {
        project.ext.set("curse_file_url", "${Properties.CURSE_HOMEPAGE}/files/${mainFile.curseFileId}")
    }
}

modrinth {
    token.set(GMUtils.locateProperty(project, "modrinth_token"))
    projectId.set(Properties.MODRINTH_PROJECT_ID)
    changelog.set(GMUtils.smallChangelog(project, Properties.GIT_REPO))
    versionName.set("Fabric-${Versions.MINECRAFT}-$version")
    versionType.set("release")
    loaders.add("fabric")
    uploadFile.set(tasks.remapJar.get())
    dependencies {
        required.project("crafttweaker")
    }
}
