package org.company.di.db.table.dao

import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}
import org.company.di.model.ConfigModel

abstract class Table_abstract_dao {
  var conn: Connection = null
  val pstmt: PreparedStatement = null
  val rs: ResultSet = null

  def execute(): Unit = {
    try {
      init()
      query()
      close()
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }

  @throws[Exception]
  private def init(): Unit = {
    Class.forName("oracle.jdbc.driver.OracleDriver")
    conn = DriverManager.getConnection(ConfigModel.ORACLE_JDBC_URL, ConfigModel.ORACLE_USER, ConfigModel.ORACLE_PASSWORD)
  }

  @throws[Exception]
  def query(): Unit

  private def close(): Unit = {
    if (rs != null) try rs.close
    catch {
      case e: Exception =>

    }
    if (pstmt != null) try rs.close
    catch {
      case e: Exception =>

    }
    if (conn != null) try rs.close
    catch {
      case e: Exception =>

    }
  }
}
