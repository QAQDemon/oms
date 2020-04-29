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
							paperscans:paperscanFulllist
							
						},
						methods:{
							
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

// console.log(typeof(paperscanFullList))
// console.log(paperscanFullList)



// var bodyleft = new Vue({
// 	el:"#bodyleft",
// 	data:{
		
// 		paperscans:paperscanFullList
		
// 	},
// 	methods:{
		
// 	}
// })