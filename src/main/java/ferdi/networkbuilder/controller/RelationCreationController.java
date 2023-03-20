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

    public RelationCreationController(@Qualifier("friendshipCreationService")RelationCreationService friendshipCreationService,@Qualifier("houseHoldCreationService")RelationCreationService houseHoldCreationService) {
        this.friendshipCreationService = friendshipCreationService;
        this.houseHoldCreationService = houseHoldCreationService;
    }

    public void buildFriendships(ModelFoundation modelFoundation, MetaConfig config) {
        friendshipCreationService.buildRelationships(modelFoundation,config);
    }


    public void buildHouseHolds(ModelFoundation modelFoundation, MetaConfig config) {
        houseHoldCreationService.buildRelationships(modelFoundation,config);
    }
}
