$(function () {
    var err = false;

    $('#pwd').change(function(){
        var result = zxcvbn($(this).val());
        if(result.score < 2){
            $(this).removeClass("fd-warning-input").removeClass("fd-valid-input").addClass("fd-invalid-input");
            $('#pwd-error').show();
            err = true;
        }else if(result.score < 4){
            $(this).removeClass("fd-invalid-input").removeClass("fd-valid-input").addClass("fd-warning-input");
            $('#pwd-error').hide();
        }else{
            $(this).removeClass("fd-invalid-input").removeClass("fd-warning-input").addClass("fd-valid-input");
            $('#pwd-error').hide();
        }
        $('#pwd-rep').change();
    });

    $('#pwd-rep').change(function(){
        if($(this).val() == '' || $(this).val() != $('#pwd').val()){
            $(this).removeClass("fd-valid-input").addClass("fd-invalid-input");
            $('#pwd-rep-error').show();
            err = true;
        }else{
            $(this).removeClass("fd-invalid-input").addClass("fd-valid-input");
            $('#pwd-rep-error').hide();
        }
    });

    $('#change-form').submit(function(){
        err = false;
        $(this).children().change();
        if(err){
            $('.fd-invalid-input').first().focus();
            return false;
        }
    });
});