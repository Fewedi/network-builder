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
        List<Agent> agents= new ArrayList<>();
        for(Map.Entry<Integer,Agent> agentEntry: agentMap.entrySet()){
            agents.add(agentEntry.getValue());
        }
        Collections.shuffle(agents);
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

        List<Agent> infList = setHealth(agents,infStart,infEnd, HealthStatus.INCUBATION);
        List<Agent> recList = setHealth(agents,recStart,recEnd, HealthStatus.RECOVERED);
        List<Agent> susList = setHealth(agents,susStart,susEnd, HealthStatus.SUSCEPTIBLE);
    }

    private List<Agent> setHealth(List<Agent> agents,int start, int end, HealthStatus status) {
        List<Agent> list = new ArrayList<>();
        for(int i = start; i <= end; i++){
            Agent agent = agents.get(i);
            agent.setHealth(new Health(status));
            list.add(agent);
        }
        return  list;
    }
}
