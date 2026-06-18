

<template>
	<div id="body"  class="list-body">
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
		<div class="list-head">
			<div class="list-title">报修记录</div>
		</div>
		<div class="list-main-div">
			<div class="list-main-query">
				<div class="list-query-item-div">
					<div  class="list-query-item-div2">
						<div  class="list-query-item-zhname">报修编号</div>
						<div class="list-query-item-content">
							<div  class="list-query-item-content0"><input type="text"  placeholder="请输入报修编号" name="bxNo" v-model="bxNo" class="form-control  query-input" id="bxNo">
						</div>
					</div>
				</div>
			</div>
			<div class="list-query-item-div">
				<div  class="list-query-item-div2">
					<div  class="list-query-item-zhname">办公设备</div>
					<div class="list-query-item-content">
						<div  class="list-query-item-content0"><select  class="form-control query-select" data-id="bgService"  v-model="bgService"  id="bgService" >
								<template v-for="item in bgServiceList">
									<option :value="item.id">{{item.name}}</option>
								</template>
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="list-query-item-div">
				<div  class="list-query-item-div2">
					<div  class="list-query-item-zhname">办公区域</div>
					<div class="list-query-item-content">
						<div  class="list-query-item-content0"><select  class="form-control query-select" data-id="bgqy"  v-model="bgqy"  id="bgqy" >
								<template v-for="item in bgqyList">
									<option :value="item.id">{{item.name}}</option>
								</template>
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="list-query-item-div">
				<div  class="list-query-item-div2">
					<div  class="list-query-item-zhname">优先程度</div>
					<div class="list-query-item-content">
						<div  class="list-query-item-content0"><select  class="form-control query-select" data-id="yxcd"  v-model="yxcd"  id="yxcd" >
								<template v-for="item in yxcdList">
									<option :value="item.id">{{item.name}}</option>
								</template>
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="list-query-item-div">
				<div  class="list-query-item-div2">
					<div  class="list-query-item-zhname">报修状态</div>
					<div class="list-query-item-content">
						<div  class="list-query-item-content0"><select  class="form-control query-select" data-id="bxStatus"  v-model="bxStatus"  id="bxStatus" >
								<template v-for="item in bxStatusList">
									<option :value="item.id">{{item.name}}</option>
								</template>
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="list-query-item-div">
				<div  class="list-query-item-div2">
					<div  class="list-query-item-zhname">管理员备注</div>
					<div class="list-query-item-content">
						<div  class="list-query-item-content0"><input type="text"  placeholder="请输入管理员备注" name="adminRemark" v-model="adminRemark" class="form-control  query-input" id="adminRemark">
					</div>
				</div>
			</div>
		</div>
		<div class="list-div-query-btn-div">
			<button @click="ajaxList(1)" class="list-div-query-btn">查询</button>
		</div>
	</div>
