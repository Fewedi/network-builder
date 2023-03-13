package ferdi.networkbuilder.services;

import java.io.FileNotFoundException;
import java.util.List;

public interface ExtractionService {

    List<List<String>> dataToList(String path, String fileName) throws FileNotFoundException;
}
