package ferdi.networkbuilder.services.simulation;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.agents.Health;
import ferdi.networkbuilder.model.agents.HealthStatus;
import ferdi.networkbuilder.model.collections.AgentMap;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class InitializationServiceImpl implements InitializationService {
    @Override
    public void initialise(AgentMap<Agent> agentMap, MetaConfig config) {
        resetAgents(agentMap,config);
        List<Agent> agents= new ArrayList<>();
        for(Map.Entry<Integer,Agent> agentEntry: agentMap.entrySet()){
            agents.add(agentEntry.getValue());
        }
        Collections.shuffle(agents,config.getRandom());
        double initInfected = config.getInitialAmountInfected();
        double initRecovered = config.getInitialAmountRecovered();
        int infStart = 0;
        int infEnd;
        if((int) Math.round(initInfected * agents.size()) >= 0){
            infEnd = (int) Math.round(initInfected * agents.size());
        }else {
            infEnd = 0;
        }
        int recStart = infEnd + 1;
        int recEnd;
        if((int) Math.round(initRecovered * agents.size()) >= 0){
            recEnd = recStart + (int) Math.round(initRecovered * agents.size());
        }else {
            recEnd = recStart;
        }
        int susStart = recEnd + 1;
        int susEnd = agents.size()-1;

        List<Agent> infList = setHealth(agents,infStart,infEnd, HealthStatus.INCUBATION, config);
        List<Agent> recList = setHealth(agents,recStart,recEnd, HealthStatus.RECOVERED, config);
        List<Agent> susList = setHealth(agents,susStart,susEnd, HealthStatus.SUSCEPTIBLE, config);

        //System.out.println(infList);
        initCTA(agents, config);
    }

    private void resetAgents(AgentMap<Agent> agentMap, MetaConfig config) {
        for(Map.Entry<Integer,Agent> agentEntry: agentMap.entrySet()){
            agentEntry.getValue().reset(config);
        }
    }

    private void initCTA(List<Agent> agents, MetaConfig config) {
        List<Agent> withMinAge = new ArrayList<>();
        for (Agent agent: agents){
            if(agent.getAge() >= config.getcTAMinAge()){
                withMinAge.add(agent);
            }
        }
        Collections.shuffle(withMinAge,config.getRandom());
        int absoluteCTAUsers = (int) Math.round(config.getcTAUsers() * (double) agents.size());
        for(int i = 0; i <= absoluteCTAUsers-1 || i< withMinAge.size(); i++){
            withMinAge.get(i).setUsesCTA(true);
        }
    }

    private List<Agent> setHealth(List<Agent> agents,int start, int end, HealthStatus status, MetaConfig config) {
        List<Agent> list = new ArrayList<>();
        for(int i = start; i <= end; i++){
            Agent agent = agents.get(i);

                agent.setHealth(new Health(status,config,agent.getAge()));
                list.add(agent);

        }


        return  list;
    }
}
