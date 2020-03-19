package in.project.javaproject.service;

import in.project.javaproject.model.SearchRepoRequest;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class GetTopOrg {

    private int counter=0;
    public List<Object> getTopOrganisattions(JSONArray filteredJsonArray, SearchRepoRequest searchRepoRequest){
        log.info("Getting top organisations based on fork counts, limiting by the count given by the user ");
        List<Object> topOrganizations = new ArrayList<>();
        for(Object jsObj : filteredJsonArray){
            if(counter < searchRepoRequest.getOrderByPopularity() && ((JSONObject)((JSONObject) jsObj).get("owner")).get("type").toString().equalsIgnoreCase("Organization")) {
                topOrganizations.add(jsObj);
                counter++;
            }else break;
        }
        return topOrganizations;
    }

}
