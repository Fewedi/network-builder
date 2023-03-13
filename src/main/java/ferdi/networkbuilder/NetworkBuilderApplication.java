package ferdi.networkbuilder;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.controller.ExtractionController;
import ferdi.networkbuilder.controller.MetaDataCreationAgeController;
import ferdi.networkbuilder.controller.MetaDataCreationHouseholdController;
import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class NetworkBuilderApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(NetworkBuilderApplication.class, args);


        MetaConfig metaConfig = (MetaConfig) ctx.getBean("metaConfig");
        HashMap<Long, GeographicHouseholdMetaData> metaLocal = metaConfig.getMetaDataLocal();
        GeographicHouseholdMetaData metaGlobal = metaConfig.getMetaDataGlobal();


        ExtractionController extractionController = (ExtractionController) ctx.getBean("extractionController");
        MetaDataCreationHouseholdController metaDataCreationController = (MetaDataCreationHouseholdController) ctx.getBean("metaDataCreationHouseholdController");
        MetaDataCreationAgeController metaDataCreationAgeController = (MetaDataCreationAgeController) ctx.getBean("metaDataCreationAgeController");

        String path = "/home/fewedi/Desktop/9_Modellierung_biologischer_Systeme_Data/Rdir";
        String fileName = "S_AGE_HType_Childrien.csv";
        List<List<String>> rawData =extractionController.extractDataFromFile(path, fileName);
        metaDataCreationController.createMetaDataGlobal(rawData, metaGlobal);
        metaDataCreationController.createMetaDataLower(rawData, metaLocal);

        String path2 = "/home/fewedi/Desktop/9_Modellierung_biologischer_Systeme_Data/Rdir";
        String fileName2 = "S_AGE.csv";
        List<List<String>> rawData2 =extractionController.extractDataFromFile(path2, fileName2);
        metaDataCreationAgeController.createMetaDataGlobal(rawData2,metaGlobal);
        metaDataCreationAgeController.createMetaDataLower(rawData2,metaLocal);


        System.out.println("Global Data:");

        System.out.println(metaGlobal);

        System.out.println("Full Data with length " + metaLocal.size() +": " );

        for(Map.Entry<Long, GeographicHouseholdMetaData> entry : metaLocal.entrySet()) {
            System.out.println(entry.getValue());

            // do what you have to do here
            // In your case, another loop.
        }


    }

}
