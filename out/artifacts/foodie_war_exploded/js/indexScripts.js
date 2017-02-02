/**
 * Created by Ricky on 28/01/2017.
 */
$(function(){
    /*var cuisine = new Bloodhound({
     datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
     queryTokenizer: Bloodhound.tokenizers.whitespace,
     remote: {
     url: 'service/autocomplete/cuisine/%QUERY',
     wildcard: '%QUERY'
     }
     });*/

    $(window).bind("pageshow", function() {
        $('#search #type').val(0);
        $('#search #order').val(2);
    });

    var places = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        remote: {
            url: 'service/autocomplete/places/%QUERY',
            wildcard: '%QUERY'
        }
    });

    var restaurants = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        remote: {
            url: 'service/autocomplete/restaurants/%QUERY',
            wildcard: '%QUERY'
        }
    });

    $("#search .typeahead").typeahead({
        hint: true,
        highlight: true,
        minLength: 3
    }/*,{
     name: 'couisine',
     source: cuisine,
     display : 'value',
     limit: 100,
     templates: {
     header: '<h4 class="header"><span class="glyphicon glyphicon-record"></span>Cuisine</h4>'
     }
     }*/,{
        name: 'places',
        source: places,
        display : 'value',
        limit: 100,
        templates: {
            header: '<h4 class="fd-suggestion-indented"><i class="fa fa-map-marker"></i> Places</h4>',
            suggestion: Handlebars.compile('<div>{{value}}, {{spec}}</div>')

        }
    },{
        name: 'restaurants',
        source: restaurants,
        display : 'value',
        limit: 100,
        templates: {
            header: '<h4 class="fd-suggestion-indented"><i class="fa fa-cutlery"></i> Restaurants</h4>',
            suggestion: Handlebars.compile('<div>{{value}}, {{spec}}</div>')

        }
    });

    $('#search .typeahead').on('typeahead:selected', function(e, datum) {
        console.log(datum);
        console.log('selected');
        switch (datum.id){
            case -2:
                $('#search #type').val(2);
                $('#search #order').val(1);
                $('#search #field').val(datum.value);
                $('#search').submit();
                break;
            case -1:
                $('#search #type').val(1);
                $('#search #order').val(1);
                $('#search #field').val(datum.value);
                $('#search').submit();
                break;
            default:
                $('<form>').attr({
                    method: 'POST',
                    action: 'RetrieveRestaurant'
                }).append($('<input>').attr({
                    type: 'hidden',
                    name: 'id',
                    value: datum.id
                })).submit();

        }
    });

});