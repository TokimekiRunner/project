var responseData;
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
      url: "http://localhost:8080/test",
      data: jsonData,
      contentType: 'application/json',
      success: function (response) {
        // 处理成功响应
        console.log("success");
        console.log(response);
        responseData = response;
        renderTable(response);
      },
      error: function (error) {
        // 处理错误响应
        console.log(error);
      }
    });
  });

  function renderTable(data) {
    var table = $("table");
    table.empty();

    // 添加表头
    var headerRow = $("<tr>");
    headerRow.append("<th>ID</th>");
    headerRow.append("<th>Name</th>");
    headerRow.append("<th>Category</th>");
    headerRow.append("<th>Year</th>");
    headerRow.append("<th>Person ID</th>");
    headerRow.append("<th>Level</th>");
    table.append(headerRow);

    // 添加数据行
    var jsonData = data;
    jsonData.forEach(function (achievement) {
      var row = $("<tr>");
      row.append("<td>" + achievement.id + "</td>");
      row.append("<td>" + achievement.name + "</td>");
      row.append("<td>" + achievement.category + "</td>");
      row.append("<td>" + achievement.year + "</td>");
      row.append("<td>" + achievement.person_id + "</td>");
      row.append("<td>" + achievement.level + "</td>");
      table.append(row);

      row.on("click", function() {
        $(".success-message").remove();
        var jsonData_detail =JSON.stringify({id: achievement.id});
        console.log(jsonData_detail);
        $.ajax({
          type: "POST",
          url: "http://localhost:8080/test",
          data: jsonData_detail,
          contentType: 'application/json',
          success: function (response) {
            console.log(response);
            var message = JSON.stringify(response);
            $("<div class='success-message'>" + message + "</div>").appendTo("body").fadeIn();
            setTimeout(function(){
              $(".success-message").fadeOut();
            }, 3000);
          },

          error: function (error) {
            console.log(error);
          }
        });
      });
    });
  }
});
