package ferdi.networkbuilder;

import ferdi.networkbuilder.controller.HubController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NetworkBuilderApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(NetworkBuilderApplication.class, args);
        HubController hubController = (HubController) ctx.getBean("hubController");
        hubController.buildNetwork(ctx);
    }

}
