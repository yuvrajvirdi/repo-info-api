package gitrepo.api.service;
import gitrepo.api.model.RepoInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlUnorderedList;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlStrong;

@Service
public class ServiceImplementation implements RepoInfoService{
    @Override
    public RepoInfo getInfo(String url){
        return scrapeInfo(url);
    }
    private RepoInfo scrapeInfo(String url){
        try {
            WebClient client = new WebClient();
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);

            // repo url
            String repoUrl = "https://github.com/"+url;
            HtmlPage repoPage = client.getPage(repoUrl);

            // repo name
            List<HtmlStrong> nameList = repoPage.getByXPath("//strong");
            String repoName = nameList.get(0).asNormalizedText();

            // about
            List<HtmlParagraph> aboutList = repoPage.getByXPath("//p[@class='f4 my-3']");
            if (aboutList.size()==0) {
                String about = "";
                 // forks
                List<DomElement> forksList = repoPage.getElementsById("repo-network-counter");
                String forks = forksList.get(0).asNormalizedText();
                // stars
                List<DomElement> starsList = repoPage.getElementsById("repo-stars-counter-star");
                String stars = starsList.get(0).asNormalizedText();
                // languages
                List<HtmlUnorderedList> languagesList = repoPage.getByXPath("//ul[@class='list-style-none']");
                String languagesStr =languagesList.get(0).asNormalizedText();
                languagesStr = languagesStr.replaceAll("\n", " ");
                String[] langsArr = languagesStr.split(" ");
                Map<String, String> languages = new TreeMap<>();
                for (int i = 0; i <= langsArr.length - 2; i = i + 2){
                    languages.put(langsArr[i], langsArr[i+1]);
                }
                // main language
                String mainLanguage = langsArr[0];
                client.close();
                return new RepoInfo("200", "retrieved", repoUrl, repoName, about, forks, stars, mainLanguage, languages);
            }
            String about = aboutList.get(0).asNormalizedText();
            

            // forks
            List<DomElement> forksList = repoPage.getElementsById("repo-network-counter");
            String forks = forksList.get(0).asNormalizedText();

            // stars
            List<DomElement> starsList = repoPage.getElementsById("repo-stars-counter-star");
            String stars = starsList.get(0).asNormalizedText();
            
            // languages
            List<HtmlUnorderedList> languagesList = repoPage.getByXPath("//ul[@class='list-style-none']");
            String languagesStr =languagesList.get(0).asNormalizedText();
            languagesStr = languagesStr.replaceAll("\n", " ");
            String[] langsArr = languagesStr.split(" ");
            Map<String, String> languages = new TreeMap<>();
            for (int i = 0; i <= langsArr.length - 2; i = i + 2){
                languages.put(langsArr[i], langsArr[i+1]);
            }

            // main language
            String mainLanguage = langsArr[0];
           

            client.close();
            return new RepoInfo("200", "retrieved", repoUrl, repoName, about, forks, stars, mainLanguage, languages);
        } catch (IOException err){
            return RepoInfo.error("400", err.getMessage());
        }
    }
}