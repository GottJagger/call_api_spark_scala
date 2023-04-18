/*
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.api.java.UDF1
import org.apache.spark.sql.functions.{col, udf, from_json, explode}
import org.apache.spark.sql.types.{ArrayType, IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql._
object Main {
  def main(args: Array[String]): Unit = {
    val appName: String = "main-app-test"
    val spark = SparkSession
      .builder()
      .appName(appName)
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val executeRestApiUDF = udf(new UDF1[String, String] {
      override def call(url: String) = {
        val httpRequest = new HttpRequest;
        httpRequest.ExecuteHttpGet(url).getOrElse("")
      }
    }, StringType)
  }
  case class RestAPIRequest (url: String)

  val restApiCallsToMake = Seq(RestAPIRequest("https://vpic.nhtsa.dot.gov/api/vehicles/getallmakes?format=json"))
  val source_df = restApiCallsToMake.toDF()

  // Define the schema used to format the REST response.  This will be used by from_json
  val restApiSchema = StructType(List(
    StructField("Count", IntegerType, true),
    StructField("Message", StringType, true),
    StructField("SearchCriteria", StringType, true),
    StructField("Results", ArrayType(
      StructType(List(
        StructField("Make_ID", IntegerType, true),
        StructField("Make_Name", StringType, true)
      ))
    ), true)
  ))
}

*/