</div>
<div  class="list-table-container">
	<div class="table-responsive">
		<table class="list-table table table-bordered">
			<thead class="list-table-header">
				<tr  class="list-table-header-tr">
					<th>报修编号</th>
					<th>办公设备</th>
					<th>设备类型</th>
					<th>办公区域</th>
					<th>优先程度</th>
					<th>故障描述</th>
					<th>故障位置</th>
					<th>报修状态</th>
					<th>管理员备注</th>
					<th>用户ID</th>
					<th>创建时间</th>
					<th  class="list-table-action-th">
						操作
					</th>
				</tr>
			</thead>
			<tbody  id="bxLogList">
				<template v-for="item in dataList">
					<tr  class="table-row">
						<td >
							<div v-html="''+(item.bxLog.bxNo==null?'':item.bxLog.bxNo)+''" class="list-td-txt"></div>
						</td>
						<td >
							<div v-html="''+(item.bgServiceStr==null?'':item.bgServiceStr)+''" class="list-td-txt"></div>
						</td>
						<td >
							<div v-html="''+(item.bxLog.serviceType==null?'':item.bxLog.serviceType)+''" class="list-td-txt"></div>
						</td>
						<td >
							<div v-html="''+(item.bgqyStr==null?'':item.bgqyStr)+''" class="list-td-txt"></div>
						</td>
						<td >
							<div v-html="''+(item.yxcdStr==null?'':item.yxcdStr)+''" class="list-td-txt"></div>
						</td>
						<td >
							<div v-html="''+(item.bxLog.gzms==null?'':item.bxLog.gzms)+''" class="list-td-txt"></div>
						</td>
						<td >
							<div v-html="''+(item.bxLog.gzLocation==null?'':item.bxLog.gzLocation)+''" class="list-td-txt"></div>
						</td>
						<td >
							<div v-html="''+(item.bxStatusStr==null?'':item.bxStatusStr)+''" class="list-td-txt"></div>
						</td>
						<td >
							<div v-html="''+(item.bxLog.adminRemark==null?'':item.bxLog.adminRemark)+''" class="list-td-txt"></div>
						</td>
						<td>
							<div class="list-td-img-div">
								<img :src="item.userIdImg" class="list-td-author-img" />
							</div>
							<div  class="list-td-author-name" v-html="''+(item.userIdStr==null?'':item.userIdStr)+''">
							</div>
						</td>
						<td >
							<div v-html="''+(item.bxLog.createTime==null?'':item.bxLog.createTime)+''" class="list-td-txt"></div>
						</td>
						<td class="list-table-action-td">
							<a href="javascript:void(0)" :data-params="'id='+item.bxLog.id+''" data-href="/admin/bx_log/detail" @click="goToPage"  class="btnStyle1 btn btn-success" >查看详情</a>
							<a href="javascript:void(0)" :data-params="'bxid='+item.bxLog.id+''" data-href="/admin/fix_gc/list" @click="goToPage"  class="btnStyle1 btn btn-success" >查看维修过程</a>
							<template v-if="item.bxLog.bxStatus=='1' || item.bxLog.bxStatus=='3'">
								<a href="javascript:void(0)" :data-params="'id='+item.bxLog.id+''" data-href="/admin/bx_log/pd_page" @click="goToPage"  class="btnStyle1 btn btn-warning" >派单</a>
							</template>
							<template v-if="item.bxLog.bxStatus=='1'">
								<a href="javascript:void(0)" :data-params="'id='+item.bxLog.id+''" data-href="/admin/bx_log/bh_page" @click="goToPage"  class="btnStyle1 btn btn-success" >驳回</a>
							</template>
							<a href="javascript:void(0)" :data-id="item.bxLog.id" @click="del"  class="btnStyle1 btn btn-danger" >删除</a>
						</td>
					</tr>
				</template>
				<template v-if="dataList==null || dataList.length==0">
					<tr class="no-data-row">
						<td colspan="12" class="no-data-cell">
							<div class="list-empty-data-div">
								<div class="list-empty-data">无数据</div>
							</div>
						</td>
					</tr>
				</template>
			</tbody>
		</table>
		<div class="list-page-utils">
			<div id="bx_log_bar" class="pagination" style="margin-top:20px;"></div>
		</div>
	</div>
</div>
</div>
</template>

