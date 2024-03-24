package guilherme.brito.ai_sync_hub.user.repository.domain

import guilherme.brito.ai_sync_hub.user.entities.dto.UserRequestDTO
import guilherme.brito.ai_sync_hub.user.entities.dto.UserResponseDTO
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tb_user")
data class User (
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val username: String = "",

    val email: String = "",

    val password: String = "",

    @Column(name = "dh_creation")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "dh_update")
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

fun User.toResponseDTO(): UserResponseDTO =
    UserResponseDTO(
        id,
        username,
        email,
        password,
        createdAt
    )

fun UserRequestDTO.toDomain(): User =
    User(
        username = this.username,
        email = this.email,
        password = this.password
    )