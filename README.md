# SearchUserOnGitHub
This repository contains android project which search User of GitHub using GitHub APIs.  In search API, it takes username to search user. 
All the API calls are made by <a href="http://square.github.io/retrofit/"><b>Retrofit 2</b></a> and for image cache I have used <a href="http://square.github.io/picasso/"><b>Picasso.</b></a></n> It is designed for both landscape and portrait mode.  Also I have used custom activity animation(See in the style.xml). For Basic <b>Authorization</b> I have generated <b>my own access token</b> to get all the users and followers(private and public).  It is used statically in the api calls.
<a href="https://developer.github.com/v3/users/#get-a-single-user"><h2>Search User API</h2></a></n>
<p>I am using basic authorization to get the user.  With authorization we can get public and private users.  If we do not use authorization then API return user with public data only and we cannot get his email and someother data.</p>
<a href="https://developer.github.com/v3/users/followers"><h2>Search Followers API</h2></a>
<p>This api call also uses basic authorization.  This API does not provide followers name in the response.  For this now I am showing followers login field as name.  You can check the response by clicking <a href="https://developer.github.com/v3/users/followers/#list-followers-of-a-user"><b>this</b></a></p> 
<h2 style="color:blue;">Screen Shots</h2>
<img src="https://github.com/shakeelnasrullah/SearchUserOnGitHub/blob/master/app/Screen%20Shots/1.png" alt="Search Screen" width="250" height="450">
<img src="https://github.com/shakeelnasrullah/SearchUserOnGitHub/blob/master/app/Screen%20Shots/2.png" alt="Search Result" width="250" height="450">
<img src="https://github.com/shakeelnasrullah/SearchUserOnGitHub/blob/master/app/Screen%20Shots/3.png" alt="Search User" width="250" height="450">
<img src="https://github.com/shakeelnasrullah/SearchUserOnGitHub/blob/master/app/Screen%20Shots/4.png" alt="Detail Screen" width="250" height="450">
        
