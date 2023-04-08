package ferdi.networkbuilder;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.controller.HubController;
import ferdi.networkbuilder.controller.SimulatorController;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.AgentMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class NetworkBuilderApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(NetworkBuilderApplication.class, args);

        MetaConfig metaConfig = (MetaConfig) ctx.getBean("metaConfig");
        if(metaConfig.isTestTransmissionProbability()){
            testTransmissionProb(ctx,metaConfig,metaConfig.getSeed());
        }else {
            if(metaConfig.getRuns() == 1){
                forOneSeed(ctx, metaConfig,metaConfig.getSeed());
            }else {
                for(int i = 1; i <= metaConfig.getRuns(); i++){
                    Random rng = new Random();
                    int seed = rng.nextInt();
                    forOneSeed(ctx, metaConfig, seed);
                }
            }
        }
        System.out.println("--------------EVERYTHING DONE---------------");
    }

    private static void forOneSeed(ApplicationContext ctx, MetaConfig config, int seed){
        if(config.isTest_multiple()){
            runMultipleSimulationCTA(ctx,config, seed);
        }else {
            runOneSimulation(ctx,config, seed);
        }
    }

    private static void runMultipleSimulationCTA(ApplicationContext ctx, MetaConfig config, int seed) {
        if(config.isToTestCTAAppliance()){
            List<Double> rates = config.getcTAApplienceList();
            for (Double rate : rates) {
                config.setcTAUsers(rate);
                runMultipleSimulationTests(ctx, config, seed);
            }
        }else {
            runMultipleSimulationTests(ctx, config, seed);
        }
    }

    private static void runOneSimulation(ApplicationContext ctx, MetaConfig config, int seed) {
        config.setSeed(seed);
        HubController hubController = (HubController) ctx.getBean("hubController");
        SimulatorController simulatorController = (SimulatorController) ctx.getBean("simulatorController");

        AgentMap<Agent> agents = hubController.buildNetwork(ctx);
        simulatorController.simulate(agents,ctx);
    }


    private static void runMultipleSimulationTests(ApplicationContext ctx, MetaConfig config, int seed) {

        if(config.isToTestTestCapacity()){
            List<Double> rates = config.getTestCapacityList();
            for (Double rate : rates) {
                config.setTestsPerNDays(rate);
                runMultipleSimulationPrio(ctx, config, seed);
            }
        }else {
            runMultipleSimulationPrio(ctx, config, seed);
        }
    }

    private static void runMultipleSimulationPrio(ApplicationContext ctx, MetaConfig config, int seed) {
        if(config.isToTestTestPriority()){
            config.setTestPrio(true);
            runOneSimulation(ctx, config, seed);
            config.setTestPrio(false);
            runOneSimulation(ctx, config, seed);
        }else {
            runOneSimulation(ctx, config, seed);
        }
    }


    private static void testTransmissionProb(ApplicationContext ctx, MetaConfig config, int seed) {
        List<Double> rates = config.getBaselineTransmissionPropList();
        for (int i = 0; i< rates.size(); i++){
            config.setBaselineTransmissionProp(rates.get(i));
            runOneSimulation(ctx, config, seed);
        }
    }
}
