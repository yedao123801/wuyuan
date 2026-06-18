

<template>
	<div id="body"  class="edit-body" >
		<div class="edit-title-div">
			<div class="edit-title">
				<div class="edit-title-txt">修改密码</div>
			</div>
		</div>
		<div class="edit-main">
			<div class="edit-main2">
				<div class="edit-main-form" >
					<div class="edit-main-form-title">旧密码</div>
					<div class="edit-main-form-input-div">
						<input type="password"  placeholder="旧密码" v-model="password1" class="form-control edit-input"  id="password1">
					</div>
				</div>
				<div class="edit-main-form" >
					<div class="edit-main-form-title">新密码</div>
					<div class="edit-main-form-input-div">
						<input type="password"  placeholder="新密码" v-model="password2" class="form-control edit-input"  id="password2">
					</div>
				</div>
				<div class="edit-main-form" >
					<div class="edit-main-form-title">新密码</div>
					<div class="edit-main-form-input-div">
						<input type="password"  placeholder="再次输入新密码" v-model="password3" class="form-control edit-input"  id="password3">
					</div>
				</div>
			</div>
		</div>
		<div class="edit-footer">
			<button class="edit-submit-btn"  @click="alterPassword" > 保存</button>
		</div>
	</div>
</template>

<script>
import $ from 'jquery'
import {utils} from '../assets/listutils.js'
export default {
	components: {
	},
	data() {
		return {
			currentLoginToken:'',
			password1:'',
			password2:'',
			password3:'',
		};
	},
	mounted() {
		this.currentLoginToken=utils.getCookie('loginToken');
	},
	methods:{
		alterPassword(){
			var that = this;
			var password1 =that.password1;
			var password2 =that.password2;
			var password3 =that.password3;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/alterPwd/alterPassword/submit",
				async: false,
				data : {
					'password1':password1,
					'password2':password2,
					'password3':password3,
					'loginToken':that.currentLoginToken,
				},
				success : function(result) {
					alert(result.msg);
				}
			});
		},
	}
};

</script>
<style type="text/css" src="../assets/admin/style.css?t=9?t=44" scoped></style>
<style scoped>
</style>



