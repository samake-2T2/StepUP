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





    $('.coment_box .coment_info .w_contents .re-coment').on('click',function(){

        if($(this).parent().next().hasClass('active')){
           $(this).parent().next().removeClass('active');
        }else{
            $(this).parent().next().addClass('active');
        }
    });






    
});