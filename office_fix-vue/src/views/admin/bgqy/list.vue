

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
			<div class="list-title">办公区域</div>
		</div>
		<div class="list-main-div">
			<div class="list-main-query">
				<div class="list-query-item-div">
					<div  class="list-query-item-div2">
						<div  class="list-query-item-zhname">区域名</div>
						<div class="list-query-item-content">
							<div  class="list-query-item-content0"><input type="text"  placeholder="请输入区域名" name="qyName" v-model="qyName" class="form-control  query-input" id="qyName">
						</div>
					</div>
				</div>
			</div>
			<div class="list-div-query-btn-div">
				<button @click="ajaxList(1)" class="list-div-query-btn">查询</button>
			</div>
		</div>
		<div class="list-global-btns">
			<a href="javascript:void(0)" :data-params="''" data-href="/admin/bgqy/add_page" @click="goToPage"  class="btnStyle1 btn btn-success" >新增</a>
		</div>
	</div>
	<div  class="list-table-container">
		<div class="table-responsive">
			<table class="list-table table table-bordered">
				<thead class="list-table-header">
					<tr  class="list-table-header-tr">
						<th>区域名</th>
						<th  class="list-table-action-th">
							操作
						</th>
					</tr>
				</thead>
				<tbody  id="bgqyList">
					<template v-for="item in dataList">
						<tr  class="table-row">
							<td >
								<div v-html="''+(item.bgqy.qyName==null?'':item.bgqy.qyName)+''" class="list-td-txt"></div>
							</td>
							<td class="list-table-action-td">
								<a href="javascript:void(0)" :data-id="item.bgqy.id" @click="del"  class="btnStyle1 btn btn-danger" >删除</a>
								<a href="javascript:void(0)" :data-params="'id='+item.bgqy.id+''" data-href="/admin/bgqy/update_page" @click="goToPage"  class="btnStyle1 btn btn-warning" >修改</a>
							</td>
						</tr>
					</template>
					<template v-if="dataList==null || dataList.length==0">
						<tr class="no-data-row">
							<td colspan="2" class="no-data-cell">
								<div class="list-empty-data-div">
									<div class="list-empty-data">无数据</div>
								</div>
							</td>
						</tr>
					</template>
				</tbody>
			</table>
			<div class="list-page-utils">
				<div id="bgqy_bar" class="pagination" style="margin-top:20px;"></div>
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
			qyName:'',
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
		del(e){
			var that = this;
			var dataId  =e.currentTarget.dataset.id;
			var r=confirm("确认删除该数据吗？");
			if(r==true){
				$.ajax({
					type : 'get',
					url : this.REQUEST_URL+"/admin/bgqy/del",
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
			var qyName = that.qyName;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+'/admin/bgqy/queryCount',
				async: false,
				data : {
					"loginToken":that.currentLoginToken,
					"qyName":qyName,
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
			var qyName = that.qyName;
			var countRs = that.ajaxCount();
			if(countRs.totalPage<page){
				page = countRs.totalPage;
			}
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+'/admin/bgqy/queryList',
				async: false,
				data : {
					"page":page,
					"loginToken":that.currentLoginToken,
					"qyName":qyName,
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
						id: 'bgqy_bar',
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



