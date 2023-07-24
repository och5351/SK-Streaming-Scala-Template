package org.company.ioc.model

import org.apache.spark.sql.functions.{col, from_json}
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.company.di.MicroBatchProcessor
import org.company.di.model.ConfigModel
import org.company.di.util.SchemaRegistry

class SparkModel {

  /**
   * Spark session 선언
   *
   * @param appName str : SparkSession의 App 이름 변수
   * @return org.apache.spark.sql.SparkSession
   */
  def createSession(appName: String): SparkSession = {
    SparkSession
      .builder
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .config("partitionoverwritemode", "dynamic")
      .appName(appName)
      .enableHiveSupport()
      .getOrCreate()
  }

  /**
   * kafka 의 지정된 클러스터, 토픽에서 stream 을 실시간으로 읽어오는 함수
   * @param spark createSession 을 통해 만든 Spark SQL Session 을 매개변수로 받는다.
   */
  def kafkaReadStreamSession(spark: SparkSession): DataFrame = {
    val BOOTSTRAP_SERVERS = ConfigModel.BOOTSTRAP_SERVER

    val TOPIC_NAME = ConfigModel.SINK_TOPIC

    spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", BOOTSTRAP_SERVERS)
      .option("subscribe", TOPIC_NAME)
      .option("startingOffsets", "earliest")
      .load()
  }

  /**
   * kafka 에서 value 를 json  테이블화 시키는 함수
   *
   * @param spark createSession 을 통해 만든 Spark SQL Session 을 매개변수로 받는다.
   * @return org.apache.spark.sql.DataFrame
   */
  def kafkaJsonValueParser(spark: DataFrame): DataFrame = {

    spark
      .selectExpr("CAST(value AS STRING)")
      .select(
        from_json(col("value"), SchemaRegistry.sink_schema())
          .alias("data")
      )
      .select("data.*")
  }

  /**
   * kafka 에서 key, value 를 테이블화 시키는 함수
   *
   * @param spark createSession 을 통해 만든 Spark SQL Session 을 매개변수로 받는다.
   * @return org.apache.spark.sql.DataFrame
   */
  def kafkaKeyValueParser(spark: DataFrame): DataFrame = {
    spark.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
  }


  /**
   * 카프카 스트리밍 실행 함수
   */
  def kafkaStreamingStarter(spark: DataFrame, sparkSession: SparkSession): Unit = {

    val microBatchprocessor = new MicroBatchProcessor

    val frequency = ConfigModel.SINK_FREQUENCY

    spark
      .writeStream
      .trigger(Trigger.ProcessingTime(frequency))
      .outputMode("append")
      .foreachBatch((batchDF: DataFrame, batchId: Long) =>
        microBatchprocessor.batch(batchDF, batchId, sparkSession)
      )
      .start()
      .awaitTermination()
  }
}
