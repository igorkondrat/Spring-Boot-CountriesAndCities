console.log("JS work");


$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});


$("#saveButtonCountry").click(function (event) {
    event.preventDefault();
    let nameCountry = $("#nameCountry").val();
    let dateOfCreation = $("#dateOfCreation").val();
    let capital = $("#capital").val();
    let square = $("#square").val();
    let population = $("#population").val();
    let reg = /^[A-Za-z ]*$/;
    if (nameCountry != "" &&
        capital != "" &&
        square > 0 &&
        population > 0 &&
        reg.test(capital) &&
        reg.test(nameCountry)) {
        $.ajax({
            url: "/saveCountyAJAX",
            type: "POST",
            data: new FormData($("#formSaveCountry")[0]),
            processData: false,
            contentType: false,
            cache: false,
            success: function () {
                console.log('ajax saved Country');
                alert('You created new country!');
            },
            error: function (err) {
                console.log(err);
                alert('Country already exist')
            }
        });
    } else {
        alert('Something Wrong')
    }

});

$("#updateButtonCountry").click(function (event) {

    event.preventDefault();

    let id = $("#id").val();
    let nameCountry = $("#nameCountry").val();
    let dateOfCreation = $("#dateOfCreation").val();
    let politicalSystem = $("#politicalSystem").val();
    let continent = $("#continent").val();
    let capital = $("#capital").val();
    let square = $("#square").val();
    let population = $("#population").val();
    let reg = /^[A-Za-z ]*$/;
    if (nameCountry != "" &&
        capital != "" &&
        square > 0 &&
        population > 0 &&
        reg.test(capital) &&
        reg.test(nameCountry)) {
        $.ajax({
            url: "/updateCountyAJAX",
            type: "POST",
            data: JSON.stringify({
                id,
                nameCountry,
                dateOfCreation,
                politicalSystem,
                continent,
                capital,
                square,
                population
            }),
            processData: false,
            contentType: 'application/json',
            cache: false,
            success: function () {
                console.log('ajax updated Country');
                alert('You updated country!');
            },
            error: function (err) {
                console.log(err);
                alert('Country already exist')
            }
        });
    } else {
        alert('Something Wrong')
    }

});

$("#searchButtonCountry").click(function (event) {
    event.preventDefault();
    let continentSearch = $("#continent").val();
    let politicalSearch = $("#politicalSystem").val();
    let nameCountry = document.getElementsByName('nameCountry');
    let NameCountryToSearch = document.getElementsByName('NameCountryToSearch');
    $.ajax({
        url: "/searchButtonCountry/" + continentSearch + '&' + politicalSearch,
        type: "GET",
        data: null,
        processData: false,
        contentType: false,
        cache: false,
        success: function (byContinent) {
            console.log(byContinent)
            for (let i = 0; i < nameCountry.length; i++) {
                nameCountry[i].style.display = "none";
            }
            for (let i = 0; i < nameCountry.length; i++) {
                for (let j = 0; j < byContinent.length; j++) {
                    if (byContinent[j].nameCountry === NameCountryToSearch[i].textContent && byContinent.length > 0) {
                        nameCountry[i].style.display = "block";
                    }
                }
            }
            console.log('ajax search');
        },
        error: function (err) {
            console.log(err);
        }
    })
});

$("#nameCountrySearch").keyup(function () {
    let SearchCountry = $("#nameCountrySearch").val().toUpperCase();
    let BlockInputArray = document.getElementsByName("nameCountry");
    for (i = 0; i < BlockInputArray.length; i++) {
        let BlockInput = BlockInputArray[i];
        if (BlockInput) {
            if (BlockInput.innerHTML.toUpperCase().indexOf(SearchCountry) > -1) {
                BlockInput.style.display = ""
            } else {
                BlockInput.style.display = "none";
            }
        }
    }
});

$("#saveButtonCity").click(function (event) {
    event.preventDefault();
    let nameCity = $("#nameCity").val();
    let dateOfCreation = $("#dateOfCreation").val();
    let population = $("#population").val();
    let history = $("#history").val();
    let reg = /^[A-Za-z ]*$/;

    let nameCountry = $("#nameCountry").val();
    if (nameCity != "" &&
        population > 0 &&
        history != "" &&
        nameCountry != "" &&
        reg.test(nameCity) &&
        reg.test(nameCountry)) {
        $.ajax({
            url: "/saveCityAJAX",
            type: "POST",
            data: new FormData($("#formSaveCity")[0]),
            processData: false,
            contentType: false,
            cache: false,
            success: function () {
                console.log('ajax saved City');
                alert('You created new City!');
            },
            error: function (err) {
                console.log(err);
                alert('City already exist')
            }
        });
    } else {
        alert('Something Wrong')
    }
});

