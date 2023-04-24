
import scalaj.http.{Http, HttpResponse}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.spark.sql.DataFrame
import org.json4s._
import org.json4s.jackson.JsonMethods._

object httpRequest{
  // Funcion get para realizar el request al servidor(API)
  def getFromApi(url: String, message: String, inputDf: DataFrame): Unit = {
    // Convierte el DataFrame de entrada en un JSON
    val jsonInput = inputDf.toJSON.collect.mkString("[", ",", "]")
    val mapper = new ObjectMapper().registerModule(DefaultScalaModule)
    val jsonObject = mapper.readTree(jsonInput)

    // Realiza una solicitud GET a la API con el JSON como cuerpo
    val response: HttpResponse[String] = Http(url)
      .header("Content-Type", "application/json")
      .param("message", message)
      .postData(jsonObject.toString)
      .method("GET")
      .asString
    println("Metodo GET request enviada del cliente")
    // Imprime la respuesta del servidor
    println(response.body)

  }
  // Funcion post para realizar el request al servidor(API)

  def postToApi(url: String, inputDf: DataFrame, inputjson: String): Unit = {
    val DataJson = if (inputjson != null) {
      // Si se proporciona inputjson, lo convierte a un árbol JSON
      new ObjectMapper().registerModule(DefaultScalaModule).readTree(inputjson)
    } else if (inputDf != null) {
      // Si se proporciona inputDf, lo convierte a JSON y lo convierte en un árbol JSON
      val jsonDf = inputDf.toJSON.collect.mkString("[", ",", "]")
      new ObjectMapper().registerModule(DefaultScalaModule).readTree(jsonDf)
    } else {
      // Si ambos son nulos, muestra un mensaje de error y sale del método
      println("Error: No se proporcionó ningún dato para enviar.")
      return
    }

    val postData = if (inputDf != null) {
      // Si se proporciona inputDf, crea un nodo JSON adicional para el JSON del DataFrame
      val jsonDf = inputDf.toJSON.collect.mkString("[", ",", "]")
      val jsonObjectDf = new ObjectMapper().registerModule(DefaultScalaModule).readTree(jsonDf)
      val jsonNode = new ObjectMapper().createObjectNode()
      jsonNode.set("jsonMetricas", DataJson)
      jsonNode.set("jsonDf", jsonObjectDf)
      jsonNode.toString
    } else {
      DataJson.toString
    }

    // Realiza una solicitud POST a la API con el JSON como cuerpo
    val response: HttpResponse[String] = Http(url)
      .header("Content-Type", "application/json")
      .postData(postData)
      .method("POST")
      .asString
    println("Método POST request enviada del cliente")

    // Imprime la respuesta del servidor
    println(response.body)
  }

}