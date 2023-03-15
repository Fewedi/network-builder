package ferdi.networkbuilder.services.metadatacreation;

import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AgeMetaDataCreationService implements MetaDataCreationService {


    @Override
    public void buildMetaDataLower(List<List<String>> data, HashMap<Long,GeographicHouseholdMetaData> metaData) {
        List<List<String>> datac = new ArrayList<>(data);
        List<Integer> header = transformHeader(datac);
        deleteHeader(datac);
        if(isCity(datac.get(0))){
            datac.remove(0);
        }

        for(List<String> l: datac){
            GeographicHouseholdMetaData area = metaData.get(Long.parseLong(l.get(0)));
            //System.out.println("Meta Household: we are in Local");
            lineToMetaData(l,header,area);
        }

        System.out.println("Metadata generated: - age - global");
    }

    @Override
    public void buildMetaDataGlobal(List<List<String>> data, GeographicHouseholdMetaData metaData) {
        List<List<String>> datac = new ArrayList<>(data);
        List<Integer> header = transformHeader(datac);
        deleteHeader(datac);
        //System.out.println(data.get(2));

        //System.out.println(datac.get(0));
        if(!isCity(datac.get(0))){
            System.out.println("Meta Household: Global Data could not be located");
        }


        lineToMetaData(datac.get(0),header,metaData);
        System.out.println("Metadata generated: - age - local");
    }

    private void lineToMetaData(List<String> l , List<Integer> header, GeographicHouseholdMetaData area){
        //System.out.println(header.size() + ": " +header);
        //System.out.println(l.size() + ": "+ l);
        for(int i = 5; i < header.size(); i++ ){
            try {
                area.putAge(header.get(i), Integer.parseInt(l.get(i)));
            }catch (NullPointerException e){
                System.out.println("l: " + l);
                System.out.println("header: " + header);
                System.out.println("i: " + i);
                e.printStackTrace();
            }
        }
        area.calcComparisonAgeData();
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

    private List<Integer> transformHeader(List<List<String>> datac) {
        List<String> stringHeader = datac.get(1);
        List<Integer> header = new ArrayList<>();
        for(int i = 4; i< stringHeader.size(); i++){
            header.add(findIntInString(stringHeader.get(i)));
        }
        for(int i = 0; i< 5 ; i++){
            header.add(0,-1);
        }


        return header;
    }

    private Integer findIntInString (String s)  {
        if(s.contains("under")) {
            //System.out.println("yes");
            return 0;
        }
        s = s.replaceAll("[^0-9]+","");
        return Integer.parseInt(s);
    }


}
