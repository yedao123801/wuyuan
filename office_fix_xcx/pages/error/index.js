import ad from '../../dist/ad/ad'
ad({
	data: {
	},
	onLoad(params){
		//1 未登录  2.操作成功，3 支付失败  4 支付成功
		if(params.tipType==1){
			this.setData({
				tipType:1,
				tip:'尚未登录',
				subTitle:'请先前往登录',
				icon: false,
				tip:'',
				subTitle:'',
				projectSubTitle:'',
				buttons: [{
					type: 'light',
					block: true,
					text: '返回',
				}],
			})
		}
		if(params.tipType==2){
			this.setData({
				tipType:2,
				tip:'操作成功',
				subTitle:'',
				icon: {
				},
				tip:'',
				subTitle:'',
				projectSubTitle:'',
				buttons: [{
					type: 'light',
					block: true,
					text: '返回',
				}],
			})
		}
		if(params.tipType==3){
			this.setData({
				tipType:3,
				tip:'支付失败',
				subTitle:'',
				icon: {
					type: 'warn',
					color: '#ef473a',
				},
				tip:'',
				subTitle:'',
				projectSubTitle:'',
				buttons: [{
					type: 'light',
					block: true,
					text: '返回',
				}],
			})
		}
		if(params.tipType==4){
			this.setData({
				tipType:4,
				tip:'支付成功',
				subTitle:'',
				icon: {
				},
				tip:'',
				subTitle:'',
				projectSubTitle:'',
				buttons: [{
					type: 'light',
					block: true,
					text: '返回',
				}],
			})
		}
	},
	onClick(e) {
		var tipType = this.data.tipType;
		if(tipType==1){
			wx.navigateTo({ url: '/pages/login/index_login'})
			}else{
			wx.navigateBack();
		}
	},
})
