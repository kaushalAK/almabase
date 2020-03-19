package in.project.javaproject.service;

import com.google.gson.JsonArray;
import com.sun.java.browser.plugin2.DOM;
import in.project.javaproject.model.SearchRepoRequest;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Log4j2
@Service
public class SearchRepoService {

    @Autowired
    FilterSearchService filterSearchService;



    private static String DOMAIN_URL = "https://api.github.com/search/repositories?q=";

    public JSONArray searchPopularRepoAndCommittee(SearchRepoRequest searchRepoRequest) {
        log.info("Searching for top organizations");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth("e72e16c7e42f292c6912e7710c838347ae178b4a");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = DOMAIN_URL+searchRepoRequest.getName()+"&sort=fork&order=desc";
        log.info("the url: "+url);
        ResponseEntity<String> js = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        JSONArray filterJsonData = filterSearchService.filterSearchData(js);
        return filterJsonData;
    }


}
