
import org.apache.spark.sql.functions._
import org.apache.spark.sql.DataFrame

object Transformaciones_udf {

  // Función que agrega una nueva columna con la transformación "jose dice" a un DataFrame dado
  def addSaysColumn(df: DataFrame, columnName: String, Text: String): DataFrame = {
    // UDF que agrega "jose dice" más el texto especificado al inicio del valor de una columna
    val addSays: String => String = (value: String) => s"jose dice $Text $value"
    val addJoseSaysUdf = udf(addSays)

    df.withColumn(s"$columnName with $Text Says", addJoseSaysUdf(col(columnName)))
  }


  // Función que filtra un DataFrame según un valor dado en una columna dada
  def filterByValue(df: DataFrame, columnName: String, value: String): DataFrame = {
    df.filter(col(columnName) === value)
  }

  // Función que renombra una columna de un DataFrame dado
  def renameColumn(df: DataFrame, oldColumnName: String, newColumnName: String): DataFrame = {
    df.withColumnRenamed(oldColumnName, newColumnName)
  }
}

