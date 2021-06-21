package org.svetikov.webclient01.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.svetikov.webclient01.model.Person
import org.svetikov.webclient01.service.ServicePerson
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@RestController
class PersonController(private val servicePerson: ServicePerson) {



    @GetMapping("/person/{id}")
    fun findByIdPerson(@PathVariable id: Int): Mono<Person> {
        return Mono.just( servicePerson.findById(id))
    }

//    @GetMapping("/web")
//    fun web(): Person? {
//    val body = client
//        .get()
//        .uri("http://localhost:8080/person/2")
//        .retrieve()
//        .bodyToMono(Person::class.java)
//        .block()
//        return body?: throw Exception("problem")
//    }


}