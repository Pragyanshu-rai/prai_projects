
// functions to be called as the page loads
function main()
{
    select();
    show_date();
}

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
    if(document.body.scrollTop > 150 || document.documentElement.scrollTop > 150)
    {
        $("#top-btn").fadeIn();
        //top_btn.style.display = "block"; 
    }
    else
    {
        $("#top-btn").fadeOut();
        //top_btn.style.display = "none";   
    }
}

// this fucntion will go on top
function page_top()
{
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

//for table checkbox
function select()
{
    var ch_box = document.getElementsByClassName('checkbox');
    for(i=0; i<ch_box.length; i++)
    {
        if(ch_box[i].checked == true)
        {
            var tr = document.getElementsByTagName('tr')[i+1];
            tr.classList.add("table-danger");
        }
        else
        {
            var tr = document.getElementsByTagName('tr')[i+1];
            tr.classList.remove("table-danger");
        }    
    }
}

// to toggle the password visibility
function togglePassword()
{
    var pass = document.getElementById("pass");
    var lock = document.getElementById("lock");
    if(lock.classList.contains("fa-unlock") == true)
    {
        lock.classList.remove("fa-unlock");
        lock.classList.add("fa-lock");
        pass.setAttribute("type", "password"); 
        pass.setAttribute("placeholder", "*********");   
    }
    else
    {
        lock.classList.remove("fa-lock");
        lock.classList.add("fa-unlock");
        pass.setAttribute("type", "text");
        pass.setAttribute("placeholder", "Password");
    }
} 

function checkPass()
{
    var pass = document.getElementById("pass");
    var conf = document.getElementById("conf");
    if(pass.value.length == 0 && conf.value.length == 0)
    {
        conf.classList.remove("red")
        conf.classList.remove("green")
        conf.classList.add("white")
    }
    else
    {
        if(pass.value == conf.value)
        {
            conf.setAttribute("style", "color:#8bd8bd");
        }
        else
        {
            conf.setAttribute("style", "color:#ff3d49");
        }
    }
}

