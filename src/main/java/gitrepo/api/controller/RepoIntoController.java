package gitrepo.api.controller;

import gitrepo.api.model.RepoInfo;
import gitrepo.api.service.RepoInfoService;
import gitrepo.api.service.ServiceImplementation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class RepoIntoController {
    @Autowired
    private RepoInfoService repoInfoService;

    @GetMapping("/repoinfo")
    public RepoInfo getInfo(@RequestParam(value = "query", defaultValue = "error") String url){
        if (!url.equals("error")){
            ServiceImplementation service = new ServiceImplementation();
            return repoInfoService.getInfo(url);
        } else {
            return RepoInfo.error("400", "Something went wrong");
        }
    }
}
