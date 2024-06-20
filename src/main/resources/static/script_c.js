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
      url: "http://localhost:8080/calculate",
      data: jsonData,
      contentType: 'application/json',
      success: function (response) {
        // 处理成功响应
        if(response[0]===undefined){
          $("#download").hide();
          var table = $("#table_1");
          table.empty();
          var table = $("#table_2");
          table.empty();
          $(".prompt").hide();
          $("#cal_e").show();
          console.log("error");
        } else {
          $(".prompt").hide();
          $("#cal_s").show();
          console.log("success");
          console.log(response);
          responseData = response;
          renderTable(response);
          $("#download").show();

          $("#download").one("click", function() {
            console.log("巩爷就是神！");
            var jsonData = JSON.stringify(response);
            // 创建 XMLHttpRequest 对象
            var request = new XMLHttpRequest();
            request.open('POST', "http://localhost:8080/download", true);
            request.setRequestHeader('Content-Type', 'application/json');
            request.responseType = 'blob';

            request.onload = function(e) {
              if (this.status === 200) {
                var blob = this.response;
                if (window.navigator.msSaveOrOpenBlob) {
                  window.navigator.msSaveBlob(blob, "test.xlsx");
                } else {
                  var downloadLink = window.document.createElement('a');
                  downloadLink.href = window.URL.createObjectURL(blob);
                  downloadLink.download = "test.xlsx";
                  document.body.appendChild(downloadLink);
                  downloadLink.click();
                  document.body.removeChild(downloadLink);
                }
              }
            };

            // 发送 AJAX POST 请求
            request.send(jsonData);
          });
        }
      },
      error: function (error) {
        // 处理错误响应
        var table = $("#table_1");
        table.empty();
        var table = $("#table_2");
        table.empty();
        $("#download").hide();
        $(".prompt").hide();
        $("#cal_e").show();
        console.log("error");
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
    var workload = 0;
    jsonData.forEach(function (achievement) {
      var row = $("<tr>");
      row.append("<td>" + achievement.id + "</td>");
      row.append("<td>" + achievement.name + "</td>");
      row.append("<td>" + achievement.category + "</td>");
      row.append("<td>" + achievement.year + "</td>");
      row.append("<td>" + achievement.person_id + "</td>");
      row.append("<td>" + achievement.level + "</td>");
      row.append("<td>" + achievement.calscore + "</td>");
      table.append(row);
      workload += achievement.calscore;

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
    var table_ = $("#table_2");
    table_.empty();
    var headerRow_ = $("<tr>");
    headerRow_.append("<th>人员姓名</th>");
    headerRow_.append("<th>人员工作量</th>");
    table_.append(headerRow_);
    var row_ = $("<tr>");
    row_.append("<td>" + jsonData[0].personname + "</td>");
    row_.append("<td>" + workload + "</td>");
    table_.append(row_);
  }
});

$(document).on("click", ".popup-close", function() {
  $(".popup").hide();
});
