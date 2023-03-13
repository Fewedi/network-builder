package ferdi.networkbuilder.services;

import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



@Service
public class HousholdMetaDataCreationService implements MetaDataCreationService {
    @Override
    public void buildMetaDataLower(List<List<String>> data, HashMap<Long,GeographicHouseholdMetaData> metaData) {
        List<List<String>> datac = new ArrayList<>(data);
        deleteHeader(datac);
        if(isCity(datac.get(0))){
            datac.remove(0);
        }
        for(List<String> l : datac){
            metaData.put(Long.parseLong(l.get(0)),lineToMetaData(l));
        }

    }
    
    public void buildMetaDataGlobal(List<List<String>> data, GeographicHouseholdMetaData metaData){
        List<List<String>> datac = new ArrayList<>(data);
        deleteHeader(datac);
        if(!isCity(datac.get(0))){
            System.out.println("Meta Household: Global Data could not be located");
        }
        lineToMetaData(datac.get(0));
    }

    private boolean isCity(List<String> l){
        return l.get(3).equals("Local Authorities");
    }

    private void deleteHeader(List<List<String>> l){
        if(l.get(0).get(0).equals("CDU_ID")){
            l.remove(0);
        }
        if(l.get(0).get(0).equals("")){
            l.remove(0);
        }
    }
    private GeographicHouseholdMetaData lineToMetaData(List<String> l ){
        GeographicHouseholdMetaData data = new GeographicHouseholdMetaData();
        data.setGeographicHouseholdMetaData(
                Long.parseLong(l.get(0)),
                l.get(1),
                l.get(2),
                Long.parseLong(l.get(5)),
                Long.parseLong(l.get(6)),
                Long.parseLong(l.get(7)),
                Long.parseLong(l.get(8)),
                Long.parseLong(l.get(9)),
                Long.parseLong(l.get(10)),
                Long.parseLong(l.get(11)),
                Long.parseLong(l.get(12)),
                Long.parseLong(l.get(13)),
                Long.parseLong(l.get(14)),
                Long.parseLong(l.get(15)),
                Long.parseLong(l.get(16)),
                Long.parseLong(l.get(17)),
                Long.parseLong(l.get(18)),
                Long.parseLong(l.get(19)),
                Long.parseLong(l.get(20))
        );
        return data;
    }
}
