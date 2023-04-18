import org.apache.spark.sql.functions._
import org.apache.spark.sql._
import scalaj.http.{Http, HttpResponse}
import org.apache.spark.sql.{SaveMode, SparkSession}

object HttpApiExample {
  def main(args: Array[String]): Unit = {
    // Crea una sesión de Spark
    val spark = SparkSession.builder()
      .master("local")
      .getOrCreate()
    import spark.implicits._


    // Realiza una llamada a la API
    val url = "http://localhost:8080/mi_api"
    val response: HttpResponse[String] = Http(url)
      .header("Content-Type", "application/json").asString
    val responseBody: String = response.body

    // Convierte los datos JSON en un DataFrame de Spark

    val ds: Dataset[String] = Seq(responseBody).toDS()
    val df: DataFrame = spark.read.json(ds)
    df.show(false)
    // Aplica una UDF al DataFrame

    //agregar un uno
    val addOne = udf((x: Int) => x + 1)
    val dfWithNewColumn = df.withColumn("messagePlusOne", addOne(col("message")))

    //agrega jose dice:
    val addJoseSaysHolaMundo = udf((datos: String) => s"jose dice $datos")
    val dfWithNewColumn2 = dfWithNewColumn.withColumn("datosConJose", addJoseSaysHolaMundo(col("datos")))

    // Muestra el DataFrame en la consola
    dfWithNewColumn2.show(false)

    // Detiene la sesión de Spark
    spark.stop()
  }
}
