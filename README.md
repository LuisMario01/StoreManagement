# StoreManagement
REST API for small snack store using Spring MVC.
The API receives request under specific URLs, retrieving data from the database and performing actions associated with each
URL.

## Getting Started

To get project for testing and developing purposes just perform:

```
git clone https://github.com/LuisMario01/StoreManagement.git
```

or download as zip file. To get a WAR file, contact author.

After dowloading the project in the desired location, Spring STS is recommended to open it.
To import it from Spring STS, go to File > Open Projects From File System and browse the location where git/zip was put under.

### Prerequisites
Tools required to develop or test the API:

```
Java 8.0
Pivotal tC Server Developer Edition v4.0./ [Apache Tomcat](http://www.baeldung.com/tomcat-deploy-war)
Postman
Snack store - REST API.postman_collection attached to test requests.

```

### Installing

Copy WAR file, go to the webapps folder of your server and put it  there.
If you are deploying it from Spring STS select the Run>Run, select Pivotal Server and the project will be automatically deployed.
In Postgres, restore the database back up found [here](product_store.sql).
IMPORTANT: Project is set up to work with the following DB credentials:
	username: postgres
	password: root
If you have different credentials to your Postgres server, you can update this in class com.store.management.configuration.JpaConfiguration (lines 49 and 50) setting your own credentials. WAR FILE WILL NOT WORK PROPERLY WITH DIFFERENT CREDENTIALS.

Import [JSON file](Snack store - REST API.postman_collection.json) to your Postman, by selecting option Import>From file (browsing the location where the project is located).

Once project is deployed and database is running, test it with Postman with the collections provided before.

## Built With

* [Spring STS](https://spring.io/tools) - The web framework used.
* [Maven](https://maven.apache.org/) - Dependency Management,
* [Postgresql](https://rometools.github.io/rome/) - Used to manage database.
* [Postman](https://www.getpostman.com/) - Used to test the API

## Authors

* **Luis De Paz** - *Initial work* - [StoreManagement](https://github.com/LuisMario01/StoreManagement)

## License

This project is licensed under the GPL v3.0. License - see the [LICENSE.md](LICENSE.md) file for details



