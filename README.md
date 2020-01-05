# SpringBoot Social Network Project

- Circle CI build status:
[![CircleCI](https://circleci.com/gh/dmcheremisin/SpringBootSocialNetwork.svg?style=svg&circle-token=54d82edd98892db8d4e69740d9bee65e48242495)](https://circleci.com/gh/dmcheremisin/SpringBootSocialNetwork)

## Introduction
This project is a continuation of the previous one. The previous project was made with pure Servlets and JSP
 technologies [details here](https://github.com/dmcheremisin/SocialNetwork). I used the same
  template layout and created a new project using SpringBoot.

## Functionality
### Base
- Login / Logout
- Register
- View own profile
- View other users profiles
- Send messages
- Add and remove friends
- Accept and decline friends requests
- Update profile information
- Change password
- Search for other users
- View other users friends lists
- View last messages
- Drag and drop profile image upload
- Internationalization: English and Russian languages
- Localization

### Admin
- Make other user admin
- Block user

## Technologies used 
### Stack:
- Java 11
- Spring: SpringBoot, MVC, Data JPA, Security, DevTools, Actuator
- Maven
- H2, Mysql, ClearDb(Heroku)
- Thymeleaf
- Javascript, jQuery
- Html, CSS, Bootstrap
- Test: JUnit, Mockito

### Environment
- Heroku as CD tool
- Circle CI as CI tool
- Docker for containerization

## Application demo is available on Heroku  
Link: [https://springboot-social-network.herokuapp.com/](https://springboot-social-network.herokuapp.com/)  
Cersei is average user and Tyrion Lannister is super admin. Tyrion can't be blocked or made average admin user.

Credentials for Cersei Lannister:
 - login cersei@lannister.ru
 - password fun123  
 
Tyrion Lannister credentials:
 - login tyrion@lannister.ru
 - password fun123
 
 ## Quick start
 
 ### Required:
 - Java 11
 - Maven 
 
 ### Steps:
 1. git clone https://github.com/dmcheremisin/SpringBootSocialNetwork.git
 2. mvn clean package -DskipTests=true
 3. cd web.social.network/target
 4. java -jar -Dspring.profiles.active=dev social-network.jar
 5. Go to http://localhost:8080

## Application profiles
- dev - profile for development, uses embedded H2 database
- qa - profile for qa testing, uses Mysql either local, or from docker container: [mysql docker container](https://github.com/dmcheremisin/SpringBootSocialNetwork/blob/master/docker/mysql%20docker%20commands.md)
- prod - profile for Heroku, uses ClearDb. ClearDb is Heroku analog of Mysql: [details](https://devcenter.heroku.com/articles/cleardb)
- docker - profile for Docker. It is created to test connection between 2 containers: mysql and social-network(spring
 boot app)
 
## Docker
This application is tested in Docker.  
I made 2 containers: mysql and social-network, and then linked them.  
Commands for images creation and containers startup may be found in the directory: [docker](https://github.com/dmcheremisin/SpringBootSocialNetwork/tree/master/docker)

## Screenshots
![1](https://raw.githubusercontent.com/dmcheremisin/SpringBootSocialNetwork/master/screenshots/1.png)
![2](https://raw.githubusercontent.com/dmcheremisin/SpringBootSocialNetwork/master/screenshots/2.png)
![3](https://raw.githubusercontent.com/dmcheremisin/SpringBootSocialNetwork/master/screenshots/3.png)
![4](https://raw.githubusercontent.com/dmcheremisin/SpringBootSocialNetwork/master/screenshots/4.png)
![5](https://raw.githubusercontent.com/dmcheremisin/SpringBootSocialNetwork/master/screenshots/5.png)
![6](https://raw.githubusercontent.com/dmcheremisin/SpringBootSocialNetwork/master/screenshots/6.png)
![7](https://raw.githubusercontent.com/dmcheremisin/SpringBootSocialNetwork/master/screenshots/7.png)
![8](https://raw.githubusercontent.com/dmcheremisin/SpringBootSocialNetwork/master/screenshots/8.png)
![9](https://raw.githubusercontent.com/dmcheremisin/SpringBootSocialNetwork/master/screenshots/9.png)
![10](https://raw.githubusercontent.com/dmcheremisin/SpringBootSocialNetwork/master/screenshots/10.png)
![11](https://raw.githubusercontent.com/dmcheremisin/SpringBootSocialNetwork/master/screenshots/11.png)
![12](https://raw.githubusercontent.com/dmcheremisin/SpringBootSocialNetwork/master/screenshots/12.png)
 
### Todo: 
- Improve test coverage. Yes, I know it is very important, but I don't have enough time.