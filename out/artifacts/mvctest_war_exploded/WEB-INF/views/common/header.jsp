<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/27
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getContextPath();
    basePath = basePath;
%>
<link rel="stylesheet" href="<%=basePath%>/resources/bootstrap-3.3.5/bootstrap.min.css">
<script src="<%=basePath%>/resources/jquery.min.js"></script>
<script src="<%=basePath%>/resources/bootstrap-3.3.5/bootstrap.min.js"></script>
<%--<script src="<%=basePath%>/resources/bootstrap-3.3.5/respond.js"></script>--%>
<%--<script src="<%=basePath%>/resources/bootstrap-3.3.5/html5shiv.js"></script>--%>
<%--<script src="<%=basePath%>/resources/jquery-validate-ext.js"></script>--%>
<%--<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>--%>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script>
    var requestBasePath1 = "<%=basePath%>";

    var alterConfimCallBackFun = null;

    function Alter(tipBody,confirmCall){
        alterConfimCallBackFun = confirmCall;
        $("#actionConfrimModal .modal-body").html(tipBody);
        $("#actionConfrimModal").modal("show");
    }
    $(document).ready(function(){
        $("#actionConfrimModal .btn-primary").click(function(){
            alterConfimCallBackFun && alterConfimCallBackFun();
            $("#actionConfrimModal").modal("hide");
        });
    });

</script>

<div class="modal fade" id="actionConfrimModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">
                    请确认
                </h4>
            </div>
            <div class="modal-body">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消
                </button>
                <button type="button" class="btn btn-primary">
                    确认
                </button>
            </div>
        </div>
    </div>
</div>