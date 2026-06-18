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
		title:'',
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
		this.getZskList();
	},
	onShow(params){
		var app = getApp();
		this.refreshList();
	},
	refreshList(){
		this.setData({
			currentPage:1,
			zskList:[]
		});
		this.getZskList();
	},
	getZskList(){
		wx.showLoading({
			title: '加载中...',
			mask: true
		})
		var pageCount = this.getZskListCount();
		if(pageCount>=this.data.currentPage){
			wx.showToast({
				title: "已最后一页"
			})
			wx.hideLoading();
			return;
		}
		this.getZskData();
		wx.hideLoading();
	},
	getZskListCount(){
		var that = this;
		var totalPage = null;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/index/zsk_search/getZskCount',
			method: 'POST',
			data: {
				"title":that.data.title==undefined?'':that.data.title,
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
					totalPage = res.data.totalPage;
				}
			},
			fail: function(err) {
				console.error('请求失败', err);
			}
		});
		return totalPage;
	},
	getZskData(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/index/zsk_search/getZskList',
			method: 'POST',
			data: {
				"page":that.data.currentPage,
				"title":that.data.title==undefined?'':that.data.title,
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
		return await app.getShareData(this, '办公室报修系统：知识库');
	}
})
