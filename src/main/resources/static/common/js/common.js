function submitAjax(formObj, settings) {
    var defaultOption = {
		type : 'post',
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		headers : {},
		dataType : 'text',
		success : function(data) {
			if(settings!==undefined && settings.success){
				settings.success(data);
			}
		},
		error:function(data){

		}
	};

    var ajaxOption;
    if(formObj !== null && formObj !== undefined){
        formObj = jQuery(formObj);
        ajaxOption = {
            url :formObj.attr("action"),
            data: settings.data ? settings.data : formObj.serialize()
        };
        if (settings){jQuery.extend(ajaxOption, settings);}
        jQuery.extend(ajaxOption, defaultOption);
    }else{
        ajaxOption = {
            url:settings.url,
            data:cm_escapeParam(settings.data)
        };
        if (settings){jQuery.extend(ajaxOption, settings);}
        jQuery.extend(ajaxOption, defaultOption);
    }

    jQuery.ajax(ajaxOption);
	return false;
}

function cm_escapeParam(paramQuery){
	if(paramQuery===undefined||paramQuery===null){
		return "";
	}

	var query = paramQuery.split("&");
	var result = "";
	for(var i =0 , ic = query.length; i < ic; i++ ){
		var keyName = query[i].split("=");
		if(keyName.length>0){
			result+= "&"+keyName[0]+"="+escape_url(keyName[1]);
		}
	}
	return result;
}

function escape_url(url){
	var i;
	var ch;
	var out = '';
	var url_string = '';

	url_string = String(url);

	for (i = 0; i < url_string.length; i++) {
		ch = url_string.charAt(i);
		if (ch == ' ')		out += '%20';
		else if (ch == '%')	out += '%25';
		else if (ch == '&')	out += '%26';
		else if (ch == '+')	out += '%2B';
		else if (ch == '=')	out += '%3D';
		else if (ch == '?') out += '%3F';
		else				out += ch;
	}
	return out;
}


