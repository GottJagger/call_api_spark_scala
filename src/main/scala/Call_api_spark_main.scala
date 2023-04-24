import org.apache.spark.sql.SparkSession
import org.json4s._
import org.json4s.jackson.JsonMethods._

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

    val cantidadLogs = 100
    val latencia = 20.5
    val cantidadGuardado = 50

    val json = JObject(
      "cantidadLogs" -> JInt(cantidadLogs),
      "Latencia" -> JDouble(latencia),
      "cantidadGuardado" -> JInt(cantidadGuardado)
    )
    val jsonString = compact(render(json))

    //parametros de entrada
    val url1 = "http://127.0.0.1:5000/mi_api"
    val url2 = "https://us-central1-wb-streaming-2.cloudfunctions.net/prueba_conexion_3"

    //transformaciones al df
    val dfWithSaysColumn = Transformaciones_udf.addSaysColumn(df, "name", "Hola")
    val dfWithRenamedColumn = Transformaciones_udf.renameColumn(dfWithSaysColumn, "age", "edad")

    println("DF original:")
    df.show()

    println("DF con columna 'columna name con jose dice Hola':")
    dfWithSaysColumn.show()

    println("DF con columna renombrada:")
    dfWithRenamedColumn.show()

    httpRequest.postToApi(url2,null,jsonString)
  }
}

