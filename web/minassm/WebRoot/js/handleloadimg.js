var leftw=$(window).width()*0.5,height=parseInt(leftw*(152/158));
var strcss='<style id="expectedcss" type="text/css">.ser_left,.ser_right{height:{height}px}</style>';
strcss=strcss.replace(/{height}/g,height);
$("body").append(strcss);
window.onload=function(){
	$("#expectedcss").remove();
}