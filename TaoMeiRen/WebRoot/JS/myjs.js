function selectType(){
   	 var value=document.getElementById("order_type").value;
   	 document.getElementById("order_detail_type").value= value;
     var value=document.getElementById("stock_type").value;
     document.getElementById("commodity_detail_type").value= value;
}
function autoSubmitSelect(){
	var form = document.getElementById("refresh_data");
	selectType();
	form.submit();
}

function changeType(order_value,commodity_value){
	document.getElementById("order_type").options[order_value-1].selected =true;
	document.getElementById("stock_type").options[commodity_value-1].selected = true;
}

function hideDiv(size){
	for(var i = 0;i < 16;i ++){
		var tmp = i + 1;
		var id = "c"+tmp;
		if(i < size){	
			document.getElementById(id).style.display = "";
		}
		else{
			document.getElementById(id).style.display = "none";
		}
		
	}
}

function alarm(msg){
	if(msg == "")
		return;
	else
		alert(msg);
}

function buy(login){
	if(login == 'false')
		alert('先登录');		
}
function btn_color(page){
	bt = document.getElementById("button"+page);
	bt.setAttribute("style","background: transparent;color: red;");
}
function create_button(page){
    var bt=document.createElement("input");
    bt.type="button";
    bt.id="button"+page;
    bt.value=page;
	bt.setAttribute("style","background: transparent;color: blue;");
    bt.style.cssText="background:transparent;color:blue;";
    bt.onclick= function(){
    	turn(bt.value);
    	btn_color(bt.value);
    };
    document.body.appendChild(bt);
}

function disable_bt(id){
    var bt=document.getElementById(id); 
    bt.disabled="true";
    bt.setAttribute("style","color: grey;");
}

function enable_bt(id){
	var bt=document.getElementById(id); 
    bt.disabled="";
    bt.setAttribute("style","color: white;");
}
function set_btn(value){
	if(value <= 0){
		enable_bt("buy_btn");
		disable_bt("book_btn");
	}
	else{
		enable_bt("book_btn");
		disable_bt("buy_btn");
	}
}
function remove_bt(id){
    var bt=document.getElementById(id);    
    document.body.removeChild(bt);
}
function turnpage(total){
	if(total == 0)
		return;
	for(var i=0;i<total/16;i++)
	{
		create_button(i+1);
	}
}
 function turn(page){
    document.getElementById("page").value = page;
    document.getElementById("turn_page").submit();
}

function collection_book(){
	var form = document.getElementById("trade_form");
	form.action = "BookCollectionServlet";
	form.submit();
}

function commentPoint(){
	var value = document.getElementById("point").value;
	document.getElementById("personal_point").value = value;
}

function indivual(){
	document.getElementById("individual_link").submit();
}
function addLoadEvent(func){
	var oldload = window.onload;
	if(typeof window.onload != 'function'){
		window.onload = func;
	}
	else{
		window.onload = function(){
			oldload();
			func();
		}
	}
}
