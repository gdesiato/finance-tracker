package com.gdesiato.financetracker.service

import com.gdesiato.financetracker.model.User
import com.gdesiato.financetracker.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository) {

    private val logger = LoggerFactory.getLogger(UserService::class.java)


    fun findAllUsers(): List<User> = userRepository.findAll()

    fun findUserById(id: Long): User? = userRepository.findById(id).orElse(null)

    fun saveUser(user: User): User = userRepository.save(user)

    fun updateUser(id: Long, newUser: User): User? {
        val optionalUser = userRepository.findById(id)
        if (optionalUser.isPresent) {
            val existingUser = optionalUser.get()
            val updatedUser = existingUser.copy(
                email = newUser.email
            )
            return userRepository.save(updatedUser)
        }
        return null
    }

    fun createUser(email: String, password: String): User {
        val newUser = User(email = email, password = password)
        return userRepository.save(newUser)
    }

    fun deleteUser(id: Long) {
        logger.info("Attempting to delete user with ID: $id")
        try {
            userRepository.deleteById(id)
            logger.info("User with ID: $id has been successfully deleted.")
        } catch (ex: Exception) {
            logger.error("Failed to delete user with ID: $id", ex)
            throw ex
        }
    }
}
