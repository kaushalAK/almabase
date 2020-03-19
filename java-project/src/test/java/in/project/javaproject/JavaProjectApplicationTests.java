package in.project.javaproject;

import in.project.javaproject.model.SearchRepoRequest;
import in.project.javaproject.model.SearchRepoResponse;
import in.project.javaproject.service.DataAggregator;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Ignore
class JavaProjectApplicationTests {

	@Autowired
	DataAggregator dataAggregator;


	@Test
	void contextLoads() {
	}

	@Test
	void searchRepoService() throws ParseException {
		SearchRepoRequest searchRepoRequest = new SearchRepoRequest();
		searchRepoRequest.setOrderByPopularity(4);
		searchRepoRequest.setName("google");
		searchRepoRequest.setOrderByCommits(5);
		SearchRepoResponse searchRepoResponse;
		searchRepoResponse	= dataAggregator.fetchFilteredDetails(searchRepoRequest);
		Assert.assertNotNull(searchRepoResponse);
	}

}
