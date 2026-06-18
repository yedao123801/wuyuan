import { createRouter, createWebHistory } from 'vue-router'
	import sys_login from '../views/sys_login.vue';
	import fix_regist from '../views/fix_regist.vue';
	import home from '../views/home.vue';
	import admin_index from '../views/admin/index.vue';
	import wxy_index from '../views/wxy/index.vue';
	import admin_fix_list from '../views/admin/fix/list.vue';
	import admin_user_list from '../views/admin/user/list.vue';
	import admin_depart_list from '../views/admin/depart/list.vue';
	import admin_bg_service_list from '../views/admin/bg_service/list.vue';
	import admin_bgqy_list from '../views/admin/bgqy/list.vue';
	import admin_bx_log_list from '../views/admin/bx_log/list.vue';
	import wxy_bx_log_list from '../views/wxy/bx_log/list.vue';
	import admin_fix_gc_list from '../views/admin/fix_gc/list.vue';
	import wxy_fix_gc_list from '../views/wxy/fix_gc/list.vue';
	import admin_zsk_list from '../views/admin/zsk/list.vue';
	import wxy_zsk_list from '../views/wxy/zsk/list.vue';
	import admin_pj_req_list from '../views/admin/pj_req/list.vue';
	import wxy_pj_req_list from '../views/wxy/pj_req/list.vue';
	import admin_eval_log_list from '../views/admin/eval_log/list.vue';
	import wxy_eval_log_list from '../views/wxy/eval_log/list.vue';
	import admin_xt_notice_list from '../views/admin/xt_notice/list.vue';
	import wxy_xt_notice_list from '../views/wxy/xt_notice/list.vue';
	import wxy_personal from '../views/wxy/personal.vue';
	import admin_bx_log_detail from '../views/admin/bx_log/detail.vue';
	import wxy_bx_log_detail from '../views/wxy/bx_log/detail.vue';
	import admin_zsk_detail from '../views/admin/zsk/detail.vue';
	import wxy_zsk_detail from '../views/wxy/zsk/detail.vue';
	import admin_xt_notice_detail from '../views/admin/xt_notice/detail.vue';
	import wxy_xt_notice_detail from '../views/wxy/xt_notice/detail.vue';
	import alter_password from '../views/alter_password.vue';
	import admin_fix_add from '../views/admin/fix/add_page.vue';
	import admin_fix_update from '../views/admin/fix/update_page.vue';
	import wxy_fix_update1 from '../views/wxy/fix/update1_page.vue';
	import admin_user_add from '../views/admin/user/add_page.vue';
	import admin_user_update from '../views/admin/user/update_page.vue';
	import admin_depart_add from '../views/admin/depart/add_page.vue';
	import admin_depart_update from '../views/admin/depart/update_page.vue';
	import admin_bg_service_add from '../views/admin/bg_service/add_page.vue';
	import admin_bg_service_update from '../views/admin/bg_service/update_page.vue';
	import admin_bgqy_add from '../views/admin/bgqy/add_page.vue';
	import admin_bgqy_update from '../views/admin/bgqy/update_page.vue';
	import admin_bx_log_pd from '../views/admin/bx_log/pd_page.vue';
	import admin_bx_log_bh from '../views/admin/bx_log/bh_page.vue';
	import wxy_bx_log_wc from '../views/wxy/bx_log/wc_page.vue';
	import wxy_fix_gc_add from '../views/wxy/fix_gc/add_page.vue';
	import wxy_fix_gc_update from '../views/wxy/fix_gc/update_page.vue';
	import admin_zsk_add from '../views/admin/zsk/add_page.vue';
	import admin_zsk_update from '../views/admin/zsk/update_page.vue';
	import admin_pj_req_jssq from '../views/admin/pj_req/jssq_page.vue';
	import admin_pj_req_jjsq from '../views/admin/pj_req/jjsq_page.vue';
	import wxy_pj_req_add from '../views/wxy/pj_req/add_page.vue';
	import wxy_pj_req_update from '../views/wxy/pj_req/update_page.vue';
	import admin_xt_notice_add from '../views/admin/xt_notice/add_page.vue';
	import admin_xt_notice_update from '../views/admin/xt_notice/update_page.vue';
	const  routes= [
{
	path: '/',
	component: sys_login,
	name:'sys_login0',
meta:{auth:true}
},
{
	path: '/sys_login',
	component: sys_login,
	name:'sys_login',
meta:{auth:true}
},
{
	path: '/fix_regist',
	component: fix_regist,
	name:'fix_regist',
meta:{auth:true}
},
{
	path: '/home',
	component: home,
	name:'home',
meta:{auth:true}
},
{
	path: '/admin/index',
	component: admin_index,
	name:'admin_index',
meta:{auth:true}
},
{
	path: '/wxy/index',
	component: wxy_index,
	name:'wxy_index',
meta:{auth:true}
},
{
	path: '/admin/fix/list',
	component: admin_fix_list,
	name:'admin_fix_list',
meta:{auth:true}
},
{
	path: '/admin/user/list',
	component: admin_user_list,
	name:'admin_user_list',
meta:{auth:true}
},
{
	path: '/admin/depart/list',
	component: admin_depart_list,
	name:'admin_depart_list',
meta:{auth:true}
},
{
	path: '/admin/bg_service/list',
	component: admin_bg_service_list,
	name:'admin_bg_service_list',
meta:{auth:true}
},
{
	path: '/admin/bgqy/list',
	component: admin_bgqy_list,
	name:'admin_bgqy_list',
meta:{auth:true}
},
{
	path: '/admin/bx_log/list',
	component: admin_bx_log_list,
	name:'admin_bx_log_list',
meta:{auth:true}
},
{
	path: '/wxy/bx_log/list',
	component: wxy_bx_log_list,
	name:'wxy_bx_log_list',
meta:{auth:true}
},
{
	path: '/admin/fix_gc/list',
	component: admin_fix_gc_list,
	name:'admin_fix_gc_list',
meta:{auth:true}
},
{
	path: '/wxy/fix_gc/list',
	component: wxy_fix_gc_list,
	name:'wxy_fix_gc_list',
meta:{auth:true}
},
{
	path: '/admin/zsk/list',
	component: admin_zsk_list,
	name:'admin_zsk_list',
meta:{auth:true}
},
{
	path: '/wxy/zsk/list',
	component: wxy_zsk_list,
	name:'wxy_zsk_list',
meta:{auth:true}
},
{
	path: '/admin/pj_req/list',
	component: admin_pj_req_list,
	name:'admin_pj_req_list',
meta:{auth:true}
},
{
	path: '/wxy/pj_req/list',
	component: wxy_pj_req_list,
	name:'wxy_pj_req_list',
meta:{auth:true}
},
{
	path: '/admin/eval_log/list',
	component: admin_eval_log_list,
	name:'admin_eval_log_list',
meta:{auth:true}
},
{
	path: '/wxy/eval_log/list',
	component: wxy_eval_log_list,
	name:'wxy_eval_log_list',
meta:{auth:true}
},
{
	path: '/admin/xt_notice/list',
	component: admin_xt_notice_list,
	name:'admin_xt_notice_list',
meta:{auth:true}
},
{
	path: '/wxy/xt_notice/list',
	component: wxy_xt_notice_list,
	name:'wxy_xt_notice_list',
meta:{auth:true}
},
{
	path: '/wxy/personal',
	component: wxy_personal,
	name:'wxy_personal',
meta:{auth:true}
},
{
	path: '/admin/bx_log/detail',
	component: admin_bx_log_detail,
	name:'admin_bx_log_detail',
meta:{auth:true}
},
{
	path: '/wxy/bx_log/detail',
	component: wxy_bx_log_detail,
	name:'wxy_bx_log_detail',
meta:{auth:true}
},
{
	path: '/admin/zsk/detail',
	component: admin_zsk_detail,
	name:'admin_zsk_detail',
meta:{auth:true}
},
{
	path: '/wxy/zsk/detail',
	component: wxy_zsk_detail,
	name:'wxy_zsk_detail',
meta:{auth:true}
},
{
	path: '/admin/xt_notice/detail',
	component: admin_xt_notice_detail,
	name:'admin_xt_notice_detail',
meta:{auth:true}
},
{
	path: '/wxy/xt_notice/detail',
	component: wxy_xt_notice_detail,
	name:'wxy_xt_notice_detail',
meta:{auth:true}
},
{
	path: '/alter_password',
	component: alter_password,
	name:'alter_password',
meta:{auth:true}
},
{
	path: '/admin/fix/add_page',
	component: admin_fix_add,
	name:'admin_fix_add',
meta:{auth:true}
},
{
	path: '/admin/fix/update_page',
	component: admin_fix_update,
	name:'admin_fix_update',
meta:{auth:true}
},
{
	path: '/wxy/fix/update1_page',
	component: wxy_fix_update1,
	name:'wxy_fix_update1',
meta:{auth:true}
},
{
	path: '/admin/user/add_page',
	component: admin_user_add,
	name:'admin_user_add',
meta:{auth:true}
},
{
	path: '/admin/user/update_page',
	component: admin_user_update,
	name:'admin_user_update',
meta:{auth:true}
},
{
	path: '/admin/depart/add_page',
	component: admin_depart_add,
	name:'admin_depart_add',
meta:{auth:true}
},
{
	path: '/admin/depart/update_page',
	component: admin_depart_update,
	name:'admin_depart_update',
meta:{auth:true}
},
{
	path: '/admin/bg_service/add_page',
	component: admin_bg_service_add,
	name:'admin_bg_service_add',
meta:{auth:true}
},
{
	path: '/admin/bg_service/update_page',
	component: admin_bg_service_update,
	name:'admin_bg_service_update',
meta:{auth:true}
},
{
	path: '/admin/bgqy/add_page',
	component: admin_bgqy_add,
	name:'admin_bgqy_add',
meta:{auth:true}
},
{
	path: '/admin/bgqy/update_page',
	component: admin_bgqy_update,
	name:'admin_bgqy_update',
meta:{auth:true}
},
{
	path: '/admin/bx_log/pd_page',
	component: admin_bx_log_pd,
	name:'admin_bx_log_pd',
meta:{auth:true}
},
{
	path: '/admin/bx_log/bh_page',
	component: admin_bx_log_bh,
	name:'admin_bx_log_bh',
meta:{auth:true}
},
{
	path: '/wxy/bx_log/wc_page',
	component: wxy_bx_log_wc,
	name:'wxy_bx_log_wc',
meta:{auth:true}
},
{
	path: '/wxy/fix_gc/add_page',
	component: wxy_fix_gc_add,
	name:'wxy_fix_gc_add',
meta:{auth:true}
},
{
	path: '/wxy/fix_gc/update_page',
	component: wxy_fix_gc_update,
	name:'wxy_fix_gc_update',
meta:{auth:true}
},
{
	path: '/admin/zsk/add_page',
	component: admin_zsk_add,
	name:'admin_zsk_add',
meta:{auth:true}
},
{
	path: '/admin/zsk/update_page',
	component: admin_zsk_update,
	name:'admin_zsk_update',
meta:{auth:true}
},
{
	path: '/admin/pj_req/jssq_page',
	component: admin_pj_req_jssq,
	name:'admin_pj_req_jssq',
meta:{auth:true}
},
{
	path: '/admin/pj_req/jjsq_page',
	component: admin_pj_req_jjsq,
	name:'admin_pj_req_jjsq',
meta:{auth:true}
},
{
	path: '/wxy/pj_req/add_page',
	component: wxy_pj_req_add,
	name:'wxy_pj_req_add',
meta:{auth:true}
},
{
	path: '/wxy/pj_req/update_page',
	component: wxy_pj_req_update,
	name:'wxy_pj_req_update',
meta:{auth:true}
},
{
	path: '/admin/xt_notice/add_page',
	component: admin_xt_notice_add,
	name:'admin_xt_notice_add',
meta:{auth:true}
},
{
	path: '/admin/xt_notice/update_page',
	component: admin_xt_notice_update,
	name:'admin_xt_notice_update',
meta:{auth:true}
},
]
const router = createRouter({
	mode: 'history', // 设置为history模式
	history:createWebHistory(),
	base: process.env.BASE_URL,
	routes
})
export default router
