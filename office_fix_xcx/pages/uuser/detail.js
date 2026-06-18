import ad from '../../dist/ad/ad'
ad({
	data: {
		currentLogin:wx.getStorageSync('currentLogin'),
		baseHostPort:wx.getStorageSync('baseHostPort'),
		token:wx.getStorageSync('token')
	},
	onLoad(params){
		this.setData({
			id:params.id
		});
		this.initDetail();
		this.setData({
			currentLogin:wx.getStorageSync('currentLogin'),
			token:wx.getStorageSync('token')
		});
	},
	btnClick(e){
		var that = this;
		var id = e.target.dataset.id;
		var btntype = e.target.dataset.btntype;
		if(btntype=='1'){
			wx.navigateTo({
				url: '/pages/uuser/update1?1=1&id='+id+''
			});
		}
	},
	initDetail(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/user/user/getDetailData',
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
						detail:res.data.detail
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
	downLoadFile33(e) {
		// 1. 校验参数：确保文件链接存在，兼容子元素点击（改用currentTarget更稳定）
		var url = e.currentTarget?.dataset?.fileurl; // 优先用currentTarget，避免target指向子元素
		if (!url) {
			wx.showToast({
				title: '文件链接为空',
				icon: 'none',
				duration: 2000
			});
			console.error('文件URL为空，请检查data-fileurl绑定');
			return;
		}
		// 2. 编码URL：处理含特殊字符（空格、中文）的链接
		url = encodeURI(url);
		console.log('开始下载文件，URL：', url);
		// 3. 显示加载提示，提升用户体验
		wx.showLoading({
			title: '下载中...',
			mask: true // 防止用户重复点击
		});
		// 4. 核心：仅执行文件下载，移除打开文档逻辑
		wx.downloadFile({
			url: url,
			timeout: 30000, // 设置30秒超时，避免长时间等待
			success (res) {
				wx.hideLoading(); // 关闭加载提示
				// 校验下载状态码（200/206均为常见成功码）
				if ([200, 206].includes(res.statusCode)) {
					// 下载成功：res.tempFilePath 是文件的临时存储路径
					console.log('文件下载成功，临时路径：', res.tempFilePath);
					wx.showToast({
						title: '文件下载成功',
						icon: 'success',
						duration: 2000
					});
					} else {
					// 状态码异常，下载失败
					console.error('下载失败，状态码：', res.statusCode);
					wx.showToast({
						title: '下载失败，状态码异常',
						icon: 'none'
					});
				}
			},
			// 下载失败的错误捕获（关键：定位问题）
			fail (err) {
				wx.hideLoading();
				console.error('文件下载失败：', err);
				// 根据错误类型给出精准提示
				if (err.errMsg.includes('url not in domain list')) {
					wx.showToast({ title: '未配置下载域名', icon: 'none' });
					} else if (err.errMsg.includes('request:fail')) {
					wx.showToast({ title: '网络错误或链接无效', icon: 'none' });
					} else {
					wx.showToast({ title: '下载失败：' + err.errMsg, icon: 'none' });
				}
			}
		});
	},
	async onShareAppMessage() {
		const app = getApp();
		return await app.getShareData(this, '办公室报修系统：用户详情');
	}
})
