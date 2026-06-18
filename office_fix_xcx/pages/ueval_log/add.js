Page({
	data: {
		evalStar: 0, // 星级默认值（0-5）
		evalStarIsEdit: '',
	},
	submitData(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/user/eval_log/add_submit',
			method: 'POST',
			data: {
				"bxid":(that.data.bxid==undefined?'':that.data.bxid),
				"loginToken":wx.getStorageSync('token'),
				"evalStar":that.data.evalStar==undefined?'':that.data.evalStar,
				"evalContent":that.data.evalContent==undefined?'':that.data.evalContent
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
										params2.bxid=(that.data.bxid==undefined?'':that.data.bxid),
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
			bxid:params.bxid==undefined?'':params.bxid,
		});
	},
	chooseStar(e) {
		var dataId = e.currentTarget.dataset.id;
		var isdisabled = this.data[dataId+'IsEdit'];
		if(isdisabled!=''&&isdisabled=='1'){
			return;
		}
		const score = Number(e.currentTarget.dataset.score);
		this.setData({ [dataId]: score });
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
