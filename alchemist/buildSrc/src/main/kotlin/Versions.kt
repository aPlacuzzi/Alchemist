import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val logback_classic: String = "1.3.0-alpha4" 

    const val caffeine: String = "2.6.2" // available: "2.7.0"

    const val com_github_cb372: String = "0.9.3" // available: "0.27.0"

    const val rtree: String = "0.8.6" 

    const val com_github_spotbugs_gradle_plugin: String = "1.6.9" // available: "2.0.0"

    const val spotbugs: String = "3.1.10" // available: "3.1.12"

    const val gson: String = "2.8.5" 

    const val guava: String = "26.0-jre" // available: "27.1-jre"

    const val concurrentlinkedhashmap_lru: String = "1.4.2" 

    const val com_gradle_build_scan_gradle_plugin: String = "2.1" // available: "2.3"

    const val com_graphhopper: String = "0.12.0" // available: "0.13.0-pre2"

    const val simplelatlng: String = "1.3.1" 

    const val com_jfrog_bintray_gradle_plugin: String = "1.8.4" 

    const val miglayout_swing: String = "5.0" // available: "5.2"

    const val commons_cli: String = "1.4" 

    const val commons_codec: String = "1.10" // available: "1.12"

    const val commons_io: String = "2.6" 

    const val javafxsvg: String = "1.3.0" 

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2" 

    const val classgraph: String = "4.4.12" // available: "4.8.36"

    const val jpx: String = "1.4.0" 

    const val kotlintest_runner_junit5: String = "3.3.2" 

    const val scafi_core_2_12: String = "0.3.2" // available: "53ddebd1"

    const val junit: String = "4.12" 

    const val trove4j: String = "3.0.3" 

    const val org_antlr: String = "4.6" // available: "4.7.2"

    const val commons_lang3: String = "3.8.1" // available: "3.9"

    const val commons_math3: String = "3.6.1" 

    const val org_apache_ignite: String = "2.6.0" // available: "2.7.0"

    const val groovy: String = "2.5.5" // available: "2.5.7"

    const val controlsfx: String = "9.0.0" // available: "11.0.0"

    const val org_danilopianini_javadoc_io_linker_gradle_plugin: String = "0.1.4" 
            // available: "0.1.4-700fdb6"

    const val org_danilopianini_publish_on_central_gradle_plugin: String = "0.1.1" 

    const val boilerplate: String = "0.2.0" 

    const val gson_extras: String = "0.2.1" 

    const val java_quadtree: String = "0.1.2" 

    const val javalib_java7: String = "0.6.1" 

    const val jirf: String = "0.1.4" 

    const val listset: String = "0.2.4" 

    const val thread_inheritable_resource_loader: String = "0.3.0" 

    const val urlclassloader_util: String = "0.1.0" // available: "0.1.1"

    const val org_jetbrains_dokka_gradle_plugin: String = "0.9.17" // available: "0.9.18"

    const val org_jetbrains_kotlin_jvm_gradle_plugin: String = "1.3.0" // available: "1.3.31"

    const val org_jetbrains_kotlin: String = "1.3.0" // available: "1.3.31"

    const val annotations: String = "16.0.3" // available: "17.0.0"

    const val jgrapht_core: String = "1.1.0" // available: "1.3.0"

    const val jool_java_8: String = "0.9.14" 

    const val mapsforge_map_awt: String = "0.6.1" // available: "0.11.0"

    const val org_openjfx: String = "11" // available: "13-ea+7"

    const val pegdown: String = "1.6.0" 

    const val org_protelis: String = "12.0.0" 

    const val org_scala_lang: String = "2.12.2" // available: "2.13.0-M5-1775dba"

    const val scalatest_2_12: String = "3.0.1" // available: "3.2.0-SNAP10"

    const val slf4j_api: String = "1.8.0-beta2" 

    const val snakeyaml: String = "1.23" // available: "1.24"

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.4.1"

        const val currentVersion: String = "5.4.1"

        const val nightlyVersion: String = "5.5-20190516000042+0000"

        const val releaseCandidate: String = ""
    }
}