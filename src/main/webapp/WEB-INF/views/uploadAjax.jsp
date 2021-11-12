<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>uploadAjax</title>
	
	<style type="text/css">
	
		.uploadResult{
         background-color: yellow;
      }
      
      .uploadResult ul{
         display: flex;
         flex-flow: row;
         justify-content: center;
         align-items: center;
      }
      
      .uploadResult ul li{
         list-style: none;
      }
      
      .uploadResult ul li img{
         width: 100px;
      }
		
		
	</style>
	
</head>
<body>

	<div class="uploadDIV">
		<input type="file" name="uploadFile" multiple>
	</div>
	
	<div class="uploadResult">
		<ul>
			
		</ul>
	</div>
	
	<button id="uploadBtn">Ajax로 업로드하기</button>
	
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	
<!-- <script>

	

	$(function() {
		
		 var cloneObj = $(".uploadDIV").clone();

		
		$("#uploadBtn").on("click", function(e) {
		
			var formData = new FormData();	
			var inputFile = $("input[name='uploadFile']");
			var files = inputFile[0].files;
			console.log(files);
			
			for(var i = 0; i < files.length; i++){
				formData.append("uploadFile", files[i])
			}
			
			$.ajax({
			
				url:'/uploadAjaxAction',
				processData : false,
				contentType : false,
				data : formData,
				type : 'POST',
				/* result 대신 data적어도됨 */
				dataType : 'json',
				success : function(result) { 
					
					console.log(result);
					
					showUploadedFile(result);
					
					$(".uploadDIV").html((cloneObj.html());
				}
				
			})
			
		})
				
		var uploadResult = $(".uploadResult  ul");
		
		function showUploadedFile(uploadedResultArr) {
			
			var str = "";
			
			$(uploadedResultArr).each(function (i, obj) {
				
				str += "<li>" + obj.fileName + "</li>"
				
			})
			
			uploadResult.append(str);
		}
		
		
	})

</script>	 -->
<script>
$(document).ready(function() {
      
      
      var cloneObj = $(".uploadDiv").clone();
      
      $("#uploadBtn").on("click", function(e) {
         
         var formData = new FormData();
         var inputFile = $("input[name='uploadFile']");
         var files = inputFile[0].files;
         console.log(files);
         
         for(var i = 0; i <files.length; i++){
            formData.append("uploadFile", files[i])
         }
         /* result 대신 data적어도됨 */
         $.ajax({
            
            url:'/uploadAjaxAction',
            processData : false,
            contentType : false,
            data : formData,
            type : 'POST',
            dataType: 'json',
            success : function(result) {
               alert("파일업로드 성공")
               console.log(result);
               
               showUploadedFile(result);
               
               $(".uploadDiv").html(cloneObj.html());
            }
            
            
         })
         
         
      })
      
      var uploadResult = $(".uploadResult ul");
      
      function showUploadedFile(uploadedResultArr) {
         
         var str = "";
         
         $(uploadedResultArr).each(function(i, obj) {
            
        	 if(!obj.image){
                 str += "<li><img src = '/resources/image/test.png'>" 
                    + obj.fileName + "</li>"
              
            }else{
            	/* str += "<li>" + obj.fileName + "</li>";	 */
            	
            	var fileCallPath = encodeURIComponent(
            		obj.uploadPath + "/t_" + obj.uuid + "_" + obj.fileName
            		
            	);
            
            	str += "<li><img src = '/display?fileName="+fileCallPath+"'></li>"
            			
            	
            }
             
         })
         
         uploadResult.append(str);
         
      }
      
      
      
   })
</script>	
</body>
</html>
