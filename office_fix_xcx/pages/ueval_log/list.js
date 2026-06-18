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
		evalContent:'',
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
		this.setData({
			bxid:params.bxid==undefined?'':params.bxid,
		});
		this.setData({
			currentLogin:wx.getStorageSync('currentLogin'),
			token:wx.getStorageSync('token')
		});
		this.refreshList();
	},
	update(e){
		var id = e.currentTarget.dataset.id;
		wx.navigateTo({
			url: '/pages/ueval_log/update?1=1&bxid='+this.data.bxid+'&id='+id+''
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
						url: wx.getStorageSync('baseHostPort')+'/user/eval_log/del1',
						method: 'POST',
						data: {
							"bxid":(that.data.bxid==undefined?'':that.data.bxid),
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
			url: wx.getStorageSync('baseHostPort')+'/user/eval_log/queryCount',
			method: 'POST',
			data: {
				"bxid":(that.data.bxid==undefined?'':that.data.bxid),
				"loginToken":wx.getStorageSync('token'),
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
			url: wx.getStorageSync('baseHostPort')+'/user/eval_log/queryList',
			method: 'POST',
			data: {
				"page":that.data.currentPage,
				"bxid":(that.data.bxid==undefined?'':that.data.bxid),
				"loginToken":wx.getStorageSync('token'),
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
})
