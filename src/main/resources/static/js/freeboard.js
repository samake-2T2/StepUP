$(function() {
    $('.list_table_wrap tbody .on_data').on('click',function(){
        if($(this).hasClass('active')){
            $(this).removeClass('active');
            $(this).next().css('display','none');
        }else{
            $(this).addClass('active');
            $(this).next().css('display','table-row');
        }
    });



    $('.coment_box .coment_info .w_info .re-coment').on('click',function(){
		console.log('click')
        if($(this).parent().parent().find(".r_contents").hasClass('active')){
           $(this).parent().parent().find(".r_contents").removeClass('active');
           $(this).html('수정');
           $(this).css('color','white');
        }else{
            $(this).parent().parent().find(".r_contents").addClass('active');
            $(this).css('color','white');
        	$(this).html('닫기');
        }
    });


	$('.search_btn').on('click',function(){
		let search_value = $('.search_value').val();
		location.href = '/board/freeboard_main?searchKeyword='+search_value
		
	})



    
});