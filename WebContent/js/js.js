

function deleteLoginUser(id){
	
	if(confirm("È·¶¨ÒªÉ¾³ýÂð£¿")){
		
		location.href="adminUserAction!deleteLoginUser?id="+id;
				
	}
}


function searchInformation(title,str){
			
		location.href=str+title;

}