

<template>
	<div id="body" class="edit-body" >
		<div class="edit-title-div">
			<div class="edit-title">
				<div class="edit-title-txt">维修过程-新增</div>
			</div>
		</div>
		<div class="edit-main">
			<div class="edit-main2">
				<div class="edit-main-form-div2">
					<div class="edit-main-form"  id="fixBzForm">
						<div class="edit-main-form-title">维修步骤<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入维修步骤" name="fixBz" v-model="fixBz" class="form-control edit-input" id="fixBz">
						</div>
					</div>
					<div class="edit-main-form"  id="ghpjForm">
						<div class="edit-main-form-title">更换配件<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入更换配件" name="ghpj" v-model="ghpj" class="form-control edit-input" id="ghpj">
						</div>
					</div>
				</div>
				<div class="edit-main-form-div1">
					<div class="edit-main-form"  id="xhgsForm">
						<div class="edit-main-form-title">消耗工时<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入消耗工时(请输入数字)" name="xhgs" v-model="xhgs" class="form-control edit-input" id="xhgs">
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
			bxid:'',
			fixBz:'',
			ghpj:'',
			xhgs:'',
		};
	},
	mounted() {
		var that = this;
		that.currentLoginToken=utils.getCookie('loginToken');
		that.bxid = that.$route.query.bxid;
	},
	methods:{
		submitData(){
			var that = this;
			var fixBz = that.fixBz;
			var ghpj = that.ghpj;
			var xhgs = that.xhgs;
			if(utils.isNum(xhgs)==false){
				alert("消耗工时请输入数字");
				return;
			}
			$.ajax({
				type : 'post',
				url : this.REQUEST_URL+"/wxy/fix_gc/add_submit",
				async: false,
				data : {
					'bxid':that.bxid,
					'fixBz':fixBz,
					'ghpj':ghpj,
					'xhgs':xhgs,
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



