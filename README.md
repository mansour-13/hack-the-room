# Welcome to *api-hack-the-room* --- 🤖Hack the Room 🕋
- Access endpoint under https://api-hack-the-room.bulbt.com/greeting
- For testing out all default endpoints have a look at [endpoints.http](endpoints.http)


## ⭐️ How to change the template
- 1.Rename repo to `new_project_name`
- 2.Create project in dokku with same name (potentially also change the dokku backend)
- 3.Github Actions workflow should be changed to the dokku project created for the template copy (not `server`) anymore
- 4.Procfile jar name should be changed to `new_project_name`
- API will be availble under `new_project_name.bulbt.com`
- Or use the following bash script
```bash
brew install gnu-sed
grep -ilr "api-hack-the-room" . | grep -v ".git/" | grep -v ".idea/" | xargs gsed -i s/api-hack-the-room/api-hack-the-room/g
```

## About the application
- Whenever you access a endpoint which is protected by Spring Security a custom form is displayed
- After logging in the `JSESSIONID` is stored in the cookies and sent for every request