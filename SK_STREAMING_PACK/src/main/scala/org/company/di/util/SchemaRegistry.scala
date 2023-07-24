package org.company.di.util

import org.apache.spark.sql.types.{StringType, StructField, StructType}

object SchemaRegistry {

  def sink_schema(): StructType = {
    /*
     * 토픽에서 읽어오는 데이터의 스키마를 정의한다.
     */
    StructType(Array(
      StructField("ID", StringType),
      StructField("PASSWD", StringType)
    ))
  }

}
