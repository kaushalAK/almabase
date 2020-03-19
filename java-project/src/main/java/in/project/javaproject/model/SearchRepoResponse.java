package in.project.javaproject.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonPropertyOrder({"data", "msg", "isError","errorMsg"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class SearchRepoResponse {

    @JsonProperty("data")
    private Object data;

    @JsonProperty ("msg")
    private String msg;

    @JsonProperty ("isError")
    private boolean isError;

    @JsonProperty ("errorMsg")
    private String errorMsg;

    public SearchRepoResponse() {
    }

    @Override
    public String toString() {
        return "SearchRepoResponse{" + "msg='" + msg + '\'' + ", isError=" + isError + ", errorMsg='" + errorMsg + '\'' + '}';
    }

}
