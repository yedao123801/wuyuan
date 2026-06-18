Page({
	data: {
	},
	submitData(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/alterPwd/alterPassword/submit',
			method: 'POST',
			data: {
				"password1":that.data.password1,
				"password2":that.data.password2,
				"password3":that.data.password3,
				"loginToken":wx.getStorageSync('token')
			},
			header: {
				'content-type': 'application/x-www-form-urlencoded'
			},
			success: function(res) {
				if(res.statusCode!=200){
					wx.showToast({
						title: '系统出错',
						icon: 'none',
						duration: 2000
					})
					return;
				}
				if(res.data.code==0){
					wx.showToast({
						title:  res.data.msg,
						icon: 'none',
						duration: 2000
					});
					}else{
					wx.showToast({
						title: res.data.msg,
						icon: 'success',
						duration: 2000,
						mask: true,
						success() {
							setTimeout(function() {
								wx.navigateBack({
									delta: 1,
									success: function () {
										// 刷新操作
										var params2 = {};
										getCurrentPages()[getCurrentPages().length - 1].onLoad(params2);
									}
								});
							}, 2000);
						},
					});
				}
			},
			fail: function(err) {
				console.error('请求失败', err);
			}
		});
	},
	onLoad(params){
	},
	inputHandler(event) {
		var eid = event.target.id;
		this.setData({[eid]:event.detail.value}) //实现双向绑定
	},
})
