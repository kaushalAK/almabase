package in.project.javaproject.controller;

import in.project.javaproject.model.SearchRepoRequest;
import in.project.javaproject.model.SearchRepoResponse;
import in.project.javaproject.service.DataAggregator;
import lombok.extern.log4j.Log4j2;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@CrossOrigin
public class GetCommitCount {

    @Autowired
    DataAggregator dataAggregator;

    @RequestMapping(value = "/api/search/repo/commitcounts", method = RequestMethod.GET)
    public ResponseEntity<?> searchRepository(@RequestBody SearchRepoRequest searchRepoRequest, BindingResult bindingResult) throws ParseException {
        log.info("Searching Repository for "+searchRepoRequest.getName()+" top "+searchRepoRequest.getOrderByPopularity()+" popular and top "+searchRepoRequest.getOrderByCommits()+"commits done");
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<String>("Binding Issues for /api/search/repo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        SearchRepoResponse searchRepoResponse;
        searchRepoResponse = dataAggregator.fetchCountOfCommits(searchRepoRequest);
        return new ResponseEntity(searchRepoResponse.getData().toString(), HttpStatus.OK);
    }



}
