

<template>
	<div class="auth-page">
		<!-- 背景装饰 -->
		<div class="auth-page__bg"></div>
		<div class="auth-page__bg-overlay"></div>
		<!-- 主容器 -->
		<div class="auth-container">
			<!-- 右侧注册表单 -->
			<div class="auth-container__form">
				<div class="form-card">
					<div class="form-header">
						<h2 class="form-title">维修员-注册</h2>
						<p class="form-subtitle">办公室报修系统</p>
					</div>
					<div class="register-form">
						<div class="form-group">
							<label class="form-label">登录名</label>
							<input type="text"  placeholder="请输入登录名" name="loginName" v-model="loginName" class="form-input" id="loginName">
						</div>
						<div class="form-group">
							<label class="form-label">密码</label>
							<input type="password" name="password"  placeholder="请输入密码"  v-model="password" class="form-input" id="password">
						</div>
						<div class="form-group">
							<label class="form-label">姓名</label>
							<input type="text"  placeholder="请输入姓名" name="name" v-model="name" class="form-input" id="name">
						</div>
						<div class="form-group">
							<label class="form-label">联系电话</label>
							<input type="text"  placeholder="请输入联系电话" name="celphone" v-model="celphone" class="form-input" id="celphone">
						</div>
						<div class="form-group">
							<label class="form-label">擅长类型</label>
							<input type="text"  placeholder="请输入擅长类型" name="szType" v-model="szType" class="form-input" id="szType">
						</div>
						<!-- 图片验证码 -->
						<div class="form-group">
							<label class="form-label">图片验证码</label>
							<div class="captcha-group">
								<input
								type="text"  id="imgCode"
								placeholder="请输入验证码"
								v-model="imgCode"
								class="form-input">
								<div class="captcha-image"  @click="changeCode" >
									<img  alt="验证码" name="codeImg" :src="codeImg1" class="captcha-img">
										<span class="captcha-refresh">换一张</span>
									</div>
								</div>
							</div>
							<!-- 提交按钮 -->
							<button
								type="button"
								@click="submitForm" id="submitBtn"
								class="submit-button">
								维修员-注册
							</button>
							<!-- 已有账号 -->
							<div class="form-footer">
								<div class="login-other-btn-div">
									<div   class="login-other-btn-div2">
										<a    data-href="/sys_login" data-params="" @click="goToPage" class="other-btn">前往登录</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
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
			loginName:'',
			password:'',
			name:'',
			celphone:'',
			szType:'',
			imgCode:'',
			codeImg1:'',
			codeToken:'',
		};
	},
	mounted() {
		var that=this;
		this.changeCode();
	},
	methods:{
		changeCode(){
			var that = this;
			$.ajax({
				type: 'post',
				url: this.REQUEST_URL+"/"+'/validCode/code',
				data: {
				},
				success: function(result) {
					that.codeToken = result.codeToken;
					that.codeImg1 = result.imgUrl;
				}
			});
		},
		submitForm(){
			var that = this;
			var loginName = that.loginName;
			var password = that.password;
			var name = that.name;
			var celphone = that.celphone;
			var szType = that.szType;
			$.ajax({
				type: 'post',
				url: this.REQUEST_URL+'/regist/fixRegistSubmit',
				data: {
					'loginName':loginName,
					'password':password,
					'name':name,
					'celphone':celphone,
					'szType':szType,
					'imgCode':that.imgCode,
					'imgCodeToken':that.codeToken,
				},
				success: function(result) {
					alert(result.msg);
					if(result.code==1){
						that.goToPageJS('/sys_login','');
					}
				}
			});
		},
	}
};

</script>
	<style type="text/css" src="../assets/login/login.css?t=44" scoped></style>
	<style scoped>
		/* 背景样式 */
		.auth-page__bg {
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background-image: url(../assets/login_bg.jpg);
		background-size: cover;
		background-position: center;
		z-index: 1;
		}
	</style>



