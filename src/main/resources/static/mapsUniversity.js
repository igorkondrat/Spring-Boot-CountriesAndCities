var MapssUniversity = function geoadres(adres, univercity) {
    var resultlat = '';
    var resultlng = '';
    $.ajax({
        async: false,
        dataType: "json",
        url: 'https://maps.googleapis.com/maps/api/geocode/json?address=' + adres + ',' + univercity + '&key=AIzaSyBg7cdLpuG1qIkXTSXInxy11-KudH-G8Bk',
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
let nameCity = document.getElementById('nameCity').innerText;
let nameUniversity = document.getElementById('nameUniversity').innerText;
console.log(nameCity);

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {
            lat: MapssUniversity(nameCity, nameUniversity).lat,
            lng: MapssUniversity(nameCity, nameUniversity).lng
        },
        zoom: 17,
        gestureHandling: 'greedy'
    });
    var marker = new google.maps.Marker({
        position: {
            lat: MapssUniversity(nameCity, nameUniversity).lat,
            lng: MapssUniversity(nameCity, nameUniversity).lng
        },
        map: map
    });
};