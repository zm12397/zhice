$(document).ready(function(){
    $("#number").blur(function(){
        //验证公司编号是否已经被注册
        $.ajax({
            url: "register/validate/company.action",
            data: {"number":$("#number").val()},
            method: "post",
            dataType: "json",
            success: function(result){
                if(result.code == "0000"){
                    if(result.message == "编号已被注册"){
                        layer.msg("编号已被注册",{time:1000});
                        $("#number").focus();
                        $("#number").val("");
                        return
                    }
                }else {
                    layer.msg(result.message,{time:1000});
                    $("#number").focus();
                    return
                }
            },
            error: function(result){
                layer.msg(result.message,{time:1000});
                $("#number").focus();
                return
            }
        });
    });

    $("#username").blur(function () {
        //验证用户名是否已经被注册
        $.ajax({
            url: "register/validate/user.action",
            data: {"username":$("#username").val()},
            method: "post",
            dataType: "json",
            success: function(result){
                if(result.code == "0000"){
                    if(result.message == "用户名已被注册"){
                        layer.msg("用户名已被注册",{time:1000});
                        $("#username").focus();
                        $("#username").val("");
                        return false
                    }
                }else {
                    layer.msg(result.message,{time:1000});
                    $("#number").focus();
                    return false
                }
            },
            error: function(result){
                layer.msg(result.message,{time:1000});
                $("#number").focus();
                return false
            }
        });
    });
    $("#register").click(function() {
        var name = $("#name").val();
        var number = $("#number").val();
        var username = $("#username").val();
        var password = $("#password").val();
        var password1 = $("#password1").val();
        if(name==""){
            layer.msg("请输入公司名称",{time:1000});
            $("#name").focus();
            return;
        }
        if(number==""){
            layer.msg("请输入公司编号",{time:1000});
            $("#number").focus();
            return;
        }
        if(username == ""){
            layer.msg("请输入用户名",{time:1000});
            $("#username").focus();
            return;
        }
        if(password == ""){
            layer.msg("请输入密码",{time:1000});
            $("#password").focus();
            return;
        }
        if(password1 == ""){
            layer.msg("请输入确认密码",{time:1000});
            $("#password").focus();
            return;
        }
        if(password != password1){
            layer.msg("密码不一致",{time:1000});
            $("#password").val("");
            $("#password1").val("");
            $("#password").focus();
            return;
        }
        //执行注册
        $.ajax({
            url: "register/register.action",
            data: {"name":name,"number":number,"username":username,"password":password},
            method: "post",
            dataType: "json",
            success: function(result){
                if(result.code == "0000"){
                    layer.msg("注册成功...前往登陆界面",
                        {
                            anim:0,
                            time:1000,
                            end:function () {
                                window.location.href="login";
                            }
                        })
                }else {
                    layer.msg(result.message,{time:1000});
                    return
                }
            },
            error: function(result){
                layer.msg(result.message,{time:1000});
                return
            }
        });
    });
});
