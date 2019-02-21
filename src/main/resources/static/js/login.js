$(document).ready(function() {
    $("#login").click(function(){
        var username = $("#uname").val();
        var password = $("#password").val();
        if(username==""){
            $("#username").focus();
            layer.msg("用户名为空",{time:1000});
            return;
        }
        if(password==""){
            layer.msg("密码为空",{time:1000});
            $("#password").focus();
            return;
        }
        $.ajax({
            url : "login/login.action",
            data : {"username":$("#uname").val(),"password":$("#password").val()},
            method:"post",
            dataType:"json",
            success : function(result) {
                if(result.code == "0000"){
                    layer.msg("登录成功...前往主页",
                        {
                            anim: 0,
                            time:1000,
                            end:function(){
                                window.location.href="realtime";
                            }
                        })
                }else{
                    layer.msg(result.message,{time:1000});
                }
            },
            error:function(result) {
                layer.msg(result.message,{time:1000});
            }
        })
    })
})