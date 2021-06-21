package org.svetikov.webclient01.service


import org.svetikov.webclient01.model.Person

interface ServicePerson {
    fun findById(id:Int):Person
}