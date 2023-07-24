package org.company.di.model

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.security.alias.CredentialProviderFactory
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.company.di.model.ConfigModel

object SparkModel{

  /**
   * Hadoop Credential 을 적용시킨 Oracle 비밀번호를 가져오는 함수 입니다.
   * @return String
   */
  private def read_oracle_password(): String = {
    val conf_H: Configuration = new Configuration()
    val alias = ConfigModel.ORACLE_JCEKS_ALIAS
    val jceksPath = ConfigModel.ORACLE_JCEKS
    conf_H.set(CredentialProviderFactory.CREDENTIAL_PROVIDER_PATH, jceksPath)
    val pass = conf_H.getPassword(alias).mkString

    pass
  }

  /**
   * Oracle DB 에 Spark 을 활용하여 질의를 날리는 함수 입니다.
   * @param spark SparkSession
   * @param sql 사용자가 조회하고 싶은 SQL
   * @return DataFrame
   */
  def oracle_select(spark: SparkSession, sql: String) = {
    spark.read
      .format("jdbc")
      .option("url", ConfigModel.ORACLE_JDBC_URL)
      .option("user", ConfigModel.ORACLE_USER)
      .option("password", this.read_oracle_password())
      .option("query", sql)
      .load()
  }

  /**
   * Oracle DB 에 Spark 을 활용하여 Insert 를 수행하는 함수 입니다.
   * @param df Insert 를 원하는 Spark.DataFrame
   * @param schema Insert 를 원하는 Target Table schema
   * @param table Insert 를 원하는 Target Table 이름
   */
  def oracle_append(df:DataFrame, url:String, user:String, schema: String, table: String):Unit = {
    df.write
      .format("jdbc")
      .option("truncate", "false")
      .option("url", url)
      .option("user", user)
      .option("password", this.read_oracle_password())
      .option("dbtable", f"$schema%s.$table%s")
      .mode("append")
      .save()
  }

  /**
   * hive 테이블에 Insert 하는 함수입니다.
   * 파티션 키를 지정합니다.
   * @param df Insert 를 원하는 Spark.DataFrame
   * @param partitionKey 파티션 키
   * @param schema Insert 를 할 DB 명
   * @param table Insert 를 할 table 명
   */
  def hiveAppend(df: DataFrame, partitionKey:String, schema: String, table: String): Unit = {
    df.write
      .partitionBy(partitionKey)
      .format("hive")
      .mode("append")
      .saveAsTable(f"$schema%s.$table%s")
  }


}
