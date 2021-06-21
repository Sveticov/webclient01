package org.svetikov.webclient01.service

import org.springframework.stereotype.Service
import org.svetikov.webclient01.model.Person

@Service
class ServicePersonImpl : ServicePerson {
    val persons = mutableListOf<Person>(
        Person(1, "Harry"),
        Person(2, "Emma"),
        Person(3, "Georg")
    )

    override fun findById(id: Int): Person {
        return persons.first { it.id == id }
    }
}