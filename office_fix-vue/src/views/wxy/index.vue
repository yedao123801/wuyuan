

<template>
	<div id="system" class="index-body-class">
		<div class="index-main-div">
			<div class="index-title-div">
				<a  class="index-title-link" href="javascript:void(0)" >
					办公室报修系统
				</a>
			</div>
			<div class="index-main-div2">
				<div class="index-welcome-div">
					欢迎您，{{loginName}}！
				</div>
				<a class="index-alert-link" href="javascript:void(0)" @click="alterPassword">
					修改密码
				</a>
				<a class="index-signout-link"  href="javascript:void(0)" @click="logout">
					退出
				</a>
			</div>
		</div>
		<div class="index-main-contain">
			<div class="index-left-div">
				<div class="index-menu-list">
					<div class="index-amenu">
						<div class="index-item-menu"   @click="gotoIframePage"  :data-url="'/wxy/personal'">
							<div class="index-item-menu-icon"></div>
							<div class="index-item-menu-span">
								个人信息
							</div>
						</div>
					</div>
					<div class="index-amenu">
						<div class="index-item-menu"   @click="gotoIframePage"  :data-url="'/wxy/bx_log/list'">
							<div class="index-item-menu-icon"></div>
							<div class="index-item-menu-span">
								报修管理
							</div>
						</div>
					</div>
					<div class="index-amenu">
						<div class="index-item-menu"   @click="gotoIframePage"  :data-url="'/wxy/zsk/list'">
							<div class="index-item-menu-icon"></div>
							<div class="index-item-menu-span">
								知识库
							</div>
						</div>
					</div>
					<div class="index-amenu">
						<div class="index-item-menu"   @click="gotoIframePage"  :data-url="'/wxy/pj_req/list'">
							<div class="index-item-menu-icon"></div>
							<div class="index-item-menu-span">
								配件申请
							</div>
						</div>
					</div>
					<div class="index-amenu">
						<div class="index-item-menu"   @click="gotoIframePage"  :data-url="'/wxy/eval_log/list'">
							<div class="index-item-menu-icon"></div>
							<div class="index-item-menu-span">
								评价查询
							</div>
						</div>
					</div>
					<div class="index-amenu">
						<div class="index-item-menu"   @click="gotoIframePage"  :data-url="'/wxy/xt_notice/list'">
							<div class="index-item-menu-icon"></div>
							<div class="index-item-menu-span">
								系统公告
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="index-right-div">
				<iframe src="" frameborder="0" id="iframeDom" name="rightiframe"  class="index-iframe-dom"></iframe>
			</div>
		</div>
	</div>
</template>

<script>
import $ from 'jquery'
import {utils} from '../../assets/listutils.js'
import {admin_utils} from '../../assets/admin/admin_utils.js'
export default {
	components: {
	},
	data() {
		return {
			loginId:'',
			loginName:'',
			loginToken:'',
			loginType:'',
			user:{},
		};
	},
	mounted() {
		this.loginId=utils.getCookie('loginId');
		this.loginName=utils.getCookie('loginName');
		this.loginToken=utils.getCookie('loginToken');
		this.loginType=utils.getCookie('loginType');
		this.initFirstMenu();
		this.getInitData();
		this.checkIsLogin();
	},
	methods:{
		handleExportJsMethod(e){
			var div = e.currentTarget;
			var methodtype = div.dataset.methodtype;
			if(methodtype=='clickMenu'){
				// 获取当前菜单的展开状态
				var isdown = e.currentTarget.dataset.isdown;
				// 获取所有子菜单元素
				var childMenus = div.parentNode.querySelectorAll('.index-achild-menu');
				// 根据当前状态切换菜单伸缩
				if(isdown==1){
					// 折叠菜单
					childMenus.forEach(menu => {
						menu.style.maxHeight = '0';
					});
					e.currentTarget.dataset.isdown = 2;
					}else{
					// 展开菜单
					childMenus.forEach(menu => {
						// 计算实际需要的高度以实现平滑过渡
						menu.style.maxHeight = menu.scrollHeight + 'px';
					});
					e.currentTarget.dataset.isdown = 1;
				}
			}
		},
		initFirstMenu() {
			// 获取左侧第一个 .index-item-menu 元素
			const firstMenu = $('.index-item-menu').first();
			if (firstMenu.length > 0) {
				$('#iframeDom').attr('src', firstMenu.data('url'));
			}
		},
		gotoIframePage(e){
			var that = this;
			$(".index-item-menu").removeClass("index-item-menu-active");
			$(e.currentTarget).addClass("index-item-menu-active");
			var url =e.currentTarget.dataset.url;
			$('#iframeDom').attr('src', url);
		},
		alterPassword(){
			var that = this;
			$('#iframeDom').attr('src', '/alter_password');
		},
		goPageFunc(e){
			var that = this;
			var url =e.currentTarget.dataset.url;
			that.goToPageWithBlankJS(url,'');
		},
		logout(){
			var that = this;
			$.ajax({
				type: 'post',
				url: this.REQUEST_URL+"/login/sys_logout",
				data: {
					"token":that.loginToken
				},
				success: function(result) {
					if(result.code==1){
						that.goToPageJS('/sys_login','');
						}else{
						alert(result.msg);
					}
				}
			});
		},
		getInitData(){
			var that = this;
			$.ajax({
				dataType:"json",
				type:"post",
				url:this.REQUEST_URL+"/wxy/index/getInitData",
				data:{
					"loginToken":that.loginToken
				},
				success:function (data,textStatus, jqXHR)  {
					var statusCode = jqXHR.status;
					if(statusCode!=200){
						that.goToPageJS('/sys_login','');
					}
					if(data.code!=undefined&&data.code==0){
						alert(data.msg);
						that.goToPageJS('/sys_login','');
					}
					that.user = data.user;
				},
				error: function(jqXHR, textStatus, errorThrown) {
					that.goToPageJS('/sys_login','');
				}
			})
		},
		checkIsLogin(){
			var that = this;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/login/checkIsLogin",
				async: false,
				data : {
					"loginToken":that.loginToken,
				},
				success : function(result) {
					if(result.code==0||result.login.loginType!=2) {
						that.goToPageJS('/sys_login','');
					}
				}
			});
		},
	}
};

</script>
<style type="text/css" src="../../assets/admin/style.css?t=9?t=44" scoped></style>
<style scoped>
</style>



