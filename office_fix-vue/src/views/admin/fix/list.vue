

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
			<div class="list-title">维修员</div>
		</div>
		<div class="list-main-div">
			<div class="list-main-query">
				<div class="list-query-item-div">
					<div  class="list-query-item-div2">
						<div  class="list-query-item-zhname">登录名</div>
						<div class="list-query-item-content">
							<div  class="list-query-item-content0"><input type="text"  placeholder="请输入登录名" name="loginName" v-model="loginName" class="form-control  query-input" id="loginName">
						</div>
					</div>
				</div>
			</div>
			<div class="list-query-item-div">
				<div  class="list-query-item-div2">
					<div  class="list-query-item-zhname">姓名</div>
					<div class="list-query-item-content">
						<div  class="list-query-item-content0"><input type="text"  placeholder="请输入姓名" name="name" v-model="name" class="form-control  query-input" id="name">
					</div>
				</div>
			</div>
		</div>
		<div class="list-query-item-div">
			<div  class="list-query-item-div2">
				<div  class="list-query-item-zhname">审核状态</div>
				<div class="list-query-item-content">
					<div  class="list-query-item-content0"><select  class="form-control query-select" data-id="checkStatus"  v-model="checkStatus"  id="checkStatus" >
							<template v-for="item in checkStatusList">
								<option :value="item.id">{{item.name}}</option>
							</template>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="list-div-query-btn-div">
			<button @click="ajaxList(1)" class="list-div-query-btn">查询</button>
		</div>
	</div>
	<div class="list-global-btns">
		<a href="javascript:void(0)" :data-params="''" data-href="/admin/fix/add_page" @click="goToPage"  class="btnStyle1 btn btn-success" >新增</a>
	</div>
</div>
<div  class="list-table-container">
	<div class="table-responsive">
		<table class="list-table table table-bordered">
			<thead class="list-table-header">
				<tr  class="list-table-header-tr">
					<th>登录名</th>
					<th>姓名</th>
					<th>联系电话</th>
					<th>头像</th>
					<th>擅长类型</th>
					<th>维修资质</th>
					<th>审核状态</th>
					<th>综合评分</th>
					<th>注册时间</th>
					<th  class="list-table-action-th">
						操作
					</th>
				</tr>
			</thead>
			<tbody  id="fixList">
				<template v-for="item in dataList">
					<tr  class="table-row">
						<td >
							<div v-html="''+(item.fix.loginName==null?'':item.fix.loginName)+''" class="list-td-txt"></div>
						</td>
						<td >
							<div v-html="''+(item.fix.name==null?'':item.fix.name)+''" class="list-td-txt"></div>
						</td>
						<td >
							<div v-html="''+(item.fix.celphone==null?'':item.fix.celphone)+''" class="list-td-txt"></div>
						</td>
						<td>
							<template v-for="item2 in item.headImgList">
								<img :src="item2" class="list-td-imgs" @click="handleExportJsMethod"  data-methodtype="maxImg">
								</template>
							</td>
							<td >
								<div v-html="''+(item.fix.szType==null?'':item.fix.szType)+''" class="list-td-txt"></div>
							</td>
							<td>
								<template v-for="item2 in item.fixZzList">
									<img :src="item2" class="list-td-imgs" @click="handleExportJsMethod"  data-methodtype="maxImg">
									</template>
								</td>
								<td >
									<div v-html="''+(item.checkStatusStr==null?'':item.checkStatusStr)+''" class="list-td-txt"></div>
								</td>
								<td >
									<div v-html="''+(item.fix.tevalScore==null?'':item.fix.tevalScore)+'分'" class="list-td-txt"></div>
								</td>
								<td >
									<div v-html="''+(item.fix.zcTime==null?'':item.fix.zcTime)+''" class="list-td-txt"></div>
								</td>
								<td class="list-table-action-td">
									<a href="javascript:void(0)" :data-id="item.fix.id" @click="del"  class="btnStyle1 btn btn-danger" >删除</a>
									<a href="javascript:void(0)" :data-params="'id='+item.fix.id+''" data-href="/admin/fix/update_page" @click="goToPage"  class="btnStyle1 btn btn-warning" >修改</a>

							<template v-if="item.fix.checkStatus=='1'">
								<a href="javascript:void(0)" :data-id="item.fix.id" @click="shtg"  class="btnStyle1 btn btn-warning" >审核通过</a>
							</template>
							<template v-if="item.fix.checkStatus=='1'">
								<a href="javascript:void(0)" :data-id="item.fix.id" @click="shbtg"  class="btnStyle1 btn btn-danger" >审核不通过</a>
							</template>

								</td>
							</tr>
						</template>
						<template v-if="dataList==null || dataList.length==0">
							<tr class="no-data-row">
								<td colspan="10" class="no-data-cell">
									<div class="list-empty-data-div">
										<div class="list-empty-data">无数据</div>
									</div>
								</td>
							</tr>
						</template>
					</tbody>
				</table>
				<div class="list-page-utils">
					<div id="fix_bar" class="pagination" style="margin-top:20px;"></div>
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
			loginName:'',
			name:'',
			checkStatus:'',
			dataList:[],
			checkStatusList:[],
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
				url : this.REQUEST_URL+"/admin/fix/getInitData",
				async: false,
				data : {
					"loginToken":that.currentLoginToken,
				},
				success : function(result) {
					that.checkStatusList = admin_utils.addAllSelect(result.checkStatusList);
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
					url : this.REQUEST_URL+"/admin/fix/del",
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


	shtg(e){
			var that = this;
			var dataId  =e.currentTarget.dataset.id;
			var r=confirm("确认审核该数据吗？");
			if(r==true){
				$.ajax({
					type : 'get',
					url : this.REQUEST_URL+"/admin/fix/shtg",
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

		shbtg(e){
			var that = this;
			var dataId  =e.currentTarget.dataset.id;
			var r=confirm("确认审核该数据吗？");
			if(r==true){
				$.ajax({
					type : 'get',
					url : this.REQUEST_URL+"/admin/fix/shbtg",
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
			var loginName = that.loginName;
			var name = that.name;
			var checkStatus = that.checkStatus;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+'/admin/fix/queryCount',
				async: false,
				data : {
					"loginToken":that.currentLoginToken,
					"loginName":loginName,
					"name":name,
					"checkStatus":checkStatus,
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
			var loginName = that.loginName;
			var name = that.name;
			var checkStatus = that.checkStatus;
			var countRs = that.ajaxCount();
			if(countRs.totalPage<page){
				page = countRs.totalPage;
			}
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+'/admin/fix/queryList',
				async: false,
				data : {
					"page":page,
					"loginToken":that.currentLoginToken,
					"loginName":loginName,
					"name":name,
					"checkStatus":checkStatus,
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
						id: 'fix_bar',
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



