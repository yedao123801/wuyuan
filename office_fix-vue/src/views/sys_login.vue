

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
						<h2 class="form-title">登录</h2>
						<p class="form-subtitle">办公室报修系统</p>
					</div>
					<div class="register-form">
						<div class="form-group">
							<label class="form-label">登录名</label>
							<input type="text"  placeholder="请输入登录名" name="name" v-model="name" class="form-input"  id="name">
						</div>
						<div class="form-group">
							<label class="form-label">密码</label>
							<input type="password"  placeholder="请输入密码" name="password" v-model="password" class="form-input"  id="password">
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
							<div class="form-group">
								<label class="form-label"></label>
								<div class="captcha-group3">
									<input name="loginType" type="radio" value="1" v-model="loginType" id="loginType1" class="login-role-input"/>
									<label for="loginType1" class="login-role-label">管理员</label>
									<input name="loginType" type="radio" value="2" v-model="loginType" id="loginType2" class="login-role-input"/>
									<label for="loginType2" class="login-role-label">维修员</label>
								</div>
							</div>
							<!-- 提交按钮 -->
							<button
								type="button"
								@click="submitForm" id="submitBtn"
								class="submit-button">
								登录
							</button>
							<!-- 已有账号 -->
							<div class="form-footer">
								<div class="login-other-btn-div">
									<div   class="login-other-btn-div2">
										<a    data-href="/fix_regist" data-params="" @click="goToPage" class="other-btn">注册维修员</a>
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
			name:'',
			password:'',
			imgCode:'',
			codeImg1:'',
			codeToken:'',
			loginType:'',
		};
	},
	mounted() {
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
			$.ajax({
				type: 'post',
				url: this.REQUEST_URL+'/login/sysSubmit',
				data: {
					'imgCode':that.imgCode,
					'imgCodeToken':that.codeToken,
					'name':that.name,
					'password':that.password,
					'loginType':that.loginType
				},
				success: function(result) {
					if(result.code==1){
						utils.setCookie('loginId',result.loginId,1);
						utils.setCookie('loginType',result.loginType,1);
						utils.setCookie('loginName',result.loginName,1);
						utils.setCookie('loginToken',result.loginToken,1);
						var lt = that.loginType;
						var visitUrl = utils.getCookie('visitUrl');
						if(visitUrl!=undefined&&visitUrl!=''){
							var urlSplit = visitUrl.split('?');
							utils.setCookie('visitUrl','',1);
							that.goToPageJS(urlSplit[0],urlSplit[1]);
							}else{
							if(lt=='1'){
								that.goToPageJS('/admin/index','');
							}
							if(lt=='2'){
								that.goToPageJS('/wxy/index','');
							}
						}
						}else{
						alert(result.msg);
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



