$(function () {
    // - 绑定点击事件
    $("span[id=sub]").click(function () {
        //获取id
        var workerId = $(this).attr("class");
        var workerSalary = $(this).next().attr("class");
        console.log(workerId)
        console.log(workerSalary)
        //计算调整后的工资
        workerSalary = parseInt(workerSalary);
        workerSalary -= 1;
        //$(this).next().text(workerSalary);
        $.ajax({
            url:'/normalUser/updateOrder',
            data:{
                orderId:workerId,
                count:workerSalary
            },
            method:"POST",
            success:function (data) {
                if(data == "OK"){
                    alert("减少成功");
                    window.location.href ="/normalUser/cart";
                }else {
                    alert("减少失败");
                }
            }
        })
    })
})

$(function () {
    // + 绑定点击事件
    $("span[id=add]").click(function () {
        //获取id
        var workerId = $(this).attr("class");
        var workerSalary = $(this).prev().attr("class");
        //计算调整后的工资
        workerSalary = parseInt(workerSalary);
        workerSalary += 1;
        //$(this).prev().text(workerSalary);
        $.ajax({
            url:'/normalUser/updateOrder',
            data:{
                orderId:workerId,
                count:workerSalary
            },
            method:"POST",
            success:function (data) {
                if(data == "OK"){
                    alert("添加成功");
                    window.location.href ="/normalUser/cart";
                }else {
                    alert("添加失败");
                }
            }
        })
    })
})