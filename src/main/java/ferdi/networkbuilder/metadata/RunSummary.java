package ferdi.networkbuilder.metadata;

import ferdi.networkbuilder.config.MetaConfig;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RunSummary {
    List<DaySummary> days;


    double cTAusers;
    double testCapacaty;
    boolean prio;

    public RunSummary (MetaConfig config){
        this.days = new ArrayList<>();
        prio = config.isTestPrio();
        cTAusers = config.getcTAUsers();
        testCapacaty = config.getTestsPerNDays();
    }

    public void add(DaySummary daySummary){
        days.add(daySummary);
    }

    public void create(MetaConfig config) {
        String path = config.getPathOutput();
        String dir = "/users-testcap-prio_" + cTAusers + "-" + testCapacaty + "-" + prio ;
        File file = new File(path + dir);
        if (!file.exists()) { // check if directory does not exist
            boolean isCreated = file.mkdirs(); // create directory and its parent directories if they do not exist
        }
        boolean created = false;
        int c = 1;
        while (!created) {
            File fileToPrint = new File(file.getAbsolutePath() + "/day_summary_" + c + ".csv");
            if (fileToPrint.exists()) {
                c++;
            } else {
                created = true;
                String input = listToString(days);
                createFile(input,fileToPrint);
            }
        }
    }

    private String listToString(List<DaySummary> daySummaryList){
        StringBuilder s = new StringBuilder(daySummaryList.get(0).headerString());
        for(int i = 0; i < daySummaryList.size(); i++){
            s.append("\n").append(daySummaryList.get(i).csvString());
        }
        return s.toString();
    }
    private void createFile(String string, File file) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] bytes = string.getBytes();
            outputStream.write(bytes);
            outputStream.close();
            System.out.println("created and successfully written to: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("An error occurred while creating or writing to " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }

    public String getRunName() {
        return "users of app: " + cTAusers + ", test capacity: " + testCapacaty + ", priority for symptomatic: " + prio ;
    }
}
