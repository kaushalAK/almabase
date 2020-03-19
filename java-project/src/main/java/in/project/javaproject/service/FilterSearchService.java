package in.project.javaproject.service;

import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class FilterSearchService {

    private static String ITEMS = "items";
    private static int initialIndex =0;

    public JSONArray filterSearchData(ResponseEntity<String> js) {
        log.info("converting data into JSONArray");
        JSONObject searchedjsnobject = convertToJsonObject(js);
        JSONArray filteredJsonArray = (JSONArray) searchedjsnobject.get(ITEMS);
        return filteredJsonArray;
    }


    public JSONObject convertToJsonObject(ResponseEntity<String> dataFromSearch){
        return new JSONObject(dataFromSearch.getBody());
    }

}
