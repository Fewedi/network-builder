package ferdi.networkbuilder.metadata;

import java.util.HashMap;
import java.util.Map;

public class GeographicHouseholdMetaData extends GeographicMetaData{

    private Long hhNodepCouple16to24;
    private Long hhNodepSingle16to24;
    private Long hhDepCouple16to24;
    private Long hhDepSingle16to24;

    private Long hhNodepCouple25to34;
    private Long hhNodepSingle25to34;
    private Long hhDepCouple25to34;
    private Long hhDepSingle25to34;


    private Long hhNodepCouple35to49;
    private Long hhNodepSingle35to49;
    private Long hhDepCouple35to49;
    private Long hhDepSingle35to49;


    private Long hhNodepCouple50to;
    private Long hhNodepSingle50to;
    private Long hhDepCouple50to;
    private Long hhDepSingle50to;


    private final Map<Integer,Integer> ageMap;

    public GeographicHouseholdMetaData() {
        this.ageMap = new HashMap<>();
    }

    public void setGeographicHouseholdMetaData(Long cduId, String geoCode, String geoLabel
            , Long hhNodepCouple16to24, Long hhDepCouple16to24, Long hhNodepSingle16to24, Long hhDepSingle16to24
            , Long hhNodepCouple25to34, Long hhDepCouple25to34, Long hhNodepSingle25to34, Long hhDepSingle25to34
            , Long hhNodepCouple35to49, Long hhDepCouple35to49, Long hhNodepSingle35to49, Long hhDepSingle35to49
            , Long hhNodepCouple50to, Long hhDepCouple50to, Long hhNodepSingle50to, Long hhDepSingle50to) {
        super.setGeographicMetaData(cduId, geoCode, geoLabel);
        this.hhNodepCouple16to24 = hhNodepCouple16to24;
        this.hhNodepSingle16to24 = hhNodepSingle16to24;
        this.hhDepCouple16to24 = hhDepCouple16to24;
        this.hhDepSingle16to24 = hhDepSingle16to24;
        this.hhNodepCouple25to34 = hhNodepCouple25to34;
        this.hhNodepSingle25to34 = hhNodepSingle25to34;
        this.hhDepCouple25to34 = hhDepCouple25to34;
        this.hhDepSingle25to34 = hhDepSingle25to34;
        this.hhNodepCouple35to49 = hhNodepCouple35to49;
        this.hhNodepSingle35to49 = hhNodepSingle35to49;
        this.hhDepCouple35to49 = hhDepCouple35to49;
        this.hhDepSingle35to49 = hhDepSingle35to49;
        this.hhNodepCouple50to = hhNodepCouple50to;
        this.hhNodepSingle50to = hhNodepSingle50to;
        this.hhDepCouple50to = hhDepCouple50to;
        this.hhDepSingle50to = hhDepSingle50to;

    }

    public Long getHoushold(boolean dependendChildien, boolean coupleHousehold, int minAge ){
        if(dependendChildien){
            if(coupleHousehold){
                switch (minAge) {
                    case 16 -> {return hhDepCouple16to24;}
                    case 25 -> {return hhDepCouple25to34;}
                    case 35 -> {return hhDepCouple35to49;}
                    case 50 -> {return hhDepCouple50to;}
                }
            }else {
                switch (minAge) {
                    case 16 -> {return hhDepSingle16to24;}
                    case 25 -> {return hhDepSingle25to34;}
                    case 35 -> {return hhDepSingle35to49;}
                    case 50 -> {return hhDepSingle50to;}
                }
            }
        }else {
            if(coupleHousehold){
                switch (minAge) {
                    case 16 -> {return hhNodepCouple16to24;}
                    case 25 -> {return hhNodepCouple25to34;}
                    case 35 -> {return hhNodepCouple35to49;}
                    case 50 -> {return hhNodepCouple50to;}
                }
            }else {
                switch (minAge) {
                    case 16 -> {return hhNodepSingle16to24;}
                    case 25 -> {return hhNodepSingle25to34;}
                    case 35 -> {return hhNodepSingle35to49;}
                    case 50 -> {return hhNodepSingle50to;}
                }
            }
        }
    return null;
    }

    public void putAge(Integer keyAge, Integer valueCount){
        ageMap.put(keyAge,valueCount);
    }

    public int getAgeCount(int age){
        return ageMap.get(age);
    }

    @Override
    public String toString() {
        return  "GeographicHouseholdMetaData{" + super.toString() +
                "hhNodepCouple16to24=" + hhNodepCouple16to24 +
                ", hhNodepSingle16to24=" + hhNodepSingle16to24 +
                ", hhDepCouple16to24=" + hhDepCouple16to24 +
                ", hhDepSingle16to24=" + hhDepSingle16to24 +
                ", hhNodepCouple25to34=" + hhNodepCouple25to34 +
                ", hhNodepSingle25to34=" + hhNodepSingle25to34 +
                ", hhDepCouple25to34=" + hhDepCouple25to34 +
                ", hhDepSingle25to34=" + hhDepSingle25to34 +
                ", hhNodepCouple35to49=" + hhNodepCouple35to49 +
                ", hhNodepSingle35to49=" + hhNodepSingle35to49 +
                ", hhDepCouple35to49=" + hhDepCouple35to49 +
                ", hhDepSingle35to49=" + hhDepSingle35to49 +
                ", hhNodepCouple50to=" + hhNodepCouple50to +
                ", hhNodepSingle50to=" + hhNodepSingle50to +
                ", hhDepCouple50to=" + hhDepCouple50to +
                ", hhDepSingle50to=" + hhDepSingle50to +
                '}' + "\n"
                + "------------Agedist: "+ ageMap;
    }
}
