#Website Tracker

a service that tracks referrers to a website and their counts by domain using programming languages and tools of your choice. 
- Create an endpoint that accepts a url as a parameter and counts the number of times the domain in the url has been seen.
- Create an endpoint that returns the 3 highest seen referring domains.
- Simple UI to display the 3 highest.

#Setup

git clone https://github.com/urpalananth/my-projects.git

cd website-tracker

In Eclipse
- Import -> Existing Maven Projects 
  - give the full for path to pom.xml ex: ~/Downloads/website-tracker/pom.xml
  
- Open class -> inst.an.websitetracker.WebsiteTrackerApplication
  - Right Click -> Run As Java Application
  
The application should be up and running on http://localhost:8080/

#User Interface - 

credentials to login - user/password

Copy and paste an URL in the text box, hit submit.

A table below will show the domain and hit counts.

#Rest Endpoints - 

http://localhost:8080/urls Method POST - Submit a URL with rest client in the format below

credentials to access Rest endpoints - user/password

{
"url": "https://www.google.com/"
}

http://localhost:8080/urls Method GET - Gives all the URLs

http://localhost:8080/urls/top3 Method GET - Gives top3 most visited domains

http://localhost:8080/urls/domainCounts GET - Gives all the domains with counts.



