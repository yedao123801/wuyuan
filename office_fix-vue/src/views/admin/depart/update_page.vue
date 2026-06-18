

<template>
	<div id="body" class="edit-body" >
		<div class="edit-title-div">
			<div class="edit-title">
				<div class="edit-title-txt">部门-修改</div>
			</div>
		</div>
		<div class="edit-main">
			<div class="edit-main2">
				<div class="edit-main-form-div1">
					<div class="edit-main-form"  id="departNameForm">
						<div class="edit-main-form-title">部门名<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入部门名" name="departName" v-model="departName" class="form-control edit-input" id="departName">
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
			departName:'',
		};
	},
	mounted() {
		var that = this;
		that.currentLoginToken=utils.getCookie('loginToken');
		that.id= that.$route.query.id;
		that.queryDataDetail();
	},
	methods:{
		queryDataDetail(){
			var that = this;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/admin/depart/queryDataDetail",
				async: false,
				data : {
					'id':that.id,
					'loginToken':that.currentLoginToken,
				},
				success : function(result) {
					that.departName=result.departName;
				}
			});
		},
		submitData(){
			var that = this;
			var departName = that.departName;
			$.ajax({
				type : 'post',
				url : this.REQUEST_URL+"/admin/depart/update_submit",
				async: false,
				data : {
					'id':that.id,
					'departName':departName,
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



