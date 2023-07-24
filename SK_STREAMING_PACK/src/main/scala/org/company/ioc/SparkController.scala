package org.siltron.ioc
import org.siltron.ioc.model.SparkModel

class SparkController {

  def sparkStreamingStarter(app_name: String):Unit = {

    val spark = new SparkModel

    val sparkStreamingSession = spark.createSession(app_name)
    val sparkSqlSession = spark.createSession("adhoc-session")

    val sparkStreaming = spark.kafkaReadStreamSession(sparkStreamingSession)

    val sparkJsonParsed = spark.kafkaKeyValueParser(sparkStreaming)

    spark.kafkaStreamingStarter(sparkJsonParsed, sparkSqlSession)
  }

}
