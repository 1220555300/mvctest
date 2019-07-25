
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>


<script>
    Date.prototype.format = function(format)
    {
        var o = {
            "M+" : this.getMonth()+1, //month
            "d+" : this.getDate(),    //day
            "h+" : this.getHours(),   //hour
            "m+" : this.getMinutes(), //minute
            "s+" : this.getSeconds(), //second
            "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
            "S" : this.getMilliseconds() //millisecond
        }
        if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
            (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)if(new RegExp("("+ k +")").test(format))
            format = format.replace(RegExp.$1,
                RegExp.$1.length==1 ? o[k] :
                    ("00"+ o[k]).substr((""+ o[k]).length));
        return format;
    }
</script>
<html>

<head>
    <title>list</title>
</head>
<body>
<div>


    <div>
        <div class="modal fade" id="planAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            新建任务
                        </h4>
                    </div>
                    <form role="form" class="form-horizontal" id="plan_add_form">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="title" class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control required" name="title" id="title" placeholder="请输入名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="begTime" class="col-sm-2 control-label">开始时间</label>
                                <div class="col-sm-10">
                                    <input type="date" class="form-control required" name="begTime" id="begTime" placeholder="请选择时间">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="endTime" class="col-sm-2 control-label">结束时间</label>
                                <div class="col-sm-10">
                                    <input type="date" class="form-control required" name="endTime" id="endTime" placeholder="请选择时间">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="refModelId" class="col-sm-2 control-label">模板</label>
                                <div class="col-sm-10">
                                    <select class="form-control"  validate="required:true" name="refModelId" id="refModelId">
                                        <option value="">请选择模板</option>
                                        <option value="1">模板1</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="onlyVisibleToCreator">仅创建者可见
                                        </label>
                                        <a id="creatorVisibleTip" data-toggle="tooltip" title="勾选该选项后,仅任务创建者能在任务列表中看到该任务及该任务所报送的数据"> <span class="glyphicon glyphicon-question-sign" style="color:#337ab7;"></span></a>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="isAllowMultiSub">允许单帐号提交多条数据
                                        </label>
                                        <a id="allowMultiTip" data-toggle="tooltip" title="一个帐号对一个任务默认只能提交一份数据，勾选该选项将可以提交多份数据（相当于excel表格里面的只能填一行和可填多行的区别 ）。"> <span class="glyphicon glyphicon-question-sign" style="color:#337ab7;"></span></a>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="submit" class="btn btn-primary">保存</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <div>
        <div class="modal fade" id="planeditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            修改任务
                        </h4>
                    </div>
                    <form role="form" class="form-horizontal" id="plan_edit_form">
                        <div class="modal-body">
                            <input type="hidden" class="edit_id_ipt" name="id" >
                            <input type="hidden" class="edit_status_ipt" name="status" >
                            <div class="form-group">
                                <label for="title" class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control required edit_title_ipt" name="title" id="title" placeholder="请输入名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="begTime" class="col-sm-2 control-label">开始时间</label>
                                <div class="col-sm-10">
                                    <input type="date" class="form-control required edit_begTime_ipt" name="begTime" id="begTime" placeholder="请选择时间">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="endTime" class="col-sm-2 control-label">结束时间</label>
                                <div class="col-sm-10">
                                    <input type="date" class="form-control required edit_endTime_ipt" name="endTime" id="endTime" placeholder="请选择时间">
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="submit" class="btn btn-primary">保存</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <button type="button" style="float:right;"  data-toggle="modal" data-target="#planAddModal" class="btn btn-default add_user_btn">+创建任务</button>
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title" style="display:inline-block;float:left;">
                <span>任务列表</span>
                <span style="display:inline-block;padding-left:30px;font-size: 13px;color: #aba9a9;">不能删除已有数据提交的任务，如需删除，请联系系统管理员。</span>
            </h3>
            <div class="checkbox" style="display:inline-block;float:right;margin:0">
                <label><input type="checkbox" id="showhistoryplan">显示历史任务</label>
            </div>
            <div style="clear:both;"></div>
        </div>
        <div class="panel-body">
            <table class="table table-hover data_list_table">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>

        <div class="my_paginationx">
            <ul class="pagination">
            </ul>
        </div>

    </div>

</div>

<script type="text/javascript">


    var requestBasePath = requestBasePath1;
    loadPlan();
    function loadPlan(name,page) {
        $.ajax({
            type: "post",
            url: requestBasePath +"/plan_list",
            cache : false,
            data: {'name': name,'page':page,'size':"10",'onlyInTime':false},
            success : function(datas, textStatus){
                var html = [];
                if(datas && datas.datas.length>0){
                    for (var idx=0;idx<datas.datas.length;idx++){
                        var data = datas.datas[idx];
                        html.push("<tr rec_id='"+data.ID+"'>");
                        html.push("<td class='title_td'>"+data.TITLE+"</td>");
                        html.push("<td>"+new Date(data.BEG_TIME).format('yyyy-MM-dd')+"</td>");
                        html.push("<td>"+data.STATUS+"</td>");
                        html.push("<td><a target='_blank' href='/anser_list?planID="+data.ID+"' type='buttom'>查询完成情况</a></td>")
                        html.push("<td><buttom  onclick='updataPlan(this)' type='buttom'>修改</buttom></td>");
                        html.push("<td><buttom  onclick='deletePlan(this)' type='buttom'>删除</buttom></td>");
                        html.push("</tr>");
                    }
                }
                $(".data_list_table").html(html.join());
                console.log(datas);
            }
        })
    }

    function addPlan(form){
        $.ajax({
            type:"post",
            url:requestBasePath +"/plan_add",
            cache:false,
            data:$(form).serialize(),
            success:function (data) {
                console.log(data);
            }
        })
    }

    function updataPlan(element){
        var tr = $(element).parent().parent();
        var id = tr.attr("rec_id");
        $("#plan_edit_form .edit_id_ipt").val(id);
        $("#plan_edit_form .edit_status_ipt").val( tr.find(".staus").text());
        $("#plan_edit_form .edit_title_ipt").val(tr.find(".title_td").text());
        $("#plan_edit_form .edit_begTime_ipt").val(tr.find("beg_time_td").text());
        $("#plan_edit_form .edit_endTime_ipt").val(tr.find(".endTime").text());
        $('#planeditModal').modal('show');
    }

    function deletePlan(element){
        var id = $(element).parent().parent().attr("rec_id");
        var name = $(element).parent().parent().find(".title_td").text();
        Alter("删除任务："+name+"?",function(){
            $.ajax({
                type:"get",
                url: requestBasePath + "/del_plan?id="+id,
                catch : false,
                success: function (data) {
                    console.log(data);
                }
            });
        });
    }

    function upPlan(form){
        $.ajax({
            type:"post",
            url:requestBasePath +"/up_plan",
            cache:false,
            data:$(form).serialize(),
            success:function (data) {
                console.log(data);
                refresh();
            }
        })
    }

    $(document).ready(function(){


        $("#plan_add_form").validate({
            rules: {
                refModelId: {
                    required: true,
                }
            },
            submitHandler:addPlan
        });

        $("#plan_edit_form").validate({
            submitHandler:upPlan
        })





    });
</script>
</body>
</html>
