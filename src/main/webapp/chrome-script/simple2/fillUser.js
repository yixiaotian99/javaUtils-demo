var count=1
function mySumbit(){
    console.log(count++)
    $("#login_username").val("haha2222");
    $("#login_password").val("111111");
    $("#loginBtn").click();
}

//每隔3秒就执行一次
window.setInterval(mySumbit, 3000)


//延时3秒后执行一次
//window.setTimeout(mySumbit, 3000)
