const locationUtils = require('../../assets/location.js');
const filterutil = require('../../assets/filterutil.js');
Page({
	data: {
		pageParams:[],
		list:[],
		currentPage:1,
		currentLogin:wx.getStorageSync('currentLogin'),
		baseHostPort:wx.getStorageSync('baseHostPort'),
		token:wx.getStorageSync('token'),
		rangeParams:'',
	},
	onAction(e) {
		console.log('onAction', e.detail)
	},
	onReachBottom(){
		this.setData({
			currentPage:this.data.currentPage+1
		});
	},
	onPullDownRefresh(){
	},
	onShow(params){
		var app = getApp();
		if (typeof this.getTabBar === 'function' &&this.getTabBar()) {
			this.getTabBar().setData({
				selected: 0
			})
		}
		this.refreshBgServiceList();
		this.refreshZskList();
		this.refreshXtNoticeList();
	},
	refreshBgServiceList(){
		this.setData({
			bgServiceList:[]
		});
		this.getBgServiceList();
	},
	getBgServiceList(){
		wx.showLoading({
			title: '加载中...',
			mask: true
		})
		this.getBgServiceData();
		wx.hideLoading();
	},
	getBgServiceData(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/index/index/getBgServiceList',
			method: 'POST',
			data: {
				"page":that.data.currentPage,
				"rangeParams":that.data.rangeParams,
				"token":wx.getStorageSync('token')
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
					var tmpList = that.data.bgServiceList;
					if(tmpList==undefined){
						tmpList = [];
					}
					for(var i=0;i<res.data.data.length;i++){
						tmpList.push(res.data.data[i]);
					}
					that.setData({
						bgServiceList:tmpList
					});
				}
			},
			fail: function(err) {
				console.error('请求失败', err);
			}
		});
	},
	refreshZskList(){
		this.setData({
			zskList:[]
		});
		this.getZskList();
	},
	getZskList(){
		wx.showLoading({
			title: '加载中...',
			mask: true
		})
		this.getZskData();
		wx.hideLoading();
	},
	getZskData(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/index/index/getZskList',
			method: 'POST',
			data: {
				"page":that.data.currentPage,
				"rangeParams":that.data.rangeParams,
				"token":wx.getStorageSync('token')
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
					var tmpList = that.data.zskList;
					if(tmpList==undefined){
						tmpList = [];
					}
					for(var i=0;i<res.data.data.length;i++){
						tmpList.push(res.data.data[i]);
					}
					that.setData({
						zskList:tmpList
					});
				}
			},
			fail: function(err) {
				console.error('请求失败', err);
			}
		});
	},
	refreshXtNoticeList(){
		this.setData({
			xtNoticeList:[]
		});
		this.getXtNoticeList();
	},
	getXtNoticeList(){
		wx.showLoading({
			title: '加载中...',
			mask: true
		})
		this.getXtNoticeData();
		wx.hideLoading();
	},
	getXtNoticeData(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/index/index/getXtNoticeList',
			method: 'POST',
			data: {
				"page":that.data.currentPage,
				"rangeParams":that.data.rangeParams,
				"token":wx.getStorageSync('token')
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
					var tmpList = that.data.xtNoticeList;
					if(tmpList==undefined){
						tmpList = [];
					}
					for(var i=0;i<res.data.data.length;i++){
						tmpList.push(res.data.data[i]);
					}
					that.setData({
						xtNoticeList:tmpList
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
	inputHandler(event) {
		var eid = event.target.id;
		this.setData({[eid]:event.detail.value}) //实现双向绑定
	},
	async onShareAppMessage() {
		const app = getApp();
		return await app.getShareData(this, '办公室报修系统：首页');
	}
})
