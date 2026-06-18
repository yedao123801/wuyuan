

<template>
	<div id="body" class="edit-body" >
		<div class="edit-title-div">
			<div class="edit-title">
				<div class="edit-title-txt">知识库-新增</div>
			</div>
		</div>
		<div class="edit-main">
			<div class="edit-main2">
				<div class="edit-main-form-div1">
					<div class="edit-main-form"  id="titleForm">
						<div class="edit-main-form-title">标题<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<input type="text"  placeholder="请输入标题" name="title" v-model="title" class="form-control edit-input" id="title">
						</div>
					</div>
				</div>
				<div class="edit-main-form-div1">
					<div class="edit-main-form"  id="subtitleForm">
						<div class="edit-main-form-title">副标题<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<textarea class="form-control edit-textarea"  name="subtitle" v-model="subtitle"  id="subtitle"></textarea>
						</div>
					</div>
				</div>
				<div class="edit-main-form-div1">
					<div class="edit-main-form"  id="zsContentForm">
						<div class="edit-main-form-title">知识内容<div class="edit-main-form-title-tip">(必填)</div></div>
						<div class="edit-main-form-input-div">
							<div class="editorDiv" id="zsContent" data-id="zsContent"></div>
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
import {initAllEditor,getEditVal,setEditorHtml} from '../../../assets/wangeditor/editor_utils.js'
export default {
	components: {
	},
	data() {
		return {
			currentLoginToken:'',
			title:'',
			subtitle:'',
			zsContent:'',
		};
	},
	mounted() {
		var that = this;
		that.currentLoginToken=utils.getCookie('loginToken');
		initAllEditor(this.REQUEST_URL,'zsContent');
	},
	methods:{
		submitData(){
			var that = this;
			var title = that.title;
			var subtitle = that.subtitle;
			var zsContent = getEditVal('zsContent');
			$.ajax({
				type : 'post',
				url : this.REQUEST_URL+"/admin/zsk/add_submit",
				async: false,
				data : {
					'title':title,
					'subtitle':subtitle,
					'zsContent':zsContent,
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



