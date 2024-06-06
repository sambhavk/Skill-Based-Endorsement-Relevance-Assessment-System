# Skill-Based-Endorsement-Relevance-Assessment-System
A system that can analyze a set of professional networking platforms profiles, the skills endorsed on those profiles, the endorsers, and the endorsers' profiles

## DesignDoc
```
https://docs.google.com/document/d/1vsI4W4_KHoHw1mHswlcZ695bgBrMJPR9ODhOZYqCmhE/edit?usp=sharing
```

## Set up on local
### Requirements
1. Intellij IDE
2. Open JDK >= 17
3. Maven - installed locally (or installed usually with IntelliJ)

### Setup IDE
Go to Run -> Edit Configurations -> Environment Variables:
add the following:
```
SPRING_PROFILES_ACTIVE=local
```

### Setup local db
1. Download neo4j db in local

   ```https://neo4j.com/download/```

2. Open neo4j application and create a dbms service and start the database with username as neo4j (default) and password as password 

3. Apply dummy data to local db by executing ./scripts/populateLocalDb.sql file in your local db
   
* Run the application from IDE