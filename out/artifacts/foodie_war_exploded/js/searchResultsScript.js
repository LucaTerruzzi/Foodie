function loadMap(query) {
    $('#map-frame').attr('src', 'https://www.google.com/maps/embed/v1/place?key=AIzaSyDubqJqJa5bSjOdkRLrgVOy5lkioEK_Okk&q=' + encodeURI(query));
    $('#map-modal').show();
}

$(function () {
    $('#map-modal').click(function () {
        $(this).hide();
    })
});