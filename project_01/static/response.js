window.onscroll = function() {myfun()};

var head = document.getElementById('head');

var stay = head.offsetTop;

function myfun()
{
    if(window.pageYOffset > stay)
    {
        head.classList.add('stay');
    
    else
        head.classList.remove('stay');
}
