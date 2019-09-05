Page({
  onLoad(query) {
    // 页面加载
    var _config = null;
		$.ajax({
			type : "POST",
			url : "/config",
			dataType : "json",
			contentType : "application/json;charset=utf-8",
			async : false,
			data : "iNoI123s22",
			success : function(data) {
				_config = data;
                alert(_config);
			}
		});
		// dd.config({
		// 			agentId : data.agentId, // 必填，微应用ID
		// 			corpId : data.corpId,//必填，企业ID
		// 			timeStamp : data.timestamp, // 必填，生成签名的时间戳
		// 			nonceStr : data.nonceStr, // 必填，生成签名的随机串
		// 			signature : data.sign, // 必填，签名
		// 			jsApiList : [ 'runtime.permission.requestAuthCode', 'device.notification.showPreloader', 'device.notification.hidePreloader', 'device.notification.vibrate',
		// 					'device.notification.actionSheet' ]
		// 		});
		//
		//可以不要:01==>>end
		
		dd.ready(function() {
			// //可以不要:02==>>start
			// dd.device.notification.showPreloader({
			// 	text : "正在获取当前用户信息 ...",
			// 	showIcon : true,
			// 	onSuccess : function(result) {
			// 	},
			// 	onFail : function(err) {
			// 	}
			// });
			//可以不要:02==>>end
			
			//获取code
			//必须
			dd.runtime.permission.requestAuthCode({
				corpId : _config.corpId,
				onSuccess : function(result) {
					var code = result.code;
					$("#code").text("钉钉传向后台CODE:" + code);
					alert(code);
					//getUserInfo(code);
				},
				onFail : function(err) {
                    alert(corpId);
                    alert('dd error: ' + JSON.stringify(err));
				}
			});

		});
		
		//拿到code以后剩下的都可以不要:03==>>start
		// function getUserInfo(code) {
		// 	$.ajax({
		// 		type : "GET",
		// 		url : "/userinfo?code=" + code,
		// 		async : false,
		// 		dataType : 'json',
		// 		contentType : "application/json;charset=utf-8",
		// 		success : (function(response) {
		// 			$("#userid").text("获取的用户信息:" + JSON.stringify(response));
		// 			dd.device.notification.hidePreloader({
		// 				onSuccess : function(result) {
		// 				},
		// 				onFail : function(err) {
		// 				}
		// 			});
        //
		// 			dd.device.notification.vibrate({
		// 				duration : 300,
		// 				onSuccess : function(result) {
		// 				},
		// 				onFail : function(err) {
		// 				}
		// 			});
		// 			//显示用户简略信息
		// 			displayInfo(response);
		// 		}),
		// 	});
        //
		// }
        //
		// function displayInfo(response) {
		// 	dd.device.notification.actionSheet({
		// 		title : "当前用户信息",
		// 		cancelButton : '关闭',
		// 		otherButtons : [ 'userid: ' + response.userid, 'deviceId: ' + response.deviceId, 'is_sys: ' + response.is_sys, 'sys_level: ' + response.sys_level ],
		// 		onSuccess : function(result) {
		// 			$.ajax({
		// 				type : "GET",
		// 				url : "/user?userid=" + response.userid,
		// 				async : false,
		// 				dataType : 'json',
		// 				contentType : "application/json;charset=utf-8",
		// 				success : (function(data) {
		// 					$("#userall").text("用户信息详细:" + JSON.stringify(data));
		// 					//显示用户详细信息
		// 					displayall(data);
		// 				})
		// 			});
		// 		},
		// 		onFail : function(err) {
		// 		}
		// 	});
		// }
        //
		// function displayall(data) {
		// 	var all = [];
		// 	all.push("userID:" + data.userid);
		// 	all.push("openID:" + data.openid);
		// 	all.push("unionid:" + data.unionid);
		// 	all.push("dingId:" + data.dingId);
		// 	all.push("name:" + data.name);
		// 	all.push("mobile:" + data.mobile);
		// 	all.push("isBoss:" + data.isBoss);
		// 	all.push("active:" + data.active);
		// 	all.push("isAdmin:" + data.isAdmin);
		// 	all.push("isSenior:" + data.isSenior);
		// 	all.push("department:" + data.department);
		// 	all.push("isLeaderInDepts:" + data.isLeaderInDepts);
        //
		// 	dd.device.notification.actionSheet({
		// 		title : "详细信息", //标题
		// 		cancelButton : '关闭', //取消按钮文本
		// 		otherButtons : all,
		// 		onSuccess : function(result) {
		// 		},
		// 		onFail : function(err) {
		// 		}
		// 	})
		// }
		//拿到code以后剩下的都可以不要:03==>>start
    console.info(`Page onLoad with query: ${JSON.stringify(query)}`);
  },
  onReady() {
    // 页面加载完成
  },
  onShow() {
    // 页面显示
  },
  onHide() {
    // 页面隐藏
  },
  onUnload() {
    // 页面被关闭
  },
  onTitleClick() {
    // 标题被点击
  },
  onPullDownRefresh() {
    // 页面被下拉
  },
  onReachBottom() {
    // 页面被拉到底部
  },
  onShareAppMessage() {
    // 返回自定义分享信息
    return {
      title: 'My App',
      desc: 'My App description',
      path: 'pages/index/index',
    };
  },
});
