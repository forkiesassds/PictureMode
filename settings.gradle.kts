@file:Suppress("LocalVariableName")

dependencyResolutionManagement {
    repositories {
        maven("https://maven.kikugie.dev/snapshots")
    }

    versionCatalogs {
        create("ft") { from("dev.kikugie.fletching-table:fletching-table.catalog:0.2.0-alpha.7") }
    }
}

pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        maven {
            name = "Architectury"
            url = uri("https://maven.architectury.dev/")
        }
        maven {
            name = "NeoForged"
            url = uri("https://maven.neoforged.net/releases")
        }
        maven {
            url = uri("https://maven.kikugie.dev/releases")
        }
        maven {
            url = uri("https://maven.kikugie.dev/snapshots")
        }
        gradlePluginPortal()
    }

    val loomVer = extra["loom_version"] as String
    val mdgVer = extra["mdg_version"] as String
    val stonecutterVer = extra["stonecutter_version"] as String
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("net.fabricmc.fabric-loom")) {
                useVersion(loomVer)
            }

            if (requested.id.id.startsWith("net.neoforged.moddev")) {
                useVersion(mdgVer)
            }

            if (requested.id.id == "dev.kikugie.stonecutter") {
                useVersion(stonecutterVer)
            }
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver") version "1.0.0"
    id("dev.kikugie.stonecutter")
}

toolchainManagement {
    jvm {
        javaRepositories {
            repository("foojay") {
                resolverClass.set(org.gradle.toolchains.foojay.FoojayToolchainResolver::class.java)
            }
        }
    }
}

stonecutter {
    kotlinController = true
    centralScript = "build.gradle.kts"

    create(getRootProject()) {
        versions("1.20.1", "1.21.1", "1.21.11")
        versions("26.1", "26.2", "26.3", "26.4").buildscript("build-unobf.gradle.kts")
        branch("fabric")
        branch("forgelike") {
            versions("1.20.1").buildscript("build-lexforge.gradle.kts")
            versions("1.21.1", "1.21.11", "26.1", "26.2", "26.3")
        }

        vcsVersion = "1.21.11"
    }
}