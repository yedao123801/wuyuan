 import $ from 'jquery'
 
 export  var admin_utils={
	showLoading(imgPath) {
    },
    hideLoading() {
    },
	modalShowHtml(html){
		const $modal = $('#bodyModal');
		const $modalTitle = $('#bodyModalTitle');
		const $modalContent = $('#bodyModalContent');
		const $body = $('body');
		// 显示图片预览并打开模态框
		$modal.show(); // 显示模态框
		setTimeout(() => {
		  $modal.addClass('admin-in'); // 延迟添加类以触发过渡效果
		}, 10);
		// 更新ARIA属性
		$modal.attr('aria-hidden', 'false');
		// 防止背景滚动
		$body.addClass('modal-open');
		$modalTitle.text('预览');
		$modalContent.html(html);
	},
	hideModal(){
		const modal = document.getElementById('bodyModal');
		const $modal = $(modal); // 将原生modal对象转为jQuery对象
const $body = $('body');
// 移除显示类
$modal.removeClass('admin-in');
// 更新ARIA属性
$modal.attr('aria-hidden', 'true');
// 移除背景滚动限制
$body.removeClass('modal-open');
// 延迟隐藏以允许过渡效果完成
setTimeout(() => {
  $modal.hide(); // 隐藏模态框
}, 300);
// 清空模态框内容
$('#bodyModalContent').html('');
$('#bodyModalTitle').text('');
	},
	maxImg(e){
    	var src = $(e).attr("src");
    	var html = '';
			html = '<img style="width:95%;margin-left:2%;max-width:500px;max-height:500px;" src="'+src+'"/>';
		const $modal = $('#bodyModal');
		const $modalTitle = $('#bodyModalTitle');
		const $modalContent = $('#bodyModalContent');
		const $body = $('body');
		// 显示图片预览并打开模态框
		$modal.show(); // 显示模态框
		setTimeout(() => {
		  $modal.addClass('admin-in'); // 延迟添加类以触发过渡效果
		}, 10);
		// 更新ARIA属性
		$modal.attr('aria-hidden', 'false');
		// 防止背景滚动
		$body.addClass('modal-open');
		$modalTitle.text('预览');
		$modalContent.html(html);
	},
	bindSimpleImgScale(img) {
	if(!img) return;
	let scale = 1, isDrag = false, startX = 0, startY = 0, x = 0, y = 0;
	// 1. 滚轮缩放（原有逻辑不变）
	img.onwheel = (e) => {
	  e.preventDefault();
	  scale = Math.max(0.5, Math.min(scale + (e.deltaY>0?-0.1:0.1), 3));
	  img.style.transform = `translate(${x}px, ${y}px) scale(${scale})`;
	};
	// 2. 拖动（原有逻辑不变）
	img.onmousedown = (e) => {
	  isDrag = true;
	  startX = e.clientX - x;
	  startY = e.clientY - y;
	  img.style.cursor = 'move';
	};
	document.onmousemove = (e) => {
	  if(!isDrag) return;
	  x = e.clientX - startX;
	  y = e.clientY - startY;
	  img.style.transform = `translate(${x}px, ${y}px) scale(${scale})`;
	};
	document.onmouseup = () => {
	  isDrag = false;
	  img.style.cursor = 'default';
	};
	// 3. 新增：单击图片恢复原状（仅3行核心代码）
	img.onclick = () => {
	  scale = 1; x = 0; y = 0; // 重置缩放比例和偏移量
	  img.style.transform = `translate(${x}px, ${y}px) scale(${scale})`; // 恢复初始样式
	};
  },
	clickMenu(e){
	if (!$(e).siblings('dd').hasClass('active')) {
		$('.navbar dl dd').removeClass('active');
		$(e).siblings('dd').removeClass('active');
		$(e).siblings('dd').addClass('active');
	} else {
		$('.navbar dl dd').removeClass('active');
		$(e).siblings('dd').removeClass('active');
	}
	if (!$(e).hasClass('activeTop')) {
		$('.navbar dl dt').removeClass('activeTop');
		$(e).removeClass('activeTop');
		$(e).addClass('activeTop');
	} else {
		$('.navbar dl dt').removeClass('activeTop');
		$(e).removeClass('activeTop');
	}
	},
  selectAll(e){
    	if($(e).prop("checked")==true){
    		$(".itemselect").prop("checked",true);
    	}else{
    		$(".itemselect").prop("checked",false);
    	}
    },
		addAllSelect(list){
		var map = {};
		map.id='';
		map.name='全部';
		var list2 =[];
		list2.push(map);
		for(var i=0;i<list.length;i++){
			var map2 = {};
			map2.id=list[i].id;
			map2.name=list[i].name;
			list2.push(map2);
		}
		return list2;
	},
	addAllSelect2(list){
		var map = {};
		map.id='';
		map.name='无';
		var list2 =[];
		list2.push(map);
		for(var i=0;i<list.length;i++){
			var map2 = {};
			map2.id=list[i].id;
			map2.name=list[i].name;
			list2.push(map2);
		}
		return list2;
	}
}
