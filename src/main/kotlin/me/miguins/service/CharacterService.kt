package me.miguins.service

import me.miguins.model.Character
import me.miguins.model.NewCharacterRequest
import me.miguins.model.UpdateCharacterRequest
import java.util.*

interface CharacterService {

    fun create(character: NewCharacterRequest): Character

    fun findById(id: Long): Optional<Character>

    fun listAll(): List<Character>

    fun update(character: Character): Character
}