# EV3 Bluetooth Remote Control

## Objective
For version I, core functions will be completed. The app will successfully connect the mobile device to the EV3 robot and use arrow buttons to drive it. 

## Requirements
* Two modules -- connection and drive
* Use a suitable navigation way to handle two views (also consider future expansion)
* Less user experience consideration
* Less UI consideration
* Screenshots here for demo only, you are free to design your UI
* *Naming convention applies*  

<img src="https://user-images.githubusercontent.com/87737934/161189643-1c10e25e-d5c4-4b11-bfd3-5426c576befc.png" height="400"> <img src="https://user-images.githubusercontent.com/87737934/161189651-6bf9c087-cde5-400c-95df-dd653c5ed84a.png" height="400"> <img src="https://user-images.githubusercontent.com/87737934/161189660-9828e121-7ea8-443c-8de9-520bffea1bdd.png" height="400">

## Design
* Assume your device has bluetooth on and has paired with the EV3
* Your app should have the counterparts of the following
  * connect button
  * close button
  * bluetooth icons (two icons for on/off)
  * status label
  * battery level indicator (not live in this version)
* If connected successfully, change the bluetooth image, display device name in orange, and 'Connected'.
* For drive module, use arrow buttons for directions and sliders for powers.
* Buttons should have 'feedback' states
* Sliders should synchronize the power value text. You are free to design.
