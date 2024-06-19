
function triggerDelete(deldata) {
  console.log("In delete");
  console.log(deldata);
  var jsonData = JSON.stringify(deldata);

  $.ajax({
    type: "POST",
    url: "http://localhost:8080/delete",
    data: jsonData,
    contentType:'application/json',
    success: function(response) {
      // 处理成功响应
      console.log("success del");
      console.log(response);

    },
    error: function(error) {

      console.log(error);
    }
  });
}

$(document).ready(function() {
  $("#function").change(function(){
    var selected_option = $(this).val();
    $(".container").hide();
    if(selected_option == "add") {
      $("#add_form").show();
    } else if (selected_option == "delete") {
      $("#delete_form").show();
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
      url: "http://localhost:8080/insert",
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
        $(".prompt").hide();
        $("#insert_e").show();
        console.log(error);
      }
    });
  });

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
      url: "http://localhost:8080/test",
      data: jsonData,
      contentType: 'application/json',
      success: function (response) {
        // 处理成功响应

        console.log("success");
        console.log(response);
        responseData = response;
        $(".prompt").hide();
        $("#delete_s").show();
        renderTable_d(response);
        $("<div class='success-message'>删除成功</div>").appendTo("body").fadeIn();
        setTimeout(function(){
          $(".success-message").fadeOut();
        }, 3000);

        $('form[name="delete"] input[type="text"]').val('');
        triggerDelete(response);
      },
      error: function (error) {
        // 处理错误响应
        $(".prompt").hide();
        $("#delete_e").show();
        console.log(error);
      }
    });
  });

  function renderTable_a(data) {
    var table = $("table");
    table.empty();

    var jsonData = JSON.parse(data);

    // 添加表头
    // var headerRow = $("<tr>");
    // headerRow.append("<th>ID</th>");
    // headerRow.append("<th>Name</th>");
    // headerRow.append("<th>Category</th>");
    // headerRow.append("<th>Year</th>");
    // headerRow.append("<th>Person ID</th>");
    // headerRow.append("<th>Level</th>");
    // table.append(headerRow);

    // 添加数据行
    // var row = $("<tr>");
    // row.append("<td>" + jsonData.id + "</td>");
    // row.append("<td>" + jsonData.name + "</td>");
    // row.append("<td>" + jsonData.category + "</td>");
    // row.append("<td>" + jsonData.year + "</td>");
    // row.append("<td>" + jsonData.person_id + "</td>");
    // row.append("<td>" + jsonData.level + "</td>");
    // table.append(row);



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
});
