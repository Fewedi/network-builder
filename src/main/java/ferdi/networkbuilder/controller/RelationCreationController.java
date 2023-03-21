package ferdi.networkbuilder.controller;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import ferdi.networkbuilder.services.agentcreation.RelationCreationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class RelationCreationController {

    private final RelationCreationService friendshipCreationService;
    private final RelationCreationService houseHoldCreationService;
    private final RelationCreationService worksiteCreationService;
    private final RelationCreationService schoolClassCreationService;

    private final RelationCreationService relativesCreationService;

    public RelationCreationController(
            @Qualifier("friendshipCreationService")RelationCreationService friendshipCreationService,
            @Qualifier("houseHoldCreationService")RelationCreationService houseHoldCreationService,
            @Qualifier("worksiteCreationService")RelationCreationService worksiteCreationService,
            @Qualifier("schoolClassCreationService")RelationCreationService schoolClassCreationService,
            @Qualifier("relativesCreationService")RelationCreationService relativesCreationService) {
        this.friendshipCreationService = friendshipCreationService;
        this.houseHoldCreationService = houseHoldCreationService;
        this.worksiteCreationService = worksiteCreationService;
        this.schoolClassCreationService=schoolClassCreationService;
        this.relativesCreationService = relativesCreationService;
    }

    public void buildFriendships(ModelFoundation modelFoundation, MetaConfig config) {
        friendshipCreationService.buildRelationships(modelFoundation,config);
    }

    public void buildHouseHolds(ModelFoundation modelFoundation, MetaConfig config) {
        houseHoldCreationService.buildRelationships(modelFoundation,config);
    }

    public void buildWorksites(ModelFoundation modelFoundation, MetaConfig config) {
        worksiteCreationService.buildRelationships(modelFoundation,config);
    }

    public void buildSchoolClasses(ModelFoundation modelFoundation, MetaConfig config) {
        schoolClassCreationService.buildRelationships(modelFoundation,config);
    }

    public void buildRelatives(ModelFoundation modelFoundation, MetaConfig config){
        relativesCreationService.buildRelationships(modelFoundation,config);
    }
}
