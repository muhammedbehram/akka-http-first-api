package com.company.service

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.company.model.Person
import com.typesafe.scalalogging.LazyLogging
import spray.json._

import scala.util.{Failure, Success}
class RestService(dbService: DbService) extends SprayJsonSupport with DefaultJsonProtocol with LazyLogging {
  val route: Route = get {
    path("people") {
      complete(dbService.getAllPeople)
    } ~ path("person" / LongNumber) { personId =>
      complete(dbService.getPerson(personId))
    }
  } ~
    post {
      path("person") {
        entity(as[Person]) { person =>
          val saved = dbService.insertPerson(person.name, person.surname)
          onComplete(saved) {
            case Success(savedPerson) => complete(savedPerson)
            case Failure(e) => {
              logger.error(s"Failed to insert a person ${person.id}", e)
              complete(StatusCodes.InternalServerError)
            }
          }
        }
      }
    } ~
    put {
      path("person" / LongNumber) { id =>
        entity(as[Person]) { person =>
          val updated = dbService.update(id, person.name, person.surname)
          onComplete(updated) {
            case Success(updatedRows) => complete(JsObject("updatedRows" -> JsNumber(updatedRows)))
            case Failure(e) => {
              logger.error(s"Failed to update a person ${id}", e)
              complete(StatusCodes.InternalServerError)
            }
          }
        }
      }
    }
}
