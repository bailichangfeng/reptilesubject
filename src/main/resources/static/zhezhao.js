function test() {
    console.log("test")
}
function test2() {
    console.log("test2")
}

window.onload=function()
{
    var zhezhao=document.getElementById("zhezhao");
    var login=document.getElementById("login");
    var bt=document.getElementById("bt");
    var btclose=document.getElementById("btclose");

    bt.onclick=function()
    {
        zhezhao.style.display="block";
        login.style.display="block";
    }
    btclose.onclick=function()
    {
        zhezhao.style.display="none";
        login.style.display="none";
    }
}
