##Car Sample
Car Sample of GWTP and GWT frameworks.

* [CarSample Demo](http://demos.arcbees.com/carsample-v4/)

##Application Login
The application login is: admin:qwerty

##Database Setup
* //TODO: specify postgresql version
* Create a database called 'carsample' with username role 'carsample' and password 'carsample'
* Database settings and credentials are in resources/META-INF/persistence.xml or in the pom.xml
* Run `mvn package -Ppostgres` or `mvn package -Pmysql` to setup persistence.xml in the target/classes path. 

##Running from the command line
* mvn clean package -Ppostgres
* mvn gwt:run
* Click on launch
* Log in as admin:qwerty

##IntelliJ IDEA Setup
* Checkout git repository
* Confirm import of project
* Go to Settings->Compiler->Java Compiler and set the maximum heap size to 1024Mb
* Enable the 'local' and 'postgresql' Maven profiles

##IntelliJ IDEA Hosted Mode
Running in GWT hosted mode from within IDEA

* Edit Configurations->Add->GWT
* Select the CarSample module
* VM options: -Xmx1024m -XX:MaxPermSize=256m

##Eclipse Setup
* Import Project using Egit, copy and past git url into git repository explorer
* Right click on Working directory
* Select Import Maven Projects (Must have Egit and Maven Egit Installed (m2e))

##Eclipse Hosted Mode
Running GWT hosted mode in eclipse

* Right click on project
* Goto Debug As
* Select Web Application
* Click on link that shows up in the Development Mode

##Cucumber Tests Setup
Download the Chrome web driver and install it in /usr/bin/...

* [Download](http://code.google.com/p/selenium/wiki/ChromeDriver)

###Eclipse Cucumber Setup
* http://mrpotes.github.com/cukes-jvm-eclipse-plugin/ - Find cucumber update site for the cucumber plugin here
* Run the cucumber selenium integration tests with "mvn integration-test"
* Right click on story and debug story.

##Hibernate Setup
There are two locations to update the persistence.xml, which one is for debugging and the other for deployment. 
The domain objects will need to be updated upon adding.  

 1. resourceTemplates/webapp/persistence.xml 
 2. src/main/resources/META-INF/persistence.xml
 3. Run `mvn package -Ppostgres` or `mvn package -Pmysql` after changes.

##Deploy
When deploying, be sure to have the the persistence profile selected or integrated into the profile being deployed.

* For instance to deploy to the demos server, the postgres setup is integrated into the `-Pdeploy-demo` profile. 
    ```
    mvn clean deploy -Pdeploy-demo
    ```
* [Tomcat Usage](http://tomcat.apache.org/maven-plugin-2.0/tomcat7-maven-plugin/usage.html)
* [Tomcat Demo Manager](http://demos.arcbees.com/manager)

##PhoneGap Build
Generically speaking the PhoneGap build wraps up the GWT module client side and builds a native version. No server side 
classes will be needed and can be deleted before zipping it and uploading it to Adobe Bd. We do this by exploding the
war file and deleting the server side and testing the web mode.

1. Run `mvn clean package`
2. Run `mvn resources:copy-resources jar:jar antrun:run -Pbuild-phonegap`
3. Find the PhoneGap zip in the target directory named `CarSample-Phonegap-Release.zip`

###Testing PhoneGap Build
1. Run `open -a Google\ Chrome --args --disable-web-security -–allow-file-access-from-files`
2. Run the `index.html` in chrome

###PhoneGap Upload
1. Goto https://build.phonegap.com/apps
2. Zip up app client side source only
3. Create development Android key like this. Do not loose your key. Do not forget your password/passphrase. 
    ``` 
    keytool -genkey -v -keystore brandon_donnelson.keystore -alias branflake2267@gmail.com -keyalg RSA -keysize 2048 -validity 10000 
    ``` 
4. Add key to adobe phone gap
5. Upload zip file of client side source 
6. Download and test the apk. Be sure the android sdk is installed locally. It can be deployed like this via usb. 
```
~/workspace-sdk/android-sdk/android-sdk-macosx/platform-tools/adb install CarSample-release.apk 
```

##FAQ

###Entity Manager exception occurs during Maven build
1. Check to see if the persistence.xml exists in target/CarSample-0.1-SNAPSHOT/WEB-INF/classes/META-INF/persistence.xml
  - Correct by running `mvn package -Ppostgres` is run.
2. Check the persistence.xml contains no variables like `${persistence.unit}`
  - run maven goal with -Ppostgres or -Pmysql or persistence profile with custom profile. 
