import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "runkeeper-client-test"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "com.github.codingricky" % "runkeeper-client" % "0.1-SNAPSHOT"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
    resolvers += "Sonatype" at "https://oss.sonatype.org/content/repositories/snapshots/"

  )

}
