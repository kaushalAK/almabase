package in.project.javaproject.service;

import in.project.javaproject.model.SearchRepoRequest;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@Service
public class GetTopCommits {

    public Map<String,List<JSONObject>> getTopCommits(Map<String,JSONArray> filteredJsonArray, SearchRepoRequest searchRepoRequest){
        log.info("Getting top commits based on given counts");
        Map<String,List<JSONObject>> topCommits = new HashMap<>();

        List<JSONObject> filteredCommits = new ArrayList<>();

        for(Map.Entry<String,JSONArray> entry: filteredJsonArray.entrySet()){
             {
                 for(int count=0; count<entry.getValue().length();count++){
                     if(count < searchRepoRequest.getOrderByCommits()){
                         filteredCommits.add((JSONObject) entry.getValue().get(count));
                     }
                 }
                topCommits.put(entry.getKey(), filteredCommits);
            }
        }
        return topCommits;
    }

    public Map<String, Object> countOfCommit(Map<String, List<JSONObject>> filteredJsonArray){
        Map<String,Object> countOfCommit = new HashMap<>();
        List<Object> countCommits = new ArrayList<>();
        Map<String,Object> tempMap = new HashMap<>();
        for(Map.Entry<String,List<JSONObject>> entry: filteredJsonArray.entrySet()){
            for(int count=0; count < entry.getValue().size();count++){
                tempMap.put("commit",entry.getValue().get(count).get("commit"));
                countCommits.add(tempMap.get("comment_count"));
            }
            countOfCommit.put(entry.getKey(),countCommits);
        }
        return countOfCommit;
    }
}
