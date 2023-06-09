package ferdi.networkbuilder.services.simulation;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.agents.Health;
import ferdi.networkbuilder.model.agents.HealthStatus;
import ferdi.networkbuilder.model.collections.AgentMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


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

        setHealth(agents,infStart,infEnd, HealthStatus.INCUBATION, config);
        setHealth(agents,recStart,recEnd, HealthStatus.RECOVERED, config);
        setHealth(agents,susStart,susEnd, HealthStatus.SUSCEPTIBLE, config);

        Collections.shuffle(agents);
        setCTAComplience(agents,config);
    }

    private void setCTAComplience(List<Agent> agents, MetaConfig config) {
        int totalCTA = (int) (config.getcTAUsers() * (double) agents.size());
        if(totalCTA != 0){
            for(int i = 0; i<totalCTA; i++){
                agents.get(i).setUsesCTA(true);
            }
            for(int i = totalCTA; i < agents.size(); i++){
                agents.get(i).setUsesCTA(false);
            }
        }else {
            for (Agent agent : agents) {
                agent.setUsesCTA(false);
            }
        }
    }

    private void resetAgents(AgentMap<Agent> agentMap, MetaConfig config) {
        for(Map.Entry<Integer,Agent> agentEntry: agentMap.entrySet()){
            agentEntry.getValue().reset(config);
        }
    }

    private void setHealth(List<Agent> agents, int start, int end, HealthStatus status, MetaConfig config) {
        for(int i = start; i <= end; i++){
            Agent agent = agents.get(i);
                agent.setHealth(new Health(status,config,agent.getAge()));
        }
    }
}
