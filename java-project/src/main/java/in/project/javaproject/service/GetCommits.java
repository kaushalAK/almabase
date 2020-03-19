package in.project.javaproject.service;

import in.project.javaproject.model.SearchRepoRequest;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Log4j2
@Service
public class GetCommits {

    @Autowired
    FilterSearchService filterSearchService;

    private static String DOMAIN_URL = "https://api.github.com/search/commits?q=";

    public Map<String,JSONArray> getPopularCommittees(List<Object> topOrg, SearchRepoRequest searchRepoRequest){

        Map<String,JSONArray> topCommittees = new HashMap<>();
        log.info("Searching for top commits");
        for(Object obj:topOrg){
            topCommittees.put((String) ((JSONObject) obj).get("full_name"),getCommitsOfOrg(obj));
        }
        return topCommittees;
    }

    public JSONArray getCommitsOfOrg(Object orgObj){
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(orgObj);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Accept","application/vnd.github.cloak-preview");
        headers.setBasicAuth("e72e16c7e42f292c6912e7710c838347ae178b4a");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = DOMAIN_URL+((JSONObject) orgObj).get("full_name")+"&order=desc";
        log.info("the url: "+url);
        ResponseEntity<String> js = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        JSONArray filterJsonData = filterSearchService.filterSearchData(js);
        return filterJsonData;
    }

}
