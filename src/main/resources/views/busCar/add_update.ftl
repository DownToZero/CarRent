<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- 设置营销机会的ID -->
    <input type="hidden" name="hcarnumber" value="${(busCar.carnumber)!}">

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">车牌号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="carnumber" id="carnumber"  value="${(busCar.carnumber)!}" placeholder="请输入车牌号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">出租类型</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="cartype"
                   id="cartype" value="${(busCar.cartype)!}" placeholder="请输入出租类型">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">出租颜色</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="color"
                   lay-verify="required"  value="${(busCar.color)!}" placeholder="请输入出租颜色">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">汽车价格</label>
        <div class="layui-input-block">
            <input type="number" class="layui-input" lay-verify="required"
                   name="price" value="${(busCar.price)!}" id="price" placeholder="请输入汽车价格">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">出租价格</label>
        <div class="layui-input-block">
            <input type="number" class="layui-input" lay-verify="required"
                   name="rentprice" value="${(busCar.rentprice)!}" id="rentprice" placeholder="请输入出租价格">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">出租押金</label>
        <div class="layui-input-block">
            <input type="number" class="layui-input" lay-verify="required" name="deposit" value="${(busCar.deposit)!}"
                   placeholder="请输入出租押金">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">出租描述</label>
        <div class="layui-input-block">
                    <textarea placeholder="请输入出租描述信息" name="description" class="layui-textarea">${(busCar.description)!}
                  	</textarea>
        </div>
    </div>
<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">指派给</label>-->
<#--        <div class="layui-input-block">-->
<#--            <select name="assignMan" id="assignMan">-->
<#--                <option value="">请选择</option>-->
<#--            </select>-->
<#--        </div>-->
<#--    </div>-->
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateBusCar">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/busCar/add_update.js"></script>
</body>
</html>