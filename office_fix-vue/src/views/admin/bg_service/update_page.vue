

<template>
	<div id="body" class="edit-body" >
		<div class="edit-title-div">
			<div class="edit-title">
				<div class="edit-title-txt">办公设备-修改</div>
			</div>
		</div>
		<div class="edit-main">
			<div class="edit-main2">
				<div class="edit-main-form-div2">
					<div class="edit-main-form"  id="serviceNoForm">
						<div class="edit-main-form-title">设备编号<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入设备编号" name="serviceNo" v-model="serviceNo" class="form-control edit-input" id="serviceNo">
						</div>
					</div>
					<div class="edit-main-form"  id="serviceTypeForm">
						<div class="edit-main-form-title">设备类型<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入设备类型" name="serviceType" v-model="serviceType" class="form-control edit-input" id="serviceType">
						</div>
					</div>
				</div>
				<div class="edit-main-form-div2">
					<div class="edit-main-form"  id="serviceNameForm">
						<div class="edit-main-form-title">设备名<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入设备名" name="serviceName" v-model="serviceName" class="form-control edit-input" id="serviceName">
						</div>
					</div>
					<div class="edit-main-form"  id="serviceXhForm">
						<div class="edit-main-form-title">设备型号<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入设备型号" name="serviceXh" v-model="serviceXh" class="form-control edit-input" id="serviceXh">
						</div>
					</div>
				</div>
				<div class="edit-main-form-div2">
					<div class="edit-main-form"  id="cgDateForm">
						<div class="edit-main-form-title">采购日期<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="date" id="cgDate"  class="form-control edit-input" >
						</div>
					</div>
					<div class="edit-main-form"  id="bgqyForm">
						<div class="edit-main-form-title">办公区域<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<select  class="form-control edit-select" data-id="bgqy"  v-model="bgqy"  id="bgqy" >
								<template v-for="item in bgqyList">
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
import {date_utils} from '../../../assets/datepicker/date_utils.js'
export default {
	components: {
	},
	data() {
		return {
			currentLoginToken:'',
			id:'',
			serviceNo:'',
			serviceType:'',
			serviceName:'',
			serviceXh:'',
			cgDate:'',
			bgqy:'',
			bgqyList:[],
		};
	},
	mounted() {
		var that = this;
		that.currentLoginToken=utils.getCookie('loginToken');
		that.id= that.$route.query.id;
		that.getDataList();
		that.queryDataDetail();
		date_utils.setDomVal("cgDate",that.cgDate);
	},
	methods:{
		getDataList(){
			var that = this;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/admin/bg_service/getInitData",
				async: false,
				data : {
					'loginToken':that.currentLoginToken
				},
				success : function(result) {
					that.bgqyList = result.bgqyList;
				}
			});
		},
		queryDataDetail(){
			var that = this;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/admin/bg_service/queryDataDetail",
				async: false,
				data : {
					'id':that.id,
					'loginToken':that.currentLoginToken,
				},
				success : function(result) {
					that.serviceNo=result.serviceNo;
					that.serviceType=result.serviceType;
					that.serviceName=result.serviceName;
					that.serviceXh=result.serviceXh;
					that.cgDate=result.cgDate;
					that.bgqy=result.bgqy+'';
				}
			});
		},
		submitData(){
			var that = this;
			var serviceNo = that.serviceNo;
			var serviceType = that.serviceType;
			var serviceName = that.serviceName;
			var serviceXh = that.serviceXh;
			var cgDate = date_utils.getDomVal("cgDate");
			var bgqy = that.bgqy;
			$.ajax({
				type : 'post',
				url : this.REQUEST_URL+"/admin/bg_service/update_submit",
				async: false,
				data : {
					'id':that.id,
					'serviceNo':serviceNo,
					'serviceType':serviceType,
					'serviceName':serviceName,
					'serviceXh':serviceXh,
					'cgDate':cgDate,
					'bgqy':bgqy,
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



