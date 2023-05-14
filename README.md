# AppternStudyCase - DeezerAPI

## Introduction
This application enables the user to access the categories of tracks and the corresponding artist lists. Additionally, a 30-second preview of the track's cover can be played. The application is a study case for AppCent

<br />

## Dependencies
During the development of the application, several dependencies were utilized including Retrofit, DaggerHilt, Room, Picasso, SmoothBottomBar, LiveData & ViewModel, Lottie, and CircleImageView. In addition, adherence to SOLID principles and the utilization of the MVVM architecture were emphasized to ensure optimal code organization and maintainability.

<br />

## Dependency Injection
Dependency Injection has been implemented for both the Room and Retrofit dependencies in order to achieve a more flexible and maintainable codebase.


<br />

## ScreenShots

<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/47759665/238212561-bd35e32f-b21f-475f-942f-e08c68bd86d3.png" width="249" height="512"> <img src="https://github-production-user-asset-6210df.s3.amazonaws.com/47759665/238210354-df0bfc7c-27f0-461e-9218-18617ec0b180.png" width="249" height="512">

<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/47759665/238210345-cd41315b-5a9b-45b4-8b13-33ad7b217b2e.png" width="249" height="512"> <img src="https://github-production-user-asset-6210df.s3.amazonaws.com/47759665/238210348-627b287a-0e14-49cb-ab3d-54ce3d4e3d05.png" width="249" height="512">

<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/47759665/238210350-3c8a006f-4170-452d-94e0-35394646374a.png" width="249" height="512"> <img src="https://github-production-user-asset-6210df.s3.amazonaws.com/47759665/238210351-be688e19-b48d-4cb4-85d1-13f33770cba9.png" width="249" height="512">

<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/47759665/238210352-7e93894f-e80f-4fec-95b6-ab0e3c4671d6.png" width="249" height="512"> <img src="https://github-production-user-asset-6210df.s3.amazonaws.com/47759665/238210353-8757a33d-05cb-43b9-b812-2a58e7e5967c.png" width="249" height="512">

<br />

At the beginning, the application has two fragments named "categories" and "likes". When listing songs, users have the option to add them to their liked songs list, which they can also remove either from the "likes" fragment or from the album/single activity. Attention has been given to ensuring the responsive design of the application. Additionally, the application checks for an internet connection upon startup, with a 2-second delay to ensure adequate time for the check. During this time, a splash screen with an animation is displayed to provide visual feedback to the user. Efforts have been made to prevent null or other exception errors from occurring. The likes are stored in the local storage of the system. To ensure their persistence, SQL has been utilized. When storing likes using SQL, the TrackID serves as the primary key, with associated data including the Track Picture URL, Track Title, and Track Release Date also stored in the SQL database
