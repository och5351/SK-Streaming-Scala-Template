package org.company.di.db.table.dao

import org.company.di.db.table.vo.Table_vo

trait Table_dao {
  @throws[Exception]
  def select(temp: String): Table_vo
}
