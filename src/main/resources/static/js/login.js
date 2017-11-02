layui.config({
	base : "js/"
}).use([ 'form', 'layer' ],function() {
	var form = layui.form, layer = parent.layer === undefined ? layui.layer
			: parent.layer, $ = layui.jquery;

	//video背景
	$(window).resize(function(){
		if($(".video-player").width() > $(window).width()){
			$(".video-player").css({"height":$(window).height(),"width":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}else{
			$(".video-player").css({"width":$(window).width(),"height":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}
	}).resize();
	
	form.verify({
		username : function(value, item) { // value：表单的值、item：表单的DOM对象
			if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$")
					.test(value)) {
				return '用户名不能有特殊字符';
			}
			if (/(^\_)|(\__)|(\_+$)/.test(value)) {
				return '用户名首尾不能出现下划线\'_\'';
			}
			if (/^\d+\d+\d$/.test(value)) {
				return '用户名不能全为数字';
			}
		},
		// 我们既支持上述函数式的方式，也支持下述数组的形式
		// 数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
		pass : [ /^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格' ],
		code : function(value, item) { // value：表单的值、item：表单的DOM对象
			var ret;
			// 同步验证码事件
			$.ajax({
				type : "get",
				url : "getKaptchaSessionKey",
				data : "code=" + value,
				async : false,
				success : function(data) {
					data = eval("(" + data + ")");
					ret = data;
				}
			});
			if (!ret) {
				return "验证码输入错误，请重来！";
			}
		}
	});

	// 登录按钮事件
	form.on("submit(login)", function(data) {
		console.log(data);
		return true;
	});

	// 切换验证码事件
	$("#kaptcha").bind(
			"click",
			function() {
				$("#kaptcha").attr(
						"src",
						"/images/kaptcha.jpg?t="
								+ Math.random());

			});
})