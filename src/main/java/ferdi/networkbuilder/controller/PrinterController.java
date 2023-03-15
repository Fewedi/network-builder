package ferdi.networkbuilder.controller;

import ferdi.networkbuilder.metadata.AgentCreationData;
import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import ferdi.networkbuilder.services.printout.Printer;
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
}
