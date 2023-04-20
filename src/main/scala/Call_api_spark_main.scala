import org.apache.spark.sql.SparkSession
object Call_api_spark_main {
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

    //parametros de entrada
    val url = "https://us-central1-wb-streaming-2.cloudfunctions.net/open_4"
    val messageGet = "Hello from GET request!"
    val messagePost = "Hello from POST request!"

    //transformaciones al df
    val dfWithSaysColumn = Transformaciones_udf.addSaysColumn(df, "name", "Hola")
    val dfWithRenamedColumn = Transformaciones_udf.renameColumn(dfWithSaysColumn, "age", "edad")

    println("DF original:")
    df.show()

    println("DF con columna 'columna name con jose dice Hola':")
    dfWithSaysColumn.show()

    println("DF con columna renombrada:")
    dfWithRenamedColumn.show()

    httpRequest.postToApi(url, messagePost, dfWithRenamedColumn)



  }
}

