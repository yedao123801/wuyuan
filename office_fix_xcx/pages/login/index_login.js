Page({
	data: {
	},
	submitLogin(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/login/sysSubmit',
			method: 'POST',
			data: {
				"imgCode":this.data.validCode,
				"imgCodeToken":this.data.validToken,
				"name":this.data.loginName,
				"password":this.data.password,
				"loginType":3
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
						duration: 2000
					});
					wx.setStorageSync('token', res.data.loginToken);
					var login = {};
					login.id = res.data.loginId;
					login.loginType = res.data.loginType;
					login.loginName = res.data.loginName;
					wx.setStorageSync('currentLogin', login);
					setTimeout(function() {
						wx.switchTab({ url: '/pages/index/index'});
					}, 1000);
				}
			},
			fail: function(err) {
				console.error('请求失败', err);
			}
		});
	},
	getValidImg(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/validCode/code',
			method: 'POST',
			data: {
			},
			success: function(res) {
				if(res.statusCode!=200){
					wx.showToast({
						title: '网络出错',
						icon: 'none',
						duration: 2000
					})
					return;
				}
				that.setData({
					validCodeImg:res.data.imgUrl,
					validToken:res.data.codeToken
				});
			},
			fail: function(err) {
			}
		});
	},
	clickValidImg(){
		this.getValidImg();
	},
	onLoad(){
		this.getValidImg()
	},
	inputHandler(event) {
		var eid = event.target.id;
		this.setData({[eid]:event.detail.value}) //实现双向绑定
	},
	async onShareAppMessage() {
		const app = getApp();
		return await app.getShareData(this, '#项目名#：#页面标题#');
	}
})
