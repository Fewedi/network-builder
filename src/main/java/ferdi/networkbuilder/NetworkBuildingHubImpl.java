package ferdi.networkbuilder;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.controller.*;
import ferdi.networkbuilder.metadata.AgentCreationData;
import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class NetworkBuildingHubImpl implements NetworkBuildingHub{
    @Override
    public void buildNetwork(ApplicationContext ctx) {

        MetaConfig metaConfig = (MetaConfig) ctx.getBean("metaConfig");
        ExtractionController extractionController = (ExtractionController) ctx.getBean("extractionController");
        MetaDataCreationHouseholdController metaDataCreationController = (MetaDataCreationHouseholdController) ctx.getBean("metaDataCreationHouseholdController");
        MetaDataCreationAgeController metaDataCreationAgeController = (MetaDataCreationAgeController) ctx.getBean("metaDataCreationAgeController");
        AgentCreationDataController agentCreationDataController = (AgentCreationDataController) ctx.getBean("agentCreationDataController");
        PrinterController printerController = (PrinterController) ctx.getBean("printerController");
        AgentCreationController agentCreationController = (AgentCreationController) ctx.getBean("agentCreationController");
        RelationCreationController relationCreationController = (RelationCreationController) ctx.getBean("relationCreationController");
        WorksiteCreationController worksiteCreationController = (WorksiteCreationController) ctx.getBean("worksiteCreationController");

        HashMap<Long, GeographicHouseholdMetaData> metaLocal = metaConfig.getMetaDataLocal();
        GeographicHouseholdMetaData metaGlobal = metaConfig.getMetaDataGlobal();

        String pathHousehold = metaConfig.getPathDataHousehold();
        String fileNameHousehold = metaConfig.getFileNameHousehold();
        List<List<String>> rawData =extractionController.extractDataFromFile(pathHousehold, fileNameHousehold);
        metaDataCreationController.createMetaDataGlobal(rawData, metaGlobal);
        metaDataCreationController.createMetaDataLower(rawData, metaLocal);

        String pathAge = metaConfig.getPathDataAge();
        String fileNameAge = metaConfig.getFileNameAge();
        List<List<String>> rawData2 =extractionController.extractDataFromFile(pathAge, fileNameAge);

        metaDataCreationAgeController.createMetaDataLower(rawData2,metaLocal);
        metaDataCreationAgeController.createMetaDataGlobal(rawData2,metaGlobal);

        int maxAgents = metaConfig.getMaxAgents();
        Map<Integer,List<List<List<AgentCreationData>>>> agentCreationData = agentCreationDataController.buildAgentCreationData(metaLocal,maxAgents);
        //printerController.printOut(agentCreationData,metaLocal);

        ModelFoundation modelFoundation = agentCreationController.createAgents(agentCreationData);
        relationCreationController.buildFriendships(modelFoundation,metaConfig);
        //printerController.printOutRelationships(modelFoundation);
        relationCreationController.buildHouseHolds(modelFoundation,metaConfig);

        String pathWorksites = metaConfig.getPathDataWorksites();
        String fileNameWorksites = metaConfig.getFileNameWorksites();
        List<List<String>> rawDataWorksites =extractionController.extractDataFromFile(pathWorksites, fileNameWorksites);

        worksiteCreationController.createWorksiteTypes(rawDataWorksites,modelFoundation,metaConfig);
        //System.out.println(rawDataWorksites);


    }
}