function DataSet(){
	this.mp			= new Object();
	this.keyArry	= new Array();
	function put ( key, value, idx ){
		var arry = this.mp[key];
		if ( !arry ){
			arry								= new Array();
			this.mp[key]                        = arry;
			this.keyArry[this.keyArry.length]	= key;
		}

		if (idx===undefined || idx == null ){ idx = 0;}
		arry[idx] = value;
	}
	function get(key, idx){ // xget ( key, idx )
		var arry = this.mp[key];
		if(!arry) return '';
		if(idx===undefined || idx == null  ){ idx = 0;}
		var val = arry[idx];

		if ( !val )	val='';

		return val.toString().replace(/(^\s*)|(\s*$)/g, "");
	}
	function add( key, value ){
		var arry = this.mp[key];
		if ( !arry ){
			arry								= new Array();
			this.mp[key]  = arry;
			this.keyArry[this.keyArry.length]	= key;
		}

		arry[arry.length] = value;
	}
	function getCount(key){
		var arry = this.mp[key];
		if ( !arry )	return 0;
		return arry.length;
	}
	function toString(){
		var str = '';
		for ( var i=0 ; i < this.keyArry.length ; i++ ){
			var key	= this.keyArry[i];
			var arr	= this.mp[key];
			str += key + ' = ' + this.mp[key].length + '[';
			for ( var j=0 ; j < arr.length ; j++ ){
				str += ( ( j != 0 ) ? ',' : '') + this.mp[key][j];
			}
			str += ']\n';
		}
		return str;
	}
	function getArray(key){
		return this.mp[key];
	}
	function clear(){
		this.mp=new Object();
		this.keyArry=new Array();
	}
	function getParam(urlEncoding){
		if(urlEncoding == null || urlEncoding == undefined ||  urlEncoding == true){
			urlEncoding = true;
		}else{
			urlEncoding = false;
		}

		var str = '';
		var pCnt = 0;

		if(urlEncoding){
			for( var i=0 ; i < this.keyArry.length ; i++ ){
				var key	= this.keyArry[i];
				var arr	= this.mp[key];
				for ( var j=0 ; j < arr.length ; j++ ){
					str += ( ( pCnt != 0 ) ? '&' : '') + key + '=' + escape_url( '' + this.mp[key][j]);
					pCnt++;
				}
			}
		}else{
			for( var i=0 ; i < this.keyArry.length ; i++ ){
				var key	= this.keyArry[i];
				var arr	= this.mp[key];
				for ( var j=0 ; j < arr.length ; j++ ){
					str += ( ( pCnt != 0 ) ? '&' : '') + key + '=' + this.mp[key][j];
					pCnt++;
				}
			}
		}
		return str;
	}
	
	function delRow(idx){
		for ( var i=0 ; i < this.keyArry.length ; i++ )
		{
			var key	= this.keyArry[i];
			var arry = this.mp[key];
			if(!arry) continue;
			arry.splice( idx, 1 );
		}
	}
	function toXML(){
		var cnt = this.count();
		var buff = "<dataset><result>";

		for ( var i=0 ; i <cnt ; i++ ){
			buff += "<data-block>";

			for ( var j=0 ; j < this.keyArry.length ; j++ ){
				var key	= this.keyArry[j];
				var arr	= this.mp[key];

				buff += "<" + key + ">";

				var tmp = arr[i];
				if(tmp == null || tmp == undefined) tmp = "";

				buff += tmp;
				buff += "</" + key + ">";
			}

			buff += "</data-block>";
		}

		buff += "</result></dataset>";
		return buff;
	}
	function count(){
		var max = 0;
		for ( var i=0 ; i < this.keyArry.length ; i++ )
		{
			var key	= this.keyArry[i];
			var len	= this.mp[key].length;

			if(max < len) max = len;
		}

		return max;
	}

	//DataSet의 key에 value와 동일한 값이 있는지 체크
	function containsKey(key, value){
		var rtn = false;

		var len = this.mp[key].length;

		for(var i=0; i < len; i++){
			if(value == this.mp[key][i]){
				rtn = true;
				break;
			}
		}
		return rtn;
	}
	//DataSet의 모든 값 중에 value와 동일한 값이 있는지 체크
	function contains(value){
		var rtn = false;
		for( var i=0; i < this.keyArry.length ; i++){
			var key = this.keyArry[i];
			var len = this.mp[key].length;

			for(var j=0; j < len; j++){
				if(value == this.mp[key][j]){
					rtn = true;
					break;
				}
			}

			if(rtn){
				break;
			}
		}

		return rtn;
	}
	function clone(){
		var ds = new DataSet();

		for( var i=0; i < this.keyArry.length ; i++){
			var key = this.keyArry[i];
			var len = this.mp[key].length;

			for(var j=0; j < len; j++){
				ds.add(key, this.mp[key][j]);
			}
		}
		
		return ds;
	}


	this.put		= put;
	this.add		= add;
	this.get		= get;
	this.getCount	= getCount;
	this.getParam	= getParam;
	this.toString	= toString;
	this.clear		= clear;
	this.getArray	= getArray;
	this.toXML		= toXML;
	this.count		= count;
	this.delRow		= delRow;
	this.containsKey	= containsKey;
	this.contains	= contains;
	this.clone 		= clone;
}//DataSet




function setCookie( name, value, expiredays ){
	var todayDate = new Date();
	if(expiredays===undefined||expiredays==null){
		expiredays = 7;
	}
	todayDate.setDate( todayDate.getDate() + expiredays );
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";";
}

function getCookie( name ){
	var nameOfCookie = name + "=";
	var x = 0;
	while ( x <= document.cookie.length ){
		var y = (x+nameOfCookie.length);
		if ( document.cookie.substring( x, y ) == nameOfCookie ) {
			if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
				endOfCookie = document.cookie.length;
			return unescape( document.cookie.substring( y, endOfCookie ) );
		}
		x = document.cookie.indexOf( " ", x ) + 1;
		if ( x == 0 )
			break;
	}
	return "";
}
