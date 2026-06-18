Page({
	data: {
		currentLogin:wx.getStorageSync('currentLogin'),
		baseHostPort:wx.getStorageSync('baseHostPort'),
		showOtherFormModal:'',
		isShowAllComment:false,
		token:wx.getStorageSync('token')
	},
	onShow(){
		this.onLoad(this.data);
	},
	onLoad(params){
		this.setData({
			id:params.id
		});
		this.initDetail();
	},
	initDetail(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/index/zsk_detail/getZskDetail',
			method: 'POST',
			data: {
				"id":that.data.id==undefined?'':that.data.id
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
						detail:res.data.data
					});
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
	showOtherFormAct(e){
		var name  = e.currentTarget.dataset.name;
		console.log(name);
		this.setData({
			showOtherFormModal:name
		});
	},
	hideBookModal(){
		this.setData({
			showOtherFormModal:''
		});
	},
	inputHandler(event) {
		var eid = event.target.id;
		this.setData({[eid]:event.detail.value}) //实现双向绑定
	},
	downLoadFile(e){
		var url = e.target.dataset.fileurl;
		//下载文件
		wx.downloadFile({
			url: url,
			success (res) {
				if (res.statusCode === 200) {
					const filePath = res.tempFilePath
					wx.openDocument({
						filePath: filePath,
						showMenu:true, //关键点
						success: function (res) {
							console.log('打开文档成功')
						}
					})
				}
			}
		})
	},
	onRefresh() {
		const pages = getCurrentPages();
		const current = pages[pages.length - 1];
		const params = current.options;
		let url = `/${current.route}`;
		if (Object.keys(params).length > 0) {
			url += '?' + Object.keys(params).map(key => `${key}=${encodeURIComponent(params[key])}`).join('&');
		}
		wx.redirectTo({ url });
	},
	async onShareAppMessage() {
		const app = getApp();
		return await app.getShareData(this, '办公室报修系统：知识库详情');
	}
})
