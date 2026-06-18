<template>
<div id="app">
<router-view :key="$route.fullPath"></router-view>
</div>
</template>
<script>
export default {
	productionTip:false,
	name: 'App'
}
</script>
<style>
</style>
