<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="User.UserDAO"
	import="User.UserVO"
	import="Ranking.Rank"


%>
<%@ page import="java.util.*"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>

<%--
	String method = request.getMethod();
 	if ("post".equals(method)){
 		out.println("can't access");
 	}
 	else {
 		int age = Integer.parseInt(request.getParameter("age"));
 		double height = Double.parseDouble(request.getParameter("height"));
 		boolean sex = Boolean.parseBoolean(request.getParameter("female"));
 		
 		
 		int isMale;
 		if (sex == false) isMale = 1;
 		else isMale = 2;
 		
 		UserVO vo = new UserVO(0, isMale, age, height, 1);
 		UserDAO dao = new UserDAO();
		
		double[] result = new double[2];
		result = Rank.Return_Rank(vo, dao);
 		
		
 		out.println("Your Rank is : " + (int)result[0]); --%>
 		<br>
<%--  	out.println("Your percnetile is : " +  (double)Math.round((result[1]*1000)/1000.000) + "%");
 	
 	}
--%> 	


<head>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
<%	


boolean cal = false;
String loading = "loading";
String sex = request.getParameter("sex_test");
String height = request.getParameter("height_test");
String age = request.getParameter("age_test");

int sex_use = Integer.parseInt(sex); 
double height_use = Double.parseDouble(height);
int age_use = Integer.parseInt(age);

String sex_text;
if(sex_use == 1) sex_text="male";
else if (sex_use == 2) sex_text="female";
else sex_text="nothing";

	
		
		

		
		UserVO vo = new UserVO(0, sex_use, age_use, height_use, 1);
		UserDAO dao = new UserDAO();
	
	double[] result = new double[3];
	//result = Rank.Return_Rank(vo, dao);
	
	cal = true;

	
	
	
	result = Rank.Return_Rank(vo, dao);
	
	int rank = (int)result[0];
	double perc= Double.parseDouble(Rank.cutDecimal(3,result[1]));
	int total = (int)result[2];


				
				// Norm : 2차원 배열 (키 : 빈도수)
				TreeMap<Integer, Integer> Norm = new TreeMap<>();
				
				int [] height_table = new int[20];
				int [] people_table = new int[20];

				
				int count = 0;
				
				Norm = Rank.Return_norm(vo, dao);
				
				for(Integer key : Norm.keySet()) {
					Integer value = Norm.get(key);
					
					height_table[count] = key;
					people_table[count] = value;
					count++;
				}
				%>

function showChart(){
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Height (cm)', 'People'],
          [<%=height_table[0]%>,  <%=people_table[0]%>],
          [<%=height_table[1]%>,  <%=people_table[1]%>],
          [<%=height_table[2]%>,  <%=people_table[2]%>],
          [<%=height_table[3]%>,  <%=people_table[3]%>],
          [<%=height_table[4]%>,  <%=people_table[4]%>],
          [<%=height_table[5]%>,  <%=people_table[5]%>],
          [<%=height_table[6]%>,  <%=people_table[6]%>],
          [<%=height_table[7]%>,  <%=people_table[7]%>],
          [<%=height_table[8]%>,  <%=people_table[8]%>],
          [<%=height_table[9]%>,  <%=people_table[9]%>],
          [<%=height_table[10]%>,  <%=people_table[10]%>],
          [<%=height_table[11]%>,  <%=people_table[11]%>],
          [<%=height_table[12]%>,  <%=people_table[12]%>],
          [<%=height_table[13]%>,  <%=people_table[13]%>],
          [<%=height_table[14]%>,  <%=people_table[14]%>],
          [<%=height_table[15]%>,  <%=people_table[15]%>],
          [<%=height_table[16]%>,  <%=people_table[16]%>],
          [<%=height_table[17]%>,  <%=people_table[17]%>],
          [<%=height_table[18]%>,  <%=people_table[18]%>],
          [<%=height_table[19]%>,  <%=people_table[19]%>]
        ]);

        var options = {
          title: 'Height Chart',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
}

function buttonChange(){
    var elem = document.getElementById("chartButton");
   
    if (elem.innerText=="show chart") {
    	elem.innerText = "cancel";
    	showChart();
        document.getElementById("curve_chart").style.display = "block";
        }
    else {
    	elem.innerText = "show chart";
        document.getElementById("curve_chart").style.display = "none";
        }
}

	


    </script>
</head>
			<script>
					
			</script>
			


			<body>
  			<%-- sex: <%=sex_text%><br>
  			height: <%=height_use%><br>
 			age: <%=age_use%><br> --%>
 			순위: <%=rank%> / 동나이 전체: <%=total%><br>
 			상위  <%=perc%>%<br>
 			
 			<div style="text-align: center ;">
 			<button id="chartButton" value="show chart" style="margin-top:20px; width: 100px; height: 30px; Background-color: blueviolet; color: white; border: none; border-radius: 7px; font-size: 15px;" onclick="buttonChange();">show chart</button>     
 			</div>
 			<div id="curve_chart" style="padding-top: 30px; padding-bottom: 53px; width: 900px; height: 500px; margin: auto;"></div>
 
 			
 			</body>
	
			<%

			%>

