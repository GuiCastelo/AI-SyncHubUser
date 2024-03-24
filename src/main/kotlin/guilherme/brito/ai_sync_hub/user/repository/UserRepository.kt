package guilherme.brito.ai_sync_hub.user.repository

import guilherme.brito.ai_sync_hub.user.repository.domain.User
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>

    fun findByEmail(email: String): Optional<User>
}