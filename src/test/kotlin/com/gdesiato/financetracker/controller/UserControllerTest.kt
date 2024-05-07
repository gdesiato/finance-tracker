package com.gdesiato.financetracker.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.gdesiato.financetracker.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `createUser should return created user`() {
        val uniqueEmail = generateUniqueEmail()
        val newUserJson = """
            {
                "email": "$uniqueEmail",
                "password": "password123"
            }
        """.trimIndent()

        mockMvc.perform(post("/api/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(newUserJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.email").value(uniqueEmail))
            .andDo(print())
    }

    @Test
    fun `deleteUser should remove user and return appropriate status`() {
        // Given
        val uniqueEmail = generateUniqueEmail()
        val createdUser = userService.createUser(uniqueEmail, "password123")
        val userId = createdUser.id

        // When
        mockMvc.perform(delete("/api/user/$userId"))
            .andExpect(status().isOk)

        // Then
        mockMvc.perform(get("/api/user/$userId"))
            .andExpect(status().isNotFound())
    }

    @Test
    fun `deleteNonExistentUser should return not found`() {
        mockMvc.perform(delete("/api/user/9999"))
            .andExpect(status().isNotFound)
            .andExpect(content().string("User not found"))
    }

    @Test
    fun `getUserById should return user when user exists`() {
        // Given
        val uniqueEmail = generateUniqueEmail();
        val createdUser = userService.createUser(uniqueEmail, "password123")
        val userId = createdUser.id

        // When & Then
        mockMvc.perform(get("/api/user/$userId"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(createdUser.id))
            .andExpect(jsonPath("$.email").value(createdUser.email))
    }

    @Test
    fun `getUserById should return not found when user does not exist`() {

        mockMvc.perform(get("/api/user/999"))
            .andExpect(status().isNotFound())
    }

    @Test
    fun `updateUser should update user and return updated info when user exists`() {

        val uniqueEmail = generateUniqueEmail();
        val existingUser = userService.createUser(uniqueEmail, "password123")
        val updatedInfo = existingUser.copy(email = "updated${existingUser.email}")

        mockMvc.perform(put("/api/user/${existingUser.id}")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedInfo)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(existingUser.id))
            .andExpect(jsonPath("$.email").value(updatedInfo.email))
    }

    @Test
    fun `updateUser should return not found when user does not exist`() {

        val uniqueEmail = generateUniqueEmail();
        val existingUser = userService.createUser(uniqueEmail, "password123")
        val nonExistentUserId = existingUser.id + 999
        val updateAttempt = existingUser.copy(email = "nonexistent${existingUser.email}")

        mockMvc.perform(put("/api/user/$nonExistentUserId")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateAttempt)))
            .andExpect(status().isNotFound())
    }


    private fun generateUniqueEmail(): String {
        return "user${UUID.randomUUID().toString()}@example.com"
    }
}
