package org.company.di.model

object ConfigModel {

  private val _APP_NAME = "DIP_SINK_SPARK_SCALA_TEST"
  private val _BOOTSTRAP_SERVER = "host1:port1 host2:port2 host3:port3"
  private val _SINK_TOPIC = "topic-01"
  private val _SINK_FREQUENCY = "1 minute"
  private val _ORACLE_JCEKS = "jceks://hdfs/user/hadoop/MY_ORACLE_PASSWORD.jceks"
  private val _ORACLE_JCEKS_ALIAS = "my_oracle.pass"
  private val _ORACLE_JDBC_URL = "jdbc:oracle:thin:@IP:PORT:SID"
  private val _ORACLE_USER = "MYUSER"
  private val _ORACLE_PASSWORD = "MYPASS"

  def APP_NAME:String = this._APP_NAME
  def BOOTSTRAP_SERVER:String = this._BOOTSTRAP_SERVER
  def SINK_TOPIC:String = this._SINK_TOPIC
  def SINK_FREQUENCY:String = this._SINK_FREQUENCY
  def ORACLE_JCEKS:String = this._ORACLE_JCEKS
  def ORACLE_JCEKS_ALIAS:String = this._ORACLE_JCEKS_ALIAS
  def ORACLE_JDBC_URL:String = this._ORACLE_JDBC_URL
  def ORACLE_USER:String = this._ORACLE_USER
  def ORACLE_PASSWORD = this._ORACLE_PASSWORD

}
