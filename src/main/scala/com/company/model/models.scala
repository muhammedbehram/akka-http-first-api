package com.company.model

import spray.json.DefaultJsonProtocol

case class Person(id: Option[Long], name: String, surname: String)

object Person extends DefaultJsonProtocol {
  implicit val personFormat = jsonFormat3(Person.apply)
}
