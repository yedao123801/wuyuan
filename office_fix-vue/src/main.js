import { createApp } from 'vue'
	import App from './App.vue'
	import router from './router'
	import installElementPlus from './plugins/element'
	//创建vue实例
	const vapp = createApp(App)
	//全局注册后台地址
	vapp.config.globalProperties.REQUEST_URL="http://localhost:8080/office_fix";
vapp.config.globalProperties.goToChildPageWithBlank =function(e){
	var href = e.currentTarget.dataset.href;
	var targetpage = e.currentTarget.dataset.targetpage;
	var url = window.btoa(targetpage);
	window.open(href+"?childPageUrl="+url);
}
//定义跳转函数到新窗口
vapp.config.globalProperties.goToPageWithBlank =function(e){
	var href = e.currentTarget.dataset.href;
	var params = e.currentTarget.dataset.params;
	var split = params.split('&');
var querys = {};
	for(var i=0;i<split.length;i++){
		if(split[i]!=''){
			var tmp = split[i].split('=');
			querys[tmp[0]]=tmp[1];
		}
	}
	let routeData = router.resolve({
		path:href,
		query:querys
	});
	window.open(routeData.href, '_blank');
}
//定义跳转函数到新窗口,JS调用
vapp.config.globalProperties.goToPageWithBlankJS =function(href,params){
	var split = params.split('&');
var querys = {};
	for(var i=0;i<split.length;i++){
		if(split[i]!=''){
			var tmp = split[i].split('=');
			querys[tmp[0]]=tmp[1];
		}
	}
	let routeData = router.resolve({
		path:href,
		query:querys
	});
	window.open(routeData.href, '_blank');
}
//定义跳转函数
vapp.config.globalProperties.goToPage =function(e){
	var href = e.currentTarget.dataset.href;
	var params = e.currentTarget.dataset.params;
	var split = params.split('&');
var querys = {};
	for(var i=0;i<split.length;i++){
		if(split[i]!=''){
			var tmp = split[i].split('=');
			querys[tmp[0]]=tmp[1];
		}
	}
	router.push({
		path:href,
		query:querys
	});
}
vapp.config.globalProperties.downloadFileMethod =function(e){
	var href = e.currentTarget.dataset.href;
	var params = e.currentTarget.dataset.params;
	var split = params.split('&');
var querys = {};
	for(var i=0;i<split.length;i++){
		if(split[i]!=''){
			var tmp = split[i].split('=');
			querys[tmp[0]]=tmp[1];
		}
	}
	router.push({
		path:href,
		query:querys
	});
}
//定义跳转函数，js调用
vapp.config.globalProperties.goToPageJS =function(href,params){
	var split = params.split('&');
var querys = {};
	for(var i=0;i<split.length;i++){
		if(split[i]!=''){
			var tmp = split[i].split('=');
			querys[tmp[0]]=tmp[1];
		}
	}
	router.push({
		path:href,
		query:querys
	});
}
//阻止显示生产模式的消息
vapp.config.productionTip = false
installElementPlus(vapp)
vapp.use(router).mount('#app')
