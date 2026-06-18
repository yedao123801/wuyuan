

<template>
	<div id="body" class="edit-body" >
		<div class="edit-title-div">
			<div class="edit-title">
				<div class="edit-title-txt">报修记录-派单</div>
			</div>
		</div>
		<div class="edit-main">
			<div class="edit-main2">
				<div class="edit-main-form-div1">
					<div class="edit-main-form"  id="adminRemarkForm">
						<div class="edit-main-form-title">管理员备注<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入管理员备注" name="adminRemark" v-model="adminRemark" class="form-control edit-input" id="adminRemark">
						</div>
					</div>
					<div class="edit-main-form"  id="fixForm">
						<div class="edit-main-form-title">维修员<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<select  class="form-control edit-select" data-id="fix"  v-model="fix"  id="fix" >
								<template v-for="item in fixList">
									<option :value="item.id">{{item.name}}</option>
								</template>
							</select>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="edit-footer">
			<button class="edit-submit-btn"  @click="submitData" > 保存</button>
			<button class="edit-return-btn" onclick="javascript:history.back(-1);" >返回</button>
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
			currentLoginToken:'',
			id:'',
			adminRemark:'',
			fix:'',
			fixList:[],
		};
	},
	mounted() {
		var that = this;
		that.currentLoginToken=utils.getCookie('loginToken');
		that.id= that.$route.query.id;
		that.getDataList();
		that.queryDataDetail();
	},
	methods:{
		getDataList(){
			var that = this;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/admin/bx_log/getInitData",
				async: false,
				data : {
					'loginToken':that.currentLoginToken
				},
				success : function(result) {
					that.fixList = result.fixList;
				}
			});
		},
		queryDataDetail(){
			var that = this;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/admin/bx_log/queryDataDetail",
				async: false,
				data : {
					'id':that.id,
					'loginToken':that.currentLoginToken,
				},
				success : function(result) {
					that.adminRemark=result.adminRemark;
					that.fix=result.fix+'';
				}
			});
		},
		submitData(){
			var that = this;
			var adminRemark = that.adminRemark;
			var fix = that.fix;
			$.ajax({
				type : 'post',
				url : this.REQUEST_URL+"/admin/bx_log/pd_submit",
				async: false,
				data : {
					'id':that.id,
					'adminRemark':adminRemark,
					'fix':fix,
					'loginToken':that.currentLoginToken,
				},
				success : function(result) {
					alert(result.msg);
					if(result.code==1){
						that.$router.go(-1);
					}
				}
			});
		},
	}
};

</script>
<style type="text/css" src="../../../assets/admin/style.css?t=44" scoped></style>
<style scoped>
</style>



