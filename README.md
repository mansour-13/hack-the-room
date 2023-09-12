# README
- Accessable under https://server.bulbt.com/greeting

## ⭐️ How to change the template
- 1.Rename repo to `new_project_name`
- 2.Create project in dokku with same name (potentially also change the dokku backend)
- 3.Github Actions workflow should be changed to the dokku project created for the template copy (not `server`) anymore
- 4.Procfile jar name should be changed to `new_project_name`
- API will be availble under `new_project_name.bulbt.com`

## Notes
- Currently set to `git_push_flags: '--force'` (not needed if you do not have several repos pushing to same remote)

## About the application
- Whenever you access a endpoint which is protected by Spring Security a custom form is displayed
- After logging in the `JSESSIONID` is stored in the cookies and sent for every request