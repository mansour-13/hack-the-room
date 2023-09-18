# Welcome to *api-hack-the-room* --- 🤖Hack the Room 🕋
- Access endpoint under https://api-hack-the-room.bulbt.com/greeting
- For testing out all default endpoints have a look at [endpoints.http](endpoints.http)

## For teachers
### Steps for setting up a new spring-boot project
1. Fork this project with a new name e.g. `api-java-islands`
2. Set required secret `SSH_KEY_PRIVATE` for the fork
3. Enable Github Actions workflow explictly by click on Actions for each repo, then showing: *Workflows aren’t being run on this forked repository .. Go ahead and enable them.*
4. Create a new application in dokku: `ssh -t dokku@168.119.171.224 apps:create api-java-islands`
5. Parametrize project with following script
```bash
brew install gnu-sed
grep -ilr "api-hack-the-room" . | grep -v ".git/" | grep -v ".idea/" | xargs gsed -i s/api-hack-the-room/api-hack-the-room/g
```
6. Push changes, CI/CD should be triggered now and go through