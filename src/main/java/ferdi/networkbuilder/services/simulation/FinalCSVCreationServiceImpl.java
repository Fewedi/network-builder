package ferdi.networkbuilder.services.simulation;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.metadata.DaySummary;
import ferdi.networkbuilder.metadata.NetworkSummaryData;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class FinalCSVCreationServiceImpl implements FinalCSVCreationService {
    @Override
    public void create(List<DaySummary> daySummaryList, MetaConfig config) {
        try {
            createFile(listToString(daySummaryList),config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String listToString(List<DaySummary> daySummaryList){
        StringBuilder s = new StringBuilder(daySummaryList.get(0).headerString());
        for(int i = 0; i < daySummaryList.size(); i++){
            s.append("\n").append(daySummaryList.get(i).csvString());
        }
        return s.toString();
    }

    private void createFile(String string, MetaConfig config) throws IOException {
        String path = config.getPathOutput() + "/" + "days.csv";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(string);

        writer.close();
    }
}
