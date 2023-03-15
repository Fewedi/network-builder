package ferdi.networkbuilder.config;

import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MetaConfig {

    private final HashMap<Long,GeographicHouseholdMetaData> metaDataLocal;
    private final GeographicHouseholdMetaData metaDataGlobal;
    private final int maxAgents;

    public MetaConfig() {
        this.maxAgents = 100;
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
