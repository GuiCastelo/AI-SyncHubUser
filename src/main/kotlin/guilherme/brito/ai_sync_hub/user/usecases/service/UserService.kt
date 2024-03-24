package guilherme.brito.ai_sync_hub.user.usecases.service

import guilherme.brito.ai_sync_hub.user.entities.dto.UserRequestDTO
import guilherme.brito.ai_sync_hub.user.entities.dto.UserResponseDTO

interface UserService {
    fun getUsers(): List<UserResponseDTO>

    fun getUser(id: Long): UserResponseDTO

    fun createUser(user: UserRequestDTO): UserResponseDTO

    fun updateUser(id: Long, editUser: UserRequestDTO): UserResponseDTO

    fun deleteUser(id: Long)
}