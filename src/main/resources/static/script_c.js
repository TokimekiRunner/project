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
    var table = $("#table_1");
    table.empty();

    // 添加表头
    var headerRow = $("<tr>");
    headerRow.append("<th>ID</th>");
    headerRow.append("<th>Name</th>");
    headerRow.append("<th>Category</th>");
    headerRow.append("<th>Year</th>");
    headerRow.append("<th>Person ID</th>");
    headerRow.append("<th>Level</th>");
    headerRow.append("<th>Workload</th>");
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
            var message = "";

            console.log(response[0].category);

            if(response[0].category === "research") {
              message = "成果编号: " + response[0].id + ", \n成果名称: " + response[0].name + ", \n成果种类: " + response[0].category + ", \n成果年份: " + response[0].year + ", \n成果等级: " + response[0].level + ", \n会议等级: " + response[0].meeting_level + ", \n会议名: " + response[0].meeting_name + ", \n\n人员编号: " + response[0].person_id + ", \n人员姓名: " + response[0].personname + ", \n人员部门: " + response[0].department + ", \n人员职称: " + response[0].position;
              console.log(message);
            } else if (response[0].category === "award") {
              message = "成果编号: " + response[0].id + ", \n成果名称: " + response[0].name + ", \n成果种类: " + response[0].category + ", \n成果年份: " + response[0].year + ", \n成果等级: " + response[0].level + ", \n获奖等级: " + response[0].award_level + ", \n获奖来源: " + response[0].award_source + ", \n\n人员编号: " + response[0].person_id + ", \n人员姓名: " + response[0].personname + ", \n人员部门: " + response[0].department + ", \n人员职称: " + response[0].position;
              console.log(message);
            } else if (response[0].category === "patent") {
              message = "成果编号: " + response[0].id + ", \n成果名称: " + response[0].name + ", \n成果种类: " + response[0].category + ", \n成果年份: " + response[0].year + ", \n成果等级: " + response[0].level + ", \n专利号: " + response[0].patent_num + ", \n\n人员编号: " + response[0].person_id + ", \n人员姓名: " + response[0].personname + ", \n人员部门: " + response[0].department + ", \n人员职称: " + response[0].position;
              console.log(message);
            } else if (response[0].category === "visit") {
              message = "成果编号: " + response[0].id + ", \n成果名称: " + response[0].name + ", \n成果种类: " + response[0].category + ", \n成果年份: " + response[0].year + ", \n成果等级: " + response[0].level + ", \n出访目的地: " + response[0].dest + ", \n出访时长: " + response[0].period + ", \n\n人员编号: " + response[0].person_id + ", \n人员姓名: " + response[0].personname + ", \n人员部门: " + response[0].department + ", \n人员职称: " + response[0].position;
              console.log(message);
            }

            $(".popup-message").text(message);
            $(".popup").show();
          },

          error: function (error) {
            console.log(error);
          }
        });
      });
    });
  }
});

$(document).on("click", ".popup-close", function() {
  $(".popup").hide();
});
