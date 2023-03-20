package ferdi.networkbuilder.controller;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import ferdi.networkbuilder.services.metadatacreation.WorksiteCreationService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WorksiteCreationController {

    private final WorksiteCreationService worksiteCreationService;

    public WorksiteCreationController(WorksiteCreationService worksiteCreationService) {
        this.worksiteCreationService = worksiteCreationService;
    }

    public void createWorksiteTypes(List<List<String>> file, ModelFoundation modelFoundation, MetaConfig config){
        worksiteCreationService.createWorksiteTypes(file,modelFoundation,config);
    }
}
