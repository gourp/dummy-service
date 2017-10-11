package application;

import com.hubspot.dropwizard.guice.GuiceBundle;
import config.AppConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resource.HelloResource;

public class DummyApplication extends Application<AppConfiguration> {

  private GuiceBundle<AppConfiguration> guiceBundle;

  public static void main(String[] args) throws Exception {
    new DummyApplication().run(args);
  }

  @Override
  public String getName() {
    return "DummyApp";
  }

  @Override
  public void initialize(Bootstrap<AppConfiguration> bootstrap) {
    guiceBundle = GuiceBundle.<AppConfiguration>newBuilder()
        .addModule(new Bindings())
        .setConfigClass(AppConfiguration.class)
        .build();

    bootstrap.addBundle(guiceBundle);
  }

  @Override
  public void run(AppConfiguration configuration,
      Environment environment) throws Exception {

    environment.jersey().register(HelloResource.class);
  }

}