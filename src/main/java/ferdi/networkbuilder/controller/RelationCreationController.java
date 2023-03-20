package ferdi.networkbuilder.controller;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import ferdi.networkbuilder.services.agentcreation.RelationCreationService;
import org.springframework.stereotype.Controller;

@Controller
public class RelationCreationController {

    private final RelationCreationService relationCreationService;

    public RelationCreationController(RelationCreationService relationCreationService) {
        this.relationCreationService = relationCreationService;
    }

    public void buildRelationships(ModelFoundation modelFoundation, MetaConfig config) {
        relationCreationService.buildRelationships(modelFoundation,config);
    }
}