$("#updateButtonCity").click(function (event) {

    event.preventDefault();

    let id = $("#id").val();
    let nameCity = $("#nameCity").val();
    let dateOfCreation = $("#dateOfCreation").val();
    let population = $("#population").val();
    let history = $("#history").val();
    let nameCountry = $("#nameCountry").val();
    let reg = /^[A-Za-z ]*$/;
    if (nameCity != "" &&
        population > 0 &&
        history != "" &&
        nameCountry != "" &&
        reg.test(nameCity) &&
        reg.test(nameCountry)) {
        $.ajax({
            url: "/updateCityAJAX" + nameCountry,
            type: "POST",
            data: JSON.stringify({id, nameCity, dateOfCreation, population, history}),
            processData: false,
            contentType: 'application/json',
            cache: false,
            success: function () {
                console.log('ajax updated City');
                alert('You updated City');
            },
            error: function (err) {
                console.log(err);
                alert('City already exist')
            }
        });
    } else {
        alert('Something Wrong')
    }
});

$("#searchButtonCity").click(function () {
    let nameCitySearch = $("#nameCitySearch").val();
    let population = $("#population").val();
    let history = $("#history").val();
    let nameCity = document.getElementsByName('nameCity');
    let nameCityToSearch = document.getElementsByName('nameCityToSearch');
    console.log(history)
    $.ajax({
        url: "/searchButtonCity/"+population +'&'+nameCitySearch+'&'+history,
        type: "GET",
        data: null,
        processData: false,
        contentType: false,
        cache: false,
        success: function (cityList) {
            console.log(cityList)
            for (let i = 0; i < nameCity.length; i++) {
                nameCity[i].style.display = "none";
            }
            for (let i = 0; i < nameCity.length; i++) {
                for (let j = 0; j < cityList.length; j++) {
                    if (cityList[j].nameCity == nameCityToSearch[i].textContent && cityList.length > 0) {
                        nameCity[i].style.display = "block";
                    }
                }
            }
            console.log('ajax search');
        },
        error: function (err) {
            console.log(err);
        }
    })
});


$("#saveButtonSight").click(function () {
    event.preventDefault();
    let nameSight = $("#nameSight").val();
    let type = $("#type").val();
    let minMoney = $("#minMoney").val();
    let description = $("#description").val();
    let nameCity = $("#nameCity").val();
    let file = $("#file").val();
    let formData = new FormData();
    formData.append("image", document.forms["formSaveSight"].file.files[0]);
    formData.append('sight', new Blob([JSON.stringify({nameSight, type, minMoney, description, file})], {
        type: "application/json"
    }));
    console.log(description);
    let reg = /^[A-Za-z ]*$/;
    if (nameSight != "" &&
        type != "" &&
        minMoney > 0 &&
        description != "" &&
        nameCity != "" &&
        reg.test(nameCity)) {
        $.ajax({
            url: "/saveSightAJAX/" + nameCity,
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            headers: {
                "Content-Type": undefined
            },
            success: function () {
                console.log('ajax saved Sight');
                alert('You created new Sight!');
            },
            error: function (err) {
                console.log(err);
                alert('Sight already exist')
            }
        });
    } else {
        alert('Something Wrong')
    }
});

$("#updateButtonSight").click(function () {
    event.preventDefault();

    let id = $("#id").val();
    let nameSight = $("#nameSight").val();
    let type = $("#type").val();
    let minMoney = $("#minMoney").val();
    let description = $("#description").val();
    let nameCity = $("#nameCity").val();
    let file = $("#file").val();
    let formData = new FormData();
    if (file == '') {
        formData.append("image", null);
    } else {
        formData.append("image", document.forms["formUpdateSight"].file.files[0]);
    }
    formData.append('sight', new Blob([JSON.stringify({id, nameSight, type, minMoney, description, file})], {
        type: "application/json"
    }));
    console.log(description);
    let reg = /^[A-Za-z ]*$/;
    if (nameSight != "" &&
        type != "" &&
        minMoney > 0 &&
        description != "" &&
        nameCity != "" &&
        reg.test(nameCity)) {
        $.ajax({
            url: "/updateSightAJAX" + nameCity,
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            headers: {
                "Content-Type": undefined
            },
            success: function () {
                alert('You update Sight!');
            },
            error: function (err) {
                console.log(err);
                alert('Something Wrong')
            }
        });
    } else {
        alert('Something Wrong')
    }
});

$("#searchButtonSights").click(function () {
    let nameSightSearch = $("#nameSightSearch").val();
    let type = $("#type").val();
    let minMoney = $("#minMoney").val();
    let nameSight = document.getElementsByName('nameSight');
    let nameSightToSearch = document.getElementsByName('nameSightToSearch');
    $.ajax({
        url: "/searchButtonSights/"+nameSightSearch +'&'+type+'&'+minMoney,
        type: "GET",
        data: null,
        processData: false,
        contentType: false,
        cache: false,
        success: function (sightList) {
            console.log(sightList);
            for (let i = 0; i < nameSight.length; i++) {
                nameSight[i].style.display = "none";
            }
            for (let i = 0; i < nameSight.length; i++) {
                for (let j = 0; j < sightList.length; j++) {
                    if (sightList[j].nameSight == nameSightToSearch[i].textContent && sightList.length > 0) {
                        nameSight[i].style.display = "block";
                    }
                }
            }
            console.log('ajax search');
        },
        error: function (err) {
            console.log(err);
        }
    })
});



