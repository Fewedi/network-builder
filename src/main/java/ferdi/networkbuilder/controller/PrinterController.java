package ferdi.networkbuilder.controller;

import ferdi.networkbuilder.metadata.AgentCreationData;
import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import ferdi.networkbuilder.services.output.Printer;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PrinterController {

    private final Printer printer;

    public PrinterController(Printer printer) {
        this.printer = printer;
    }

    public void printOut(Map<Long,List<List<List<AgentCreationData>>>> agentCreationData, HashMap<Long, GeographicHouseholdMetaData> metaLocal){
        printer.printOut(agentCreationData,metaLocal);
    }

    public void printOutRelationships(ModelFoundation modelFoundation) {
        for (Map.Entry<Integer, Agent> entry: modelFoundation.getFullMap().entrySet()){
            System.out.println(entry.getValue().toStingFriends());
        }
    }
}
