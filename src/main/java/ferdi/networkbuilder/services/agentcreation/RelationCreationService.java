package ferdi.networkbuilder.services.agentcreation;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import org.springframework.stereotype.Service;

@Service
public interface RelationCreationService {

    void buildRelationships(ModelFoundation modelFoundation, MetaConfig config);
}
