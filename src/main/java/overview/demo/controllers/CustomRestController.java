package overview.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import overview.demo.dao.CitiesDao;
import overview.demo.dao.CountriesDao;
import overview.demo.dao.SightsDao;
import overview.demo.dao.UniversitiesDao;
import overview.demo.models.City;
import overview.demo.models.Country;
import overview.demo.models.Sight;
import overview.demo.models.University;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@RestController
public class CustomRestController {

    @Autowired
    private CountriesDao countriesDao;

    @Autowired
    private CitiesDao citiesDao;

    @Autowired
    private SightsDao sightsDao;

    @Autowired
    private UniversitiesDao universitiesDao;


    @PostMapping("/saveCityAJAX")
    public void saveCityAJAX(@RequestParam String nameCity,
                             @RequestParam String dateOfCreation,
                             @RequestParam int population,
                             @RequestParam String history,
                             @RequestParam String nameCountry) {
        LocalDate date = LocalDate.parse(dateOfCreation, DateTimeFormatter.ofPattern("yyyy-MM-dd")); /*зі String робимо знову LocalDate*/
        City city = new City(nameCity, date, population, history);
        Country country = countriesDao.findByName(nameCountry);

        city.setCountry(country);
        if (country != null) {
            citiesDao.save(city);
            System.out.println("Збережено: " + city);
        } else {
            System.out.println("Cheater Detected!");
        }

    }

    @PostMapping("/updateCityAJAX{nameCountry}")
    public void updateCityAJAX(@RequestBody City city,
                               @PathVariable String nameCountry) {
        Country country = countriesDao.findByName(nameCountry);
        city.setCountry(country);
        if (country != null) {
            citiesDao.save(city);
            System.out.println("Оновлено: " + city);
        } else {
            System.out.println("Cheater Detected!");
        }
    }


