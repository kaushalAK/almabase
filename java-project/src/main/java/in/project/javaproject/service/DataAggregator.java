package in.project.javaproject.service;

import in.project.javaproject.model.SearchRepoRequest;
import in.project.javaproject.model.SearchRepoResponse;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class DataAggregator {
    // data fetcher and aggregator

    @Autowired
    SearchRepoService searchRepoService;

    @Autowired
    GetTopOrg getTopOrg;

    @Autowired
    GetCommits getCommits;

//    @Autowired
//    SearchRepoResponse searchRepoResponse;

    @Autowired
    GetTopCommits getTopCommits;


    public SearchRepoResponse fetchFilteredDetails(SearchRepoRequest searchRepoRequest) throws ParseException {
        Map<String, List<JSONObject>> filterCommits = fetchAndAggregateData(searchRepoRequest);
        SearchRepoResponse searchRepoResponse = new SearchRepoResponse();
        searchRepoResponse.setData(filterCommits);
        searchRepoResponse.setError(false);
        return searchRepoResponse;
    }

    public SearchRepoResponse fetchCountOfCommits(SearchRepoRequest searchRepoRequest) throws ParseException {
        Map<String, List<JSONObject>> filterCommits = fetchAndAggregateData(searchRepoRequest);
        Map<String, Object> countOfCommits = getTopCommits.countOfCommit(filterCommits);
        SearchRepoResponse searchRepoResponse = new SearchRepoResponse();
        searchRepoResponse.setData(countOfCommits);
        searchRepoResponse.setError(false);
        return searchRepoResponse;
    }

    public Map<String, List<JSONObject>> fetchAndAggregateData(SearchRepoRequest searchRepoRequest){
        log.info("fetching and aggregating data");
        JSONArray filteredRepoData = searchRepoService.searchPopularRepoAndCommittee(searchRepoRequest);
        log.info("getting top org from the list");
        List<Object> topOrganisattions = getTopOrg.getTopOrganisattions(filteredRepoData, searchRepoRequest);
        log.info("getting popular committees");
        Map<String,JSONArray> topCommits = getCommits.getPopularCommittees(topOrganisattions, searchRepoRequest);
        log.info("got popular committees");
        return getTopCommits.getTopCommits(topCommits,searchRepoRequest);
    }


}
