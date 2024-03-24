package guilherme.brito.ai_sync_hub.user

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.util.*

@SpringBootApplication(scanBasePackages = ["guilherme.brito.ai_sync_hub.user"])
class Boot {
	companion object {
		@JvmStatic fun main(args: Array<String>) {
			TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"))
			SpringApplication.run(Boot::class.java, *args)
		}
	}
}
