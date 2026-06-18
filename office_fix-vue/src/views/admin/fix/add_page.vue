

<template>
	<div id="body" class="edit-body" >
		<div class="edit-title-div">
			<div class="edit-title">
				<div class="edit-title-txt">维修员-新增</div>
			</div>
		</div>
		<div class="edit-main">
			<div class="edit-main2">
				<div class="edit-main-form-div2">
					<div class="edit-main-form"  id="loginNameForm">
						<div class="edit-main-form-title">登录名<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入登录名" name="loginName" v-model="loginName" class="form-control edit-input" id="loginName">
						</div>
					</div>
					<div class="edit-main-form"  id="passwordForm">
						<div class="edit-main-form-title">密码<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="password" name="password"  placeholder="请输入密码"  v-model="password" class="form-control edit-input" id="password">
						</div>
					</div>
				</div>
				<div class="edit-main-form-div2">
					<div class="edit-main-form"  id="nameForm">
						<div class="edit-main-form-title">姓名<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入姓名" name="name" v-model="name" class="form-control edit-input" id="name">
						</div>
					</div>
					<div class="edit-main-form"  id="celphoneForm">
						<div class="edit-main-form-title">联系电话<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入联系电话" name="celphone" v-model="celphone" class="form-control edit-input" id="celphone">
						</div>
					</div>
				</div>
				<div class="edit-main-form-div1">
					<div class="edit-main-form"  id="headImgForm">
						<div class="edit-main-form-title">头像<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<div id="headImg" class="dropzone" data-id="headImg" max-num="1" file-type="1" max-size="1000" init-val="" :request-url="REQUEST_URL" :init-val="headImg"></div>
						</div>
					</div>
				</div>
				<div class="edit-main-form-div1">
					<div class="edit-main-form"  id="szTypeForm">
						<div class="edit-main-form-title">擅长类型<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入擅长类型" name="szType" v-model="szType" class="form-control edit-input" id="szType">
						</div>
					</div>
				</div>
				<div class="edit-main-form-div1">
					<div class="edit-main-form"  id="fixZzForm">
						<div class="edit-main-form-title">维修资质<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<div id="fixZz" class="dropzone" data-id="fixZz" max-num="1" file-type="1" max-size="1000" init-val="" :request-url="REQUEST_URL" :init-val="fixZz"></div>
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
import {getFileVal2,initDropZone,setZoneImg} from '../../../assets/imgupload/js/imgupload.js'
export default {
	components: {
	},
	data() {
		return {
			currentLoginToken:'',
			loginName:'',
			password:'',
			name:'',
			celphone:'',
			headImg:'',
			szType:'',
			fixZz:'',
		};
	},
	mounted() {
		var that = this;
		that.currentLoginToken=utils.getCookie('loginToken');
		var headImgDrop = initDropZone('headImg');
		var fixZzDrop = initDropZone('fixZz');
	},
	methods:{
		submitData(){
			var that = this;
			var loginName = that.loginName;
			var password = that.password;
			var name = that.name;
			var celphone = that.celphone;
			var headImg = getFileVal2('headImg');
			var szType = that.szType;
			var fixZz = getFileVal2('fixZz');
			$.ajax({
				type : 'post',
				url : this.REQUEST_URL+"/admin/fix/add_submit",
				async: false,
				data : {
					'loginName':loginName,
					'password':password,
					'name':name,
					'celphone':celphone,
					'headImg':headImg,
					'szType':szType,
					'fixZz':fixZz,
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
<style type="text/css" src="../../../assets/imgupload/css/dropzone.min.css?t=44" scoped></style>
<style scoped>
</style>



