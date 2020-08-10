<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="Ranking.Rank"%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width">
        <title>Height Ranking</title>
        <link href="style.css" rel="stylesheet" type="text/css" />
        <style>
        

        .title{	
            font-size: 65px;
            text-align: center;
            padding-top: 50px;
        }

        .subtitle{
            font-size: 30px;
            text-align: center;
            padding-top: 10px;
            color: aliceblue;
        }

        .personal_input{
            display: flex;
            padding-top: 130px;
            font-size: 25px;
            margin:0 auto;;
        }

        .sex_select{
            padding-left: 450px;
            

        }
        

        .age_height{
            padding-left: 390px
        }

   

        





        </style>

    </head>
    <body style="background-color: #3a3a4b; color: aliceblue;">
       <div class="version" id="version">
           ver.alpha
       </div> 
      

       
           <div class="title">
               Height Ranking
           </div>
           <div class="subtitle">
               나의 키는 상위 몇 %일까?
           </div>
           <div class="personal_input">
            <div class="sex_select" id="sexSelect" >
                <input type="radio" id="male" name="gender" value="1">
                남자<br>
                <input type="radio" id="female" name="gender" value="2">
                여자
            </div>
            <div style="text-align: center;">
            <form id="info_form">
	     				<div style="margin-left:400px;">
	     					<label>나이 : &nbsp;</label><input name="age_real" id="age_real" style="height: 25px; width: 100px;">
	     				</div>
	     				<div style="margin-left:400px;">
	     					<label>키 &nbsp;&nbsp;&nbsp;: &nbsp;</label><input name="height_real" id="height_real" style="height: 25px; width: 100px;">
	     				</div>
	 				</form>
	 				</div>
	 				</div>
	 				<br>
	 				<div style="text-align: center ;">
	     					<button id="checkButton" value="check" style="Background-color: blueviolet; color: white; height: 55px; width: 80px; border: none; border-radius: 7px; font-size: 20px;"onclick="getInput(); buttonClick();">check</button>
	     				</div>           
            
            

         

           
                
                <script src="https://code.jquery.com/jquery-latest.min.js" type="application/javascript"></script>
		<script type="application/javascript">
			$(document).ready(function(){
    		$("#checkButton").click(function(){
    			document.getElementById("msg").style.innerHTML = "loading";
		        //getInput();
		        //buttonClick();
		        //showChart();
		    });
		  });
		
			
			
            var age;
            var height;
            var maleCheck;
            var femaleCheck;
            var sex;
            var i = 0;
            var version_text="ver.alpha 2020.08.09"
            
            
            
            $(document).ready(function(){
            	  $("#version").mouseover(function(){
            	    $("#version").text(version_text);
            	  });
            	  $("#version").mouseout(function(){
            	    $("#version").text("ver.alpha");
            	  });
            	});
            
        

            //var bar = document.getElementById("myBar");
            //var progress = document.getElementById("myProgress");
            

            /*function barDisplay(){
                var bar = document.getElementById("myBar");
                var progress = document.getElementById("myProgress");
                progress.style.width = "70%";
                progress.style.backgroundColor = "#ddd";
                bar.style.width = "1%";
                bar.style.backgroundColor = "blueviolet";
                bar.style.height = "30px";
                progress.style.display = "block";
                bar.style.display = "block";
            }

            function barHide(){
                var bar = document.getElementById("myBar");
                var progress = document.getElementById("myProgress");

                progress.style.display = "none";
                bar.style.display = "none";
            }

            function move() {
            if (i == 0) {
                i = 1;
                var elem = document.getElementById("myBar");
                var width = 1;
                var id = setInterval(frame, 10);
                function frame() {
                if (width >= 100) {
                    clearInterval(id);
                    i = 0;
                } else {
                    width++;
                    elem.style.width = width + "%";
                }
                }
            }
            }*/

            function buttonClick(){
                var elem = document.getElementById("checkButton");
               
                if (elem.innerText=="check") {
                	elem.innerText = "cancel";
                    document.getElementById("msg").innerHTML = "loading";
                    callAjax();
                    document.getElementById("msg").style.display = "block";
                    }
                else {
                	elem.innerText = "check";
                    //barHide();
                    //document.getElementById("demo").style.display = "none";
                    document.getElementById("msg").innerHTML = "loading";
                    document.getElementById("msg").style.display = "none";
                    }
            }

            function getInput(){
                age = document.getElementById("age_real").value;
                height = document.getElementById("height_real").value;
                maleCheck = document.getElementById("male").checked;
                femaleCheck = document.getElementById("female").checked;

                if(maleCheck == true){
                    sex = 1;
                }
                else if(femaleCheck == true){
                    sex = 2;
                }else{
                	sex = 0;
                }

            }
    
			
            

            </script>
          



            <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
            <script type="text/javascript">
          

            

            	
			
			
		  function callAjax(){
		      $.ajax({
			        type: "post",
			        url : "./calculate.jsp",
			        data: {
			        	sex_test : sex,
		     			height_test : $('#height_real').val(),
		     			age_test : $('#age_real').val(),
			        },
			        success: whenSuccess,
			        error: whenError
		   	});
		  }
		
		  function whenSuccess(resdata){
		      $("#msg").html(resdata);
		      console.log(resdata);
		  }
		  
		  function whenError(){
		      alert("Error");
		  }
		
		</script>
                   <!--div id="myProgress" style="height: 40px; width: 70%; background-color: #ddd; margin: auto;"></div-->
                <!--div id="myBar"></div-->
            
                


            <!--p id="demo" style="text-align: center;"></p-->
            <p id="msg" style="text-align: center; display: none; font-size: 20px; padding-top: 30px;">loading</p>

            
    </body>
</html>

