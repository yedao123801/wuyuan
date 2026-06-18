

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
			<div class="detail-title-div">报修记录</div>
		</div>
		<div class="detail-main" >
			<div class="detail-main2">
				<div class="detail-msg-body">
					<div class="detail-table-item-div2">
						<div class="detail-item">
							<div class="detail-table-col1">报修编号</div>
							<div class="detail-table-col2" v-html="bxNo==null?'':bxNo"></div>
						</div>
						<div class="detail-item">
							<div class="detail-table-col1">办公设备</div>
							<div class="detail-table-col2" v-html="bgServiceStr"></div>
						</div>
					</div>
					<div class="detail-table-item-div2">
						<div class="detail-item">
							<div class="detail-table-col1">设备类型</div>
							<div class="detail-table-col2" v-html="serviceType==null?'':serviceType"></div>
						</div>
						<div class="detail-item">
							<div class="detail-table-col1">办公区域</div>
							<div class="detail-table-col2" v-html="bgqyStr"></div>
						</div>
					</div>
					<div class="detail-table-item-div2">
						<div class="detail-item">
							<div class="detail-table-col1">优先程度</div>
							<div class="detail-table-col2" v-html="yxcdStr"></div>
						</div>
						<div class="detail-item">
							<div class="detail-table-col1">故障描述</div>
							<div class="detail-table-col2" v-html="gzms==null?'':gzms"></div>
						</div>
					</div>
					<div class="detail-table-item-div1">
						<div class="detail-item">
							<div class="detail-table-col1">故障图片</div>
							<div class="detail-table-col2">
								<template v-for="item2 in gzImgList">
									<div class="detail-table-img-div"><img :src="item2" class="detail-table-img" /></div>
								</template>
							</div>
						</div>
					</div>
					<div class="detail-table-item-div2">
						<div class="detail-item">
							<div class="detail-table-col1">故障视频</div>
							<div class="detail-table-col2">
								<template v-for="item2 in gzVeoList">
									<div class="detail-table-video-div"><video :src="item2" class="detail-table-video-div" controls></video></div>
								</template>
							</div>
						</div>
						<div class="detail-item">
							<div class="detail-table-col1">故障位置</div>
							<div class="detail-table-col2" v-html="gzLocation==null?'':gzLocation"></div>
						</div>
					</div>
					<div class="detail-table-item-div2">
						<div class="detail-item">
							<div class="detail-table-col1">报修状态</div>
							<div class="detail-table-col2" v-html="bxStatusStr"></div>
						</div>
						<div class="detail-item">
							<div class="detail-table-col1">管理员备注</div>
							<div class="detail-table-col2" v-html="adminRemark==null?'':adminRemark"></div>
						</div>
					</div>
					<div class="detail-table-item-div2">
						<div class="detail-item">
							<div class="detail-table-col1">验收说明</div>
							<div class="detail-table-col2" v-html="ysNote==null?'':ysNote"></div>
						</div>
						<div class="detail-item">
							<div class="detail-table-col1">维修员备注</div>
							<div class="detail-table-col2" v-html="fixRemark==null?'':fixRemark"></div>
						</div>
					</div>
					<div class="detail-table-item-div2">
						<div class="detail-item">
							<div class="detail-table-col1">维修员</div>
							<div class="detail-table-col2" >
								<div  class="detail-table-author-img-div">
									<img :src="fixImg" class="detail-table-author-img" />
								</div>
								<div  class="detail-table-author-name" v-html="fixStr">
								</div>
							</div>
						</div>
						<div class="detail-item">
							<div class="detail-table-col1">用户ID</div>
							<div class="detail-table-col2" >
								<div  class="detail-table-author-img-div">
									<img :src="userIdImg" class="detail-table-author-img" />
								</div>
								<div  class="detail-table-author-name" v-html="userIdStr">
								</div>
							</div>
						</div>
					</div>
					<div class="detail-table-item-div1">
						<div class="detail-item">
							<div class="detail-table-col1">创建时间</div>
							<div class="detail-table-col2" v-html="createTime==null?'':createTime"></div>
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
			bxNo:'',
			bgServiceStr:'',
			serviceType:'',
			bgqyStr:'',
			yxcdStr:'',
			gzms:'',
			gzImgList:[],
			gzVeoList:[],
			gzLocation:'',
			bxStatusStr:'',
			adminRemark:'',
			ysNote:'',
			fixRemark:'',
			fixImg:'',
			fixStr:'',
			userIdImg:'',
			userIdStr:'',
			createTime:'',
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
				url : this.REQUEST_URL+"/admin/bx_log/getDetailData",
				async: false,
				data : {
					'id':that.id,
					'loginToken':that.currentLoginToken,
				},
				success : function(result) {
					that.bxNo = (result.detail.bxLog.bxNo==null?'':result.detail.bxLog.bxNo);
					that.bgServiceStr = (result.detail.bgServiceStr==null?'':result.detail.bgServiceStr);
					that.serviceType = (result.detail.bxLog.serviceType==null?'':result.detail.bxLog.serviceType);
					that.bgqyStr = (result.detail.bgqyStr==null?'':result.detail.bgqyStr);
					that.yxcdStr = (result.detail.yxcdStr==null?'':result.detail.yxcdStr);
					that.gzms = (result.detail.bxLog.gzms==null?'':result.detail.bxLog.gzms);
					that.gzImgList = result.detail.gzImgList;
					that.gzVeoList = result.detail.gzVeoList;
					that.gzLocation = (result.detail.bxLog.gzLocation==null?'':result.detail.bxLog.gzLocation);
					that.bxStatusStr = (result.detail.bxStatusStr==null?'':result.detail.bxStatusStr);
					that.adminRemark = (result.detail.bxLog.adminRemark==null?'':result.detail.bxLog.adminRemark);
					that.ysNote = (result.detail.bxLog.ysNote==null?'':result.detail.bxLog.ysNote);
					that.fixRemark = (result.detail.bxLog.fixRemark==null?'':result.detail.bxLog.fixRemark);
					that.fixImg = (result.detail.fixImg==null?'':result.detail.fixImg);
					that.fixStr = (result.detail.fixStr==null?'':result.detail.fixStr);
					that.userIdImg = (result.detail.userIdImg==null?'':result.detail.userIdImg);
					that.userIdStr = (result.detail.userIdStr==null?'':result.detail.userIdStr);
					that.createTime = (result.detail.bxLog.createTime==null?'':result.detail.bxLog.createTime);
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



