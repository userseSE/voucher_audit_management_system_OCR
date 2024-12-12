$(document).ready(function() {
    // 使用Ajax请求后端Controller层传递的数据
    $.ajax({
        type: "GET",
        url: "/getUserInfo",
        dataType: "json",
        success: function(data) {
            // 获取数据成功后，将数据显示在页面中
            var username = data.username;
            var activeName2 = data.activeName2;

            // 转换activeName2为对应的身份
            var identity;
            switch (activeName2) {
                case "auditor":
                    identity = "审计师";
                    break;
                case "intern":
                    identity = "实习生";
                    break;
                case "manager":
                    identity = "管理员";
                    break;
                default:
                    identity = "";
                    break;
            }

            // 使用Ajax请求获取员工姓名
            $.ajax({
                type: "GET",
                url: "/getUserInfo",
                dataType: "json",
                data: {
                    username: username,
                    activeName2: activeName2
                },
                success: function(data) {
                    var name = data.name;
                    var identity=data.identity;
                    var greeting = document.getElementById("greeting");
                    greeting.innerHTML = "  您好，" + identity + " "+name + "！";

                    return greeting;
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    var greeting = document.getElementById("greeting");
                    greeting.innerHTML = "  您好！"
                    console.log(data.identity);
                    console.log(data.name);
                    console.log(data);
                    console.log("获取员工姓名失败：" + errorThrown);
                }
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("获取用户信息失败：" + errorThrown);
        }
    });
});