const filterutil = require('../../assets/filterutil.js');
Page({
	data: {
		pageParams:[],
		list:[],
		currentPage:1,
		currentLogin:wx.getStorageSync('currentLogin'),
		baseHostPort:wx.getStorageSync('baseHostPort'),
		token:wx.getStorageSync('token'),
		filterDimensionList:[],
		filterModalVisible: false,
		activeTab: 'all',
		activeFilterTab: '',
		// 当前选中的筛选维度对象（用于渲染标签）
		currentFilterDimension: null,
		// 总选中数（用于角标）
		totalSelectedCount: 0,
		filterResult: {},
		yxcd:'',
		bxStatus:'',
		searchKeyWord:'',
	},
	onAction(e) {
		console.log('onAction', e.detail)
	},
	onReachBottom(){
		this.setData({
			currentPage:this.data.currentPage+1
		});
		this.getList();
	},
	onLoad(params){
		this.getPageData();
		this.setData({
			currentLogin:wx.getStorageSync('currentLogin'),
			token:wx.getStorageSync('token')
		});
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
			url: wx.getStorageSync('baseHostPort')+'/user/bx_log/getRelativeDataList',
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
					var yxcdFiler={};
					yxcdFiler.zhName='优先程度';
					yxcdFiler.attrName='yxcd';
					yxcdFiler.selectedRs='';
					yxcdFiler.list=res.data.yxcdList;
					dimensionList.push(yxcdFiler);
					var bxStatusFiler={};
					bxStatusFiler.zhName='报修状态';
					bxStatusFiler.attrName='bxStatus';
					bxStatusFiler.selectedRs='';
					bxStatusFiler.list=res.data.bxStatusList;
					dimensionList.push(bxStatusFiler);
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
	go_eval_log(e){
		var id = e.currentTarget.dataset.id;
		var adata = {};
		for(var i=0;i<this.data.list.length;i++){
			if(this.data.list[i].bxLog.id==id){
				adata = this.data.list[i];
				break;
			}
		}
		wx.navigateTo({
			url: '/pages/ueval_log/add?1=1&bxid='+adata.bxLog.id+''
		});
	},
	del1(e){
		var that = this;
		var id = e.currentTarget.dataset.id;
		wx.showModal({
			title: '提示',
			content: '确认要删除该数据？',
			success (res) {
				if (res.confirm) {
					wx.request({
						url: wx.getStorageSync('baseHostPort')+'/user/bx_log/del1',
						method: 'POST',
						data: {
							"loginToken":wx.getStorageSync('token'),
							"id":id
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
									title:  res.data.msg,
									icon: 'success',
									duration: 2000
								});
								setTimeout(function(){
									that.refreshList();
								}, 2000)
							}
						},
						fail: function(err) {
							console.error('请求失败', err);
						}
					});
					} else if (res.cancel) {
				}
			}
		})
	},
	ys(e){
		var id = e.currentTarget.dataset.id;
		wx.navigateTo({
			url: '/pages/ubx_log/ys?1=1&id='+id+''
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
	refreshList(){
		this.setData({
			currentPage:1,
			list:[]
		});
		this.getList();
	},
	onPullDownRefresh(){
		this.refreshList();
	},
	getList(){
		wx.showLoading({
			title: '加载中...',
			mask: true
		})
		var pageCount = this.getListCount();
		if(pageCount>=this.data.currentPage){
			wx.showToast({
				title: "已最后一页"
			})
			wx.hideLoading();
			return;
		}
		this.getData();
		wx.hideLoading();
	},
	getListCount(){
		var that = this;
		var totalPage = null;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/user/bx_log/queryCount',
			method: 'POST',
			data: {
				"loginToken":wx.getStorageSync('token'),
				"yxcdFilter":that.data.filterResult['yxcdFilter']==undefined?'':that.data.filterResult['yxcdFilter'],
				"searchKeyWord":that.data.searchKeyWord==undefined?'':that.data.searchKeyWord,
				"bxStatusFilter":that.data.filterResult['bxStatusFilter']==undefined?'':that.data.filterResult['bxStatusFilter']
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
	getData(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/user/bx_log/queryList',
			method: 'POST',
			data: {
				"page":that.data.currentPage,
				"loginToken":wx.getStorageSync('token'),
				"yxcdFilter":that.data.filterResult['yxcdFilter']==undefined?'':that.data.filterResult['yxcdFilter'],
				"searchKeyWord":that.data.searchKeyWord==undefined?'':that.data.searchKeyWord,
				"bxStatusFilter":that.data.filterResult['bxStatusFilter']==undefined?'':that.data.filterResult['bxStatusFilter']
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
					var tmpList = (that.data.list==null?[]:that.data.list);
					for(var i=0;i<res.data.list.length;i++){
						tmpList.push(res.data.list[i]);
					}
					that.setData({
						list:tmpList
					});
				}
			},
			fail: function(err) {
				console.error('请求失败', err);
			}
		});
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
})
