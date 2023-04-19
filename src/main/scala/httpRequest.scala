
import scalaj.http.{Http, HttpResponse}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.spark.sql.DataFrame

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
  def postToApi(url: String, message: String, inputDf: DataFrame): Unit = {
    // Convierte el DataFrame de entrada en un JSON
    val jsonInput = inputDf.toJSON.collect.mkString("[", ",", "]")
    val mapper = new ObjectMapper().registerModule(DefaultScalaModule)
    val jsonObject = mapper.readTree(jsonInput)

    // Realiza una solicitud POST a la API con el JSON como cuerpo
    val response: HttpResponse[String] = Http(url)
      .header("Content-Type", "application/json")
      .postData(jsonObject.toString)
      .param("message", message)
      .method("POST")
      .asString
    println("Metodo POST request enviada del cliente")


    // Imprime la respuesta del servidor
    println(response.body)

  }
}