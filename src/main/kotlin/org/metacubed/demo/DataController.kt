package org.metacubed.demo

import com.toedter.spring.hateoas.jsonapi.JsonApiId
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/data", produces = ["application/vnd.api+json"])
class DataController {

    @GetMapping("/{id}")
    fun getData(@PathVariable id: UUID): EntityModel<Data> =
        EntityModel.of(Data(id, "name-1", "description-1"))
            .add(linkTo<DataController> { getData(id) }.withSelfRel())
}

@JsonApiTypeForClass("Data")
data class Data(@JsonApiId val id: UUID, val name: String, val description: String)
