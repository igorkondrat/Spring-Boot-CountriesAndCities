var Mapss = function geoadres(adres) {
    var resultlat = '';
    var resultlng = '';
    $.ajax({
        async: false,
        dataType: "json",
        url: 'https://maps.googleapis.com/maps/api/geocode/json?address=' + adres + '&key=AIzaSyBg7cdLpuG1qIkXTSXInxy11-KudH-G8Bk',
        success: function (data) {
            for (var key in data.results) {
                resultlat = data.results[key].geometry.location.lat;
                resultlng = data.results[key].geometry.location.lng;
            }
        }
    });
    return {lat: resultlat, lng: resultlng}
};
let map;
let NameCountry = document.getElementById('NameCountry').innerText;
console.log(NameCountry);

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: Mapss(NameCountry).lat, lng: Mapss(NameCountry).lng},
        zoom: 5,
        gestureHandling: 'greedy'
    });
    var marker = new google.maps.Marker({
        position: {lat: Mapss(NameCountry).lat, lng: Mapss(NameCountry).lng},
        map: map
    });
}