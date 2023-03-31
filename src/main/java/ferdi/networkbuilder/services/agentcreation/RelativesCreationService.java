package ferdi.networkbuilder.services.agentcreation;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import org.apache.commons.math3.distribution.BinomialDistribution;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RelativesCreationService implements RelationCreationService {

    @Override
    public void buildRelationships(ModelFoundation modelFoundation, MetaConfig config) {
        Map<Integer, Agent> agents = modelFoundation.getFullMap();
        List<Agent > agentList = new ArrayList<>();
        for(Map.Entry<Integer,Agent> entry: agents.entrySet()){
            agentList.add(entry.getValue());
        }
        int relationsCount = 0;
        int personsWithRelationships = 0;
        Collections.shuffle(agentList,config.getRandom());
        for(Map.Entry<Integer,Agent> entry: agents.entrySet()){
            int amount = getBinomialDistributedNr(config.getRandom(),config.getRelativesMin(),config.getRelativesAv()) / 2;
            personsWithRelationships++;
            Agent pers = entry.getValue();
            for (int i = 1; i <= amount; i++){
                Agent relative = findRelative(entry.getValue(),agentList,config);
                pers.getRealtives().add(relative);
                relative.getRealtives().add(pers);
                relationsCount++;
                relationsCount++;
            }
        }
        System.out.println(relationsCount);
        System.out.println("Relations created: " + relationsCount + ", for " + personsWithRelationships + " people, mean relations per person: " + ((double) relationsCount / (double) personsWithRelationships));

    }

    private Agent findRelative(Agent value, List<Agent> agentList, MetaConfig config) {
        boolean overlap = true;
        Agent relative = null;
        while(overlap){
            relative = agentList.get(config.getRandom().nextInt(agentList.size()));
            if(!relative.equals(value) && !relative.getHousehold().contains(value) && !relative.getRealtives().contains(value)){
                overlap = false;
            }
        }
        return relative;
    }

    private int getBinomialDistributedNr(Random rng, int min, int av){
        int n = (av - min) * 2;  // number of trials
        double p = 0.5;  // probability of success
        BinomialDistribution binomial = new BinomialDistribution(n, p);
        int bin = binomial.inverseCumulativeProbability(rng.nextDouble());
        return (bin) + min;
    }

}
