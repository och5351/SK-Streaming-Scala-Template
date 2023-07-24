package org.company.di.dip_util

import org.apache.spark.sql.DataFrame
import org.company.di.db.table.dao.Table_impl
import org.company.di.model.{ConfigModel, SparkModel}

object Preprocessor {

  /**
   * 함수 생성
   *
   * @param temp 임시 변수
   * @return String
   */
  def method(temp:String):String = {
    ...
    temp
  }

  /**
   * Oracle 에 DataFrame 을 저장하는 함수
   * @param df DataFrame
   */
  def oracleSaver(df:DataFrame):Unit = {
    val schema = "MY_DB"
    val table = "MY_TABLE"
    val url = ConfigModel.ORACLE_JDBC_URL
    val user = ConfigModel.ORACLE_USER

    SparkModel.oracle_append(df, url, user, schema, table)
    
  }
}
