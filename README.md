# GithubRepoExplorer
This is Spring boot application that can be used to retrieve information about Github user's repositories and all branches for said repositories.

# Install
* Clone this repository
* Project was made and built on Java 17 and Spring boot 3, so I recommend using these.
* Build the project using ```mvn clean package```
* Run the service using ``` mvn spring-boot:run```
If everything was successful you should see these

```
2023-06-08T21:25:40.944+02:00  INFO 17000 --- [nio-8080-exec-3] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2023-06-08T21:25:40.945+02:00  INFO 17000 --- [nio-8080-exec-3] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
```

# Usage
### Get user repository data
### Request
```
GET /api/explorer/:username 
Accept: application/json
```
### Response
```
Status: 200 OK
Body: 
[
    {
        "repository_name": "name",
        "owner": "owner",
        "branch_info": [
            {
                "sha": "07feb6",
                "name": "branchName"
            }
        ]
    }
]
```
