

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
			<div class="list-title">知识库</div>
		</div>
		<div class="list-main-div">
			<div class="list-main-query">
				<div class="list-query-item-div">
					<div  class="list-query-item-div2">
						<div  class="list-query-item-zhname">标题</div>
						<div class="list-query-item-content">
							<div  class="list-query-item-content0"><input type="text"  placeholder="请输入标题" name="title" v-model="title" class="form-control  query-input" id="title">
						</div>
					</div>
				</div>
			</div>
			<div class="list-query-item-div">
				<div  class="list-query-item-div2">
					<div  class="list-query-item-zhname">副标题</div>
					<div class="list-query-item-content">
						<div  class="list-query-item-content0"><input type="text"  placeholder="请输入副标题" name="subtitle" v-model="subtitle" class="form-control  query-input" id="subtitle">
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
					<th>标题</th>
					<th>副标题</th>
					<th>发布时间</th>
					<th  class="list-table-action-th">
						操作
					</th>
				</tr>
			</thead>
			<tbody  id="zskList">
				<template v-for="item in dataList">
					<tr  class="table-row">
						<td >
							<div v-html="''+(item.zsk.title==null?'':item.zsk.title)+''" class="list-td-txt"></div>
						</td>
						<td >
							<div v-html="''+(item.zsk.subtitle==null?'':item.zsk.subtitle)+''" class="list-td-txt"></div>
						</td>
						<td >
							<div v-html="''+(item.zsk.fbTime==null?'':item.zsk.fbTime)+''" class="list-td-txt"></div>
						</td>
						<td class="list-table-action-td">
							<a href="javascript:void(0)" :data-params="'id='+item.zsk.id+''" data-href="/wxy/zsk/detail" @click="goToPage"  class="btnStyle1 btn btn-success" >查看详情</a>
						</td>
					</tr>
				</template>
				<template v-if="dataList==null || dataList.length==0">
					<tr class="no-data-row">
						<td colspan="4" class="no-data-cell">
							<div class="list-empty-data-div">
								<div class="list-empty-data">无数据</div>
							</div>
						</td>
					</tr>
				</template>
			</tbody>
		</table>
		<div class="list-page-utils">
			<div id="zsk_bar" class="pagination" style="margin-top:20px;"></div>
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
			title:'',
			subtitle:'',
			dataList:[],
		};
	},
	mounted() {
		var that = this;
		that.currentLoginToken=utils.getCookie('loginToken');
		that.currentLoginId=utils.getCookie('loginId');
		that.currentLoginType=utils.getCookie('loginType');
		that.ajaxList(1);
	},
	methods:{
		ajaxCount(){
			var that = this;
			var rs = null;
			var title = that.title;
			var subtitle = that.subtitle;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+'/wxy/zsk/queryCount',
				async: false,
				data : {
					"loginToken":that.currentLoginToken,
					"title":title,
					"subtitle":subtitle,
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
			var title = that.title;
			var subtitle = that.subtitle;
			var countRs = that.ajaxCount();
			if(countRs.totalPage<page){
				page = countRs.totalPage;
			}
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+'/wxy/zsk/queryList',
				async: false,
				data : {
					"page":page,
					"loginToken":that.currentLoginToken,
					"title":title,
					"subtitle":subtitle,
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
						id: 'zsk_bar',
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



