package ferdi.networkbuilder;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.controller.FinalCSVCreationController;
import ferdi.networkbuilder.controller.InitializationController;
import ferdi.networkbuilder.metadata.DaySummary;
import ferdi.networkbuilder.metadata.RunSummary;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.agents.TestCenter;
import ferdi.networkbuilder.model.collections.AgentMap;
import ferdi.networkbuilder.model.collections.AreaMap;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SimulatorImpl implements Simulator{
    @Override
    public void simulate(AgentMap<Agent> agentMap,ApplicationContext ctx) {
        InitializationController initializationController = (InitializationController) ctx.getBean("initializationController");
        MetaConfig config = (MetaConfig) ctx.getBean("metaConfig");
        if(!config.isTest_multiple()){
            runOneSimulation(agentMap,initializationController, config);
        }else {
            runMultipleSimulationCTA(agentMap,initializationController, config);
        }
    }

    private void runMultipleSimulationCTA(AgentMap<Agent> agentMap, InitializationController initializationController, MetaConfig config) {

        if(config.isToTestCTAAppliance()){
            List<Double> rates = config.getcTAApplienceList();
            for (Double rate : rates) {
                config.setcTAUsers(rate);
                runMultipleSimulationTests(agentMap, initializationController, config);
            }
        }else {
            runMultipleSimulationTests(agentMap,initializationController, config);
        }
    }



    private void runMultipleSimulationTests(AgentMap<Agent> agentMap, InitializationController initializationController, MetaConfig config) {

        if(config.isToTestTestCapacity()){
            List<Double> rates = config.getTestCapacityList();
            for (Double rate : rates) {
                config.setTestsPerNDays(rate);
                runMultipleSimulationPrio(agentMap, initializationController, config);
            }
        }else {
            runMultipleSimulationPrio(agentMap,initializationController, config);
        }
    }

    private void runMultipleSimulationPrio(AgentMap<Agent> agentMap, InitializationController initializationController, MetaConfig config) {
        if(config.isToTestTestPriority()){
            config.setTestPrio(true);
            runOneSimulation(agentMap,initializationController, config);
            config.setTestPrio(false);
            runOneSimulation(agentMap,initializationController, config);
        }else {
            runOneSimulation(agentMap,initializationController, config);
        }
    }

    private void runOneSimulation(AgentMap<Agent> agentMap, InitializationController initializationController, MetaConfig config){
        initializationController.initialize(agentMap,config);
        TestCenter testCenter = new TestCenter(config,agentMap);
        Map<Integer,List<Agent>> areaMap = buildAreaMap(agentMap);
        RunSummary dayList = new RunSummary(config);
        int week = 7;
        int weekday = 0;
        System.out.println();
        System.out.println("--------NEW RUN " +config.getRunCounter() +  " WITH: " + dayList.getRunName() + "-------------------------------------------");

        for( int day = 1; day <= config.getDays() ; day++){
            weekday = (weekday % week) +1;
            DaySummary daySummary = new DaySummary(day);

            testCenter.nextDay();
            updateState(agentMap,config);
            identifySymptomsAndReact(agentMap,config,testCenter,daySummary);
            updateDaySummary(daySummary,agentMap,testCenter);
            meetAndInfect(weekday,areaMap,agentMap,config, daySummary);
            dayList.add(daySummary);
            if(day%50 == 0 || day == config.getDays()){
                System.out.println("simulated day " + day);
            }
        }
        dayList.create(config);
        config.addRun();
    }

    private void meetAndInfect(int weekday, Map<Integer, List<Agent>> areaMap, AgentMap<Agent> agentMap, MetaConfig config, DaySummary daySummary) {
        for (Map.Entry<Integer, Agent> agentEntry : agentMap.entrySet()) {

            Agent agent = agentEntry.getValue();
            agent.meetAndInfect(areaMap.get(agent.getArea()),config, weekday,daySummary);
        }
        for (Map.Entry<Integer, Agent> agentEntry : agentMap.entrySet()) {
            Agent agent = agentEntry.getValue();
            agent.applyBeeingInfected(daySummary,config);
        }
    }

    private int printConfigDifs(AgentMap<Agent> agentMap, MetaConfig config){
        int counterCTA = 0;
        for(Map.Entry<Integer,Agent> agentEntry: agentMap.entrySet()){
            if(agentEntry.getValue().usesCTA()){
                counterCTA++;
            }
        }
        return counterCTA;
    }
    private void printAgents(AgentMap<Agent> agentMap, MetaConfig config) {
        for(Map.Entry<Integer,Agent> agentEntry: agentMap.entrySet()){
            Agent agent = agentEntry.getValue();
            if(agentEntry.getValue().getHealth().isInfectedCurrently()  || agentEntry.getValue().isIsolated()){
                System.out.println(agent +" --- "+ agent.getHealth() + " --- " +agent.toStringIsolation()+ " --- " + agent.toStringHousehold() + " --- " + agent.toStringRelatives());
            }
        }
    }

    private void identifySymptomsAndReact(AgentMap<Agent> agentMap, MetaConfig config, TestCenter testCenter, DaySummary daySummary) {
        for(Map.Entry<Integer,Agent> agentEntry: agentMap.entrySet()){
            agentEntry.getValue().nextDay(config,testCenter,daySummary);
        }
        for(Map.Entry<Integer,Agent> agentEntry: agentMap.entrySet()){
            agentEntry.getValue().dealWithSymptomsFromCovid(config,testCenter);
            agentEntry.getValue().dealWithSymptomsFromILI(config,testCenter);
        }
        testCenter.testAll(config);
    }

    private void updateDaySummary(DaySummary daySummary, AgentMap<Agent> agentMap, TestCenter testCenter) {
        for(Map.Entry<Integer,Agent> agentEntry: agentMap.entrySet()){
            Agent agent = agentEntry.getValue();
            daySummary.addOne(agent.getHealth().getHealthStatus(),agent.getHealth().isInfectious(),testCenter);
        }
    }

    private void updateState(AgentMap<Agent> agentMap, MetaConfig config){
        for(Map.Entry<Integer,Agent> agentEntry: agentMap.entrySet()){
            Agent agent = agentEntry.getValue();
            agent.getHealth().nextDay(config,agent.getAge(),agent.getFemale());
        }
    }

    private Map<Integer, List<Agent>> buildAreaMap(AgentMap<Agent> agents){
        AreaMap map = new AreaMap();
        map.putAll(agents);
        Map<Integer, Map<Integer, Agent>> rmap= map.getAreaMap();
        Map<Integer, List<Agent>> convertedMap = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, Agent>> entry :rmap.entrySet()) {
            List<Agent> list = new ArrayList<>(entry.getValue().values());
            convertedMap.put(entry.getKey(), list);
        }
        return convertedMap;
    }
}
