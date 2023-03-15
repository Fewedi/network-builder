package ferdi.networkbuilder.config;

import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Configuration
@ConfigurationProperties(prefix = "default")
public class MetaConfig {

    private int maximalPopulation;

    private String pathDataHousehold;
    private String pathDataAge;
    private String fileNameHousehold;
    private String fileNameAge;




    public int getMaxAgents() {
        return maximalPopulation;
    }

    public void setMaximalPopulation(int maxAgents) {
        this.maximalPopulation = maxAgents;
    }

    public String getPathDataHousehold() {
        return pathDataHousehold;
    }

    public void setPathDataHousehold(String pathDataHousehold) {
        this.pathDataHousehold = pathDataHousehold;
    }

    public String getPathDataAge() {
        return pathDataAge;
    }

    public void setPathDataAge(String pathDataAge) {
        this.pathDataAge = pathDataAge;
    }

    public String getFileNameHousehold() {
        return fileNameHousehold;
    }

    public void setFileNameHousehold(String fileNameHousehold) {
        this.fileNameHousehold = fileNameHousehold;
    }

    public String getFileNameAge() {
        return fileNameAge;
    }

    public void setFileNameAge(String fileNameAge) {
        this.fileNameAge = fileNameAge;
    }

    private final HashMap<Long,GeographicHouseholdMetaData> metaDataLocal;
    private final GeographicHouseholdMetaData metaDataGlobal;

    public MetaConfig() {
        this.metaDataLocal = new HashMap<>();
        this.metaDataGlobal = new GeographicHouseholdMetaData();
    }

    public HashMap<Long, GeographicHouseholdMetaData> getMetaDataLocal() {
        return metaDataLocal;
    }

    public GeographicHouseholdMetaData getMetaDataGlobal() {
        return metaDataGlobal;
    }
}