    @PostMapping("/saveCountyAJAX")
    public void saveCountyAJAX(@RequestParam String nameCountry,
                               @RequestParam String dateOfCreation,
                               @RequestParam String politicalSystem,
                               @RequestParam String continent,
                               @RequestParam String capital,
                               @RequestParam int square,
                               @RequestParam int population) {
        LocalDate date = LocalDate.parse(dateOfCreation, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(date);
        Country country = new Country(nameCountry, date, politicalSystem, continent, capital, square, population);
        countriesDao.save(country);
        System.out.println("Збережено: " + country);
    }

    @PostMapping("/updateCountyAJAX")
    public void updateCountyAJAX(@RequestBody Country country) {
        countriesDao.save(country);
        System.out.println("Оновлено country: " + country);
    }

    @GetMapping("/SearchCountryAJAX{nameCountry}")
    public String SearchCountryAJAX(@PathVariable String nameCountry,
                                    Model model) {

        Country countryList = countriesDao.findByName(nameCountry);
        model.addAttribute("countryList", countryList);
        System.out.println(nameCountry);
        System.out.println(countriesDao.findByName(nameCountry));
        return "countries/search";
    }

    @GetMapping("/searchButtonCountry/{continentSearch}&{politicalSearch}")
    public @ResponseBody
    List<Country> searchButtonCountry(
            @PathVariable String continentSearch,
            @PathVariable String politicalSearch) {
        List<Country> byContinent = countriesDao.findAll();
        System.out.println(politicalSearch + "pol");
        System.out.println(continentSearch);

        Iterator<Country> countryIterator = byContinent.iterator();
        if (continentSearch.length() > 2) {
            while (countryIterator.hasNext()) {
                Country countryNext = countryIterator.next();
                if (countryNext.getContinent().toLowerCase().equals(continentSearch.toLowerCase())) {
                } else {
                    countryIterator.remove();
                }
            }
        }
        Iterator<Country> politicalIterator = byContinent.iterator();
        if (politicalSearch.length() > 2) {
            while (politicalIterator.hasNext()) {
                Country politicalNext = politicalIterator.next();
                if (politicalNext.getPoliticalSystem().toLowerCase().equals(politicalSearch.toLowerCase())) {
                } else {
                    politicalIterator.remove();
                }
            }
        }
        return byContinent;
    }


    @GetMapping("/searchButtonCity/{populationStr}&{nameCitySearch}&{history}")
    public @ResponseBody
    List<City> searchButtonCity(@PathVariable String nameCitySearch,
                                @PathVariable String populationStr,
                                @PathVariable String history) {
        List<City> cityList = citiesDao.findAll();

        if (nameCitySearch.length() > 0) {
            Iterator<City> iteratorName = cityList.iterator();
            while (iteratorName.hasNext()) {
                City nextNameCity = iteratorName.next();
                boolean containsNameCity = nextNameCity.getNameCity().toLowerCase().contains(nameCitySearch.toLowerCase());
                if (containsNameCity == false) {
                    iteratorName.remove();
                }
            }
        }
        if (populationStr.length() > 0) {
            int population = Integer.parseInt(populationStr);
            if (population > 0) {
                Iterator<City> iteratorPopulation = cityList.iterator();
                while (iteratorPopulation.hasNext()) {
                    City nextPopulationCity = iteratorPopulation.next();
                    if (nextPopulationCity.getPopulation() < population) {
                        iteratorPopulation.remove();
                    }
                }
            }
        }
        if (history.length() > 0) {
            Iterator<City> iteratorHistory = cityList.iterator();
            while (iteratorHistory.hasNext()) {
                City nextHistoryCity = iteratorHistory.next();
                boolean containsHistoryCity = nextHistoryCity.getHistory().toLowerCase().contains(history.toLowerCase());
                if (containsHistoryCity == false) {
                    iteratorHistory.remove();
                }
            }
        }
        return cityList;
    }

    @GetMapping("/searchButtonUniversity/{nameUniversitySearch}&{direction}&{ownership}&{formOfTraining}")
    public @ResponseBody
    List<University> searchButtonUniversity(@PathVariable String nameUniversitySearch,
                                            @PathVariable String direction,
                                            @PathVariable String ownership,
                                            @PathVariable String formOfTraining) {

        List<University> universityList = universitiesDao.findAll();
        if (nameUniversitySearch.length() > 0){
            Iterator<University> iteratorUniversitySearch = universityList.iterator();
            while (iteratorUniversitySearch.hasNext()){
                University nextUniversityName = iteratorUniversitySearch.next();
                boolean containsNameUniversity = nextUniversityName.getNameUniversity().toLowerCase().contains(nameUniversitySearch.toLowerCase());
                if (containsNameUniversity == false){
                    iteratorUniversitySearch.remove();
                }
            }
        }
        if (direction.length() > 0) {
            Iterator<University> iteratorUniversityDirection = universityList.iterator();
            while (iteratorUniversityDirection.hasNext()) {
                University nextDirectionUniversity = iteratorUniversityDirection.next();
                boolean containsDirectionUniversity = nextDirectionUniversity.getDirection().toLowerCase().contains(direction.toLowerCase());
                if (containsDirectionUniversity == false) {
                    iteratorUniversityDirection.remove();
                }
            }
        }
        if (ownership.length() > 0){
            Iterator<University> OwnershipUniversityIterator = universityList.iterator();
            while (OwnershipUniversityIterator.hasNext()) {
                University nextOwnershipUniversity = OwnershipUniversityIterator.next();
                boolean containsOwnershipUniversity = nextOwnershipUniversity.getOwnership().toLowerCase().contains(ownership.toLowerCase());
                if (containsOwnershipUniversity == false){
                    OwnershipUniversityIterator.remove();
                }
            }
        }
        if (formOfTraining.length() > 0){
            Iterator<University> iteratorUniversityformOfTraining = universityList.iterator();
            while (iteratorUniversityformOfTraining.hasNext()) {
                University nextFormOfTrainingUniversity = iteratorUniversityformOfTraining.next();
                boolean containsFormOfTrainingUniversity = nextFormOfTrainingUniversity.getFormOfTraining().toLowerCase().contains(formOfTraining.toLowerCase());
                if (containsFormOfTrainingUniversity == false) {
                    iteratorUniversityformOfTraining.remove();
                }
            }
        }
        return universityList;
    }


    @GetMapping("/searchButtonSights/{nameSightSearch}&{type}&{minMoney}")
    public @ResponseBody
    List<Sight> searchButtonSights(@PathVariable String nameSightSearch,
                                   @PathVariable String type,
                                   @PathVariable String minMoney) {
        List<Sight> sightList = sightsDao.findAll();

        if (nameSightSearch.length() > 0) {
            Iterator<Sight> iteratorSightSearch = sightList.iterator();
            while (iteratorSightSearch.hasNext()) {
                Sight nextSightName = iteratorSightSearch.next();
                boolean containsNameSight = nextSightName.getNameSight().toLowerCase().contains(nameSightSearch.toLowerCase());
                if (containsNameSight == false) {
                    iteratorSightSearch.remove();
                }
            }
        }
        if (type.length() > 0) {
            Iterator<Sight> iteratorTypeSight = sightList.iterator();
            while (iteratorTypeSight.hasNext()) {
                Sight nextTypeSight = iteratorTypeSight.next();
                boolean containsTypeSight = nextTypeSight.getType().toLowerCase().contains(type.toLowerCase());
                if (containsTypeSight == false) {
                    iteratorTypeSight.remove();
                }
            }
        }
        if (minMoney.length() > 0) {
            Iterator<Sight> iteratorMoneySight = sightList.iterator();
            while (iteratorMoneySight.hasNext()) {
                Sight nextMoneySight = iteratorMoneySight.next();
                int minMoneyParse = Integer.parseInt(minMoney);
                if (nextMoneySight.getMinMoney() > minMoneyParse) {
                    iteratorMoneySight.remove();
                }
            }
        }
        return sightList;
    }


    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping(value = "/saveSightAJAX/{nameCity}", consumes = {"multipart/form-data"})
    public void saveSightAJAXform(@RequestPart("sight") @Valid Sight sight,
                                  @PathVariable String nameCity,
                                  @RequestPart("image") @Valid @NotNull @NotBlank MultipartFile file) throws IOException {

        final String baseDir = System.getProperty("user.dir");
        NewPhoto(sight, file, baseDir, baseDir);
        City city = citiesDao.findByName(nameCity);
        sight.setCity(city);
        if (city != null) {
            sightsDao.save(sight);
            System.out.println("Збережено: " + sight);
        } else {
            System.out.println("Cheater detected");
        }

    }

    @PostMapping("/commentSights{nameSight}")
    public void Coment(@PathVariable String nameSight,
                         @RequestParam String comment){
        Sight sight = sightsDao.findByName(nameSight);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get Username
        sight.setComment(username + ": " + comment);
        sightsDao.save(sight);
    }

    @PostMapping("/updateSightAJAX{nameCity}")
    public void updateSightAJAX(@RequestPart("sight") @Valid Sight sight,
                                @PathVariable String nameCity,
                                @RequestPart("image") @Nullable MultipartFile file) throws IOException {
        City city = citiesDao.findByName(nameCity);
        String sightFile = sight.getFile();
        System.out.println(sightFile);
        System.out.println(file);
        if (file == null) {
            Sight SightbyName = sightsDao.findByName(sight.getNameSight());
            String Oldfile = SightbyName.getFile();
            sight.setFile(Oldfile);
            System.out.println("Файл без фото апдейтнутий");
        }
        if (file != null) {
            Sight SightbyName = sightsDao.findByName(sight.getNameSight());
            String Oldfile = SightbyName.getFile();
            String baseDir = System.getProperty("user.dir");
            Files.delete(Paths.get(baseDir + uploadPath + File.separator + Oldfile));
            System.out.println("Старе фото видалено і апдейтнуто");
            final String baseDirection = System.getProperty("user.dir");
            NewPhoto(sight, file, baseDir, baseDirection);
        }
        sight.setCity(city);
        if (city != null) {
            sightsDao.save(sight);
            System.out.println("Оновлено: " + sight);
        } else {
            System.out.println("Cheater detected");
        }
    }

    private void NewPhoto(@RequestPart("sight") @Valid Sight sight, @RequestPart("image") @Valid @NotNull @NotBlank MultipartFile file, String baseDir, String baseDirection) throws IOException {
        if (file != null) {
            File uploadFolder = new File(baseDirection + uploadPath);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + file.getOriginalFilename();

            final File targetFile = new File(baseDir + uploadPath + File.separator + resultFileName);
            targetFile.createNewFile();
            file.transferTo(targetFile);
            sight.setFile(resultFileName);
        }
    }


    @PostMapping("/saveUniversityAJAX")
    public void saveCountyAJAX(@RequestParam String nameUniversity,
                               @RequestParam String direction,
                               @RequestParam String ownership,
                               @RequestParam String formOfTraining,
                               @RequestParam String dateOfCreation,
                               @RequestParam String street,
                               @RequestParam String nameCity) {
        LocalDate date = LocalDate.parse(dateOfCreation, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(date);
        University university = new University(nameUniversity, direction, ownership, formOfTraining, date, street);
        City city = citiesDao.findByName(nameCity);
        university.setCity(city);
        if (city != null) {
            universitiesDao.save(university);
            System.out.println("Збережено: " + university);
        } else {
            System.out.println("Cheater Detected!");
        }

    }

    @PostMapping("/updateUniversityAJAX{nameCity}")
    public void updateButtonUniversity(@RequestBody University university,
                                       @PathVariable String nameCity) {
        City city = citiesDao.findByName(nameCity);
        university.setCity(city);
        if (city != null) {
            universitiesDao.save(university);
            System.out.println("Оновлено: " + university);
        } else {
            System.out.println("Cheater Detected!");
        }
    }

}
