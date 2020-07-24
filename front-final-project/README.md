# TmnT-CRM

![IronHack Logo](https://s3-eu-west-1.amazonaws.com/ih-materials/uploads/upload_d5c5793015fec3be28a63c4fa3dd4d55.png)

# CRMHomework - Unit5

<p align="center"><strong> Cristian Saavedra, Luis Feijoo, Ivan de Gregorio, Andrea Rodriguez</strong></p>

* [Goal](#goal)

* [How it works](#how-it-works)

* [Security](#security)

* [Methodology](#methodology)

* [Tools](#tools)


## <a name="goal"></a>Goal
Based on our last homework [CRM Homework 4](https://github.com/ccsi923/TmnT-CRM-4) where we transformed a monolithic application into a microservices architecture, we have now developed the front-end of the application. The project is a **CRM** (*Customer Relationship Management*) system with a Java Program allowing a **Sales Person** to generate, store data (**Leads**, **Opportunities**, **Contacts** and **Accounts**) and report features based on the specific consults they want to do.

This new project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 9.1.3.

## <a name="how-it-works"></a>How it works

This project works with the current branch: Master.

**The backend has been deployed into Heroku**, so there is no need to run it in local in order to test the front. (please bear in mind that when you run the application the first calls could fail because the Heroku servers might be asleep).

If you wish to run the backend in local you can download the frontAPI branch from [CRM Homework 4](https://github.com/ccsi923/TmnT-CRM-4) and follow an extra step you have to do on this Angular project which is:
In the enviroments folder --> change both apiUrl to : http://localhost:8080/

STEPS TO RUN THE PROJECT

1. Download the **CRM-Homework-Unit5** repository on your local computer

2. You will need to execute this command:
```npm install```
and then
```ng serve``` in order to execute the project

3. Navigate to `http://localhost:4200/`.

![Photo LOGIN](https://i.ibb.co/4VcgM6L/login.png)


## <a name="security"></a>Security
* We implemented BasicAuth security on the edge service for classic credential indentification.

All routes are secure. It is mandatory to be logged in the application. So even if you put directly the HTTP route without being logged in you will be redirected to the login page.

There are two users you can use:

````
user: admin
password: admin

user: salesrep
password: admin
````

* If you are logged as a SalesRep you will see the Create SalesRep option disabled because you need to registred as an Admin.

## <a name="methodology"></a>Methodology

The front was created using a Bootstrap template.

We implemented:
- navbar (user display with a logout option)
- sidebar (menu with the 6 main actions that can be performed + a dropdown menu on the *Reports* option)
- error and success popup notifications
- reactive forms with validators
- dynamic graphs for the reports
- TMNT logo favicon

## <a name="tools"></a>Tools used
- IntelliJ
- Spring
- MySQL
- Postman
- Heroku
- PostgreSQL
- Figma (for interface design)
- Angular
- Bootstrap

If any doubts do not hesitate to contact us! We look forward for your feedback

## Team 
<table>
<td align="center"><a href="https://github.com/luismiguelfeijoo"><img src="https://avatars1.githubusercontent.com/u/54450673?s=400&u=58ff2bef6ab37fd73f17047f011068ffba3c91dd&v=4" width="100px;" alt="Luis avatar"/><br/><sub><b>Luis Miguel Feijo</b></sub></a><br/><a href="https://github.com/luismiguelfeijoo"></a>
<td align="center"><a href="https://github.com/IvanDGregor"><img src="https://avatars1.githubusercontent.com/u/22318386?s=400&u=65b74839b2f9cfbf6e1b636a4dffdf8dfd7541e0&v=4" width="100px;" alt="Ivan avatar"/><br/><sub><b>Ivan De Gregorio</b></sub></a><br/><a href="https://github.com/IvanDGregor"></a>
<td align="center"><a href="https://github.com/ccsi923"><img src="https://avatars2.githubusercontent.com/u/65124499?s=400&v=4" width="100px;" alt="Cristian avatar"/><br/><sub><b>Cristian Saavedra</b></sub></a><br/><a href="https://github.com/ccsi923"></a>
<td align="center"><a href="https://github.com/AndreaRdzPerez"><img src="https://avatars0.githubusercontent.com/u/51881798?s=400&u=b6b7e44b6b90cf8824c72bb2209d4b02e1302e6e&v=4" width="100px;" alt="Andrea avatar"/><br/><sub><b>Andrea Rodrigez</b></sub></a><br/><a href="https://github.com/AndreaRdzPerez"></a>
</table>

