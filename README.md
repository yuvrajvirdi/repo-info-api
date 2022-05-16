# repo-info-api

rest api for repository info

* returns information for a queried repo

# Use

Hit the following endpoint `https://repo-info-api.herokuapp.com/repoinfo?query=<YOUR-USERNAME>/<REPO-NAME>`

## Example

Endpoint: `https://repo-info-api.herokuapp.com/repoinfo?query=yuvrajvirdi/movie-recommender`

```java
{
  "status":"200",
  "message":"retrieved",
  "repoUrl":"https://github.com/yuvrajvirdi/movie-recommender",
  "repoName":"movie-recommender",
  "about":"Movie recommendation web app using machine learning",
  "forks":"0",
  "stars":"0",
  "mainLanguage":"Python",
  "languages": {
    "CSS":"37.7%",
    "HTML":"21.0%",
    "Python":"41.3%"
   }
}
```

# Installation

Clone the repo

```bash
git clone https://yuvrajvirdi/repo-info-api.git
```
