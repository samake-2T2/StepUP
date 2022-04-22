/* 카테고리 */
function getCategory_List(e) {
   e.preventDefault(); //고유이벤트중지
   if( e.target.tagName != 'A') return; //태그검증
   var obj = $(e.target).data("set"); //데이터셋을 가져옴 

   //토글색처리
   $(e.currentTarget).find("a").removeClass("sub_menu_select"); 
   $(e.target).addClass("sub_menu_select"); 
   //태그처리
   if(obj.category_lv == 1 || obj.category_lv == 2) {
      console.log('1lv');
      $().loading(); //로딩
      $(e.currentTarget).category_remove(); //이전 카테고리삭제
      //////////////////////////////////////////////////
      //비동기콜백에서 category_create() 호출
      //비동기호출후 category_set() 호출 
      //console.log(obj);
      $.ajax({
         type: "get",
         url: "../getCategoryChild/" + obj.category_group_id + "/" + obj.category_lv + "/" + obj.category_detail_lv,
         dataType: "json", 
         //data:,
         //contentType:,
         success:function(data){
	
			category_create(data); //응답 받은 데이터를 카테고리 생성함수에 전달
            
         },
         error: function(error, status){
            alert("카테고리 조회 실패. 관리자에 문의하세요.");
         }
      })
   }
   
   $(e.target).category_set();


   
}
//카테고리세팅
$.fn.category_set = function() {
	var category_id = this.data("set").category_id;
	var group_id = this.data("set").category_group_id;
	$("??").val(category_group_id + category_id ); //name이 prod_category인 곳에 추가
}
//이전카테고리 삭제JS
$.fn.category_remove = function() {
	while(this.next().length != 0) {
		$(this).next().remove();
	}
}
//다음카테고리 생성JS
function category_create(data) {

	//예시데이터
/*	var data = [
	 {category_lv: 2, group_id: 'B', category_detail_nm: '값선택', category_detail_parent_nm: '값선택'},
	 {category_lv: 2, group_id: 'B', category_detail_nm: '값선택', category_detail_parent_nm: '값선택'},
	 {category_lv: 2, group_id: 'B', category_detail_nm: '값선택', category_detail_parent_nm: '값선택'}
  ];
*/
	 var category02 = "";
     data.forEach(function(result, index) {
                	  
     category02 += '<option data-set=' + JSON.stringify(result) + '>' + result.category_detail_nm + '</option>';
     })
                  
     console.log(category02);
                  
     $(".minor_option").append(category02);
}
