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








    
});