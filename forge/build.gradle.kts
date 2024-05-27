import com.blamejared.botanypotstweaker.gradle.Properties
import com.blamejared.botanypotstweaker.gradle.Versions
import com.blamejared.gradle.mod.utils.GMUtils
import net.darkhax.curseforgegradle.TaskPublishCurseForge
import net.darkhax.curseforgegradle.Constants as CFG_Constants

plugins {
    id("com.blamejared.botanypotstweaker.default")
    id("com.blamejared.botanypotstweaker.loader")
    id("net.minecraftforge.gradle") version ("[6.0,6.2)")
    id("org.spongepowered.mixin") version ("0.7-SNAPSHOT")
    id("com.modrinth.minotaur")
}

mixin {
    add(sourceSets.main.get(), "${Properties.MODID}.refmap.json")
    config("${Properties.MODID}.mixins.json")
    config("${Properties.MODID}.forge.mixins.json")
}

minecraft {
    mappings("official", Versions.MINECRAFT)
    accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))
    runs {
        create("client") {
            taskName("Client")
            workingDirectory(project.file("run"))
            ideaModule("${rootProject.name}.${project.name}.main")
            args("-mixin.config=${Properties.MODID}.mixins.json")
            mods {
                create(Properties.MODID) {
                    source(sourceSets.main.get())
                    source(project(":common").sourceSets.main.get())
                }
            }
        }
    }
}

dependencies {
    val mc = create("net.minecraftforge:forge:${Versions.MINECRAFT}-${Versions.FORGE}")
    "minecraft"(mc)
    implementation(project(":common"))
    annotationProcessor("org.spongepowered:mixin:0.8.5-SNAPSHOT:processor")
    val crt = "com.blamejared.crafttweaker:CraftTweaker-forge-${Versions.MINECRAFT}:${Versions.CRAFTTWEAKER}"
    implementation(fg.deobf(crt))
    annotationProcessor("com.blamejared.crafttweaker:Crafttweaker_Annotation_Processors:${Versions.CRAFTTWEAKER_ANNOTATION_PROCESSOR}")
    annotationProcessor(crt)
    annotationProcessor(mc)
    implementation(fg.deobf("net.darkhax.bookshelf:Bookshelf-Forge-${Versions.MINECRAFT}:${Versions.BOOKSHELF}"))
    implementation(fg.deobf("net.darkhax.botanypots:BotanyPots-Forge-${Versions.MINECRAFT}:${Versions.BOTANYPOTS}"))
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