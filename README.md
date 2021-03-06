# Search User On GitHub
This repository contains android project which search User of GitHub using GitHub APIs.  In search API, it takes username to search user. 
All the API calls are made by <a href="http://square.github.io/retrofit/"><b>Retrofit 2</b></a> and for image cache I have used <a href="http://square.github.io/picasso/"><b>Picasso.</b></a></n> It is designed for both landscape and portrait mode.  Also I have used custom activity animation(See in the style.xml). For Basic <b>Authorization</b> I have generated <b>my own access token</b> to get all the users and followers(private and public).  It is used statically in the api calls. 
### To test this project, Generate your own Access token and replace it in API Service Interface which is under networking package.
Like this
```
@GET("{username}?access_token= Put Your Access Token Here")
@GET("{username}/followers?access_token= Put Your Access Token Here")
```
[Search User API](https://developer.github.com/v3/users/#get-a-single-user)
<p>I am using basic authorization to get the user.  With authorization we can get public and private users.  If we do not use authorization then API return user with public data only and we cannot get his email and someother data.</p>

[Search Followers API](https://developer.github.com/v3/users/followers)
<p>This api call also uses basic authorization.  This API does not provide followers name in the response.  For this now I am showing followers login field as name.  You can check the response by clicking <a href="https://developer.github.com/v3/users/followers/#list-followers-of-a-user"><b>this</b></a></p> 
<h4>This project follows the rule of <b>MVP</b> design Pattern.</h4>
<h2 style="color:blue;">Screen Shots</h2>

<div class="container">   
<div class = "vertpan pic"><img class="aligncenter" alt="Search Screen" src="https://github.com/shakeelnasrullah/SearchUserOnGitHub/blob/master/app/Screen%20Shots/1.png" width="250" height="450" hspace="20"/></div>
        <div class = "vertpan pic"><img class="aligncenter" alt="Search Result" src="https://github.com/shakeelnasrullah/SearchUserOnGitHub/blob/master/app/Screen%20Shots/2.png" width="250" height="450" hspace="20"/></div>
        <div class = "vertpan pic"><img class="aligncenter" alt="Search User" src="https://github.com/shakeelnasrullah/SearchUserOnGitHub/blob/master/app/Screen%20Shots/3.png" width="250" height="450" hspace="20" /></div>
        <div class = "vertpan pic"><img class="aligncenter" alt="Detail Screen" src="https://github.com/shakeelnasrullah/SearchUserOnGitHub/blob/master/app/Screen%20Shots/4.png" width="250" height="450" hspace="30"/></div>


        
