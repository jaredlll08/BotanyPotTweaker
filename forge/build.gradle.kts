import com.blamejared.botanypotstweaker.gradle.Properties
import com.blamejared.botanypotstweaker.gradle.Versions
import com.blamejared.gradle.mod.utils.GMUtils
import net.darkhax.curseforgegradle.TaskPublishCurseForge
import net.darkhax.curseforgegradle.Constants as CFG_Constants

plugins {
    id("com.blamejared.botanypotstweaker.default")
    id("com.blamejared.botanypotstweaker.loader")
    id("net.neoforged.moddev.legacyforge")
    id("com.modrinth.minotaur")
}

legacyForge {
    version = "${Versions.MINECRAFT}-${Versions.FORGE}"

    validateAccessTransformers = true

    runs {
        register("client") {
            client()
        }
        register("server") {
            server()
        }
    }

    mods {
        register(Properties.MODID) {
            sourceSet(sourceSets.main.get())
        }
    }
}


dependencies {
    implementation(project(":common"))

    val crt = "com.blamejared.crafttweaker:CraftTweaker-forge-${Versions.MINECRAFT}:${Versions.CRAFTTWEAKER}"
    modImplementation(crt)
    annotationProcessor("com.blamejared.crafttweaker:Crafttweaker_Annotation_Processors:${Versions.CRAFTTWEAKER_ANNOTATION_PROCESSOR}")
    annotationProcessor(crt)

    modImplementation("net.darkhax.bookshelf:Bookshelf-Forge-${Versions.MINECRAFT}:${Versions.BOOKSHELF}")
    modImplementation("net.darkhax.botanypots:BotanyPots-Forge-${Versions.MINECRAFT}:${Versions.BOTANYPOTS}")
}

tasks.create<TaskPublishCurseForge>("publishCurseForge") {
    apiToken = GMUtils.locateProperty(project, "curseforgeApiToken") ?: 0

    val mainFile = upload(Properties.CURSE_PROJECT_ID, file("${project.buildDir}/libs/${base.archivesName.get()}-$version.jar"))
    mainFile.changelogType = "markdown"
    mainFile.changelog = GMUtils.smallChangelog(project, Properties.GIT_REPO)
    mainFile.releaseType = CFG_Constants.RELEASE_TYPE_RELEASE
    mainFile.addJavaVersion("Java ${Versions.JAVA}")
    mainFile.addRequirement("crafttweaker")

    doLast {
        project.ext.set("curse_file_url", "${Properties.CURSE_HOMEPAGE}/files/${mainFile.curseFileId}")
    }
}

modrinth {
    token.set(GMUtils.locateProperty(project, "modrinth_token"))
    projectId.set(Properties.MODRINTH_PROJECT_ID)
    changelog.set(GMUtils.smallChangelog(project, Properties.GIT_REPO))
    versionName.set("Forge-${Versions.MINECRAFT}-$version")
    versionType.set("release")
    uploadFile.set(tasks.jar.get())
    dependencies {
        required.project("crafttweaker")
    }
}

tasks.jar {
    finalizedBy("reobfJar")
}