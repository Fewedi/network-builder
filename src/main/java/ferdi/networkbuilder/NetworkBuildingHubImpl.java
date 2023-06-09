package ferdi.networkbuilder;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.controller.*;
import ferdi.networkbuilder.metadata.AgentCreationData;
import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import ferdi.networkbuilder.metadata.NetworkSummaryData;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.AgentMap;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class NetworkBuildingHubImpl implements NetworkBuildingHub{
    @Override
    public AgentMap<Agent> buildNetwork(ApplicationContext ctx) {
        System.out.println();

        System.out.println("--------BUILD NETWORK----------------------------------------------------------------------------------------------------------------------");

        MetaConfig metaConfig = (MetaConfig) ctx.getBean("metaConfig");
        ExtractionController extractionController = (ExtractionController) ctx.getBean("extractionController");
        MetaDataCreationHouseholdController metaDataCreationController = (MetaDataCreationHouseholdController) ctx.getBean("metaDataCreationHouseholdController");
        MetaDataCreationAgeController metaDataCreationAgeController = (MetaDataCreationAgeController) ctx.getBean("metaDataCreationAgeController");
        AgentCreationDataController agentCreationDataController = (AgentCreationDataController) ctx.getBean("agentCreationDataController");
        PrinterController printerController = (PrinterController) ctx.getBean("printerController");
        AgentCreationController agentCreationController = (AgentCreationController) ctx.getBean("agentCreationController");
        RelationCreationController relationCreationController = (RelationCreationController) ctx.getBean("relationCreationController");
        WorksiteCreationController worksiteCreationController = (WorksiteCreationController) ctx.getBean("worksiteCreationController");
        NetworkSummaryCreationController networkSummaryCreationController = (NetworkSummaryCreationController) ctx.getBean("networkSummaryCreationController");

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

        ModelFoundation modelFoundation = agentCreationController.createAgents(agentCreationData, metaConfig);
        relationCreationController.buildFriendships(modelFoundation,metaConfig);
        relationCreationController.buildHouseHolds(modelFoundation,metaConfig);

        String pathWorksites = metaConfig.getPathDataWorksites();
        String fileNameWorksites = metaConfig.getFileNameWorksites();
        List<List<String>> rawDataWorksites =extractionController.extractDataFromFile(pathWorksites, fileNameWorksites);

        worksiteCreationController.createWorksiteTypes(rawDataWorksites,modelFoundation,metaConfig);
        relationCreationController.buildWorksites(modelFoundation,metaConfig);
        relationCreationController.buildSchoolClasses(modelFoundation,metaConfig);
        relationCreationController.buildRelatives(modelFoundation,metaConfig);

        NetworkSummaryData summary = networkSummaryCreationController.createNetworkSummary(modelFoundation,metaConfig);
        return modelFoundation.getFullMap();
    }
}
