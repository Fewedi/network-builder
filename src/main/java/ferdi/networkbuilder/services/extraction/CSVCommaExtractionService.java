package ferdi.networkbuilder.services.extraction;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CSVCommaExtractionService implements ExtractionService {

    @Override
    public List<List<String>> dataToList(String path, String fileName) throws FileNotFoundException {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path + "/" + fileName))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }
        System.out.println("Extraction from " + path + "/" + fileName);
        return records;
    }

    private String replaceFalseCommas(String line){
        if(line.contains("\"")){
            StringBuilder l = new StringBuilder(line);
            int i1 = l.indexOf("\"");
            int i2 = l.lastIndexOf("\"");
            if(i1 == i2){
                return line;
            }
            String subString = l.substring(i1,i2);
            return l.replace(i1,i2,subString.replace(",","")).toString();
        }
        else return line;
    }

    private List<String> getRecordFromLine(String line) {
        line = replaceFalseCommas(line);
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }



}
