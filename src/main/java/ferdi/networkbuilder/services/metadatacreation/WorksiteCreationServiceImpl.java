package ferdi.networkbuilder.services.metadatacreation;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import ferdi.networkbuilder.model.contacts.WorksiteType;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorksiteCreationServiceImpl implements WorksiteCreationService {
    @Override
    public void createWorksiteTypes(List<List<String>> file, ModelFoundation modelFoundation, MetaConfig config) {
        List<String> headline = file.get(0);
        List<String> count = file.get(1);
        int total = Integer.parseInt(file.get(1).get(count.size()-1));
        for(int i = 0; i< headline.size() -1; i++){
            int amount = Integer.parseInt(file.get(1).get(i));
            String header = file.get(0).get(i);
            if(header.contains("-")){
                String[] strings = header.split("-");
                int min = Integer.parseInt(strings[0]);
                int max = Integer.parseInt(strings[1]);
                double relativeAmount = (double) amount/ (double) total;
                WorksiteType worksiteType =new WorksiteType(min,max,relativeAmount);
                modelFoundation.getWorksiteTypes().add(worksiteType);
            }else if(header.contains("+")) {
                String[] strings = header.split("\\+");
                int min = Integer.parseInt(strings[0]);
                int max = config.getMaxForHighestWorksiteSizeIsNTimesOfMin() * min;
                double relativeAmount = (double) amount/ (double) total;
                WorksiteType worksiteType =new WorksiteType(min,max,relativeAmount);
                modelFoundation.getWorksiteTypes().add(worksiteType);
            }
        }
    }
}
