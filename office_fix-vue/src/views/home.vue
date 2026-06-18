

<template>
	<div class="gather-body">
		<button class="global-print-btn" onclick="window.print()">打印</button>
		<h1 class="page-title">统计分析</h1>
		<!-- 核心指标统计卡片区域 - 所有卡片统一使用stat-card类 -->
		<div class="stats-container">
			<!-- 总访问量 -->
			<div class="stat-card">
				<div class="stat-title">维修员注册数</div>
				<div class="stat-value" >{{wxyzcs.data}}</div>
			</div>
			<div class="stat-card">
				<div class="stat-title">用户注册数</div>
				<div class="stat-value" >{{yhzcs.data}}</div>
			</div>
			<div class="stat-card">
				<div class="stat-title">报修总数</div>
				<div class="stat-value" >{{bxzs.data}}</div>
			</div>
			<div class="stat-card">
				<div class="stat-title">维修过程总数</div>
				<div class="stat-value" >{{wxgczs.data}}</div>
			</div>
			<div class="stat-card">
				<div class="stat-title">知识库总数</div>
				<div class="stat-value" >{{zskzs.data}}</div>
			</div>
		</div>
		<!-- 图表区域 -->
		<div class="charts-container">
			<div class="chart-card">
				<div class="chart-header">
					<div class="chart-title"><span class="icon-bar"></span> 维修员注册统计</div>
					<div class="chart-filter">
						<select class="table-select" id="checkStatusFilterWxyzctj" data-name="checkStatusFilter" gather-name="wxyzctj" @change="getHomeWxyzctj">
							<option value="">全部审核状态</option>
							<template v-for="item in data.checkStatusList">
								<option :value="item.id">{{item.name}}</option>
							</template>
						</select>
						<select class="table-select" id="dateRangeWxyzctj" data-name="dateRange" gather-name="wxyzctj" @change="getHomeWxyzctj">
							<template v-for="item in data.dateList">
								<option :value="item.id">{{item.name}}</option>
							</template>
						</select>
					</div>
				</div>
				<div id="wxyzctj" class="chart-content"></div>
			</div>
			<div class="chart-card">
				<div class="chart-header">
					<div class="chart-title"><span class="icon-bar"></span> 用户注册统计</div>
					<div class="chart-filter">
						<select class="table-select" id="departFilterYhzctj" data-name="departFilter" gather-name="yhzctj" @change="getHomeYhzctj">
							<option value="">全部部门</option>
							<template v-for="item in data.departList">
								<option :value="item.id">{{item.name}}</option>
							</template>
						</select>
						<select class="table-select" id="dateRangeYhzctj" data-name="dateRange" gather-name="yhzctj" @change="getHomeYhzctj">
							<template v-for="item in data.dateList">
								<option :value="item.id">{{item.name}}</option>
							</template>
						</select>
					</div>
				</div>
				<div id="yhzctj" class="chart-content"></div>
			</div>
			<div class="chart-card">
				<div class="chart-header">
					<div class="chart-title"><span class="icon-bar"></span> 满意度统计</div>
					<div class="chart-filter">
					</div>
				</div>
				<div id="mydtj" class="chart-content"></div>
			</div>
			<div class="chart-card">
				<div class="chart-header">
					<div class="chart-title"><span class="icon-bar"></span> 报修统计</div>
					<div class="chart-filter">
						<select class="table-select" id="bgqyFilterBxtj" data-name="bgqyFilter" gather-name="bxtj" @change="getHomeBxtj">
							<option value="">全部办公区域</option>
							<template v-for="item in data.bgqyList">
								<option :value="item.id">{{item.name}}</option>
							</template>
						</select>
					</div>
				</div>
				<div id="bxtj" class="chart-content"></div>
			</div>
			<div class="chart-card">
				<div class="chart-header">
					<div class="chart-title"><span class="icon-bar"></span> 维修过程统计</div>
					<div class="chart-filter">
						<select class="table-select" id="dateRangeWxgctj" data-name="dateRange" gather-name="wxgctj" @change="getHomeWxgctj">
							<template v-for="item in data.dateList">
								<option :value="item.id">{{item.name}}</option>
							</template>
						</select>
					</div>
				</div>
				<div id="wxgctj" class="chart-content"></div>
			</div>
			<div class="chart-card">
				<div class="chart-header">
					<div class="chart-title"><span class="icon-bar"></span> 知识库统计</div>
					<div class="chart-filter">
						<select class="table-select" id="dateRangeZsktj" data-name="dateRange" gather-name="zsktj" @change="getHomeZsktj">
							<template v-for="item in data.dateList">
								<option :value="item.id">{{item.name}}</option>
							</template>
						</select>
					</div>
				</div>
				<div id="zsktj" class="chart-content"></div>
			</div>
		</div>
	</div>
