ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.15"

val spark_version ="2.4.0"

lazy val root = (project in file("."))
  .settings(
    name := "call_api_Spark_2.4.0",
    //librerias sbt
    // https://mvnrepository.com/artifact/org.apache.spark/spark-core
    libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.0",
    // https://mvnrepository.com/artifact/org.apache.spark/spark-streaming
    libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.0" ,
    // https://mvnrepository.com/artifact/org.apache.spark/spark-sql
    libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.0",
    // https://mvnrepository.com/artifact/commons-io/commons-io
    libraryDependencies += "commons-io" % "commons-io" % "2.11.0",
    // https://mvnrepository.com/artifact/org.apache.httpcomponents.client5/httpclient5
    libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.4.2",
    libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.12.0",
    libraryDependencies += "com.squareup.okhttp3" % "okhttp" % "4.9.0",

  )
