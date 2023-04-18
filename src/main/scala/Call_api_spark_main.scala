import org.apache.spark.sql.SparkSession
class Call_api_spark_main {
  def main(args: Array[String]): Unit =  {
    val spark = SparkSession.builder()
      .master("local")
      .getOrCreate()
    import spark.implicits._

    // Define un DataFrame de prueba
    val df = Seq(
      ("John", 25),
      ("Lucy", 30),
      ("Peter", 45)
    ).toDF("name", "age")

    val url = "http://localhost:8080/mi_api"
    val messageGet = "Hello from GET request!"
    val messagePost = "Hello from POST request!"

    val dfWithSaysColumn = Transformaciones_udf.addSaysColumn(df, "name", "Hola")
    val dfWithRenamedColumn = Transformaciones_udf.renameColumn(dfWithSaysColumn, "age", "edad")

    println("DF original:")
    df.show()

    println("DF con columna 'name with Hola Says':")
    dfWithSaysColumn.show()

    println("DF con columna renombrada:")
    dfWithRenamedColumn.show()

    val responseGet = httpRequest.getFromApi(url, messageGet, dfWithRenamedColumn)
    println("Respuesta de GET request:")
    println(responseGet)

    val responsePost = httpRequest.postToApi(url, messagePost, dfWithRenamedColumn)
    println("Respuesta de POST request:")
    println(responsePost)

  }
}