</template>

<script>
import $ from 'jquery'
import {utils} from '../assets/listutils.js'
import {admin_utils} from '../assets/admin/admin_utils.js'
import echarts from '../assets/echarts/echarts.min.js'
export default {
	components: {
	},
	data() {
		return {
			currentLoginToken:'',
			currentLoginId:'',
			currentLoginType:'',
			data:{},
			wxyzcs:{},
			yhzcs:{},
			bxzs:{},
			wxgczs:{},
			zskzs:{},
			wxyzctj:{},
			yhzctj:{},
			mydtj:{},
			bxtj:{},
			wxgctj:{},
			zsktj:{},
		};
	},
	mounted() {
		this.currentLoginToken=utils.getCookie('loginToken');
		this.currentLoginId=utils.getCookie('loginId');
		this.currentLoginType=utils.getCookie('loginType');
		this.initData();
		this.getHomeWxyzctj();
		this.getHomeYhzctj();
		this.getHomeMydtj();
		this.getHomeBxtj();
		this.getHomeWxgctj();
		this.getHomeZsktj();
	},
	methods:{
		initData(){
			var that = this;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/home/getHomeMsg",
				async: false,
				data : {
					'loginToken':that.currentLoginToken,
				},
				success : function(result) {
					that.wxyzcs = result.wxyzcs;
					that.yhzcs = result.yhzcs;
					that.bxzs = result.bxzs;
					that.wxgczs = result.wxgczs;
					that.zskzs = result.zskzs;
					that.data = result;
				}
			});
		},
		getHomeWxyzctj(){
			var that = this;
			var checkStatusFilter = $("#checkStatusFilterWxyzctj").val();
			var dateRange = $("#dateRangeWxyzctj").val();
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/home/getHomeWxyzctj",
				async: false,
				data : {
					'loginToken':that.currentLoginToken,
					"checkStatusFilter":checkStatusFilter,
					"dateRange":dateRange,
				},
				success : function(result) {
					if(result.code==0){
						alert(result.msg);
						return;
					}
					that.wxyzctj = result;
					var gatherData = result.data;
					var  dom = document.getElementById('wxyzctj');
					var charts = echarts.init(dom);
					var allseries = [];
					var seriesName = [];
					var allSeletedColors = ['#409eff', '#67c23a', '#e6a23c'];
					var allseries = [];
					var colors = [];
					for(var k=0;k<gatherData.yAxisData.length;k++){
						var aseries=	{
							name: gatherData.yAxisData[k].yname,
							type: 'line',
							smooth: true,
							data: gatherData.yAxisData[k].yValues,
							lineStyle: { width: 3 },
							areaStyle: {
								color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
								{ offset: 0, color: 'rgba(64, 158, 255, 0.3)' },
								{ offset: 1, color: 'rgba(64, 158, 255, 0.05)' }
								])
							},
							itemStyle: { color: allSeletedColors[k%allSeletedColors.length], borderRadius: 5 },
							symbol: 'circle',
							symbolSize: 6
						};
						allseries.push(aseries);
					}
					var option = {
						tooltip: { trigger: 'axis' },
						legend: { top: 0, left: 'center', data: seriesName, textStyle: { fontSize: 12 } },
						grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
						xAxis: { type: 'category', boundaryGap: false, data: gatherData.xValue },
						yAxis: { type: 'value', name: '', nameTextStyle: { fontSize: 12 } },
						series: allseries
					};
					charts.setOption(option);
				}
			});
		},
		getHomeYhzctj(){
			var that = this;
			var departFilter = $("#departFilterYhzctj").val();
			var dateRange = $("#dateRangeYhzctj").val();
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/home/getHomeYhzctj",
				async: false,
				data : {
					'loginToken':that.currentLoginToken,
					"departFilter":departFilter,
					"dateRange":dateRange,
				},
				success : function(result) {
					if(result.code==0){
						alert(result.msg);
						return;
					}
					that.yhzctj = result;
					var gatherData = result.data;
					var  dom = document.getElementById('yhzctj');
					var charts = echarts.init(dom);
					var allseries = [];
					var seriesName = [];
					var allSeletedColors = ['#409eff', '#67c23a', '#e6a23c'];
					var allseries = [];
					var colors = [];
					for(var k=0;k<gatherData.yAxisData.length;k++){
						var aseries=	{
							name: gatherData.yAxisData[k].yname,
							type: 'line',
							smooth: true,
							data: gatherData.yAxisData[k].yValues,
							lineStyle: { width: 3 },
							areaStyle: {
								color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
								{ offset: 0, color: 'rgba(64, 158, 255, 0.3)' },
								{ offset: 1, color: 'rgba(64, 158, 255, 0.05)' }
								])
							},
							itemStyle: { color: allSeletedColors[k%allSeletedColors.length], borderRadius: 5 },
							symbol: 'circle',
							symbolSize: 6
						};
						allseries.push(aseries);
					}
					var option = {
						tooltip: { trigger: 'axis' },
						legend: { top: 0, left: 'center', data: seriesName, textStyle: { fontSize: 12 } },
						grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
						xAxis: { type: 'category', boundaryGap: false, data: gatherData.xValue },
						yAxis: { type: 'value', name: '', nameTextStyle: { fontSize: 12 } },
						series: allseries
					};
					charts.setOption(option);
				}
			});
		},
		getHomeMydtj(){
			var that = this;
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/home/getHomeMydtj",
				async: false,
				data : {
					'loginToken':that.currentLoginToken,
				},
				success : function(result) {
					if(result.code==0){
						alert(result.msg);
						return;
					}
					that.mydtj = result;
					var gatherData = result.data;
					var  allColors = [
					'#409EFF',
					'#67C23A',
					'#E6A23C',
					'#F56C6C',
					'#909399',
					'#722ED1',
					'#FF4D4F',
					'#13C2C2',
					'#1890FF',
					'#F7BA1E',
					'#7CB305',
					'#FF9500',
					'#FF6B9D',
					'#8C8C8C',
					'#52C41A',
					'#2F54EB',
					'#FA8C16',
					'#EB2F96',
					'#0FC6C2',
					'#7B68EE'
					];
					var dom = document.getElementById('mydtj');
					var charts = echarts.init(dom);
					var list = [];
					var yname = '';
					if(gatherData.yAxisData.length>0){
						yname = gatherData.yAxisData[0].yname;
						for(var j=0;j<gatherData.xValue.length;j++){
							var value = 0;
							if(gatherData.yAxisData[0].yValues.length>j){
								value = gatherData.yAxisData[0].yValues[j];
							}
							var map = {};
							map.value=value;
							map.name=gatherData.xValue[j];
							list.push(map);
						}
					}
					var option = {
						tooltip: { trigger: 'item', formatter: '{b}: {c}% ({d}%)' },
						legend: { orient: 'vertical', left: 'left', top: 'center', textStyle: { fontSize: 12 } },
						series: [{
							name: yname,
							type: 'pie',
							radius: ['40%', '70%'],
							center: ['60%', '50%'],
							data: list,
							label: { show: true, fontSize: 11, formatter: '{d}%' },
							emphasis: { itemStyle: { shadowBlur: 10, shadowOffsetX: 0, shadowColor: 'rgba(0, 0, 0, 0.5)' } },
							color: allColors.slice(0,list.length)
						}]
					};
					charts.setOption(option);
				}
			});
		},
		getHomeBxtj(){
			var that = this;
			var bgqyFilter = $("#bgqyFilterBxtj").val();
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/home/getHomeBxtj",
				async: false,
				data : {
					'loginToken':that.currentLoginToken,
					"bgqyFilter":bgqyFilter,
				},
				success : function(result) {
					if(result.code==0){
						alert(result.msg);
						return;
					}
					that.bxtj = result;
					var gatherData = result.data;
					var  dom = document.getElementById('bxtj');
					var charts = echarts.init(dom);
					var allSeletedColors = ['#409eff', '#67c23a', '#e6a23c'];
					var allseries = [];
					var colors = [];
					for(var k=0;k<gatherData.yAxisData.length;k++){
						colors.push(allSeletedColors[k%allSeletedColors.length]);
						var aseries={
							name: gatherData.yAxisData[k].yname,
							type: 'bar',
							data:  gatherData.yAxisData[k].yValues,
							itemStyle: { borderRadius: 4 }
						};
						allseries.push(aseries);
					}
					var option = {
						tooltip: { trigger: 'axis', axisPointer: { type: 'shadow' } },
						legend: { top: 0, left: 'center', textStyle: { fontSize: 12 } },
						grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
						xAxis: { type: 'category', data: gatherData.xValue },
						yAxis: { type: 'value', name: '', nameTextStyle: { fontSize: 12 } },
						series: allseries,
						color:colors
					};
					charts.setOption(option);
				}
			});
		},
		getHomeWxgctj(){
			var that = this;
			var dateRange = $("#dateRangeWxgctj").val();
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/home/getHomeWxgctj",
				async: false,
				data : {
					'loginToken':that.currentLoginToken,
					"dateRange":dateRange,
				},
				success : function(result) {
					if(result.code==0){
						alert(result.msg);
						return;
					}
					that.wxgctj = result;
					var gatherData = result.data;
					var  dom = document.getElementById('wxgctj');
					var charts = echarts.init(dom);
					var allseries = [];
					var seriesName = [];
					var allSeletedColors = ['#409eff', '#67c23a', '#e6a23c'];
					var allseries = [];
					var colors = [];
					for(var k=0;k<gatherData.yAxisData.length;k++){
						var aseries=	{
							name: gatherData.yAxisData[k].yname,
							type: 'line',
							smooth: true,
							data: gatherData.yAxisData[k].yValues,
							lineStyle: { width: 3 },
							areaStyle: {
								color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
								{ offset: 0, color: 'rgba(64, 158, 255, 0.3)' },
								{ offset: 1, color: 'rgba(64, 158, 255, 0.05)' }
								])
							},
							itemStyle: { color: allSeletedColors[k%allSeletedColors.length], borderRadius: 5 },
							symbol: 'circle',
							symbolSize: 6
						};
						allseries.push(aseries);
					}
					var option = {
						tooltip: { trigger: 'axis' },
						legend: { top: 0, left: 'center', data: seriesName, textStyle: { fontSize: 12 } },
						grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
						xAxis: { type: 'category', boundaryGap: false, data: gatherData.xValue },
						yAxis: { type: 'value', name: '', nameTextStyle: { fontSize: 12 } },
						series: allseries
					};
					charts.setOption(option);
				}
			});
		},
		getHomeZsktj(){
			var that = this;
			var dateRange = $("#dateRangeZsktj").val();
			$.ajax({
				type : 'get',
				url : this.REQUEST_URL+"/home/getHomeZsktj",
				async: false,
				data : {
					'loginToken':that.currentLoginToken,
					"dateRange":dateRange,
				},
				success : function(result) {
					if(result.code==0){
						alert(result.msg);
						return;
					}
					that.zsktj = result;
					var gatherData = result.data;
					var  dom = document.getElementById('zsktj');
					var charts = echarts.init(dom);
					var allseries = [];
					var seriesName = [];
					var allSeletedColors = ['#409eff', '#67c23a', '#e6a23c'];
					var allseries = [];
					var colors = [];
					for(var k=0;k<gatherData.yAxisData.length;k++){
						var aseries=	{
							name: gatherData.yAxisData[k].yname,
							type: 'line',
							smooth: true,
							data: gatherData.yAxisData[k].yValues,
							lineStyle: { width: 3 },
							areaStyle: {
								color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
								{ offset: 0, color: 'rgba(64, 158, 255, 0.3)' },
								{ offset: 1, color: 'rgba(64, 158, 255, 0.05)' }
								])
							},
							itemStyle: { color: allSeletedColors[k%allSeletedColors.length], borderRadius: 5 },
							symbol: 'circle',
							symbolSize: 6
						};
						allseries.push(aseries);
					}
					var option = {
						tooltip: { trigger: 'axis' },
						legend: { top: 0, left: 'center', data: seriesName, textStyle: { fontSize: 12 } },
						grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
						xAxis: { type: 'category', boundaryGap: false, data: gatherData.xValue },
						yAxis: { type: 'value', name: '', nameTextStyle: { fontSize: 12 } },
						series: allseries
					};
					charts.setOption(option);
				}
			});
		},
	}
};

