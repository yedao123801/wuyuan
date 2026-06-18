Page({
	data: {
	},
	onLoad() {
		this.setData({
			currentLogin:wx.getStorageSync('currentLogin'),
			token:wx.getStorageSync('token'),
		});
		if (typeof this.getTabBar === 'function' &&this.getTabBar()) {
			this.getTabBar().setData({
				selected: 1
			})
		}
		this.loadPersonalMsg();
	},
	onShow() {
		if (typeof this.getTabBar === 'function' &&this.getTabBar()) {
			this.getTabBar().setData({
				selected: 1
			})
		}
		this.loadPersonalMsg();
	},
	loadPersonalMsg(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/user/index/getInitData',
			method: 'POST',
			data: {
				"loginToken":wx.getStorageSync("token")
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
					wx.navigateTo({ url: '/pages/login/index_login'})
					}else{
					that.setData({
						model:res.data.userModel
					});
					if(res.data.limitUrl!=undefined&&res.data.limitUrl!=null&&res.data.limitUrl!=''){
						for(var i=0;i<res.data.limitUrl.length;i++){
							var limit = res.data.limitUrl[i];
							var step1Str = limit.replace(/\//g, "");
							var step2 = step1Str.replace(/:/g, "");
							const newStr = step2.replace(/_/g, "");
							that.setData({
								[newStr]:0
							});
						}
					}
				}
			},
			fail: function(err) {
				console.error('请求失败', err);
			}
		});
	},
	exitLogin(){
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/login/sys_logout',
			method: 'POST',
			data: {
				"token":wx.getStorageSync("token")
			},
			header: {
				'content-type': 'application/x-www-form-urlencoded'
			},
			success: function(res) {
				wx.redirectTo({ url: '/pages/login/index_login'});
			},
			fail: function(err) {
				console.error('请求失败', err);
			}
		});
	},
	onPreview(e) {
		wx.previewImage({
			urls: [e.currentTarget.dataset.url],
		})
	},
	checkLimitUrlInclude(path) {
		// 兜底：确保limitUrl是数组，避免调用includes时报错
		const limitList = Array.isArray(this.data.limitUrl) ? this.data.limitUrl : [];
		return limitList.includes(path);
	},
})
