   import $ from 'jquery'
export var date_utils={
	getDomVal(domId){
		var val = $("#"+domId).val();
		if(val==''||val==undefined){
			return '';
		}
		if($("#"+domId).attr("type")=='date'){
				return val;
		}else{
			return val.replace('T',' ')+':00';
		}
	},
	setDomVal(domId,val){
		val = val.replace(' ','T');
		$("#"+domId).val(val);
	}
}
