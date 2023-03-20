package ferdi.networkbuilder.services.metadatacreation;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.model.collections.ModelFoundation;

import java.util.List;

public interface WorksiteCreationService {

    void createWorksiteTypes (List<List<String>> file, ModelFoundation modelFoundation, MetaConfig config);
}
