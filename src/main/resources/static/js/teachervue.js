var mainleft = new Vue({
	el:"#mainleft",
	data:{
		classes:classlist,
		years:teachyears,
		searchword:'',
		selected:''
	},
	methods:{
		search(selected, searchword){
			var newlist1 = []
			var newlist2 = []
			this.classes.forEach(item => {
				if(String(item.teachYear).indexOf(selected) != -1){
					newlist1.push(item)
				}
			})
			newlist1.forEach(item => {
				if(item.className.indexOf(searchword) != -1){
					newlist2.push(item)
				}
			})
			return newlist2
		}
	}
})

// var bodyleft = new Vue({
// 	el:"#bodyleft",
// 	data:{
// 		paperscan,
		
// 	},
// 	methods:{
		
// 	}
// })

