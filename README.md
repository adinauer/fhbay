FHBay
=====

This is an auction platform which allows you to offer articles and bid on them.


Building and Deploying
----------------------

To build the application and deploy it to JBoss (needs to be running) execute the following command in the root of the project.
    $ mvn clean install -DskipTests=true; mvn -pl fhbay-ear jboss-as:deploy

After the EAR file has been deployed you can execute the tests with the command:
    $ mvn test


Inserting Test Data
-------------------

Go to http://localhost:8080/fhbay-web/insertTestData to have the system insert:
* Categories
* Customers
* Articles
* Bids on some of the articles
after the insert has finished you will be redirected to the index page (/)


Login
-----

USERNAME: PASSWORD
* tom.seller: expensive
* bud.bidder: cheap
* admin: power


Roles
-----

Users can:
* Offer articles: http://localhost:8080/fhbay-web/offerArticle
* Bid on articles: http://localhost:8080/fhbay-web/article/19/Canon-EOS-1D-X-(SLR)-Body (calls http://localhost:8080/fhbay-web/bid)

Admins can:
* View the bid history: http://localhost:8080/fhbay-web/bidHistory?articleId=23

Anonymous (everyone) can:
* View categories: http://localhost:8080/ (bottom left)
* List all articles: http://localhost:8080/
* List only articles in a certain category and its subcategories: http://localhost:8080/fhbay-web/category/57/Photography
* List only articles in a certain subcategory: http://localhost:8080/fhbay-web/category/58/Photography/Cameras
* View article details: http://localhost:8080/fhbay-web/article/76/Canon-EOS-7D-(SLR)-Body
* Search for articles by name and description: http://localhost:8080/ using the search form (top left)
* Search for articles by name and description that are in a certain category or one of its subcategories: http://localhost:8080/ using the search form (top left)
* Login using a username and password: http://localhost:8080/ using the login form (middle left)


Internal Structure:
-------------------

fhbay-commons
* Contains classes used by fhbay-server and fhbay-web

fhbay-config
* Contains configuration files like standalone.xml for JBoss

fhbay-ear
* Assembles the EAR file for deployment to the Application Server.

fhbay-parent
* Contains common Maven configuration for all the modules.

fhbay-server
* Contains the Beans, DAOs and Business Logic of the application.

fhbay-web
* Contains the Web Client for the application.
* Assembles the WAR file.
