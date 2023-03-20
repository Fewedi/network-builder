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
        //TODO
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
        Collections.shuffle(set);
        List<Agent> nodes = new ArrayList<>(n * m);
        Graph<Agent, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        for (int i = 0 ; i<m; i++){
            graph.addVertex(set.get(i));
        }
        for (int i = 0 ; i<m; i++) {
            for (int j = 0; j < m; j++) {
                if(i != j){
                    graph.addEdge(set.get(i),set.get(j));
                    nodes.add(set.get(i));
                }
            }
        }

        for (int i = m; i < set.size(); i++){
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
        return graph;
    }
}
