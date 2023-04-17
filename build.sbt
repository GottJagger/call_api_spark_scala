ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

val spark_version ="2.4.0"

lazy val root = (project in file("."))
  .settings(
    name := "call_api_Spark_2.4.0",
    //librerias sbt
    // https://mvnrepository.com/artifact/org.apache.spark/spark-core
    libraryDependencies += "org.apache.spark" %% "spark-core" % "3.4.0",
    // https://mvnrepository.com/artifact/org.apache.spark/spark-streaming
    libraryDependencies += "org.apache.spark" %% "spark-streaming" % "3.4.0" % "provided",
    // https://mvnrepository.com/artifact/org.apache.spark/spark-sql
    libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.4.0" % "provided",
    // https://mvnrepository.com/artifact/commons-io/commons-io
    libraryDependencies += "commons-io" % "commons-io" % "2.11.0",
    // https://mvnrepository.com/artifact/org.apache.httpcomponents.client5/httpclient5
    libraryDependencies += "org.apache.httpcomponents.client5" % "httpclient5" % "5.1",
      //assembly
    ThisBuild / assemblyMergeStrategy := {
      case PathList("META-INF", xs@_*) => MergeStrategy.filterDistinctLines
      case x => MergeStrategy.first
    },
    ThisBuild / assemblyShadeRules := Seq(
      ShadeRule.rename("com.**" -> "shadegoogle.@1").inAll
    ),
    assembly / assemblyJarName := "call_api_spark"

  )
