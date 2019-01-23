var Places = function PlacesAddr(type, city) {
    var resultlat = '';
    var resultlng = '';
    $.ajax({
        async: false,
        dataType: "json",
        url: 'https://maps.googleapis.com/maps/api/place/textsearch/json?query=' + type + ' in ' + city + '&key=AIzaSyBg7cdLpuG1qIkXTSXInxy11-KudH-G8Bk',
        success: function (data) {
            for (var key in data.results) {
                resultlat = data.results[key].geometry.location.lat;
                resultlng = data.results[key].geometry.location.lng;
            }
        }
    });
    return {lat: resultlat, lng: resultlng}
};

var Mapss = function geoadres(adres, city) {
    var resultlat = '';
    var resultlng = '';
    $.ajax({
        async: false,
        dataType: "json",
        url: 'https://maps.googleapis.com/maps/api/geocode/json?address=' + adres + ',' + city + '&key=AIzaSyBg7cdLpuG1qIkXTSXInxy11-KudH-G8Bk',
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
let nameSights = document.getElementById('nameSights').innerText;
let nameCity = document.getElementById('nameCity').innerText;

function initMap() {
    map = new google.maps.Map(document.getElementById('mapComment'), {
        center: {lat: Mapss(nameSights, nameCity).lat, lng: Mapss(nameSights, nameCity).lng},
        zoom: 20,
        gestureHandling: 'greedy'
    });
    var marker = new google.maps.Marker({
        position: {lat: Mapss(nameSights, nameCity).lat, lng: Mapss(nameSights, nameCity).lng},
        map: map
    });
}



document.getElementById("com").addEventListener("mouseover", mouseover);
document.getElementById("closeComment").addEventListener("click", click);

document.getElementById("comText").style.opacity = "0";
document.getElementById("comArea").style.opacity = "0";

function mouseover() {
    document.getElementById("comText").style.transition = "1.5s";
    document.getElementById("comText").style.opacity = "1";
    document.getElementById("comArea").style.transition = "1.5s";
    document.getElementById("comArea").style.opacity = "1";
    document.getElementById("com").style.height = "500px";
    document.getElementById("com").style.width = "260px";
    document.getElementById("mapComment").style.transition = "0.5s";
    document.getElementById("mapComment").style.width = "75%";
    document.getElementById("mapComment").style.left = "25%";
    document.getElementById("com").style.transition = "0.5s";
    document.getElementById("com").style.backgroundImage = "";
}

function click() {
    document.getElementById("comText").style.opacity = "0";
    document.getElementById("comText").style.transition = "0.2s";
    document.getElementById("comArea").style.opacity = "0";
    document.getElementById("comArea").style.transition = "0.2s";
    document.getElementById("mapComment").style.transition="0.5s";
    document.getElementById("mapComment").style.width="97%";
    document.getElementById("mapComment").style.left="3%";
    document.getElementById("com").style.position="absolute";
    document.getElementById("com").style.width="30px";
    document.getElementById("com").style.transition = "0.5s";

    // let countries;
    // fetch("https://restcountries.eu/rest/v2/all")
    //     .then(res => res.json())
    //     .then(data=> countryProperties(data))
    //     .catch(err=> console.log(err));
    //
    // function countryProperties(countriesData){
    //     countries = countriesData;
    //     for (let i=0; i<countries.length;i++){
    //             console.log('fg');
    //             let nameCountry = countries[i].name;
    //             let dateOfCreation ='01.10.1990';
    //             let politicalSystem ='TOTALITAR';
    //             let capital = countries[i].capital;
    //             let continent = countries[i].region;
    //             let history = "zbs city";
    //             let population = countries[i].population;
    //             let formdata = new FormData;
    //             formdata.append('nameCity' ,capital,);
    //             formdata.append('dateOfCreation' ,dateOfCreation);
    //             formdata.append('population' ,population);
    //             formdata.append('history' ,history);
    //             formdata.append('nameCountry' ,nameCountry);
    //        console.log(formdata)
    //                 $.ajax({
    //                     url: "/parseAjax",
    //                     type: "POST",
    //                     data: formdata,
    //                     processData: false,
    //                     contentType: false,
    //                     cache: false,
    //                     success: function () {
    //                         console.log('ajax saved Country');
    //                        // alert('You created new country!');
    //                     },
    //                     error: function (err) {
    //                         console.log(err);
    //                         alert('Country already exist')
    //                     }
    //                 });
    // }
}