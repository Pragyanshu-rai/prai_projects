
// to restrict the date input max attribute to today
function restrict_max()
{
    var da =new Date().toISOString().split("T")[0];
    console.log(da);
    document.getElementById("dateid").max= da;
    console.log(document.getElementById("dateid").max);
}

// to restrict the date input min attribute to tomorrow
function restrict_min()
{
    var da = new Date();
    console.log(da);
    da.setDate(da.getDate() + 1);
    da = da.toISOString().split("T")[0];
    console.log(da);
    document.getElementById("slotdate").min = da;
    console.log(document.getElementById("slotdate").min);
}

// to display current date
function show_date()
{
    var date = new Date();
    var display = document.getElementById("date");
    display.innerHTML = date.toISOString().split("T")[0];
}
    
window.onscroll = function(){scroll_check()};

//to check if the screen is scrolled down
function scroll_check()
{
    top_btn = document.getElementById("top-btn");
    if(document.body.scrollTop > 200 || document.documentElement.scrollTop > 200)
    {
        top_btn.style.display = "block";   
    }
    else
    {
        top_btn.style.display = "none";   
    }
}

// this fucntion will go on top
function page_top()
{
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

