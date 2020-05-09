var paperscanFullList
var bodyleft
// alert(bodyleft)
// alert(1);
//juery功能
$(function(){
	 // alert(1)
	// alert(1);
	// $("tr:even").css("background-color", "hsl(30,100%, 70%)");
	$("li.answeritem").click(function(){
	    // $("[class='dropdown classitem']").removeClass("open")
		$("li.answeritem").removeClass("active")
		$(this).addClass("active")
		// $(this).parent().parent().addClass("open")
		// $("[class='dropdown classitem']").addClass("open")
		// $("li").addClass("active")
		var classId = $(this).parent().parent().attr("value")
		var answerId = $(this).attr("value")
		$.ajax({
			type:"get",
			url:"getscore",
			async:true,
			data:{"classId":classId,"answerId":answerId},
			dataType:"json",
			success:function(paperscanFulllist){
				// console.log(typeof(paperscanFull))
				console.log(paperscanFulllist)
				// paperscanFullList = paperscanFulllist
				if(bodyleft == undefined){
					bodyleft = new Vue({
						el:"#bodyleft",
						data:{						
							paperscans:paperscanFulllist,
							searchword:''
						},
						methods:{
							search(searchword){
								var newlist1 = []
								this.paperscans.forEach(item => {
									if(item.studentName.indexOf(searchword) != -1){
										newlist1.push(item)
									}else if(String(item.studentId).indexOf(searchword) != -1){
										newlist1.push(item)
									}
								})
								return newlist1
							}
						}
					})
				}else{
					bodyleft.paperscans = paperscanFulllist
				}
			}
		})
	})
	
	$("li.answeritem:first").trigger("click")
    // console.log(typeof(paperscanFullList))
    // console.log(paperscanFullList)
})

// $(function(){
// 	$("#tableright").click(function(){
// 		var centerwidth = $("#bodycenter").css("width")
// 		var centerwidthnum = (Number)(centerwidth.substring(0,centerwidth.length-2))
// 		var rightwidth = $("#bodyright").css("width")
// 		var rightwidthnum = (Number)(rightwidth.substring(0,rightwidth.length-2))
// 		if(centerwidthnum < 140 && rightwidthnum > 140){
// 			// $("#bodyleft").css("width","80%")
// 			// $("#bodyright").css("width","10%")
// 			// $("#bodyleft").css("width","80%")
// 			// alert(1)
// 			// $("#bodyleft").style.width = "80%"
// 			document.getElementById("bodyright").style.width = "10%"
// 			document.getElementById("bodyleft").style.width = "80%"
// 		}
// 		// $("#bodycenter").css("width","10%")
// 		// // $("#bodyright").css("width","10%")
// 		// $("#bodyleft").css("width","80%")
// 		// $("#bodyright").css("width","10%")
// 		// $("#bodyleft").css("width","60%")
// 		// alert(1)
// 		var width = $("#mainright").css("width")
// 		var num = (Number)(width.substring(0,width.length-2))
// 		// alert(typeof(num))
// 		// alert(width.substring(0,width.length-2))
// 		// alert(centerwidth)
// 		// alert(typeof(centerwidth))
// 		// document.getElementById("#bodycenter").style.width = 10%
// 		// alert(1)
// 		document.getElementById("bodycenter").style.width = "10%"
// 		document.getElementById("bodyleft").style.width = "70%"
// 		// $("#bodycenter").css("width","10%")
// 		// $("#bodyleft").css("width","70%")
// 		// $(this).child().removeClass("glyphicon-step-forward")
// 		// $(this).child().addClass("glyphicon-step-backward")
// 		// $("#tablerightspan").removeClass("glyphicon-step-forward")
// 		// $("#tablerightspan").addClass("glyphicon-step-backward")
// 		// $(this).removeClass("slideright")
// 		// $(this).addClass("slideleft")
// 	})
// 	$("#chartright").click(function(){
// 		$("#bodyright").css("width","10%");
		
// 	})
// })
// div.sty
// div.style.backgroundColor = "red"
// document.getElementById("bodyleft").style.width = "70%"

var myChart = echarts.init(document.getElementById('scorebarchart'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'ECharts 入门示例'
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option)