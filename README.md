![](app/src/main/logo-web.png&s=120)
<img src="app/src/main/logo-web.png" width="200">
# WebServiceExample
<a href="WebServiceExample.apk" download>Click to Download the APK file!</a>
#### **Hi!,**  
I am Ismail. This is my first repository. I've just started to explore the world of android development and I'm already loving it. I have created this pretty basic app just for learning purpose. It uses rest api client [**Retrofit**](http://square.github.io/retrofit/) to retreive data from the internet and display it in the app.  
The app is divided in two parts.  
1. Movie Response  
This displays a small list of superheroes with images and some details in a [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview). I have used [Glide library](https://github.com/bumptech/glide) to load the images. When your click on any item in recyclerview it displays another activity that displays full information of the selected super hero.
2. Google Api
This is a _"little"_ bit advance than the first one, It takes the input from the user such as source,destination and travel mode and fetches the route details from the [Google Maps API](https://developers.google.com/maps/documentation/android-sdk/intro) and displays the duration of the route.  

>I have used [Material Design](https://material.io/develop/android/) Components in this app like _CardView_, _TextInputLayout_, _TextInputEditText_ as Google is so encouraging about material design (and personally, I like it too!).  
Also to justify long bio TextView i have used a [library on github](https://github.com/programingjd/justified) as android doesn't provide a built-in functionality to justify the text in TextView.

![](app_preview.gif&s=120)
<p align="center">
<img src="app_preview.gif" width="350" align="center"/>
</p>
