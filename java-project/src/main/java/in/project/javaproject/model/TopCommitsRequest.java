package in.project.javaproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopCommitsRequest {

    private String name;
    private String order;

    public TopCommitsRequest(){}

    @Override
    public String toString() {
        return "TopCommitsRequest{" + "nameOfRepo='" + name + '\'' + ", orderBy='" + order + '\'' + '}';
    }


}
