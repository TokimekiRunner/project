const ipAddress = '47.93.252.177';

$(document).ready(function() {
  $("#function").change(function(){
    var selected_option = $(this).val();
    $(".container").hide();
    $(".prompt").hide();
    $("#asdfgh").hide();
    $("#confirm_delete").hide();
    $("#confirm_delete_p").hide();
    var table = $("table");
    table.empty();
    if(selected_option == "add") {
      $("#add_form").show();
    } else if (selected_option == "delete") {
      $("#delete_form").show();
    } else if (selected_option == "add_p") {
      $("#add_p_form").show();
    } else if (selected_option == "delete_p") {
      $("#delete_p_form").show();
    } else if (selected_option == "none") {
      inquiry_a();
    }
  });

  $("#category_a").change(function(){
    var selected_option_a = $(this).val();
    $(".input_addition").hide();
    if(selected_option_a == "research") {
      $("#research_a").show();
    } else if (selected_option_a == "award") {
      $("#award_a").show();
    } else if (selected_option_a == "patent") {
      $("#patent_a").show();
    } else if (selected_option_a == "visit") {
      $("#visit_a").show();
    }
  });

  $("form[name='add']").submit(function(event) {
    event.preventDefault();

    var formData = {};
    $(this).serializeArray().forEach(function(item) {
      formData[item.name] = item.value;
    });

    var jsonData = JSON.stringify(formData);
    console.log(jsonData);
    $.ajax({
      type: "POST",
      url: "http://"+ipAddress+":8080/insert",
      data: jsonData,
      contentType:'application/json',
      success: function(response) {
        // 处理成功响应
        console.log("success");
        console.log(response);
        responseData = response;
        $(".prompt").hide();
        $("#insert_s").show();
        renderTable_a(jsonData);
        $("<div class='success-message'>插入成功</div>").appendTo("body").fadeIn();
        setTimeout(function(){
          $(".success-message").fadeOut();
        }, 3000);

        $('form[name="add"] input[type="text"]').val('');
      },
      error: function(error) {
        // 处理错误响应
        var table = $("table");
        table.empty();
        $(".prompt").hide();
        $("#insert_e").show();
        console.log(error);
      }
    });
  });

  function renderTable_a(data) {
    var table = $("table");
    table.empty();

    var jsonData = JSON.parse(data);

    const keys = Object.keys(jsonData).filter(key => jsonData[key] !== "");
    var headerRow = $("<tr>");
    keys.forEach(key => {
      headerRow.append("<th>" + key + "</th>");
    });
    table.append(headerRow);
    var row = $("<tr>");
    keys.forEach(key => {
      row.append("<td>" + jsonData[key] + "</td>");
    });
    table.append(row);
  }

  $("form[name='delete']").submit(function (event) {
    event.preventDefault();

    var formData = {};
    $(this).serializeArray().forEach(function (item) {
      formData[item.name] = item.value;
    });

    var jsonData = JSON.stringify(formData);

    console.log(jsonData);
    $.ajax({
      type: "POST",
      url: "http://"+ipAddress+":8080/test",
      data: jsonData,
      contentType: 'application/json',
      success: function (response) {
        // 处理成功响应
        console.log(response);
        responseData = response;
        console.log(response[0]);
        if(response[0] === undefined) {
          $(".prompt").hide();
          $("#delete_e").show();
          var table = $("table");
          table.empty();
          console.log("error");
        } else {
          console.log("success");
          $(".prompt").hide();
          $("#delete_s").show();
          renderTable_d(response);
          $("#confirm_delete").show();

          $("#confirm_delete").click(function() {
            triggerDelete(response);
            $("#confirm_delete").hide();
          });
        }
      },
      error: function (error) {
        // 处理错误响应
        $(".prompt").hide();
        $("#delete_e").show();
        var table = $("table");
        table.empty();
        console.log(error);
      }
    });
  });

  function renderTable_d(data) {
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
    });
  }

  function triggerDelete(deldata) {
    console.log("In delete");
    console.log(deldata);
    var jsonData = JSON.stringify(deldata);

    $.ajax({
      type: "POST",
      url: "http://"+ipAddress+":8080/delete",
      data: jsonData,
      contentType:'application/json',
      success: function(response) {
        // 处理成功响应
        $(".prompt").hide();
        var table = $("table");
        table.empty();
        console.log("success del");
        console.log(response);
        $("<div class='success-message'>删除成功</div>").appendTo("body").fadeIn();
        setTimeout(function(){
          $(".success-message").fadeOut();
        }, 3000);
      },
      error: function(error) {
        $(".prompt").hide();
        $("#delete_e").show();
        var table = $("table");
        table.empty();
        console.log("error");
      }
    });
  }

  // 新增人员
  $("form[name='add_p']").submit(function(event) {
    event.preventDefault();

    var formData = {};
    $(this).serializeArray().forEach(function(item) {
      formData[item.name] = item.value;
    });

    var jsonData = JSON.stringify(formData);
    console.log(jsonData);
    $.ajax({
      type: "POST",
      url: "http://"+ipAddress+":8080/insertperson",
      data: jsonData,
      contentType:'application/json',
      success: function(response) {
        // 处理成功响应
        console.log("success");
        console.log(response);
        responseData = response;
        $(".prompt").hide();
        $("#insert_p_s").show();
        renderTable_a_p(jsonData);
        $("<div class='success-message'>插入成功</div>").appendTo("body").fadeIn();
        setTimeout(function(){
          $(".success-message").fadeOut();
        }, 3000);

        $('form[name="add_p"] input[type="text"]').val('');
      },
      error: function(error) {
        // 处理错误响应
        $(".prompt").hide();
        $("#insert_p_e").show();
        console.log("error");
      }
    });
  });

  function renderTable_a_p(data) {
    var table = $("table");
    table.empty();

    var jsonData = JSON.parse(data);

    const keys = Object.keys(jsonData).filter(key => jsonData[key] !== "");
    var headerRow = $("<tr>");
    keys.forEach(key => {
      headerRow.append("<th>" + key + "</th>");
    });
    table.append(headerRow);
    var row = $("<tr>");
    keys.forEach(key => {
      row.append("<td>" + jsonData[key] + "</td>");
    });
    table.append(row);
  }

  $("form[name='delete_p']").submit(function (event) {
    event.preventDefault();

    var formData = {};
    $(this).serializeArray().forEach(function (item) {
      formData[item.name] = item.value;
    });

    var jsonData = JSON.stringify(formData);

    console.log(jsonData);
    $.ajax({
      type: "POST",
      url: "http://"+ipAddress+":8080/getperson",
      data: jsonData,
      contentType: 'application/json',
      success: function (response) {
        // 处理成功响应
        console.log(response);
        responseData = response;
        console.log(response);
        if(response === "") {
          $(".prompt").hide();
          $("#delete_p_n_e").show();
          var table = $("table");
          table.empty();
          $("#confirm_delete_p").hide();
          console.log("error");
        } else {
          console.log("success");
          $(".prompt").hide();
          $("#delete_p_s").show();
          renderTable_d_p(response);
          $("#confirm_delete_p").show();

          $("#confirm_delete_p").click(function() {
            triggerDelete_p(response);
            $("#confirm_delete_p").hide();
          });
        }
      },
      error: function (error) {
        // 处理错误响应
        $(".prompt").hide();
        $("#delete_p_e").show();
        var table = $("table");
        table.empty();
        console.log("error");
      }
    });
  });

  function renderTable_d_p(data) {
    var table = $("table");
    table.empty();

    // 添加表头
    var headerRow = $("<tr>");
    headerRow.append("<th>person_id</th>");
    headerRow.append("<th>person_name</th>");
    headerRow.append("<th>department</th>");
    headerRow.append("<th>position</th>");
    table.append(headerRow);

    // 添加数据行
    var jsonData = data;
    var row = $("<tr>");
    row.append("<td>" + jsonData.person_id + "</td>");
    row.append("<td>" + jsonData.personname + "</td>");
    row.append("<td>" + jsonData.department + "</td>");
    row.append("<td>" + jsonData.position + "</td>");
    table.append(row);
  }

  function triggerDelete_p(deldata) {
    console.log("In delete");
    console.log(deldata);
    var jsonData = JSON.stringify(deldata);

    $.ajax({
      type: "POST",
      url: "http://"+ipAddress+":8080/deleteperson",
      data: jsonData,
      contentType:'application/json',
      success: function(response) {
        // 处理成功响应
        $(".prompt").hide();
        var table = $("table");
        table.empty();
        console.log("success del");
        console.log(response);
        if(response !== 0){
          $(".prompt").hide();
          $("#delete_p_c_e").show();
          var table = $("table");
          table.empty();
          console.log("error");
        }else{
          $("<div class='success-message'>删除成功</div>").appendTo("body").fadeIn();
          setTimeout(function(){
            $(".success-message").fadeOut();
          }, 3000);
        }

      },
      error: function(error) {
        $(".prompt").hide();
        $("#delete_p_c_e").show();
        var table = $("table");
        table.empty();
        console.log("error");
      }
    });
  }
});

function inquiry_a() {
  $("#asdfgh").show();

  var formdata={"name":"","personname":"","category":"","year":""};
  var jsonData = JSON.stringify(formdata);
  console.log(jsonData);
  $.ajax({
    type: "POST",
    url: "http://"+ipAddress+":8080/test",
    data: jsonData,
    contentType: 'application/json',
    success: function (response) {
      // 处理成功响应
      console.log("success");
      console.log(response);
      responseData = response;
      renderTable_i(response);
    },
    error: function (error) {
      // 处理错误响应
      console.log(error);
    }
  });
}

function renderTable_i(data) {
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
        url: "http://"+ipAddress+":8080/test",
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

$(document).on("click", ".popup-close", function() {
  $(".popup").hide();
});
