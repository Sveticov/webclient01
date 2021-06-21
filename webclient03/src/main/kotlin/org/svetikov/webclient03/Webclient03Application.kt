package org.svetikov.webclient03

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.http.MediaType.*
import org.springframework.stereotype.Service

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body


import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@SpringBootApplication
class Webclient03Application()

fun main(args: Array<String>) {
    val servicePerson = ServicePersonImpl()
    runApplication<Webclient03Application>(*args) {
        addInitializers(
            beans {
                bean {
                    router {
                        accept(TEXT_PLAIN).nest {
                            GET("/person/{name}", ::hello)
                            GET("/person2") { ok().body(Mono.just("Hello")) }
                        }
                        accept(APPLICATION_JSON).nest {
                            GET("/person3", ::getPerson)
                        }
                        "/app".nest {
                            accept(APPLICATION_JSON).nest {
                                GET("/person/{id}", servicePerson::findIdPerson)
                                GET("/persons", servicePerson::findAll)
                                POST("/person/add", servicePerson::savePerson)
                            }
                        }

                    }
                }

                bean {
                    TestBean().printTestBean("test")
                }
            })
    }

}

fun hello(request: ServerRequest) =
    ServerResponse.ok().body(Mono.just("test ${request.pathVariable("name")}"))

fun getPerson(request: ServerRequest) =
    ServerResponse.ok().body(Mono.just(Person(1, "Emma")))

data class Person(val id: Int, val name: String)


class TestBean() {
    fun printTestBean(name: String) {
        println("this name $name")

    }
}

@Service
class ServicePersonImpl : ServicePerson {

    val persons = mutableListOf<Person>(
        Person(1, "Harry"),
        Person(2, "Germeona"),
        Person(3, "Volandemort")
    )
    val personF = Flux.just(
        Person(1, "Harry"),
        Person(2, "Germeona"),
        Person(3, "Volandemort")
    )

    override fun findAll(request: ServerRequest) =
        ServerResponse.ok().body(Mono.just(persons))


    override fun findIdPerson(request: ServerRequest) =
        ServerResponse.ok().body(Mono.just(persons.first { it.id == request.pathVariable("id").toInt() })) //


    fun savePerson(request: ServerRequest) =
        request.bodyToMono(Person::class.java)
            .flatMap(::savePersonLoc)
            .onErrorResume { ServerResponse.ok().body(Mono.just("error")) }


    private fun savePersonLoc(person: Person) =
        ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(persons.add(person), Person::class.java)


}

interface ServicePerson {
    fun findAll(request: ServerRequest): Mono<ServerResponse>
    fun findIdPerson(request: ServerRequest): Mono<ServerResponse>
//    fun savePerson(request: ServerRequest):Per

}




