package ferdi.networkbuilder.services.agentcreationdata;

import ferdi.networkbuilder.metadata.AgentCreationData;
import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AgentCreationDataServiceImpl implements AgentCreationDataService{
    @Override
    public Map<Integer, List<List<List<AgentCreationData>>>> buildAgentCreationData(Map<Long, GeographicHouseholdMetaData> metaData, int maxAgents) {

        int agentCount = 0;
        Map<Integer, List<List<List<AgentCreationData>>>> map = new HashMap<>();
        List<GeographicHouseholdMetaData> valuesList = new ArrayList<>(metaData.values());
        if(valuesList.get(0).getSumAgePopulationFull() > maxAgents){
            agentCount += maxAgents;
            System.out.println("Area "+ valuesList.get(0).getCduId()+ " size: " + valuesList.get(0).getSumAgePopulationFull().intValue() + ", agentCount: " + agentCount);

            map.put(valuesList.get(0).getCduId().intValue(),buildForArea(valuesList.get(0)));
            return map;
        }
        int i = 0;
        while (maxAgents - valuesList.get(i).getSumAgePopulationFull().intValue() > 0 || i >= valuesList.size())
            {

            agentCount += valuesList.get(i).getSumAgePopulationFull();
            maxAgents -= valuesList.get(i).getSumAgePopulationFull().intValue();
            System.out.println("Area "+ valuesList.get(i).getCduId()+ " size: " + valuesList.get(i).getSumAgePopulationFull().intValue() + ", agentCount: " + agentCount);
            map.put(valuesList.get(i).getCduId().intValue(),buildForArea(valuesList.get(i)));
            i++;
        }
        System.out.println("Count of Agents with certain Attributes generated for " + agentCount + " Agents in " + map.size() + " Areas" );
        return map;
    }

    private List<List<List<AgentCreationData>>> buildForArea(GeographicHouseholdMetaData metaData){
        List<List<AgentCreationData>> l0to15 = buildByAgeBelow16(0,15,metaData);
        List<List<AgentCreationData>> l16to24 = buildByAge(16,24,metaData);
        List<List<AgentCreationData>> l25to34 = buildByAge(25,34,metaData);
        List<List<AgentCreationData>> l35to49 = buildByAge(35,49,metaData);
        List<List<AgentCreationData>> l50to = buildByAge(50,100,metaData);

        List<List<List<AgentCreationData>>> retList = new ArrayList<>();
        retList.add(l0to15);
        retList.add(l16to24);
        retList.add(l25to34);
        retList.add(l35to49);
        retList.add(l50to);
        return retList;
    }

    private List<List<AgentCreationData>> buildByAgeBelow16(int ageMin, int ageMax, GeographicHouseholdMetaData metaData) {
        Long area = metaData.getCduId();
        HashMap<Integer,Long> subAgeMap = metaData.getSubAgeMap(ageMin,ageMax);
        List<AgentCreationData> myList = new ArrayList<>();
        for(int i = ageMin; i <= ageMax; i++){
            long finalCountL = Math.round(subAgeMap.get(i).doubleValue());
            AgentCreationData data = new AgentCreationData(i,false,false,area, (int) finalCountL);
            myList.add(data);
        }
        List<List<AgentCreationData>> listList = new ArrayList<>() ;
        listList.add(myList);
        return listList;
    }

    private List<List<AgentCreationData>> buildByAge(int ageMin, int ageMax, GeographicHouseholdMetaData metaData) {
        List<List<AgentCreationData>> list = new ArrayList<>();

        Long area = metaData.getCduId();
        Double sumByAge = (double) metaData.getByAge(ageMin,ageMax).longValue();
        Double sumByHousehold = metaData.getByHouseholdInAgeRange(ageMin).doubleValue();
        HashMap<Integer,Long> subAgeMap = metaData.getSubAgeMap(ageMin,ageMax);

        Double noKidsCouple = metaData.getHoushold(false,true,ageMin).doubleValue();
        Double noKidsSingle = metaData.getHoushold(false,false,ageMin).doubleValue();
        Double kidsCouple = metaData.getHoushold(true,true,ageMin).doubleValue();
        Double kidsSingle = metaData.getHoushold(true,false,ageMin).doubleValue();

        list.add(buildByHousehold(noKidsCouple,sumByAge,sumByHousehold,subAgeMap,ageMin,ageMax,area,false,true));
        list.add(buildByHousehold(noKidsSingle,sumByAge,sumByHousehold,subAgeMap,ageMin,ageMax,area,false,false));
        list.add(buildByHousehold(kidsCouple,sumByAge,sumByHousehold,subAgeMap,ageMin,ageMax,area,true,true));
        list.add(buildByHousehold(kidsSingle,sumByAge,sumByHousehold,subAgeMap,ageMin,ageMax,area,true,false));

        return list;

    }

    private List<AgentCreationData> buildByHousehold(Double singleByHousehold, Double sumByAge, Double sumByHousehold, HashMap<Integer,Long> subAgeMap, int ageMin, int ageMax, Long area, boolean kids, boolean couple){
        double fractionOfAll = singleByHousehold/sumByHousehold;
        double differenceBetweenAgeAndHouseholdData =  sumByHousehold/sumByAge;
        List<AgentCreationData> myList = new ArrayList<>();
        for(int i = ageMin; i <= ageMax; i++){
            long finalCountL = Math.round(subAgeMap.get(i).doubleValue() * differenceBetweenAgeAndHouseholdData * fractionOfAll);
            AgentCreationData data = new AgentCreationData(i,kids,couple,area, (int) finalCountL);
            myList.add(data);
        }//Age : Age 16 to 24 - Dependent children in household : No dependent children in household - Household type [E][S][W] : Living in a couple household - Population (usual residents) : All usual residents in households - Unit : Persons

        return myList;
    }

}
