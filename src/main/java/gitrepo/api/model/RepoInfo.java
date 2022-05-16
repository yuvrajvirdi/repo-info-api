package gitrepo.api.model;

import java.util.Collections;
import java.util.Map;

/**
 * Repository information response when url is queried
 * 
 * @author Yuvraj Virdi
 */
public class RepoInfo {
    private final String status;
    private final String message;
    private final String repoUrl;
    private final String repoName;
    private final String about;
    private final String forks;
    private final String stars;
    private final String mainLanguage;
    private final Map<String,String> languages;

    public RepoInfo(String status, String message, String repoUrl ,String repoName, String about, String forks, String stars, String mainLanguage, Map<String, String> languages){
        this.status = status;
        this.message = message;
        this.repoUrl = repoUrl;
        this.repoName = repoName;
        this.about = about;
        this.forks = forks;
        this.stars = stars;
        this.mainLanguage = mainLanguage;
        this.languages = languages;
    }

    public static RepoInfo error(String status, String message){
        return new RepoInfo(status, message, "", "", "", "", "", "", Collections.emptyMap());
    }

    public String getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }

    public String getRepoUrl(){
        return repoUrl;
    }

    public String getRepoName(){
        return repoName;
    }

    public String getAbout(){
        return about;
    }

    public String getForks(){
        return forks;
    }

    public String getStars(){
        return stars;
    }

    public String getMainLanguage(){
        return mainLanguage;
    }

    public Map<String, String> getLanguages(){
        return languages;
    }

    public boolean equals(RepoInfo other){
        return this.repoUrl.equals(other.repoUrl);
    }
}
