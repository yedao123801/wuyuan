

<template>
	<div id="body" class="edit-body" >
		<div class="edit-title-div">
			<div class="edit-title">
				<div class="edit-title-txt">配件申请-修改</div>
			</div>
		</div>
		<div class="edit-main">
			<div class="edit-main2">
				<div class="edit-main-form-div2">
					<div class="edit-main-form"  id="pjNameForm">
						<div class="edit-main-form-title">配件名<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入配件名" name="pjName" v-model="pjName" class="form-control edit-input" id="pjName">
						</div>
					</div>
					<div class="edit-main-form"  id="xyNumForm">
						<div class="edit-main-form-title">需要数量<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入需要数量(请输入数字)" name="xyNum" v-model="xyNum" class="form-control edit-input" id="xyNum">
						</div>
					</div>
				</div>
				<div class="edit-main-form-div1">
					<div class="edit-main-form"  id="reqNoteForm">
						<div class="edit-main-form-title">申请说明<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<textarea class="form-control edit-textarea"  name="reqNote" v-model="reqNote"  id="reqNote"></textarea>
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
			pjName:'',
			xyNum:'',
			reqNote:'',
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
				url : this.REQUEST_URL+"/wxy/pj_req/queryDataDetail",
				async: false,
				data : {
					'id':that.id,
					'loginToken':that.currentLoginToken,
				},
				success : function(result) {
					that.pjName=result.pjName;
					that.xyNum=result.xyNum;
					that.reqNote=result.reqNote;
					$("#reqNote").val(result.reqNote);
				}
			});
		},
		submitData(){
			var that = this;
			var pjName = that.pjName;
			var xyNum = that.xyNum;
			if(utils.isNum(xyNum)==false){
				alert("需要数量请输入数字");
				return;
			}
			var reqNote = that.reqNote;
			$.ajax({
				type : 'post',
				url : this.REQUEST_URL+"/wxy/pj_req/update_submit",
				async: false,
				data : {
					'id':that.id,
					'pjName':pjName,
					'xyNum':xyNum,
					'reqNote':reqNote,
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