$("#searchButtonUniversity").click(function () {
    let nameUniversitySearch = $("#nameUniversitySearch").val();
    let direction = $("#direction").val();
    let ownership = $("#ownership").val();
    let formOfTraining = $("#formOfTraining").val();
    let nameUniversity = document.getElementsByName('nameUniversity');
    let nameUniversityToSearch = document.getElementsByName('nameUniversityToSearch');
    $.ajax({
        url: "/searchButtonUniversity/"+nameUniversitySearch +'&'+direction+'&'+ ownership +'&'+ formOfTraining,
        type: "GET",
        data: null,
        processData: false,
        contentType: false,
        cache: false,
        success: function (universityList) {
            console.log(universityList);
            for (let i = 0; i < nameUniversity.length; i++) {
                nameUniversity[i].style.display = "none";
            }
            for (let i = 0; i < nameUniversity.length; i++) {
                for (let j = 0; j < universityList.length; j++) {
                    if (universityList[j].nameUniversity == nameUniversityToSearch[i].textContent && universityList.length > 0) {
                        nameUniversity[i].style.display = "block";
                    }
                }
            }
            console.log('ajax search');
        },
        error: function (err) {
            console.log(err);
        }
    })
});

$("#commentButton").click(function () {
    let comment = $("#commentText").val().replace(/\n/g, '');
    let nameSight = document.getElementById('nameSights').innerText;
    let formData = new FormData();
    formData.append('comment', comment);
    $.ajax({
        url: "/commentSights" + nameSight,
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,

        success: function () {
            let comentDiv = document.getElementsByName('commentDiv');
            let comText = document.getElementById('comText');
            let newDiv = document.createElement("div");
            let newP = document.createElement("p");
            let username = $("#username").val();
            let newComment = document.createTextNode(username+': '+ comment);
            newP.appendChild(newComment);
            newDiv.appendChild(newP);
            newDiv.setAttribute('name','commentDiv');
            comText.insertBefore(newDiv,comentDiv[0]);
            comentDiv.innerHTML = comment;
        },
        error: function (err) {
            console.log(err);
        }
    });
});


$("#saveButtonUniversity").click(function (event) {
    event.preventDefault();
    let nameUniversity = $("#nameUniversity").val();
    let direction = $("#direction").val();
    let ownership = $("#ownership").val();
    let formOfTraining = $("#formOfTraining").val();
    let dateOfCreation = $("#dateOfCreation").val();
    let street = $("#street").val();
    let nameCity = $("#nameCity").val();
    let reg = /^[A-Za-z _]*$/;
    if (nameUniversity != "" &&
        direction != "" &&
        ownership != "" &&
        formOfTraining != "" &&
        street != "" &&
        nameCity != "" &&
        reg.test(nameUniversity) &&
        reg.test(direction) &&
        reg.test(street) &&
        reg.test(nameCity) &&
        reg.test(formOfTraining) &&
        reg.test(ownership)) {
        $.ajax({
            url: "/saveUniversityAJAX",
            type: "POST",
            data: new FormData($("#formSaveUniversity")[0]),
            processData: false,
            contentType: false,
            cache: false,
            success: function () {
                console.log('ajax saved Universuty');
                alert('You created new University!');
            },
            error: function (err) {
                console.log(err);
                alert('University already exist')
            }
        });
    } else {
        alert('Something Wrong')
    }
});

$("#updateButtonUniversity").click(function () {
    event.preventDefault();

    let id = $("#id").val();
    let nameUniversity = $("#nameUniversity").val();
    let direction = $("#direction").val();
    let ownership = $("#ownership").val();
    let formOfTraining = $("#formOfTraining").val();
    let dateOfCreation = $("#dateOfCreation").val();
    let street = $("#street").val();
    let nameCity = $("#nameCity").val();
    let reg = /^[A-Za-z _]*$/;
    if (nameUniversity != "" &&
        direction != "" &&
        ownership != "" &&
        formOfTraining != "" &&
        street != "" &&
        nameCity != "" &&
        reg.test(nameUniversity) &&
        reg.test(direction) &&
        reg.test(street) &&
        reg.test(nameCity) &&
        reg.test(formOfTraining) &&
        reg.test(ownership)) {
        $.ajax({
            url: '/updateUniversityAJAX' + nameCity,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({id, nameUniversity, direction, ownership, formOfTraining, dateOfCreation, street}),
            success: function () {
                console.log('ajax update University');
                alert('You updated University!');
            },
            error: function (err) {
                console.log(err);
                alert('University already exist')
            }
        });
    } else {
        alert('Something Wrong')
    }
});

$("#registrationButton").click(function () {
    let log = document.getElementById('login');
    let reg = document.getElementById('register');
    let err = document.getElementById('error');
    reg.style.display = "block";
    log.style.display = "none";
    err.style.display = "none";
});
$("#loginButton").click(function () {
    let log = document.getElementById('login');
    let reg = document.getElementById('register');
    let err = document.getElementById('error');
    log.style.display = "block";
    reg.style.display = "none";
    err.style.display = "none";
});
