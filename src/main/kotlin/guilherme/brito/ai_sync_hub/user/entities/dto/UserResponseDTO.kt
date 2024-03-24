package guilherme.brito.ai_sync_hub.user.entities.dto

import java.time.LocalDateTime

data class UserResponseDTO(
    val id: Long,
    val username: String,
    val email: String,
    val password: String,
    val createdAt: LocalDateTime
)
