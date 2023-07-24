ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.15"

lazy val root = (project in file("."))
  .settings(
    name := "DIP_IMAGE_SINK_SPARK"
  )

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.1.3",
  "org.apache.spark" %% "spark-sql" % "3.1.3",
  "org.apache.hadoop" % "hadoop-common" % "3.2.1",
  "org.apache.spark" %% "spark-streaming" % "3.1.3",
)

artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  "APP-0.1.0-SNAPSHOT.jar"
}
