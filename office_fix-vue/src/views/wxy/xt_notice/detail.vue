

<template>
	<div id="body" class="detail-body">
		<div class="admin-modal" id="bodyModal" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="admin-modal-dialog">
				<div class="admin-modal-content">
					<div class="admin-modal-header">
						<h4 class="admin-modal-title" id="bodyModalTitle"></h4>
						<button type="button" class="admin-close" @click="handleExportJsMethod"  data-methodtype="hideModal">
							<span class="admin-close-btn" aria-hidden="true">×</span>
							<span class="admin-sr-only">关闭</span>
						</button>
					</div>
					<div class="admin-modal-body" id="bodyModalContent"></div>
					<div class="admin-modal-footer">
						<button type="button" class="admin-btn admin-btn-white" @click="handleExportJsMethod"  data-methodtype="hideModal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<div class="detail-title">
			<div class="detail-title-div">系统公告</div>
		</div>
		<div class="detail-main" >
			<div class="detail-main2">
				<div class="detail-msg-body">
					<div class="detail-table-item-div2">
						<div class="detail-item">
							<div class="detail-table-col1">标题</div>
							<div class="detail-table-col2" v-html="title==null?'':title"></div>
						</div>
						<div class="detail-item">
							<div class="detail-table-col1">副标题</div>
							<div class="detail-table-col2" v-html="subtitle==null?'':subtitle"></div>
						</div>
					</div>
					<div class="detail-table-item-div1">
						<div class="detail-item">
							<div class="detail-table-col1">公告内容</div>
							<div class="detail-table-col2" v-html="noticeContent==null?'':noticeContent"></div>
						</div>
					</div>
					<div class="detail-table-item-div1">
						<div class="detail-item">
							<div class="detail-table-col1">发布时间</div>
							<div class="detail-table-col2" v-html="fbTime==null?'':fbTime"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="detail-btn-area">
				<a href="javascript:void(0)"  onclick="javascript:history.back(-1);" class="detail-btn-return">返回</a>
			</div>
		</div>
	</div>
</template>

<script>
import $ from 'jquery'
import {utils} from '../../../assets/listutils.js'
import {admin_utils} from '../../../assets/admin/admin_utils.js'
export default {
	components: {
	},
	data() {
		return {
			id:'',
			currentLoginToken:'',
			currentLoginId:'',
			currentLoginType:'',
			title:'',
			subtitle:'',
			noticeContent:'',
			fbTime:'',
			detail:{},
		};
	},
	mounted() {
		this.id= this.$route.query.id;
		this.currentLoginToken=utils.getCookie('loginToken');
		this.currentLoginId=utils.getCookie('loginId');
		this.currentLoginType=utils.getCookie('loginType');
		this.queryDataDetail();
	},
	methods:{
		queryDataDetail(){
			var that = this;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/wxy/xt_notice/getDetailData",
				async: false,
				data : {
					'id':that.id,
					'loginToken':that.currentLoginToken,
				},
				success : function(result) {
					that.title = (result.detail.xtNotice.title==null?'':result.detail.xtNotice.title);
					that.subtitle = (result.detail.xtNotice.subtitle==null?'':result.detail.xtNotice.subtitle);
					that.noticeContent = (result.detail.xtNotice.noticeContent==null?'':result.detail.xtNotice.noticeContent);
					that.fbTime = (result.detail.xtNotice.fbTime==null?'':result.detail.xtNotice.fbTime);
				}
			});
		},
	}
};

</script>
<style type="text/css" src="../../../assets/admin/style.css?t=9?t=44" scoped></style>
<style scoped>
	a{
	text-decoration: none;
	font-weight: 600;
	cursor: pointer;
	transition: color 0.2s ease; /* 简洁过渡 */
	}
	a:hover{
	color: #2563eb; /* 统一hover色 */
	}
	#body {
	font-family: "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; /* 更现代字体 */
	font-size: 14px;
	line-height: 1.5;
	color: #334155; /* 更舒适文本色 */
	background-color: #f8fafc; /* 柔和背景 */
	}
	.btnStyle{
	margin-left:8px;
	margin-top:8px
	}
	.loadingModel {
	position: absolute;
	top: 0;
	left: 0;
	background-color: rgba(255,255,255,0.85); /* 更轻的遮罩 */
	width: 100%;
	height: 100%;
	z-index: 1000;
	backdrop-filter: blur(1px); /* 轻微模糊 */
	display: flex;
	align-items: center;
	justify-content: center;
	}
	.loading-content {
	width: 280px; /* 固定宽度 */
	text-align: center;
	background: #ffffff;
	border-radius: 6px;
	line-height: 30px;
	z-index: 10001;
	box-shadow: 0 2px 10px rgba(0,0,0,0.1);
	padding: 20px;
	}
	input{
	height:35px !important;
	border-radius: 4px; /* 适度圆角 */
	border: 1px solid #e2e8f0;
	padding: 0 12px; /* 内边距补充 */
	}
	input:focus{
	outline: none;
	border-color: #3b82f6;
	box-shadow: 0 0 0 2px rgba(59, 130, 246, 0.1); /* 轻量焦点 */
	}
	select{
	height:35px !important;
	border-radius: 4px;
	border: 1px solid #e2e8f0;
	padding: 0 12px;
	}
	select:focus{
	outline: none;
	border-color: #3b82f6;
	box-shadow: 0 0 0 2px rgba(59, 130, 246, 0.1);
	}
	td >div{
	display:inline;
	margin-left:5px;
	}
	table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	background: #ffffff;
	}
	* {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	}
	.query-select{
	height:35px;
	width:200px;
	display:inline;
	}
	.query-input{
	width:200px;
	display:inline;
	}
	video{
	width:200px !important;
	border-radius: 4px;
	}
	audio{
	width:200px !important;
	border-radius: 4px;
	}
	input{
	height:20px;
	}
	th{
	background-color:#f8fafc !important;
	padding: 12px 16px;
	text-align: left;
	font-weight: 600;
	color: #334155;
	border-bottom: 2px solid #e2e8f0;
	}
</style>



