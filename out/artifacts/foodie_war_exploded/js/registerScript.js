$(function () {
    var err = false;

    $('#name').change(function(){
        if($(this).val().length < 2 || $(this).val().length > 20){
            $(this).removeClass("fd-valid-input").addClass("fd-invalid-input");
            $('#name-error').show();
            err = true;
        }else{
            $(this).removeClass("fd-invalid-input").addClass("fd-valid-input");
            $('#name-error').hide();
        }
    });

    $('#surname').change(function(){
        if($(this).val().length < 2 || $(this).val().length > 20){
            $(this).removeClass("fd-valid-input").addClass("fd-invalid-input");
            $('#surname-error').show();
            err = true;
        }else{
            $(this).removeClass("fd-invalid-input").addClass("fd-valid-input");
            $('#surname-error').hide();
        }
    });

    $('#email').change(function(){
        var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
        if(!pattern.test($(this).val())){
            $(this).removeClass("fd-valid-input").addClass("fd-invalid-input");
            $('#email-error').show();
            err = true;
        }else{
            $(this).removeClass("fd-invalid-input").addClass("fd-valid-input");
            $('#email-error').hide();
        }
        $('#email-rep').change();
    });

    $('#email-rep').change(function(){
        if($(this).val() == '' || $(this).val() != $('#email').val()){
            $(this).removeClass("fd-valid-input").addClass("fd-invalid-input");
            $('#email-rep-error').show();
            err = true;
        }else{
            $(this).removeClass("fd-invalid-input").addClass("fd-valid-input");
            $('#email-rep-error').hide();
        }
    });

    $('#pwd').change(function(){
        var result = zxcvbn($(this).val());
        if(result.score < 2){
            $(this).removeClass("warning-input").removeClass("fd-valid-input").addClass("fd-invalid-input");
            $('#pwd-error').show();
            err = true;
        }else if(result.score < 4){
            $(this).removeClass("fd-invalid-input").removeClass("fd-valid-input").addClass("warning-input");
            $('#pwd-error').hide();
        }else{
            $(this).removeClass("fd-invalid-input").removeClass("warning-input").addClass("fd-valid-input");
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

    $('#terms').change(function(){
        if(!this.checked){
            $(this).removeClass("fd-valid-input").addClass("fd-invalid-input");
            $('label[for="'+ this.id +'"]').removeClass("valid-label").addClass("invalid-label");
            err = true;
        }else{
            $(this).removeClass("fd-invalid-input").addClass("fd-valid-input");
            $('label[for="'+ this.id +'"]').removeClass("invalid-label").addClass("valid-label");
        }
    });

    $('#register-form').submit(function(){
        err = false;
        $(this).children().change();
        if(err){
            $('.fd-invalid-input').first().focus();
            return false;
        }
    });
});