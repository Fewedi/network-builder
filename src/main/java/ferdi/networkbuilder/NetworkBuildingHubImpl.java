package ferdi.networkbuilder;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.controller.*;
import ferdi.networkbuilder.metadata.AgentCreationData;
import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
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

        HashMap<Long, GeographicHouseholdMetaData> metaLocal = metaConfig.getMetaDataLocal();
        GeographicHouseholdMetaData metaGlobal = metaConfig.getMetaDataGlobal();

        String path = "/home/fewedi/Desktop/9_Modellierung_biologischer_Systeme_Data/Rdir";
        String fileName = "S_AGE_HType_Childrien.csv";
        List<List<String>> rawData =extractionController.extractDataFromFile(path, fileName);
        metaDataCreationController.createMetaDataGlobal(rawData, metaGlobal);
        metaDataCreationController.createMetaDataLower(rawData, metaLocal);

        String path2 = "/home/fewedi/Desktop/9_Modellierung_biologischer_Systeme_Data/Rdir";
        String fileName2 = "S_AGE.csv";
        List<List<String>> rawData2 =extractionController.extractDataFromFile(path2, fileName2);

        metaDataCreationAgeController.createMetaDataLower(rawData2,metaLocal);
        metaDataCreationAgeController.createMetaDataGlobal(rawData2,metaGlobal);

        int maxAgents = 2000;
        Map<Long,List<List<List<AgentCreationData>>>> agentCreationData = agentCreationDataController.buildAgentCreationData(metaLocal,maxAgents);
        printerController.printOut(agentCreationData,metaLocal);
    }
}