</script>
<style type="text/css" src="../assets/admin/style.css?t=9?t=44" scoped></style>
<style scoped>
	* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: "Microsoft Yahei", sans-serif;
	}
	.gather-body {
	background-color: #f5f7fa;
	padding: 20px;
	}
	/* 新增：全局打印按钮样式 */
	.global-print-btn {
	position: fixed;
	top: 20px;
	right: 20px;
	z-index: 9999;
	padding: 10px 20px;
	border: 1px solid #409eff;
	border-radius: 6px;
	background-color: #409eff;
	color: #fff;
	font-size: 14px;
	cursor: pointer;
	transition: all 0.2s ease;
	display: inline-flex;
	align-items: center;
	gap: 6px;
	box-shadow: 0 2px 10px rgba(64, 158, 255, 0.3);
	}
	.global-print-btn:hover {
	background-color: #337ecc;
	border-color: #337ecc;
	}
	.global-print-btn::before {
	content: "🖨️";
	font-size: 16px;
	}
	/* 页面标题 */
	.page-title {
	text-align: center;
	color: #1f2937;
	margin-bottom: 25px;
	font-size: 32px;
	font-weight: 700;
	letter-spacing: 1.5px;
	}
	/* 核心指标统计卡片容器 */
	.stats-container {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
	gap: 20px;
	margin-bottom: 30px;
	padding: 0 10px;
	}
	/* 统计卡片样式（流行BI风格） */
	.stat-card {
	background: linear-gradient(135deg, #ffffff 0%, #f9fafb 100%);
	border-radius: 12px;
	box-shadow: 0 2px 15px rgba(0, 0, 0, 0.06);
	padding: 20px;
	position: relative;
	overflow: hidden;
	}
	/* 统计卡片装饰角标 - 6种颜色循环 */
	.stat-card::before {
	content: "";
	position: absolute;
	top: 0;
	left: 0;
	width: 4px;
	height: 100%;
	}
	/* 第1/7/13...个卡片（6n+1）- 蓝色 */
	.stats-container .stat-card:nth-child(6n+1)::before {
	background: #409eff;
	}
	/* 第2/8/14...个卡片（6n+2）- 紫色 */
	.stats-container .stat-card:nth-child(6n+2)::before {
	background: #9333ea;
	}
	/* 第3/9/15...个卡片（6n+3）- 绿色 */
	.stats-container .stat-card:nth-child(6n+3)::before {
	background: #10b981;
	}
	/* 第4/10/16...个卡片（6n+4）- 橙色 */
	.stats-container .stat-card:nth-child(6n+4)::before {
	background: #f97316;
	}
	/* 第5/11/17...个卡片（6n+5）- 红色 */
	.stats-container .stat-card:nth-child(6n+5)::before {
	background: #ef4444;
	}
	/* 第6/12/18...个卡片（6n+6）- 青色 */
	.stats-container .stat-card:nth-child(6n+6)::before {
	background: #06b6d4;
	}
	.stat-title {
	font-size: 14px;
	color: #6b7280;
	margin-bottom: 8px;
	font-weight: 500;
	}
	.stat-value {
	font-size: 28px;
	font-weight: 700;
	color: #1f2937;
	line-height: 1.2;
	}
	.stat-desc {
	font-size: 12px;
	color: #9ca3af;
	margin-top: 8px;
	display: flex;
	align-items: center;
	}
	.stat-desc .up { color: #10b981; }
	.stat-desc .down { color: #ef4444; }
	.stat-desc span { margin-left: 4px; }
	/* 图表容器网格布局 */
	.charts-container {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(500px, 1fr));
	gap: 25px;
	padding: 0 10px;
	margin-bottom: 30px;
	}
	/* 列表模块容器 */
	.table-container {
	padding: 0 10px;
	margin-bottom: 30px;
	}
	/* 列表卡片样式 */
	.table-card {
	background: #ffffff;
	border-radius: 12px;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
	padding: 20px;
	}
	/* 列表头部（标题+筛选+打印按钮） */
	.table-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 15px;
	padding-bottom: 10px;
	border-bottom: 1px solid #f0f2f5;
	}
	.table-title {
	font-size: 18px;
	color: #2c3e50;
	font-weight: 500;
	display: flex;
	align-items: center;
	}
	.table-title i::before {
	content: "📋";
	margin-right: 8px;
	color: #409eff;
	font-size: 20px;
	}
	/* 单个图表卡片样式（核心精美样式） */
	.chart-card {
	background: #ffffff;
	border-radius: 12px;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
	padding: 20px;
	transition: all 0.3s ease;
	}
	.chart-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 8px 30px rgba(0, 0, 0, 0.12);
	}
	/* 图表/列表头部（标题+筛选+打印按钮） */
	.chart-header, .table-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 15px;
	padding-bottom: 10px;
	border-bottom: 1px solid #f0f2f5;
	}
	.chart-title, .table-title {
	font-size: 18px;
	color: #2c3e50;
	font-weight: 500;
	display: flex;
	align-items: center;
	}
	.chart-title i, .table-title i {
	margin-right: 8px;
	color: #409eff;
	font-size: 20px;
	}
	/* 下拉筛选框样式 */
	.chart-filter, .table-filter {
	position: relative;
	margin-right: 10px;
	}
	.chart-select, .table-select {
	padding: 8px 35px 8px 15px;
	margin-top:4px;
	margin-left:8px;
	max-width:150px;
	border: 1px solid #e6e6e6;
	border-radius: 6px;
	background-color: #f9f9f9;
	color: #333;
	font-size: 14px;
	cursor: pointer;
	appearance: none;
	outline: none;
	transition: all 0.2s ease;
	}
	.chart-select:hover, .table-select:hover {
	border-color: #409eff;
	background-color: #fff;
	}
	.chart-select:focus, .table-select:focus {
	border-color: #409eff;
	box-shadow: 0 0 0 2px rgba(64, 158, 255, 0.2);
	}
	/* 下拉框箭头伪元素 */
	.chart-filter::after, .table-filter::after {
	position: absolute;
	right: 12px;
	top: 50%;
	transform: translateY(-50%);
	font-size: 10px;
	color: #999;
	pointer-events: none;
	}
	/* 图表画布容器 */
	.chart-content {
	width: 100%;
	height: 400px;
	border-radius: 8px;
	}
	/* 数据列表样式 */
	.data-table {
	width: 100%;
	border-collapse: collapse;
	font-size: 14px;
	}
	.data-table th {
	background-color: #f8f9fa;
	color: #2c3e50;
	font-weight: 500;
	padding: 12px 15px;
	text-align: left;
	border-bottom: 2px solid #e9ecef;
	}
	.data-table td {
	padding: 12px 15px;
	color: #495057;
	border-bottom: 1px solid #e9ecef;
	}
	.data-table tr:hover {
	background-color: #f8f9fa;
	}
	.data-table .number {
	color: #409eff;
	font-weight: 500;
	}
	.data-table .rate-up {
	color: #10b981;
	}
	.data-table .rate-down {
	color: #ef4444;
	}
	/* 列表分页样式 */
	.table-pagination {
	display: flex;
	justify-content: flex-end;
	align-items: center;
	margin-top: 20px;
	gap: 10px;
	font-size: 14px;
	}
	.pagination-btn {
	padding: 6px 12px;
	border: 1px solid #e6e6e6;
	border-radius: 4px;
	background-color: #fff;
	color: #495057;
	cursor: pointer;
	transition: all 0.2s ease;
	}
	.pagination-btn:hover {
	border-color: #409eff;
	color: #409eff;
	}
	.pagination-btn.active {
	background-color: #409eff;
	color: #fff;
	border-color: #409eff;
	}
	/* 响应式适配 */
	@media (max-width: 1200px) {
	.charts-container {
	grid-template-columns: repeat(auto-fit, minmax(400px, 1fr));
	}
	.chart-content {
	height: 350px;
	}
	}
	@media (max-width: 768px) {
	.stats-container {
	grid-template-columns: 1fr 1fr;
	}
	.charts-container {
	grid-template-columns: 1fr;
	}
	.chart-content {
	height: 300px;
	}
	.chart-header, .table-header {
	flex-direction: column;
	align-items: flex-start;
	gap: 10px;
	}
	.data-table {
	font-size: 12px;
	}
	.data-table th, .data-table td {
	padding: 8px 10px;
	}
	.header-actions {
	width: 100%;
	display: flex;
	justify-content: flex-start;
	}
	/* 响应式适配全局打印按钮 */
	.global-print-btn {
	top: 10px;
	right: 10px;
	padding: 8px 15px;
	font-size: 12px;
	}
	}
	@media (max-width: 480px) {
	.stats-container {
	grid-template-columns: 1fr;
	}
	.table-pagination {
	flex-wrap: wrap;
	justify-content: center;
	}
	}
	/* 模拟图标样式（替代Font Awesome，无额外依赖） */
	.icon-bar::before { content: "📊"; }
	.icon-line::before { content: "📈"; }
	.icon-pie::before { content: "🥧"; }
	.icon-wordcloud::before { content: "🔤"; }
	.icon-radar::before { content: "🎯"; }
	/* 打印按钮样式 */
	.print-btn {
	padding: 8px 16px;
	border: 1px solid #409eff;
	border-radius: 6px;
	background-color: #409eff;
	color: #fff;
	font-size: 14px;
	cursor: pointer;
	transition: all 0.2s ease;
	display: inline-flex;
	align-items: center;
	gap: 6px;
	}
	.print-btn:hover {
	background-color: #337ecc;
	border-color: #337ecc;
	}
	.print-btn::before {
	content: "🖨️";
	font-size: 16px;
	}
	/* 修复打印样式 - 精准控制打印区域 */
	@media print {
	/* 全局打印时显示所有内容 */
	body.global-print * {
	visibility: visible !important;
	position: static !important;
	box-shadow: none !important;
	border: none !important;
	}
	/* 局部打印时只显示目标区域 */
	body:not(.global-print) * {
	visibility: hidden;
	}
	body:not(.global-print) .print-area {
	visibility: visible;
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	padding: 20px;
	}
	body:not(.global-print) .print-area * {
	visibility: visible;
	}
	/* 隐藏所有打印按钮和筛选、分页控件 */
	.print-btn, .global-print-btn, .chart-filter, .table-filter, .table-pagination {
	display: none !important;
	}
	.chart-content {
	height: 500px !important;
	}
	.chart-header, .table-header {
	border-bottom: 1px solid #e0e0e0 !important;
	margin-bottom: 20px !important;
	}
	}
	/* 头部操作区（筛选+打印按钮） */
	.header-actions {
	display: flex;
	align-items: center;
	}
</style>



