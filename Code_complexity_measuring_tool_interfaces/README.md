# CDE Code Complexity Mesuring Tool - Build guide

### Prerequisites
1) Maven installed
2) NodeJs and npm installed
3) MongoDB installed
4) git installed

### Steps
1) Start MongoDB
2) Go to mongod application in a new terminal
3) Create a database with name 'acc' by using command ```use acc```
1) Make a directory in your computer as the root directory of the project. eg: ```mkdir cde-ccmt-project```
1) Change working directory to project root directory. eg: ```cd cde-ccmt-project```
3) Clone the angular - electron project repository. ```git clone <example repo>.git```
1) Change working directory to angular - electron project derectory. ```cd CDE-ccmt-electron```
2) Get npm dependencies. ```npm install```
2) Build the project by running. ```npm run build```
2) Package the electron project by running. ```npm run package```
2) Go back to project root directory. eg: ```cd ..```
1) Clone the CCMT java project repository. ```git clone <example repo>.git```
1) Change working directory to angular - electron project derectory. ```cd CDE-CCMT```
2) Install dependencies by running. ```mvn clean install```
2) Build the project by running. ```mvn clean package```
5) Copy built jar file ```ccmt-project/CDE-CCMT/target/CDE-CCMT-0.0.1-SNAPSHOT.jar``` to electron package location ```cde-ccmt-project/CDE-ccmt-electron/ccmt-electron-win32-x64```
6) Copy config folder ```ccmt-project/CDE-CCMT/config``` from CCMT root folder to electron package location ```cde-ccmt-project/CDE-ccmt-electron/ccmt-electron-win32-x64```
7) Finally, run the jar file ```cde-ccmt-project/ccmt-electron/ccmt-electron-win32-x64/CDE-CCMT-0.0.1-SNAPSHOT.jar```
