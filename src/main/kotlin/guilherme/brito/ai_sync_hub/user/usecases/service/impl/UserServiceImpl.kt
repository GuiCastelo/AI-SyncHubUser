package guilherme.brito.ai_sync_hub.user.usecases.service.impl

import guilherme.brito.ai_sync_hub.user.entities.dto.UserRequestDTO
import guilherme.brito.ai_sync_hub.user.entities.dto.UserResponseDTO
import guilherme.brito.ai_sync_hub.user.entities.exceptions.UserAlreadyRegisteredException
import guilherme.brito.ai_sync_hub.user.entities.exceptions.UserNotFoundException
import guilherme.brito.ai_sync_hub.user.repository.UserRepository
import guilherme.brito.ai_sync_hub.user.repository.domain.User
import guilherme.brito.ai_sync_hub.user.repository.domain.toDomain
import guilherme.brito.ai_sync_hub.user.repository.domain.toResponseDTO
import guilherme.brito.ai_sync_hub.user.usecases.service.UserService
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun getUsers(): List<UserResponseDTO> =
        userRepository.findAll().map { it.toResponseDTO() }

    override fun getUser(id: Long): UserResponseDTO {
        val user: Optional<User> = userRepository.findById(id)
        
        if(user.isEmpty)
            throw UserNotFoundException(message = "Usuário com id $id não encontrado")
        
        return user.get().toResponseDTO()
    }

    override fun createUser(user: UserRequestDTO): UserResponseDTO {
        checkIfUserAlreadyExists(user)
        
        val createdUser: User = userRepository.save(user.toDomain())
        
        return createdUser.toResponseDTO()
    }

    override fun updateUser(id: Long, editUser: UserRequestDTO): UserResponseDTO {
        val user: Optional<User> = userRepository.findById(id)
        
        if(user.isEmpty)
            throw UserNotFoundException(message = "Usuário com id $id não encontrado")
        
        return user.get().let { user ->
            if(editUserHasChanges(editUser, user)) {
                val updatedUser = User(
                    id = user.id,
                    username = editUser.username,
                    email = editUser.email,
                    password = editUser.password,
                    createdAt = user.createdAt
                )
                
                userRepository.save(updatedUser).toResponseDTO()
            } else {
                user.toResponseDTO()
            }
        }
    }

    override fun deleteUser(id: Long) {
        val user: Optional<User> = userRepository.findById(id)

        if(user.isEmpty)
            throw UserNotFoundException(message = "Usuário com id $id não encontrado")

        userRepository.deleteById(id)
    }
    
    private fun editUserHasChanges(editUser: UserRequestDTO, user: User): Boolean =
        (editUser.username != user.username) ||
        (editUser.email != user.email) ||
        (editUser.password != user.password)
    
    private fun checkIfUserAlreadyExists(user: UserRequestDTO) {
        val existingUsername: Optional<User> = userRepository.findByUsername(user.username)
        
        if(existingUsername.isPresent)
            throw UserAlreadyRegisteredException(message = "Username: ${user.username} já existente")
        
        val existingEmail: Optional<User> = userRepository.findByEmail(user.email)
        
        if(existingEmail.isPresent)
            throw UserAlreadyRegisteredException(message = "Email: ${user.email} já existente")
    }
}