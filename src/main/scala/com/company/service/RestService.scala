package com.company.service

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.company.model.Person
import spray.json.DefaultJsonProtocol

import scala.util.{Failure, Success}
class RestService(dbService: DbService) extends SprayJsonSupport with DefaultJsonProtocol {
  val route: Route = get {
    path("people") {
      complete(dbService.getAllPeople)
    }
  } ~
    post {
      path("person") {
        entity(as[Person]) { person =>
          val saved = dbService.insertPerson(person.name, person.surname)
          onComplete(saved) {
            case Success(_) => complete("Done")
            case Failure(_) => complete(StatusCodes.InternalServerError)
          }
        }
      }
    } ~
    put {
      path("person" / LongNumber) { id =>
        entity(as[Person]) { person =>
          val updated = dbService.update(id, person.name, person.surname)
          onComplete(updated) {
            case Success(_) => complete("Done")
            case Failure(_) => complete(StatusCodes.NotFound)
          }
        }
      }
    }
}
