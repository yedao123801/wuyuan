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
		serviceNo:'',
		bgqy:'',
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
		this.getBgServiceList();
	},
	onShow(params){
		var app = getApp();
		this.getPageData();
		this.refreshList();
	},
	onTabChange(e) {
		var id = e.target.id;
		this.setData({
			[id]: e.detail.key,
		});
		this.refreshList();
	},
	getPageData(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/index/bg_service_search/getListData',
			method: 'POST',
			data: {
				"loginToken":wx.getStorageSync('token'),
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
					var dimensionList=[];
					var bgqyFiler={};
					bgqyFiler.zhName='办公区域';
					bgqyFiler.attrName='bgqy';
					bgqyFiler.selectedRs='';
					bgqyFiler.list=res.data.bgqyList;
					dimensionList.push(bgqyFiler);
					;
					that.setData({
						filterDimensionList: dimensionList
						}, () => {
						that.initFilterAllMsg();
					});
				}
			},
			fail: function(err) {
				console.error('请求失败', err);
			}
		});
	},
	refreshList(){
		this.setData({
			currentPage:1,
			bgServiceList:[]
		});
		this.getBgServiceList();
	},
	getBgServiceList(){
		wx.showLoading({
			title: '加载中...',
			mask: true
		})
		var pageCount = this.getBgServiceListCount();
		if(pageCount>=this.data.currentPage){
			wx.showToast({
				title: "已最后一页"
			})
			wx.hideLoading();
			return;
		}
		this.getBgServiceData();
		wx.hideLoading();
	},
	getBgServiceListCount(){
		var that = this;
		var totalPage = null;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/index/bg_service_search/getBgServiceCount',
			method: 'POST',
			data: {
				"serviceNo":that.data.serviceNo==undefined?'':that.data.serviceNo,
				"bgqyFilter":that.data.bgqyFilter==undefined?'':that.data.bgqyFilter,
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
	getBgServiceData(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/index/bg_service_search/getBgServiceList',
			method: 'POST',
			data: {
				"page":that.data.currentPage,
				"serviceNo":that.data.serviceNo==undefined?'':that.data.serviceNo,
				"bgqyFilter":that.data.bgqyFilter==undefined?'':that.data.bgqyFilter,
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
	initFilterAllMsg(){
		filterutil.initFilterAllMsg(this);
	},
	// 切换弹窗内筛选维度Tab
	switchFilterTab(e) {
		const attrName = e.currentTarget.dataset.attrName;
		const currentDimension = this.data.filterDimensionList.find(item => item.attrName === attrName);
		this.setData({
			activeFilterTab: attrName,
			currentFilterDimension: currentDimension
		});
	},
	/**
	* 核心：选择筛选标签，同步更新selectedRs、checked、filterResult
	*/
	selectFilterItem(e) {
		const { attrName, itemId } = e.currentTarget.dataset;
		filterutil.selectFilterItemAct(attrName,itemId,this);
	},
	resetCurrentFilter() {
		filterutil.resetCurrentFilter(this);
	},
	// 显示筛选弹窗
	showFilterModal() {
		this.setData({
			filterModalVisible: true,
			activeTab: 'filter'
		});
	},
	// 隐藏筛选弹窗
	hideFilterModal() {
		this.setData({ filterModalVisible: false });
	},
	// 重置所有筛选条件
	resetAllFilter() {
		filterutil.resetAllFilter(this);
	},
	// 确认筛选
	confirmFilter() {
		filterutil.confirmFilter(this);
	},
	rangeByAttr(e){
		var name= e.currentTarget.dataset.name;
		var isLocation = e.currentTarget.dataset.islocation;
		filterutil.rangeByAttrAct(name,isLocation,this);
	},
	async onShareAppMessage() {
		const app = getApp();
		return await app.getShareData(this, '办公室报修系统：办公设备');
	}
})
