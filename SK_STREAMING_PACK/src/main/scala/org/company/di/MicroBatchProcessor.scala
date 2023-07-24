package org.company.di

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.company.di.util.{Preprocessor, SchemaRegistry}

class Processor {
  /**
   * 의존성 주입 메인 함수
   * @param df 카프카 배치로 consume 하는 데이터
   * @param batch_id 카프카로 부터 consume 한 배치 번호
   * @param spark 새로운 스파크 처리를 위한 세션
   */
  def batch(df: DataFrame, batch_id: Long, spark: SparkSession): Unit = {

    df.show()

  }
}