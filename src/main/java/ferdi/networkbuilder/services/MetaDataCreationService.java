package ferdi.networkbuilder.services;

import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;

import java.util.HashMap;
import java.util.List;

public interface MetaDataCreationService {

    void buildMetaDataLower(List<List<String>> data, HashMap<Long,GeographicHouseholdMetaData> metaData);

    void buildMetaDataGlobal(List<List<String>> data, GeographicHouseholdMetaData metaData);
}
