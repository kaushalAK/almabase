package in.project.javaproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchRepoRequest {

    private String name;
    private int orderByPopularity;
    private int orderByCommits;

    public SearchRepoRequest(){}

    @Override
    public String toString() {
        return "SearchRepoRequest{" + "nameOfOrg='" + name + '\'' + ", top '" + orderByPopularity +" popular"+ '\'' + ", top '" + orderByCommits +" commits"+ '\'' + '}';
    }
}
