
function handleClick(e) {
 console.log("Button Clicked!")
 var rand = document.getElementById("Random");
 rand.innerHTML = Math.random() * 100;
 console.log(document.getElementById("clickme").style);
}

// default handleClick;

var rotate = false;

function rotateToggle() {
 var navbtn = document.getElementById("navbtn");

 if (rotate === true) {
  navbtn.style.transform = "rotate(0deg)";
  rotate = false;
  console.log("rotate is true");
 }
 else {
  navbtn.style.transform = "rotate(90deg)";
  rotate = true;
  console.log("rotate is false");
 }
 console.log(navbtn.classList)
}

function copyText() {
 var text = document.getElementById("Random");
 var textip = document.createElement("textarea");
 textip.value = text.textContent;
 document.body.appendChild(textip);
 textip.select();
 document.execCommand("copy");
 text.setAttribute("title", "Text Copied!");
 alert("Text Copied! " + text.textContent);
 // document.body.removeChild(textip);
 textip.remove();
}

function pass(e) {
 console.log("pass invoked by :- " + e.target);
 console.log(e)
}

function show() {
 var element = document.querySelectorAll(".styleimg");
 element.forEach(function (el) {
  el.classList.add("show");
 });
}

function playOrPause(play) {
 const video = document.getElementById("logoVideo");
 if (play == true) {
  console.log("play");
  video.play();
 }
 else {
  console.log("pause");
  video.load();
  video.pause();
 }
}

function isElementInViewport(element) {
 var vp = element.getBoundingClientRect();
 return (
  (vp.top <= 0 && vp.bottom >= 0)
  ||
  (vp.bottom >= (window.innerHeight || document.documentElement.clientHeight) && vp.top <= (window.innerHeight || document.documentElement.clientHeight))
  ||
  (vp.top >= 0 && vp.bottom <= (window.innerHeight || document.documentElement.clientHeight))
 );
}


function scrollAnimate() {
 var play = false;
 var scrollCheck = window.requestAnimationFrame || function (callback) { window.setTimeout(callback, 1000 / 5000) };
 var elementsX = document.querySelectorAll(".from-rt, .from-lf, .from-rt-fast, .from-lf-fast");
 var elementsY = document.querySelectorAll(".from-up, .from-dn");

 // console.log(elementsX);
 // console.log(elementsY);

 function loops() {
  elementsX.forEach(function (element) {
   if (isElementInViewport(element)) {
    element.classList.add("show-x");
   }
   else {
    element.classList.remove("show-x");
   }
  }
  );
  elementsY.forEach(function (element) {
   if (isElementInViewport(element)) {
    element.classList.add("show-y");
    play = true;
   }
   else {
    element.classList.remove("show-y");
    paly = false;
   }
  });
  scrollCheck(loops);
 }
 loops();
 // playOrPause(play);
}

function statusChange(caller) {
 var current = document.querySelectorAll(".active");
 current.forEach(function (element) {
  if (element.classList.contains("nav-link")) {
   element.classList.remove("active");
  }
 });
 console.log(caller.classList);
 caller.classList.add("active");
}

function checkActive() {
 
 var checkList = document.querySelectorAll(".landing");
 var inViewPort = null;
 
 checkList.forEach(function (element) {
  console.log(element.id);
  if (isElementInViewport(element)) {
   inViewPort = element;
  }
 });

 var navList = document.querySelectorAll(".nav-link");
 
 navList.forEach(function (navItem) {
  if (navItem.getAttribute("href") == '#'+inViewPort.id){
   navItem.classList.add("active");
  }
  else{
   navItem.classList.remove("active");
  }
 });
 console.log(inViewPort);
}

function test(caller){
 console.log("Called by: "+caller);
}

/*
* This function is called after the html is loaded.
* To run any function after the html document is loaded call it inside the main function
*/
function main() {
 checkActive();
 scrollAnimate();
}