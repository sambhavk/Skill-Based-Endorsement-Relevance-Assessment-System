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
3. Docker
4. Maven - installed locally (or installed usually with IntelliJ)

### Setup IDE
Go to Run -> Edit Configurations -> Environment Variables:
add the following:
```
SPRING_PROFILES_ACTIVE=local
```

### Setup local db on docker instance
1. bring up local postgres docker instance

   ```docker-compose up -d```

2. Apply dummy data to local db by copying insert statements from ./scripts/populateLocalDb.sql file and run it in your local db
   
* run the application from IDE