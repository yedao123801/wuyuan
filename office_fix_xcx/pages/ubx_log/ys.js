Page({
	data: {
	},
	submitData(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/user/bx_log/ys_submit',
			method: 'POST',
			data: {
				"loginToken":wx.getStorageSync('token'),
				"ysNote":that.data.ysNote==undefined?'':that.data.ysNote,
				"id":that.data.id
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
		this.setData({
			id:params.id
		});
		this.initPreData();
	},
	initPreData(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/user/bx_log/getDetailData',
			method: 'POST',
			data: {
				"id":that.data.id,
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
					that.setData({
					ysNote:res.data.detail.bxLog.ysNote});
				}
			},
			fail: function(err) {
				console.error('请求失败', err);
			}
		});
	},
	castListVal(list){
		var list2 = [];
		for(var i=0;i<list.length;i++){
			var map ={};
			map.value=list[i].id+'';
			map.title=list[i].name;
			list2.push(map);
		}
		return list2;
	},
	inputHandler(event) {
		var eid = event.target.id;
		this.setData({[eid]:event.detail.value}) //实现双向绑定
	},
})
