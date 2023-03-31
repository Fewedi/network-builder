package ferdi.networkbuilder.services.agentcreation;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FriendshipCreationService implements RelationCreationService {



    @Override
    public void buildRelationships(ModelFoundation modelFoundation, MetaConfig config) {
        if(config.isDefaultFriendshipsSkewedByAge()){
            buildSkewedByAge(modelFoundation, config);
        }else{
            buildIndependentOfAge(modelFoundation,config);
        }
    }

    private void buildSkewedByAge(ModelFoundation modelFoundation, MetaConfig config){
        int minAge = config.getFriendshipsMinAge();
        int ageGroupSize = config.getFriendshipsSkewedByAgeAgeGroup();
        List<Integer> ageGroups = new ArrayList<>();
        int c = ageGroupSize +1;
        for(int i = minAge; i <= 100 ; i++){
            if(c >= ageGroupSize){
                c = 1;
                ageGroups.add(i);
            }else {
                c++;
                ageGroups.set(ageGroups.size() - 1, ageGroups.get(ageGroups.size() - 1));
            }
        }
        List<List<Integer>> groups = new ArrayList<>();
        for (Integer age: ageGroups){
            List<Integer> oneGroup = new ArrayList<>();
            for(int i = age; i < age + ageGroupSize; i++ ){
                if(i <= 100){
                    oneGroup.add(i);
                }
            }
            groups.add(oneGroup);
        }
        //System.out.println(groups);
        if(groups.size() > 1){

            for(int i = 0; i < groups.size() -1; i++){
                int startAge = groups.get(i).get(0);
                int endAge = groups.get(i+1).get(groups.get(i+1).size()-1);
                Map<Integer,Agent> map = modelFoundation.getAgeMapRange(startAge,endAge);
                //System.out.println(map);
                Graph<Agent,DefaultEdge> graph = applyBarabasiAlbertModel(map,config.getFriendshipsBarabasiAlbertGraphM() / 2,config.getRandom());
                graphToAgentRelationships(graph);
            }
        }else {
            buildIndependentOfAge(modelFoundation,config);
        }
        System.out.println("Friendships created: "+  modelFoundation.getFriendShipCount() + ", friends per Agent: ~" + ((double)modelFoundation.getFriendShipCount()/ (double)modelFoundation.getFullMap().size()));
    }

    private void buildIndependentOfAge(ModelFoundation modelFoundation, MetaConfig config){
        Map< Integer, Agent> map = modelFoundation.getAgeMapRange(config.getFriendshipsMinAge(),100);
        Graph<Agent, DefaultEdge> graph = applyBarabasiAlbertModel(map, config.getFriendshipsBarabasiAlbertGraphM(), config.getRandom());
        graphToAgentRelationships(graph);
        System.out.println("Friendships created: "+  modelFoundation.getFriendShipCount() + ", friends per Agent: ~" + ((double)modelFoundation.getFriendShipCount()/ (double)modelFoundation.getFullMap().size()));
    }

    private void graphToAgentRelationships(Graph<Agent, DefaultEdge> graph){
        for(Agent agent: graph.vertexSet()){
            Set<DefaultEdge> set = graph.edgesOf(agent);
            for(DefaultEdge egde: set){
                Agent v1 = graph.getEdgeTarget(egde);
                if(!(v1.getId() == agent.getId())){
                    agent.addFriend(v1);
                    v1.addFriend(agent);
                }
            }

        }

    }

    private Graph<Agent, DefaultEdge> applyBarabasiAlbertModel(Map<Integer,Agent> map, int m, Random rng){
        int n = map.size();
        List<Agent> set =new ArrayList<>();
        for (Map.Entry<Integer,Agent> entry : map.entrySet()){
            set.add(entry.getValue());
        }
        Collections.shuffle(set,rng);
        List<Agent> nodes = new ArrayList<>(n * m);
        Graph<Agent, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        for (int i = 0 ; i<m; i++){
            if(i < set.size()){
                graph.addVertex(set.get(i));
            }
        }
        for (int i = 0 ; i<m; i++) {
            for (int j = 0; j < m; j++) {
                if(i != j){
                    if(i < set.size() && j < set.size()) {
                        graph.addEdge(set.get(i), set.get(j));
                        nodes.add(set.get(i));
                    }
                }
            }
        }
        if(m < set.size()) {

            for (int i = m; i < set.size(); i++) {
                Agent curAgent = set.get(i);
                List<Agent> newEndpoints = new ArrayList<>();
                graph.addVertex(curAgent);
                int added = 0;
                while (added < m) {
                    int r = rng.nextInt(nodes.size());
                    Agent potentialFriend = nodes.get(r);
                    if (!graph.containsEdge(curAgent, potentialFriend) && !curAgent.equals(potentialFriend)) {
                        graph.addEdge(curAgent, potentialFriend);
                        added++;
                        newEndpoints.add(curAgent);
                        if (graph.vertexSet().size() > 1) {
                            newEndpoints.add(potentialFriend);
                        }
                    }
                }
                nodes.addAll(newEndpoints);
            }
        }
        return graph;
    }
}
