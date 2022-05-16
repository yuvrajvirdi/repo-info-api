package gitrepo.api.service;

import gitrepo.api.model.RepoInfo;

public interface RepoInfoService {
    RepoInfo getInfo(String url);
}
