package me.miguins.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import me.miguins.model.CharacterResponse
import me.miguins.model.NewCharacterRequest
import me.miguins.service.CharacterService
import javax.validation.Valid

@Validated
@Controller("/api/v1/characters")
class CharacterController(private val characterService: CharacterService) {

    @Post
    fun create(@Body @Valid request: NewCharacterRequest): HttpResponse<Any> {

        val character = characterService.create(request)

        val location = HttpResponse.uri("/api/v1/characters/${character.id}")

        return HttpResponse.created(location)
    }

    @Get("/{id}")
    fun find(@PathVariable id: Long): HttpResponse<Any> {

        val possibleCharacter = characterService.findById(id)

        if (possibleCharacter.isEmpty) {
            return HttpResponse.notFound()
        }

        return HttpResponse.ok(CharacterResponse(possibleCharacter.get()))
    }
}