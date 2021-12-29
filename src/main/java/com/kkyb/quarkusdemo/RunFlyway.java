package com.kkyb.quarkusdemo;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.flywaydb.core.Flyway;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class RunFlyway {

    @ConfigProperty(name = "myapp.flyway.migrate")
    boolean runMigration;
    @ConfigProperty(name = "myapp.flyway.url")
    String datasourceUrl;

    @ConfigProperty(name = "quarkus.datasource.username")
    String datasourceUsername;
    @ConfigProperty(name = "quarkus.datasource.password")
    String datasourcePassword;

    public void runFlywayMigration(@Observes StartupEvent event) {
        if (runMigration) {
            Flyway.configure()
                    .dataSource(datasourceUrl, datasourceUsername, datasourcePassword)
                    .load()
                    .migrate();
        }
    }
}
