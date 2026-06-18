

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
			<div class="list-title">维修过程</div>
		</div>
		<div class="list-main-div">
			<div class="list-main-query">
				<div class="list-query-item-div">
					<div  class="list-query-item-div2">
						<div  class="list-query-item-zhname">维修步骤</div>
						<div class="list-query-item-content">
							<div  class="list-query-item-content0"><input type="text"  placeholder="请输入维修步骤" name="fixBz" v-model="fixBz" class="form-control  query-input" id="fixBz">
						</div>
					</div>
				</div>
			</div>
			<div class="list-query-item-div">
				<div  class="list-query-item-div2">
					<div  class="list-query-item-zhname">更换配件</div>
					<div class="list-query-item-content">
						<div  class="list-query-item-content0"><input type="text"  placeholder="请输入更换配件" name="ghpj" v-model="ghpj" class="form-control  query-input" id="ghpj">
					</div>
				</div>
			</div>
		</div>
		<div class="list-query-item-div date-range-container">
			<div  class="list-query-item-div2">
				<div  class="list-query-item-zhname">创建时间</div>
				<div class="list-query-item-content3  date-range">
					<div  class="list-query-item-content1"><input type="date" id="createTime1"  class="form-control  query-input" >
				</div>
				<div class="date-separator">至</div>
				<div  class="list-query-item-content2"><input type="date" id="createTime2"  class="form-control  query-input" >
			</div>
		</div>
	</div>
</div>
<div class="list-div-query-btn-div">
	<button @click="ajaxList(1)" class="list-div-query-btn">查询</button>
</div>
</div>
<div class="list-global-btns">
<a href="javascript:void(0)" :data-params="'bxid='+bxid+''" data-href="/wxy/fix_gc/add_page" @click="goToPage"  class="btnStyle1 btn btn-success" >新增</a>
</div>
</div>
<div  class="list-table-container">
<div class="table-responsive">
<table class="list-table table table-bordered">
	<thead class="list-table-header">
		<tr  class="list-table-header-tr">
			<th>维修步骤</th>
			<th>更换配件</th>
			<th>消耗工时</th>
			<th>报修ID</th>
			<th>创建时间</th>
			<th  class="list-table-action-th">
				操作
			</th>
		</tr>
	</thead>
	<tbody  id="fixGcList">
		<template v-for="item in dataList">
			<tr  class="table-row">
				<td >
					<div v-html="''+(item.fixGc.fixBz==null?'':item.fixGc.fixBz)+''" class="list-td-txt"></div>
				</td>
				<td >
					<div v-html="''+(item.fixGc.ghpj==null?'':item.fixGc.ghpj)+''" class="list-td-txt"></div>
				</td>
				<td >
					<div v-html="''+(item.fixGc.xhgs==null?'':item.fixGc.xhgs)+''" class="list-td-txt"></div>
				</td>
				<td >
					<div v-html="''+(item.bxidStr==null?'':item.bxidStr)+''" class="list-td-txt"></div>
				</td>
				<td >
					<div v-html="''+(item.fixGc.createTime==null?'':item.fixGc.createTime)+''" class="list-td-txt"></div>
				</td>
				<td class="list-table-action-td">
					<a href="javascript:void(0)" :data-id="item.fixGc.id" @click="del"  class="btnStyle1 btn btn-danger" >删除</a>
					<a href="javascript:void(0)" :data-params="'id='+item.fixGc.id+''" data-href="/wxy/fix_gc/update_page" @click="goToPage"  class="btnStyle1 btn btn-warning" >修改</a>
				</td>
			</tr>
		</template>
		<template v-if="dataList==null || dataList.length==0">
			<tr class="no-data-row">
				<td colspan="6" class="no-data-cell">
					<div class="list-empty-data-div">
						<div class="list-empty-data">无数据</div>
					</div>
				</td>
			</tr>
		</template>
	</tbody>
</table>
<div class="list-page-utils">
	<div id="fix_gc_bar" class="pagination" style="margin-top:20px;"></div>
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
			fixBz:'',
			ghpj:'',
			createTime1:'',
			createTime2:'',
			dataList:[],
		};
	},
	mounted() {
		var that = this;
		that.currentLoginToken=utils.getCookie('loginToken');
		that.currentLoginId=utils.getCookie('loginId');
		that.currentLoginType=utils.getCookie('loginType');
		that.bxid = (that.$route.query.bxid==undefined?'':that.$route.query.bxid);
		that.ajaxList(1);
	},
	methods:{
		del(e){
			var that = this;
			var dataId  =e.currentTarget.dataset.id;
			var r=confirm("确认删除该数据吗？");
			if(r==true){
				$.ajax({
					type : 'get',
					url : this.REQUEST_URL+"/wxy/fix_gc/del",
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
			var fixBz = that.fixBz;
			var ghpj = that.ghpj;
			var createTime1 = date_utils.getDomVal("createTime1");
			var createTime2 = date_utils.getDomVal("createTime2");
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+'/wxy/fix_gc/queryCount',
				async: false,
				data : {
					"loginToken":that.currentLoginToken,
					"bxid":that.bxid,
					"fixBz":fixBz,
					"ghpj":ghpj,
					"createTime1":createTime1,
					"createTime2":createTime2,
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
			var fixBz = that.fixBz;
			var ghpj = that.ghpj;
			var createTime1 = date_utils.getDomVal("createTime1");
			var createTime2 = date_utils.getDomVal("createTime2");
			var countRs = that.ajaxCount();
			if(countRs.totalPage<page){
				page = countRs.totalPage;
			}
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+'/wxy/fix_gc/queryList',
				async: false,
				data : {
					"page":page,
					"loginToken":that.currentLoginToken,
					"bxid":that.bxid,
					"fixBz":fixBz,
					"ghpj":ghpj,
					"createTime1":createTime1,
					"createTime2":createTime2,
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
						id: 'fix_gc_bar',
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



