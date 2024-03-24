package guilherme.brito.ai_sync_hub.user.repository.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(basePackages = ["guilherme.brito.ai_sync_hub.user.repository"])
class PostgresConfig {

    @Value("\${postgres.host}")
    private val postgresHost: String = ""
    @Value("\${postgres.port}")
    private val postgresPort: String = ""
    @Value("\${postgres.db-name}")
    private val postgresDatabaseName: String = ""
    @Value("\${postgres.username}")
    private val postgresUsername: String = ""
    @Value("\${postgres.password}")
    private val postgresPassword: String = ""

    @Bean(name = ["AISyncHubUserSource"])
    fun dataSource(): DataSource =
        DataSourceBuilder.create()
            .url("jdbc:postgresql://${postgresHost}:${postgresPort}/${postgresDatabaseName}")
            .username(postgresUsername)
            .password(postgresPassword)
            .driverClassName("org.postgresql.Driver")
            .build()

    @Bean(name = ["entityManagerFactory"])
    fun managerFactory(
        builder: EntityManagerFactoryBuilder, @Qualifier("AISyncHubUserSource") dataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(dataSource)
            .packages("guilherme.brito.ai_sync_hub.user.repository.domain")
            .persistenceUnit("ai_sync_hub-user")
            .build()
    }

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager =
        JpaTransactionManager(entityManagerFactory)
}