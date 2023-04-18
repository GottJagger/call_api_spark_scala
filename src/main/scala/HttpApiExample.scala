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

    // Detiene la sesión de Spark
    spark.stop()
  }
}
