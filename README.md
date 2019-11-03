# Personalcapital


This application show the list of RSS feed item from Personal Captial blog. It implementing Java + MVVM + Architecture components.

# Libray dependancy
* AndroidX cardview
* AndroidX recyclerview
* com.squareup.picasso
* live data + view model
* junit 
* Mockito 
 
# Tablet ScreenShot
![alt text](https://github.com/geminihsu/Personalcapital/blob/master/screenshot/Screen%20Shot%202019-11-03%20at%202.52.56%20AM.png)

# HandSet ScreenShot
![alt text](https://github.com/geminihsu/Personalcapital/blob/master/screenshot/Screen%20Shot%202019-11-03%20at%203.17.35%20AM.png)

# Each article display on the webView
![alt text](https://github.com/geminihsu/Personalcapital/blob/master/screenshot/Screen%20Shot%202019-11-03%20at%203.25.43%20AM.png)

# Folder structure
- [x] model : Article and Article collection property
- [x] view : Define the user interface window and Adatper object
- [x] viewmodel : Retain the list of Article object within Live data
- [ ] constant : Define custome variable
- [ ] network : The network api function call
- [ ] util : DeviceManger used to check if the device is handset or tablet. RSSManger used to parse HTML tag and mapping to Article model object. SettingsManager used to deal with displaying images on the recycle view.


