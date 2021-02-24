
(function ($) {
    "use strict";


    /*==================================================================
    [ Focus input ]*/
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })
  
  
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('name') == 'admin_jik') {
            if($(input).val().trim() != '사원' && $(input).val().trim() != '대리' && $(input).val().trim() != '과장' && $(input).val().trim() != '부장'){
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }    
    
   /*===========id check==========*/    
$('.btn-id-check').on('click', function(){
	$("#errMsg_adminid").empty();
        $.ajax({
		     type:"POST",
		     url:"checkSignupAdminId",
		     data:{
		            "admin_id":$('#admin_id').val()
		     },
		     success:function(data){	
		            if($.trim(data)=="YES"){
		               if($('#admin_id').val()!=''){ 
		            	   $("#errMsg_adminid").show();
		       				$("#errMsg_adminid").text("사용가능한 아이디 입니다");
		       				
		               }
		           	}else{
		               if($('#admin_id').val()!=''){
		            	   $("#errMsg_adminid").show();
		       				$("#errMsg_adminid").text("중복된 아이디 입니다");
		       				
		               }
		            }
		         }
		    })
    });

    /*==================================================================
    [ Show pass ]*/
    var showPass = 0;
    $('.btn-show-pass').on('click', function(){
        if(showPass == 0) {
            $(this).next('input').attr('type','text');
            $(this).find('i').removeClass('zmdi-eye');
            $(this).find('i').addClass('zmdi-eye-off');
            showPass = 1;
        }
        else {
            $(this).next('input').attr('type','password');
            $(this).find('i').addClass('zmdi-eye');
            $(this).find('i').removeClass('zmdi-eye-off');
            showPass = 0;
        }
        
    });


})(jQuery);