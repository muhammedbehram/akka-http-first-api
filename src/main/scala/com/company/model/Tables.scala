package com.company.model

import slick.jdbc.PostgresProfile.api._

object Tables {
  class People(tag: Tag) extends Table[Person](tag, "person_list") {
    def id = column[Option[Long]]("person_id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("person_name")

    def surname = column[String]("person_surname")

    def * =
      (id, name, surname) <> ((Person.apply _).tupled, Person.unapply)
  }
  val people = TableQuery[People]

}
