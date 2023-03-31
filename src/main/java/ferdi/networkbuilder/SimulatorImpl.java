package ferdi.networkbuilder;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.controller.FinalCSVCreationController;
import ferdi.networkbuilder.controller.InitializationController;
import ferdi.networkbuilder.metadata.DaySummary;
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
        FinalCSVCreationController finalCSVCreationController = (FinalCSVCreationController) ctx.getBean("finalCSVCreationController");
        MetaConfig config = (MetaConfig) ctx.getBean("metaConfig");
        initializationController.initialize(agentMap,config);
        TestCenter testCenter = new TestCenter(config,agentMap);
        Map<Integer,List<Agent>> areaMap = buildAreaMap(agentMap);
        List<DaySummary> dayList = new ArrayList<>();
        int week = 7;
        int weekday = 0;

        System.out.println("prop hh is: " + config.getBaselineTransmissionProp() * config.getTransmissionRateHousehold() * config.getTransmissionReductionHouseholdIsolated());
        System.out.println("prop hh no is: " + config.getBaselineTransmissionProp() * config.getTransmissionRateHousehold());
        for( int day = 1; day <= config.getDays() ; day++){
            weekday = (weekday % week) +1;
            DaySummary daySummary = new DaySummary(day);

            testCenter.nextDay();
            updateState(agentMap,config);
            identifySymptomsAndReact(agentMap,config,testCenter,daySummary);
            updateDaySummary(daySummary,agentMap,testCenter);
            meetAndInfect(weekday,areaMap,agentMap,config,testCenter,daySummary);
            System.out.println(daySummary);
            // treffen Kontakte
            // infektion Kontakte
            // output generierung
            // printAgents(agentMap,config,testCenter);
            dayList.add(daySummary);
        }
        finalCSVCreationController.createNetworkSummary(dayList,config);
    }

    private void meetAndInfect(int weekday, Map<Integer, List<Agent>> areaMap, AgentMap<Agent> agentMap, MetaConfig config, TestCenter testCenter, DaySummary daySummary) {
        for (Map.Entry<Integer, Agent> agentEntry : agentMap.entrySet()) {

            Agent agent = agentEntry.getValue();
            agent.meetAndInfect(areaMap.get(agent.getArea()),config,testCenter,weekday,daySummary);
        }
    }

    private void printAgents(AgentMap<Agent> agentMap, MetaConfig config, TestCenter testCenter) {
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
            if(agentEntry.getValue().getHealth().isInfectedCurrently()){
                    //System.out.println(agentEntry.getValue() +" --- "+ agentEntry.getValue().getHealth());
            }
        }
        testCenter.testAll(config);
    }

    private void updateDaySummary(DaySummary daySummary, AgentMap<Agent> agentMap, TestCenter testCenter) {
        for(Map.Entry<Integer,Agent> agentEntry: agentMap.entrySet()){
            Agent agent = agentEntry.getValue();
            daySummary.addOne(agent.getHealth().getHealthStatus(),agent.getHealth().isInfectious(),testCenter);
            if(agent.getHealth().isInfectious()){
               // System.out.println(agent);
            }
        }
    }

    private void updateState(AgentMap<Agent> agentMap, MetaConfig config){
        for(Map.Entry<Integer,Agent> agentEntry: agentMap.entrySet()){
            Agent agent = agentEntry.getValue();
            agent.getHealth().nextDay(config,agent.getAge(),agent.getFemale());
            if(agentEntry.getValue().getHealth().isInfectedCurrently()){
                //System.out.println(agentEntry.getValue() +" --- "+ agentEntry.getValue().getHealth() + " --- " + agentEntry.getValue().toStringIsolation());
            }
        }
    }

    private Map<Integer, List<Agent>> buildAreaMap(AgentMap<Agent> agents){
        AreaMap map = new AreaMap();
        map.putAll(agents);
        Map<Integer, Map<Integer, Agent>> rmap= map.getAreaMap();
        Map<Integer, List<Agent>> convertedMap = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, Agent>> entry :rmap.entrySet()) {
            List<Agent> list = new ArrayList<>();
            list.addAll(entry.getValue().values());
            convertedMap.put(entry.getKey(), list);
        }
        return convertedMap;
    }
}
