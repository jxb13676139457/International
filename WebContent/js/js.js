
function deleteLoginUser(id){
	
	if(confirm("ȷ��Ҫɾ����")){
		
		location.href="adminUserAction!deleteLoginUser?id="+id;
				
	}
}


function searchInformation(title,str){
			
		location.href=str+title;

}