

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
			<div class="list-title">评价记录</div>
		</div>
		<div class="list-main-div">
			<div class="list-main-query">
				<div class="list-query-item-div">
					<div  class="list-query-item-div2">
						<div  class="list-query-item-zhname">报修ID</div>
						<div class="list-query-item-content">
							<div  class="list-query-item-content0"><select  class="form-control query-select" data-id="bxid"  v-model="bxid"  id="bxid" >
									<template v-for="item in bxLogList">
										<option :value="item.id">{{item.name}}</option>
									</template>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="list-query-item-div">
					<div  class="list-query-item-div2">
						<div  class="list-query-item-zhname">评价内容</div>
						<div class="list-query-item-content">
							<div  class="list-query-item-content0"><input type="text"  placeholder="请输入评价内容" name="evalContent" v-model="evalContent" class="form-control  query-input" id="evalContent">
						</div>
					</div>
				</div>
			</div>
			<div class="list-query-item-div date-range-container">
				<div  class="list-query-item-div2">
					<div  class="list-query-item-zhname">评价时间</div>
					<div class="list-query-item-content3  date-range">
						<div  class="list-query-item-content1"><input type="date" id="evalTime1"  class="form-control  query-input" >
					</div>
					<div class="date-separator">至</div>
					<div  class="list-query-item-content2"><input type="date" id="evalTime2"  class="form-control  query-input" >
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
				<th>报修ID</th>
				<th>评价星级</th>
				<th>评价内容</th>
				<th>用户ID</th>
				<th>评价时间</th>
			</tr>
		</thead>
		<tbody  id="evalLogList">
			<template v-for="item in dataList">
				<tr  class="table-row">
					<td >
						<div v-html="''+(item.bxidStr==null?'':item.bxidStr)+''" class="list-td-txt"></div>
					</td>
					<td >
						<div v-html="''+(item.evalStarStr==null?'':item.evalStarStr)+''" class="list-td-txt"></div>
					</td>
					<td >
						<div v-html="''+(item.evalLog.evalContent==null?'':item.evalLog.evalContent)+''" class="list-td-txt"></div>
					</td>
					<td>
						<div class="list-td-img-div">
							<img :src="item.userIdImg" class="list-td-author-img" />
						</div>
						<div  class="list-td-author-name" v-html="''+(item.userIdStr==null?'':item.userIdStr)+''">
						</div>
					</td>
					<td >
						<div v-html="''+(item.evalLog.evalTime==null?'':item.evalLog.evalTime)+''" class="list-td-txt"></div>
					</td>
				</tr>
			</template>
			<template v-if="dataList==null || dataList.length==0">
				<tr class="no-data-row">
					<td colspan="5" class="no-data-cell">
						<div class="list-empty-data-div">
							<div class="list-empty-data">无数据</div>
						</div>
					</td>
				</tr>
			</template>
		</tbody>
	</table>
	<div class="list-page-utils">
		<div id="eval_log_bar" class="pagination" style="margin-top:20px;"></div>
	</div>
</div>
</div>
</div>
</template>

<script>
import $ from 'jquery'
import {utils} from '../../../assets/listutils.js'
import {admin_utils} from '../../../assets/admin/admin_utils.js'
import {date_utils} from '../../../assets/datepicker/date_utils.js'
import {myPagination} from '../../../assets/page/myPagination.js'
export default {
	components: {
	},
	data() {
		return {
			currentLoginToken:'',
			currentLoginId:'',
			currentLoginType:'',
			bxid:'',
			evalContent:'',
			evalTime1:'',
			evalTime2:'',
			dataList:[],
			bxLogList:[],
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
				url : this.REQUEST_URL+"/wxy/eval_log/getInitData",
				async: false,
				data : {
					"loginToken":that.currentLoginToken,
				},
				success : function(result) {
					that.bxLogList = admin_utils.addAllSelect(result.bxLogList);
				}
			});
		},
		ajaxCount(){
			var that = this;
			var rs = null;
			var bxid = that.bxid;
			var evalContent = that.evalContent;
			var evalTime1 = date_utils.getDomVal("evalTime1");
			var evalTime2 = date_utils.getDomVal("evalTime2");
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+'/wxy/eval_log/queryCount',
				async: false,
				data : {
					"loginToken":that.currentLoginToken,
					"bxid":bxid,
					"evalContent":evalContent,
					"evalTime1":evalTime1,
					"evalTime2":evalTime2,
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
			var bxid = that.bxid;
			var evalContent = that.evalContent;
			var evalTime1 = date_utils.getDomVal("evalTime1");
			var evalTime2 = date_utils.getDomVal("evalTime2");
			var countRs = that.ajaxCount();
			if(countRs.totalPage<page){
				page = countRs.totalPage;
			}
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+'/wxy/eval_log/queryList',
				async: false,
				data : {
					"page":page,
					"loginToken":that.currentLoginToken,
					"bxid":bxid,
					"evalContent":evalContent,
					"evalTime1":evalTime1,
					"evalTime2":evalTime2,
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
						id: 'eval_log_bar',
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



