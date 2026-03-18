package net.engineeringdigest.journalApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// transactions can't happen on standalone mongodb server, it needs replication(it just ensures data consistency and availability) for which we will use atlas(aws) now

@SpringBootApplication
@EnableTransactionManagement // to use @Transactional
public class JournalApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JournalApplication.class, args);
        System.out.println(context.getEnvironment().getActiveProfiles()); //to see which profile running right now
    }

    //this bean will take care of transactions
    //PlatformTransactionManager is an interface that takes care for commits and rollback of transactions
    //MongoTransactionManager is an implementation that does that. MongoDatabaseFactory helps to connect with database
    @Bean
    public PlatformTransactionManager add(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

}