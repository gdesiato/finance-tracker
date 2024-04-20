package com.gdesiato.financetracker.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `createUser should return created user`() {
        val uniqueEmail = generateUniqueEmail()
        val newUserJson = """
            {
                "email": "$uniqueEmail",
                "password": "password123"
            }
        """.trimIndent()

        mockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(newUserJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.email").value(uniqueEmail))
            .andDo(print())
    }

    private fun generateUniqueEmail(): String {
        return "user${UUID.randomUUID().toString()}@example.com"
    }
}
