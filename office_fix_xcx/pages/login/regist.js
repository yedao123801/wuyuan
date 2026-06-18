Page({
	data: {
		headImg:'',
		headImgFileList:[],
		depart:'',
		departList:[],
		departShowList:[],
		departShowAll:false,
	},
	submitRegist(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/regist/userRegistSubmit',
			method: 'POST',
			data: {
				"loginName":that.data.loginName==undefined?'':that.data.loginName,
				"password":that.data.password==undefined?'':that.data.password,
				"name":that.data.name==undefined?'':that.data.name,
				"celphone":that.data.celphone==undefined?'':that.data.celphone,
				"headImg":that.data.headImg==undefined?'':that.data.headImg,
				"depart":that.data.depart==undefined?'':that.data.depart,
				"imgCode":that.data.validCode,
				"imgCodeToken":that.data.validToken
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
						duration: 2000
					});
					setTimeout(function() {
						wx.navigateTo({
							url: '/pages/login/index_login',
						})
					}, 1000);
				}
			},
			fail: function(err) {
				console.error('请求失败', err);
			}
		});
	},
	getValidImg(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/validCode/code',
			method: 'POST',
			data: {
			},
			success: function(res) {
				if(res.statusCode!=200){
					wx.showToast({
						title: '网络出错',
						icon: 'none',
						duration: 2000
					})
					return;
				}
				that.setData({
					validCodeImg:res.data.imgUrl,
					validToken:res.data.codeToken
				});
			},
			fail: function(err) {
			}
		});
	},
	clickValidImg(){
		this.getValidImg();
	},
	onLoad(){
		this.setData({uploadUrl:wx.getStorageSync('baseHostPort')+'/imgUpload/thumb'});
		this.setData({audioPlaying:false});
		this.setData({currentAudioIndex:-1});
		this.setData({currentAudioDataId:''});
		this.setData({supportFileTypes:['doc', 'docx', 'xls', 'xlsx', 'ppt', 'pptx', 'pdf']});
		this.setData({directDownloadTypes:['txt', 'zip','rar']});
		this.getPageData();
		this.getValidImg();
	},
	initFileData(val, dataId, fileType = 'image') {
		const that = this;
		let list = [];
		if (val && val !== '') {
			const split = val.split(';');
			split.forEach(item => {
				if (item !== '') {
					const fileItem = {
						tempUrl: item,
						serverUrl: item,
						uploadStatus: 'success',
						fileType: fileType,
						fileName: fileType === 'file' ? item.split('/').pop() : (fileType === 'audio' ? '音频文件' : ''),
						fileSize: fileType === 'file' || fileType === 'audio' ? that.formatFileSize(0) : '',
						duration: fileType === 'video' ? '00:00' : '',
						coverUrl: fileType === 'video' ? item : ''
					};
					list.push(fileItem);
				}
			});
		}
		that.setData({
			[dataId + 'FileList']: list,
			[dataId]: val
		});
	},
	// ========== 核心修改：根据data-filetype直接触发对应上传 ==========
	chooseUploadType(e) {
		const that = this;
		var filetype = e.currentTarget.dataset.filetype;
		var maxnum = e.currentTarget.dataset.maxnum;
		var dataId = e.currentTarget.dataset.id;
		// 2. 参数合法性校验
		if (!filetype || !maxnum || !dataId) {
			wx.showToast({ title: '参数错误', icon: 'none', duration: 1500 });
			return;
		}
		// 3. 计算剩余可上传数量
		const currentFileList = this.data[dataId + 'FileList'] || [];
		const remainNum = maxnum - currentFileList.length;
		if (remainNum <= 0) {
			wx.showToast({ title: `最多只能上传${maxnum}个文件`, icon: 'none', duration: 1500 });
			return;
		}
		// 4. 根据filetype触发对应上传
		switch (Number(filetype)) {
			case 1: // 图片
			that.chooseMedia('image', remainNum, dataId);
			break;
			case 2: // 视频
			that.chooseMedia('video', remainNum, dataId);
			break;
			case 3: // 音频
			that.chooseFile('audio', remainNum, dataId);
			break;
			case 4: // 文件
			that.chooseFile('file', remainNum, dataId);
			break;
			default: // 非法类型
			wx.showToast({ title: '文件类型不支持', icon: 'none', duration: 1500 });
			break;
		}
	},
	chooseMedia(type, count, dataId) {
		const that = this;
		wx.chooseMedia({
			count: count,
			mediaType: [type],
			sourceType: ['album', 'camera'],
			maxDuration: 60,
			camera: 'back',
			success(res) {
				res.tempFiles.forEach((tempFile, loopIndex) => {
					const newFileItem = {
						tempUrl: tempFile.tempFilePath,
						serverUrl: '',
						uploadStatus: 'uploading',
						fileType: type,
						fileName: '',
						fileSize: that.formatFileSize(tempFile.size),
						duration: type === 'video' ? that.formatVideoDuration(tempFile.duration) : '',
						coverUrl: type === 'video' ? tempFile.thumbTempFilePath : ''
					};
					const newFileList = [...that.data[dataId + 'FileList'], newFileItem];
					const newIndex = newFileList.length - 1;
					that.setData({
						[dataId + 'FileList']: newFileList
					});
					that.uploadToServer(tempFile.tempFilePath, newIndex, dataId, type);
				});
			},
			fail(err) {
				wx.showToast({ title: '选择媒体失败', icon: 'none' });
				console.error('选择图片/视频失败：', err);
			}
		});
	},
	chooseFile(type, count, dataId) {
		const that = this;
		const fileType = type === 'file' ? ['file'] : ['audio'];
		wx.chooseMessageFile({
			count: count,
			type: 'file',
			extension: fileType,
			success(res) {
				res.tempFiles.forEach((tempFile, loopIndex) => {
					const newFileItem = {
						tempUrl: tempFile.path,
						serverUrl: '',
						uploadStatus: 'uploading',
						fileType: type,
						fileName: tempFile.name,
						fileSize: that.formatFileSize(tempFile.size),
						duration: '',
						coverUrl: ''
					};
					const newFileList = [...that.data[dataId + 'FileList'], newFileItem];
					const newIndex = newFileList.length - 1;
					that.setData({
						[dataId + 'FileList']: newFileList
					});
					that.uploadToServer(tempFile.path, newIndex, dataId, type);
				});
			},
			fail(err) {
				wx.showToast({ title: `选择${type === 'file' ? '文件' : '音频'}失败`, icon: 'none' });
				console.error(`选择${type === 'file' ? '文件' : '音频'}失败：`, err);
			}
		});
	},
	uploadToServer(tempUrl, index, dataId, fileType) {
		const that = this;
		const url = this.data.uploadUrl;
		const name = 'file';
		const header = {};
		wx.showLoading({ title: '上传中...', mask: true });
		wx.uploadFile({
			url: url,
			filePath: tempUrl,
			name: name,
			header: header,
			formData: { fileType: fileType },
			success(res) {
				wx.hideLoading();
				let result = {};
				try {
					result = JSON.parse(res.data);
					} catch (e) {
					console.error('解析上传结果失败：', e);
					that.updateUploadStatus(index, 'fail', '', dataId);
					wx.showToast({ title: '上传失败：返回格式错误', icon: 'none' });
					return;
				}
				if (result.code == 0 && result.url!=undefined) {
					that.updateUploadStatus(index, 'success', result.url, dataId);
					wx.showToast({ title: '上传成功', icon: 'success', duration: 800 });
					} else {
					that.updateUploadStatus(index, 'fail', '', dataId);
					wx.showToast({ title: `上传失败：${result.msg || '接口异常'}`, icon: 'none' });
				}
			},
			fail(err) {
				wx.hideLoading();
				that.updateUploadStatus(index, 'fail', '', dataId);
				wx.showToast({ title: '上传失败：网络错误', icon: 'none' });
				console.error('文件上传失败：', err);
			}
		});
	},
	// 索引越界修复：增加index边界校验
	updateUploadStatus(index, status, serverUrl = '', dataId) {
		const fileListKey = `${dataId}FileList`;
		const fileList = this.data[fileListKey] || [];
		if (index < 0 || index >= fileList.length) {
			console.error(`updateUploadStatus索引越界：index=${index}，数组长度=${fileList.length}`);
			return;
		}
		const newFileList = [...fileList];
		newFileList[index].uploadStatus = status;
		if (serverUrl) {
			newFileList[index].serverUrl = serverUrl;
		}
		let urls = '';
		newFileList.forEach(item => {
			if (item.serverUrl) urls += item.serverUrl + ';';
		});
		urls = urls ? urls.substring(0, urls.length - 1) : '';
		this.setData({
			[fileListKey]: newFileList,
			[dataId]: urls
		});
	},
	previewMedia(e) {
		const that = this;
		const index = e.currentTarget.dataset.index;
		const dataId = e.currentTarget.dataset.id;
		const currentFileItem = this.data[dataId + 'FileList'][index];
		const mediaFileList = this.data[dataId + 'FileList'].filter(item => item.fileType === 'image' || item.fileType === 'video');
		const currentRealIndex = mediaFileList.findIndex(item => item.tempUrl === currentFileItem.tempUrl);
		if (currentRealIndex === -1) {
			wx.showToast({ title: '预览文件不存在', icon: 'none' });
			return;
		}
		const previewSources = mediaFileList.map(item => ({
			url: item.serverUrl || item.tempUrl,
			type: item.fileType
		}));
		wx.previewMedia({
			current: currentRealIndex,
			sources: previewSources,
			success() { console.log('媒体预览成功'); },
			fail(err) {
				wx.showToast({ title: '预览失败', icon: 'none' });
				console.error('媒体预览失败：', err);
				if (currentFileItem.fileType === 'image') {
					wx.previewImage({
						current: currentFileItem.serverUrl || currentFileItem.tempUrl,
						urls: mediaFileList.map(item => item.serverUrl || item.tempUrl)
					});
				}
			}
		});
	},
	/**
	* 核心修改：TXT/ZIP文件直接下载，其他文件按原有逻辑
	*/
	openFile(e) {
		const that = this;
		const index = e.currentTarget.dataset.index;
		const dataId = e.currentTarget.dataset.id;
		const fileItem = this.data[dataId + 'FileList'][index];
		const targetUrl = fileItem.serverUrl || fileItem.tempUrl;
		// 处理文件类型
		if (fileItem.fileType === 'file') {
			// 获取文件扩展名（小写）
			let fileExt = '';
			if (fileItem.fileName) {
				fileExt = fileItem.fileName.split('.').pop()?.toLowerCase() || '';
				} else if (targetUrl) {
				fileExt = targetUrl.split('.').pop()?.toLowerCase() || '';
			}
			// TXT/ZIP文件 直接下载
			if (this.data.directDownloadTypes.includes(fileExt)) {
				that.downloadFileToLocal(targetUrl)
				.then(() => {
					wx.showToast({
						title: `${fileExt.toUpperCase()}文件下载成功`,
						icon: 'success',
						duration: 1500
					});
				})
				.catch((errMsg) => {
					wx.showToast({ title: `下载失败：${errMsg}`, icon: 'none' });
					if (errMsg.includes('downloadFile:fail')) {
						wx.showModal({
							title: '重要提示',
							content: '文件下载失败，请检查：n1. downloadFile合法域名是否配置n2. 服务器文件是否存在n3. 网络连接是否正常',
							showCancel: false
						});
					}
				});
				return; // 直接返回，不执行后续逻辑
			}
			// 其他支持的文件类型（PDF/Word/Excel等）：下载+打开
			if (!fileExt || !this.data.supportFileTypes.includes(fileExt)) {
				wx.showModal({
					title: '提示',
					content: `不支持打开${fileExt ? '.' + fileExt : '此'}类型文件，支持的类型：${this.data.supportFileTypes.join('、')}，TXT/ZIP可直接下载`,
					showCancel: false
				});
				return;
			}
			that.downloadFileToLocal(targetUrl)
			.then((localTempPath) => {
				wx.openDocument({
					filePath: localTempPath,
					fileType: fileExt,
					showMenu: true,
					success() { console.log('文件打开成功'); },
					fail(err) {
						wx.showToast({ title: `无法打开文件：${err.errMsg}`, icon: 'none' });
						console.error('文件打开失败：', err);
					}
				});
			})
			.catch((errMsg) => {
				wx.showToast({ title: errMsg, icon: 'none' });
				if (errMsg.includes('downloadFile:fail')) {
					wx.showModal({
						title: '重要提示',
						content: '文件下载失败，请检查：n1. downloadFile合法域名是否配置n2. 服务器文件是否存在n3. 网络连接是否正常',
						showCancel: false
					});
				}
			});
		}
		// 音频播放逻辑（保持原有修复）
		else if (fileItem.fileType === 'audio') {
			that.downloadFileToLocal(targetUrl)
			.then((localTempPath) => {
				that.forceStopAllAudio();
				const audioContext = wx.createInnerAudioContext();
				that.audioContext = audioContext;
				audioContext.src = localTempPath;
				audioContext.play();
				that.setData({
					audioPlaying: true,
					currentAudioIndex: index,
					currentAudioDataId: dataId
				});
				wx.showToast({ title: '开始播放音频', icon: 'none', duration: 1500 });
				audioContext.onEnded(() => {
					that.forceStopAllAudio();
				});
				audioContext.onError((err) => {
					wx.showToast({ title: `音频播放失败: ${err.errMsg}`, icon: 'none' });
					console.error('音频播放错误：', err);
					that.forceStopAllAudio();
				});
				audioContext.onStop(() => {
					that.setData({ audioPlaying: false });
				});
				audioContext.onPause(() => {
					that.setData({ audioPlaying: false });
				});
			})
			.catch((errMsg) => {
				wx.showToast({ title: errMsg, icon: 'none' });
			});
		}
	},
	delFile(e) {
		const that = this;
		const dataId = e.currentTarget.dataset.id;
		const index = e.currentTarget.dataset.index;
		const fileItem = this.data[dataId + 'FileList'][index];
		wx.showModal({
			title: '提示',
			content: '确定要删除该文件吗？',
			success(res) {
				if (res.confirm) {
					if (fileItem.fileType === 'audio' &&
					that.data.audioPlaying &&
					that.data.currentAudioIndex === index &&
					that.data.currentAudioDataId === dataId) {
						that.forceStopAllAudio();
					}
					const newFileList = [...that.data[dataId + 'FileList']];
					newFileList.splice(index, 1);
					let urls = '';
					newFileList.forEach(item => {
						if (item.serverUrl) urls += item.serverUrl + ';';
					});
					urls = urls ? urls.substring(0, urls.length - 1) : '';
					that.setData({
						[dataId + 'FileList']: newFileList,
						[dataId]: urls
					});
					wx.showToast({ title: '删除成功', icon: 'none', duration: 800 });
				}
			}
		});
	},
	// ========== 工具函数 ==========
	formatFileSize(bytes) {
		if (bytes < 1024) return bytes + 'B';
		else if (bytes < 1024*1024) return (bytes / 1024).toFixed(1) + 'KB';
		else return (bytes / (1024*1024)).toFixed(1) + 'MB';
	},
	formatVideoDuration(seconds) {
		const min = Math.floor(seconds / 60);
		const sec = Math.floor(seconds % 60);
		return `${min.toString().padStart(2, '0')}:${sec.toString().padStart(2, '0')}`;
	},
	// 通用下载函数（增强文件类型识别）
	downloadFileToLocal(url) {
		return new Promise((resolve, reject) => {
			if (url.startsWith('wxfile://') || url.startsWith('tmp://')) {
				resolve(url);
				return;
			}
			wx.showLoading({ title: '文件下载中...', mask: true });
			wx.downloadFile({
				url: url,
				header: {},
				success: (res) => {
					wx.hideLoading();
					if (res.statusCode === 200) {
						resolve(res.tempFilePath);
						} else {
						reject(`文件下载失败，状态码: ${res.statusCode}`);
					}
				},
				fail: (err) => {
					wx.hideLoading();
					const errMsg = err.errMsg || '网络错误，文件加载失败';
					reject(errMsg);
					console.error('文件下载失败：', err);
				}
			});
		});
	},
	// ========== 音频控制核心函数 ==========
	forceStopAllAudio() {
		if (this.audioContext) {
			try {
				this.audioContext.stop();
				this.audioContext.destroy();
				} catch (e) {
				console.error('销毁音频上下文失败：', e);
			}
			this.audioContext = null;
		}
		this.setData({
			audioPlaying: false,
			currentAudioIndex: -1,
			currentAudioDataId: ''
		});
		wx.showToast({ title: '音频已停止', icon: 'none', duration: 1000 });
	},
	toggleAudioPlay(e) {
		const index = e.currentTarget.dataset.index;
		const dataId = e.currentTarget.dataset.id;
		const fileItem = this.data[dataId + 'FileList'][index];
		if (this.data.audioPlaying &&
		this.data.currentAudioIndex === index &&
		this.data.currentAudioDataId === dataId) {
			if (this.audioContext && !this.audioContext.paused) {
				this.audioContext.pause();
				this.setData({ audioPlaying: false });
				wx.showToast({ title: '暂停播放音频', icon: 'none', duration: 1000 });
				} else if (this.audioContext && this.audioContext.paused) {
				this.audioContext.play();
				this.setData({ audioPlaying: true });
				wx.showToast({ title: '继续播放音频', icon: 'none', duration: 1000 });
			}
			} else {
			this.forceStopAllAudio();
			this.openFile(e);
		}
	},
	stopAudioManually(e) {
		this.forceStopAllAudio();
	},
	updateShowList(dataId) {
		var value = this.data[dataId];
		value = value +'';
		var dataList=  this.data[dataId+'List'];
		var split = value.split(',');
		console.log('aaa333');
		for(var j=0;j<dataList.length;j++){
			var isIn =false;
			for(var i=0;i<split.length;i++){
				if(split[i]!=''&&split[i]==dataList[j].value){
					isIn=true;
					break;
				}
			}
			if(isIn==true){
				dataList[j].isSelected=true;
				}else{
				dataList[j].isSelected=false;
			}
		}
		this.setData({ [dataId+'List']: dataList })
		var isShow = this.data[dataId+'ShowAll'];
		var showList = isShow ? dataList : dataList.slice(0, 7);
		console.log(showList);
		this.setData({
			[dataId+'ShowList']: showList
		});
	},
	toggleSelect(e) {
		var iscomplex = e.currentTarget.dataset.iscomplex;
		var dataId =e.currentTarget.dataset.id;
		var dataValue =e.currentTarget.dataset.value;
		var dataList=  this.data[dataId+'List'];
		var preValue = this.data[dataId];
		if(preValue==null){
			preValue='';
		}
		console.log('aaa');
		if(iscomplex!=''&&iscomplex==1){
			if(preValue==''){
				preValue = dataValue;
				}else{
				var isIn =false;
				var split4 = preValue.split(',');
				for(var i=0;i<split4.length;i++){
					if(split4[i]!=''&&split4[i]==dataValue){
						isIn=true;
						break;
					}
				}
				if(isIn==false){
					preValue = preValue+','+dataValue;
					}else{
					var split44 = preValue.split(',');
					var tmpValue='';
					for(var i=0;i<split44.length;i++){
						if(split44[i]!=''&&split44[i]==dataValue){
							}else{
							tmpValue+=split44[i]+',';
						}
					}
					if(tmpValue!=''){
						tmpValue = tmpValue.substring(0,tmpValue.length-1);
					}
					preValue = tmpValue;
				}
			}
			}else{
			for(var i=0;i<dataList.length;i++){
				if(dataList[i].isSelected==undefined){
					dataList[i].isSelected=false;
				}
			}
			preValue = dataValue;
		}
		var newList = [];
		for(var i=0;i<dataList.length;i++){
			var map = {};
			map.value=dataList[i].value;
			map.title=dataList[i].title;
			if(dataList[i].value==dataValue){
				map.isSelected=true;
				}else{
				map.isSelected=false;
			}
			newList.push(map);
		}
		this.setData({ [dataId+'List']: newList,[dataId]:preValue }, () => {
			this.updateShowList(dataId);
		});
	},
	toggleShow(e) {
		var dataId = e.currentTarget.dataset.id;
		var showAll = this.data[dataId+'ShowAll'];
		var showList = this.data[dataId+'List'];
		// 1. 取反显示全部的状态
		const newShowAll = !showAll;
		// 2. 根据状态更新展示列表：true=显示全部，false=显示前7个
		const newShowList = newShowAll ? showList : showList.slice(0, 7);
		// 3. 更新页面数据
		this.setData({
			[dataId+'ShowAll']: newShowAll,
			[dataId+'ShowList']: newShowList
		});
	},
	getPageData(){
		var that = this;
		wx.request({
			url: wx.getStorageSync('baseHostPort')+'/regist/getListData',
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
					that.setData({
						departList:that.castListVal(res.data.departList),
					});
					that.updateShowList('depart');
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
		return await app.getShareData(this, '办公室报修系统：用户注册');
	}
})
