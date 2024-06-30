var responseData;
const ipAddress = '47.93.252.177';

$(document).ready(function () {
  $("form").submit(function (event) {
    event.preventDefault();

    var formData = {};
    $(this).serializeArray().forEach(function (item) {
      formData[item.name] = item.value;
    });

    var jsonData = JSON.stringify(formData);
    console.log(jsonData);
    $.ajax({
      type: "POST",
      url: "http://"+ipAddress+":8080/login",
      data: jsonData,
      contentType: 'application/json',
      success: function (response) {
        // 处理成功响应
        console.log("success");
        console.log(response);
        if (response) {
          window.location.href = "http://"+ipAddress+":8080/adm";
        }
        else {
          $("<div class='success-message'>用户名或密码错误</div>").appendTo("body").fadeIn();
          setTimeout(function(){
            $(".success-message").fadeOut();
          }, 3000);
        }
      },
      error: function (error) {
        // 处理错误响应
        console.log("error");
      }
    });
  });
});
