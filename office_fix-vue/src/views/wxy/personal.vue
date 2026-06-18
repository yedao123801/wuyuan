

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
			<div class="detail-title-div">维修员</div>
		</div>
		<div class="detail-main" >
			<div class="detail-main2">
				<div class="detail-msg-body">
					<div class="detail-table-item-div2">
						<div class="detail-item">
							<div class="detail-table-col1">登录名</div>
							<div class="detail-table-col2" v-html="loginName==null?'':loginName"></div>
						</div>
						<div class="detail-item">
							<div class="detail-table-col1">姓名</div>
							<div class="detail-table-col2" v-html="name==null?'':name"></div>
						</div>
					</div>
					<div class="detail-table-item-div2">
						<div class="detail-item">
							<div class="detail-table-col1">联系电话</div>
							<div class="detail-table-col2" v-html="celphone==null?'':celphone"></div>
						</div>
						<div class="detail-item">
							<div class="detail-table-col1">头像</div>
							<div class="detail-table-col2">
								<template v-for="item2 in headImgList">
									<div class="detail-table-img-div"><img :src="item2" class="detail-table-img" /></div>
								</template>
							</div>
						</div>
					</div>
					<div class="detail-table-item-div2">
						<div class="detail-item">
							<div class="detail-table-col1">擅长类型</div>
							<div class="detail-table-col2" v-html="szType==null?'':szType"></div>
						</div>
						<div class="detail-item">
							<div class="detail-table-col1">维修资质</div>
							<div class="detail-table-col2">
								<template v-for="item2 in fixZzList">
									<div class="detail-table-img-div"><img :src="item2" class="detail-table-img" /></div>
								</template>
							</div>
						</div>
					</div>
					<div class="detail-table-item-div2">
						<div class="detail-item">
							<div class="detail-table-col1">审核状态</div>
							<div class="detail-table-col2" v-html="checkStatusStr"></div>
						</div>
						<div class="detail-item">
							<div class="detail-table-col1">综合评分</div>
							<div class="detail-table-col2" v-html="tevalScore==null?'':tevalScore"></div>
						</div>
					</div>
					<div class="detail-table-item-div1">
						<div class="detail-item">
							<div class="detail-table-col1">注册时间</div>
							<div class="detail-table-col2" v-html="zcTime==null?'':zcTime"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="detail-btn-area">
				<a  href="javascript:void(0)" :data-params="'id='+id+''" data-href="/wxy/fix/update1_page" @click="goToPage" class="detail-btn btn btn-warning">修改</a>
			</div>
		</div>
	</div>
</template>

<script>
import $ from 'jquery'
import {utils} from '../../assets/listutils.js'
import {admin_utils} from '../../assets/admin/admin_utils.js'
export default {
	components: {
	},
	data() {
		return {
			id:'',
			currentLoginToken:'',
			currentLoginId:'',
			currentLoginType:'',
			loginName:'',
			name:'',
			celphone:'',
			headImgList:[],
			szType:'',
			fixZzList:[],
			checkStatusStr:'',
			tevalScore:'',
			zcTime:'',
			detail:{},
		};
	},
	mounted() {
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
				url : this.REQUEST_URL+"/wxy/fix/getDetailData",
				async: false,
				data : {
					'id':that.id,
					'loginToken':that.currentLoginToken,
				},
				success : function(result) {
					that.id = result.detail.fix.id;
					that.loginName = (result.detail.fix.loginName==null?'':result.detail.fix.loginName);
					that.name = (result.detail.fix.name==null?'':result.detail.fix.name);
					that.celphone = (result.detail.fix.celphone==null?'':result.detail.fix.celphone);
					that.headImgList = result.detail.headImgList;
					that.szType = (result.detail.fix.szType==null?'':result.detail.fix.szType);
					that.fixZzList = result.detail.fixZzList;
					that.checkStatusStr = (result.detail.checkStatusStr==null?'':result.detail.checkStatusStr);
					that.tevalScore = (result.detail.fix.tevalScore==null?'':result.detail.fix.tevalScore);
					that.zcTime = (result.detail.fix.zcTime==null?'':result.detail.fix.zcTime);
				}
			});
		},
	}
};

</script>
<style type="text/css" src="../../assets/admin/style.css?t=9?t=44" scoped></style>
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



