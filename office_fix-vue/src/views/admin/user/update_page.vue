

<template>
	<div id="body" class="edit-body" >
		<div class="edit-title-div">
			<div class="edit-title">
				<div class="edit-title-txt">用户-修改</div>
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
					<div class="edit-main-form"  id="nameForm">
						<div class="edit-main-form-title">姓名<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入姓名" name="name" v-model="name" class="form-control edit-input" id="name">
						</div>
					</div>
				</div>
				<div class="edit-main-form-div1">
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
					<div class="edit-main-form"  id="departForm">
						<div class="edit-main-form-title">部门<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<select  class="form-control edit-select" data-id="depart"  v-model="depart"  id="depart" >
								<template v-for="item in departList">
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
import {getFileVal2,initDropZone,setZoneImg} from '../../../assets/imgupload/js/imgupload.js'
export default {
	components: {
	},
	data() {
		return {
			currentLoginToken:'',
			id:'',
			loginName:'',
			name:'',
			celphone:'',
			headImg:'',
			depart:'',
			departList:[],
		};
	},
	mounted() {
		var that = this;
		that.currentLoginToken=utils.getCookie('loginToken');
		that.id= that.$route.query.id;
		that.getDataList();
		var headImgDrop = initDropZone('headImg');
		that.queryDataDetail();
		setZoneImg(headImgDrop,that.headImg);
	},
	methods:{
		getDataList(){
			var that = this;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/admin/user/getInitData",
				async: false,
				data : {
					'loginToken':that.currentLoginToken
				},
				success : function(result) {
					that.departList = result.departList;
				}
			});
		},
		queryDataDetail(){
			var that = this;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/admin/user/queryDataDetail",
				async: false,
				data : {
					'id':that.id,
					'loginToken':that.currentLoginToken,
				},
				success : function(result) {
					that.loginName=result.loginName;
					that.name=result.name;
					that.celphone=result.celphone;
					that.headImg=result.headImg;
					that.depart=result.depart+'';
				}
			});
		},
		submitData(){
			var that = this;
			var loginName = that.loginName;
			var name = that.name;
			var celphone = that.celphone;
			var headImg = getFileVal2('headImg');
			var depart = that.depart;
			$.ajax({
				type : 'post',
				url : this.REQUEST_URL+"/admin/user/update_submit",
				async: false,
				data : {
					'id':that.id,
					'loginName':loginName,
					'name':name,
					'celphone':celphone,
					'headImg':headImg,
					'depart':depart,
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



