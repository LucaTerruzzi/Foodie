$(function () {
    var err = false;

    $('#title').change(function(){
        if($(this).val().length < 3 || $(this).val().length > 63){
            $(this).removeClass("fd-valid-input").addClass("fd-invalid-input");
            $('#title-error').show();
            err = true;
        }else{
            $(this).removeClass("fd-invalid-input").addClass("fd-valid-input");
            $('#title-error').hide();
        }
    });

    $('#desc').change(function(){
        if($(this).val().length < 16 || $(this).val().length > 1023){
            $(this).removeClass("fd-valid-input").addClass("fd-invalid-input");
            $('#desc-error').show();
            err = true;
        }else{
            $(this).removeClass("fd-invalid-input").addClass("fd-valid-input");
            $('#desc-error').hide();
        }
    });

    $('input:radio[name=rating]').change(function(){
        if(!$('input:radio[name=rating]:checked').length){
            $(this).parent().removeClass("fd-valid-radio").addClass("fd-invalid-radio");
            err = true;
        }else{
            $(this).parent().removeClass("fd-invalid-radio").addClass("fd-valid-radio");
        }
    });

    $('#review-form').submit(function(){
        err = false;
        $(this).children().change();
        $('input:radio[name=rating]').change();
        if(err){
            $('.fd-invalid-input').first().focus();
            return false;
        }
    });
});