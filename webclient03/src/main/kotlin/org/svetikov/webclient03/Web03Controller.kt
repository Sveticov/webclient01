package org.svetikov.webclient03

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Web03Controller {

    @GetMapping("/per/test")
    fun perTest()="test pe"
}