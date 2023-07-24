package org.company.di.db.table.dao

import org.company.di.db.table.vo.Table_vo

class Table_impl extends Table_vo_dao{
  val sql = new StringBuffer
  val table_vo = new Table_vo_vo

  @Override
  def select(temp: String): Table_vo = {
    sql.append("SELECT ")
    sql.append("* ")
    sql.append("FROM SCHEMA.TABLE A ")
    sql.append("WHERE 1=1 ")
    sql.append("AND A.ID = '2100' ")
    sql.append("AND A.PASSWD = '1234' ")
    sql.append("AND A.TEMP = ?")
    new Table_abstract_dao {
      override def query(): Unit = {
        val pstmt = conn.prepareStatement(sql.toString)
        pstmt.setString(1, temp)
        val rs = pstmt.executeQuery
        while (rs.next) table_vo.setIngotId(rs.getString(1))
      }
    }.execute

    table_vo
  }


}
