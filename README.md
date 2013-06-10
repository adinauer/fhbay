FHBay
=====

This is an auction platform which allows you to offer articles and bid on them.

This project is part of the SVE (Service Engineering) course at FH Hagenberg.


Building and Deploying
----------------------

To build the application and deploy it to JBoss (JBoss and MySql need to be running and the fhbay database needs to exist) execute the following command in the root of the project.

    $ mvn clean install -DskipITs && mvn -pl fhbay-ear jboss-as:deploy

    
After the EAR file has been deployed you can execute the long running tests (Integration and EndToEnd) with the command:

    $ mvn verify


Inserting Test Data
-------------------

Go to [insert test data page](http://localhost:8080/fhbay-web/insertTestData) to have the system insert:
* Categories
* Customers
* Articles
* Bids on some of the articles

After the insert has finished you will be redirected to the [index page](http://localhost:8080/fhbay-web/)


Login
-----

    +------------------+-----------+-------------+
    | USERNAME         | PASSWORD  | ROLES       |
    +------------------+-----------+-------------+
    | tom.seller       | expensive | USER        |
    | bud.bidder       | cheap     | USER        |
    | admin            | power     | USER, ADMIN |
    | otto.otherbidder | cheaper   | USER        |
    +------------------+-----------+-------------+


Roles
-----

Users can:
* Offer articles: [offer article page](http://localhost:8080/fhbay-web/offerArticle)
* Bid on articles: [article details page](http://localhost:8080/fhbay-web/article/19/Canon-EOS-1D-X) (calls http://localhost:8080/fhbay-web/bid)

Admins can:
* View the bid history: [bid history page](http://localhost:8080/fhbay-web/bidHistory?articleId=23)

Anonymous (everyone) can:
* View categories: [index](http://localhost:8080/fhbay-web/) (bottom left)
* List all articles: [index](http://localhost:8080/fhbay-web/)
* List only articles in a certain category and its subcategories: [category page](http://localhost:8080/fhbay-web/category/57/Photography)
* List only articles in a certain subcategory: [subcategory page](http://localhost:8080/fhbay-web/category/58/Photography/Cameras)
* View article details: [article details page](http://localhost:8080/fhbay-web/article/76/Canon-EOS-7D-(SLR)-Body)
* Search for articles by name and description: [index page](http://localhost:8080/fhbay-web/) using the search form (top left)
* Search for articles by name and description that are in a certain category or one of its subcategories: [index page](http://localhost:8080/fhbay-web/) using the search form (top left)
* Login using a username and password: [index page](http://localhost:8080/fhbay-web/) using the login form (middle left)


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
