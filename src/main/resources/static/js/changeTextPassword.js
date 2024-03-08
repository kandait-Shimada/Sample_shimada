/**
 *  paswordに関する記述
 * passwordがデフォルトでは確認できず、
 * 目のアイコンに触れると確認できる仕様
 */
function pushHideButton() {
	var txtPass = document.getElementById("textPassword");
	var btnEye = document.getElementById("buttonEye");
	if(txtPass.type == "text") {
		txtPass.type = "password";
		btnEye.className = "fa fa-eye"; 
	} else {
		txtPass.type="text";
		btnEye.className = "fa fa-eye-slash";
	}
}
