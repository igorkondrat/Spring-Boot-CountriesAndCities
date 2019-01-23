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

var MapssSightUniversity = function geoadres(adres, city) {
    var resultlat = '';
    var resultlng = '';
    $.ajax({
        async: false,
        dataType: "json",
        url: 'https://maps.googleapis.com/maps/api/geocode/json?address=' + adres + ', ' + city + '&key=AIzaSyBg7cdLpuG1qIkXTSXInxy11-KudH-G8Bk',
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
let nameSight = document.getElementsByName('nameSight');
let nameUniversity = document.getElementsByName('nameUniversity');
console.log(nameCity);

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: Mapss(nameCity).lat, lng: Mapss(nameCity).lng},
        zoom: 13,
        gestureHandling: 'greedy'
    });
    var image = {
        url: 'http://fxprotect.com/wp-content/uploads/2017/03/marker-blue.png',
        size: new google.maps.Size(20, 32),
        origin: new google.maps.Point(0, 0),
        anchor: new google.maps.Point(0, 32)
    };
    for (let i = 0; i < nameSight.length; i++) {
        var marker = new google.maps.Marker({
            map: map,
            draggable: true,
            animation: google.maps.Animation.DROP,
            position: {
                lat: MapssSightUniversity(nameSight.item(i).innerText, nameCity).lat,
                lng: MapssSightUniversity(nameSight.item(i).innerText, nameCity).lng
            },
            icon: image
        });
    }
    for (let i = 0; i < nameUniversity.length; i++) {
        var marker = new google.maps.Marker({
            map: map,
            draggable: true,
            animation: google.maps.Animation.DROP,
            position: {
                lat: MapssSightUniversity(nameUniversity.item(i).innerText, nameCity).lat,
                lng: MapssSightUniversity(nameUniversity.item(i).innerText, nameCity).lng
            },
        });
    }
}