<script>
import $ from 'jquery'
import {utils} from '../../../assets/listutils.js'
import {admin_utils} from '../../../assets/admin/admin_utils.js'
import {myPagination} from '../../../assets/page/myPagination.js'
export default {
	components: {
	},
	data() {
		return {
			currentLoginToken:'',
			currentLoginId:'',
			currentLoginType:'',
			bxNo:'',
			bgService:'',
			bgqy:'',
			yxcd:'',
			bxStatus:'',
			adminRemark:'',
			dataList:[],
			yxcdList:[],
			bgServiceList:[],
			bxStatusList:[],
			bgqyList:[],
		};
	},
	mounted() {
		var that = this;
		that.currentLoginToken=utils.getCookie('loginToken');
		that.currentLoginId=utils.getCookie('loginId');
		that.currentLoginType=utils.getCookie('loginType');
		that.getInitData();
		that.ajaxList(1);
	},
	methods:{
		getInitData(){
			var that = this;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/admin/bx_log/getInitData",
				async: false,
				data : {
					"loginToken":that.currentLoginToken,
				},
				success : function(result) {
					that.yxcdList = admin_utils.addAllSelect(result.yxcdList);
					that.bgServiceList = admin_utils.addAllSelect(result.bgServiceList);
					that.bxStatusList = admin_utils.addAllSelect(result.bxStatusList);
					that.bgqyList = admin_utils.addAllSelect(result.bgqyList);
				}
			});
		},
		del(e){
			var that = this;
			var dataId  =e.currentTarget.dataset.id;
			var r=confirm("确认删除该数据吗？");
			if(r==true){
				$.ajax({
					type : 'get',
					url : this.REQUEST_URL+"/admin/bx_log/del",
					async: false,
					data : {
						"loginToken":that.currentLoginToken,
						"id":dataId
					},
					success : function(result) {
						alert(result.msg);
						if(result.code==1){
							that.ajaxList(1);
						}
					}
				});
			}
		},
		ajaxCount(){
			var that = this;
			var rs = null;
			var bxNo = that.bxNo;
			var bgService = that.bgService;
			var bgqy = that.bgqy;
			var yxcd = that.yxcd;
			var bxStatus = that.bxStatus;
			var adminRemark = that.adminRemark;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+'/admin/bx_log/queryCount',
				async: false,
				data : {
					"loginToken":that.currentLoginToken,
					"bxNo":bxNo,
					"bgService":bgService,
					"bgqy":bgqy,
					"yxcd":yxcd,
					"bxStatus":bxStatus,
					"adminRemark":adminRemark,
				},
				success : function(result) {
					rs = result;
				}
			});
			return rs;
		},
		ajaxList(page){
			var that = this;
			if(page==undefined||page==null){
				page=1;
			}
			var bxNo = that.bxNo;
			var bgService = that.bgService;
			var bgqy = that.bgqy;
			var yxcd = that.yxcd;
			var bxStatus = that.bxStatus;
			var adminRemark = that.adminRemark;
			var countRs = that.ajaxCount();
			if(countRs.totalPage<page){
				page = countRs.totalPage;
			}
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+'/admin/bx_log/queryList',
				async: false,
				data : {
					"page":page,
					"loginToken":that.currentLoginToken,
					"bxNo":bxNo,
					"bgService":bgService,
					"bgqy":bgqy,
					"yxcd":yxcd,
					"bxStatus":bxStatus,
					"adminRemark":adminRemark,
				},
				success : function(result) {
					if(result.code==0){
						alert(result.msg);
						return;
					}
					admin_utils.hideLoading();
					var rows = result.list;
					var total = countRs.count;//得到数据总数
					var totalPage=countRs.totalPage;
					that.dataList=rows;
					new myPagination({
						id: 'bx_log_bar',
						curPage:page, //初始页码
						pageTotal:totalPage, //总页数
						pageAmount: 10,  //每页多少条
						dataTotal: total, //总共多少条数据
						pageSize: 10, //可选,分页个数
						showPageTotalFlag:true, //是否显示数据统计
						showSkipInputFlag:false, //是否支持跳转
						getPage: function (page) {
							that.ajaxList(page)
						}
					})
				}
			});
		},
		handleExportJsMethod(e){
			const div = e.currentTarget;
			const methodtype = div.dataset.methodtype;
			const modal = document.getElementById('bodyModal');
			const modalBody = document.getElementById('bodyModalContent');
			if(methodtype === 'maxImg'){
				admin_utils.maxImg(e.currentTarget);
				admin_utils.bindSimpleImgScale(modalBody.querySelector('img'));
			}
			if(methodtype === 'hideModal'){
				admin_utils.hideModal();
			}
		},
	}
};

</script>
<style type="text/css" src="../../../assets/page/myPagination.css?t=44" scoped></style>
<style type="text/css" src="../../../assets/admin/style.css?t=9?t=44" scoped></style>
<style scoped>
</style>



