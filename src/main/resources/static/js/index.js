/*
 * Demo
 * 使用Layui的form和upload模块	
 */
//第一个参数是加载模块，第二个参数实现功能
layui.use(['form', 'upload', 'layer'], function(){
	var form = layui.form //获取form模块
	, $ = layui.$ //获取jquery
	, layer = layui.layer;
	  
	//监听提交按钮
	form.on('sumbit(test)', function(data){
		console.log(data);
	});
});