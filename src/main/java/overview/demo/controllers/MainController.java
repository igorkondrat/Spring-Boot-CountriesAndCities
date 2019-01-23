package overview.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import overview.demo.dao.CitiesDao;
import overview.demo.dao.CountriesDao;
import overview.demo.dao.SightsDao;
import overview.demo.dao.UniversitiesDao;
import overview.demo.models.*;
import overview.demo.services.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private CountriesDao countriesDao;

    @Autowired
    private CitiesDao citiesDao;

    @Autowired
    private UniversitiesDao universitiesDao;

    @Autowired
    private SightsDao sightsDao;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        System.out.println("home");
        return "index";
    }


    @GetMapping("/CountrySearch")
    public String CountrySearch(Model model) {
        List<Country> countryList = countriesDao.findAll();
        model.addAttribute("countryList", countryList);
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName(); //get Username
//        model.addAttribute("name", name);
        return "countries/search";
    }

    @GetMapping("/country")
    public String country(Model model) {
        List<Country> countryList = countriesDao.findAll();
        model.addAttribute("countryList", countryList);
        return "redirect:/CountrySearch";
    }

    @GetMapping("/createNewCountryButton")
    public String createNewCountryButton() {
        return "countries/create";
    }

    @GetMapping("/createNewCountryButtonView")
    public String createNewCountry() {
        return "countries/create";
    }

    @GetMapping("/viewAllCountries")
    public String cancelCreate() {
        return "redirect:/country";
    }

    @GetMapping("/viewCountry/{id}")
    public String resolveSingleContact(@PathVariable int id,
                                       Model model) {
        Country country = countriesDao.findById(id).get();
        model.addAttribute("country", country);
        List<City> cityList = citiesDao.findAll();
        model.addAttribute("cityList", cityList);
        return "countries/view";
    }

    @GetMapping("/editCountry/{id}")
    public String editCountry(@PathVariable int id,
                              Model model) {
        Country country = countriesDao.findById(id).get();
        model.addAttribute("country", country);
        return "countries/edit";
    }

    @GetMapping("/cancelEdit/{id}")
    public String cancelEdit(@PathVariable int id, Model model) {
        Country country = countriesDao.findById(id).get();
        model.addAttribute("country", country);
        return "redirect:/viewCountry/{id}";
    }

    @GetMapping("/deleteCountry/{id}")
    public String delete(@PathVariable int id) {
        Country country = countriesDao.findById(id).get();
        System.out.println("Видалено: " + country);
        countriesDao.delete(country);
        return "redirect:/viewAllCountries";
    }


    @GetMapping("/cities")
    public String cities(Model model) {
        List<City> cityList = citiesDao.findAll();
        model.addAttribute("cityList", cityList);
        return "redirect:/CitiesSearch";
    }

    @GetMapping("/CitiesSearch")
    public String CitiesSearch(Model model) {
        List<City> cityList = citiesDao.findAll();
        model.addAttribute("cityList", cityList);
        return "cities/search";
    }

    @GetMapping("/viewAllCities")
    public String viewAllCities() {
        return "redirect:/cities";
    }

    @GetMapping("/viewCity/{id}")
    public String CityById(@PathVariable int id,
                           Model model) {
        City city = citiesDao.findById(id).get();
        model.addAttribute("city", city);
        List<Sight> sightList = sightsDao.findAll();
        model.addAttribute("sightList", sightList);
        List<University> universityList = universitiesDao.findAll();
        model.addAttribute("universityList", universityList);
        return "cities/view";
    }

    @GetMapping("/createNewCityButton")
    public String createNewCityButton(Model model) {
        List<Country> countryList = countriesDao.findAll();
        model.addAttribute("countryList", countryList);
        return "cities/create";
    }

    @GetMapping("/editCity/{id}")
    public String editCity(@PathVariable int id,
                           Model model) {
        City city = citiesDao.findById(id).get();
        model.addAttribute("city", city);
        List<Country> countryList = countriesDao.findAll();
        model.addAttribute("countryList", countryList);
        String history = city.getHistory();
        model.addAttribute("history", history);
        return "cities/edit";
    }

    @GetMapping("/createNewCityButtonView")
    public String createNewCityButtonView() {
        return "redirect:/createNewCityButton";
    }

    @GetMapping("/cancelCityEdit/{id}")
    public String cancelCityEdit(@PathVariable int id,
                                 Model model) {
        City city = citiesDao.findById(id).get();
        model.addAttribute("city", city);
        return "redirect:/viewCity/{id}";
    }

    @GetMapping("/deleteCity/{id}")
    public String deleteCity(@PathVariable int id) {
        City city = citiesDao.findById(id).get();
        System.out.println("Видалено: " + city);
        citiesDao.delete(city);
        return "redirect:/viewAllCities";
    }


    @GetMapping("/sights")
    public String sights(Model model) {
        List<Sight> sightList = sightsDao.findAll();
        model.addAttribute("sightList", sightList);
        return "redirect:/SightsSearch";
    }

    @GetMapping("/SightsSearch")
    public String SightsSearch(Model model) {
        List<Sight> sightList = sightsDao.findAll();
        model.addAttribute("sightList", sightList);
        return "sights/search";
    }

    @GetMapping("/createNewSightButton")
    public String createNewSightButton(Model model) {
        List<City> cityList = citiesDao.findAll();
        model.addAttribute("cityList", cityList);
        return "sights/create";
    }

    @GetMapping("/viewSight/{id}")
    public String viewSight(@PathVariable int id,
                            Model model) {
        Sight sight = sightsDao.findById(id).get();
        ArrayList<String> comments = sight.getComments();
        model.addAttribute("sight", sight);
        model.addAttribute("comments", comments);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        return "sights/view";
    }


    @GetMapping("/createNewSightButtonView")
    public String createNewSightButtonView() {
        return "redirect:/createNewSightButton";
    }

    @GetMapping("/editSight/{id}")
    public String editSight(@PathVariable int id,
                            Model model) {
        Sight sight = sightsDao.findById(id).get();
        model.addAttribute("sight", sight);
        List<City> cityList = citiesDao.findAll();
        model.addAttribute("cityList", cityList);
        String description = sight.getDescription();
        model.addAttribute("description", description);
        return "sights/edit";
    }

    @GetMapping("/cancelSightEdit/{id}")
    public String cancelSightEdit(@PathVariable int id, Model model) {
        Sight sight = sightsDao.findById(id).get();
        model.addAttribute("sight", sight);
        return "redirect:/viewSight/{id}";
    }


    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/deleteSight/{id}")
    public String deleteSight(@PathVariable int id) throws IOException {
        Sight sight = sightsDao.findById(id).get();
        String file = sight.getFile();
        String baseDir = System.getProperty("user.dir");
        Files.delete(Paths.get(baseDir + uploadPath + File.separator + file));
        System.out.println("Видалено: " + sight);
        sightsDao.delete(sight);
        return "redirect:/sights";
    }

    @GetMapping("/viewAllSight")
    public String viewAllSight() {
        return "redirect:/SightsSearch";
    }


    @GetMapping("/createNewUniversityButton")
    public String createNewUniversityButton(Model model) {
        List<City> cityList = citiesDao.findAll();
        model.addAttribute("cityList", cityList);
        return "universities/create";
    }

    @GetMapping("/viewUniversity/{id}")
    public String resolveSingleContactUniversity(@PathVariable int id,
                                                 Model model) {
        University university = universitiesDao.findById(id).get();
        model.addAttribute("university", university);
        return "universities/view";
    }

    @GetMapping("/editUniversity/{id}")
    public String editUniversity(@PathVariable int id,
                                 Model model) {
        University university = universitiesDao.findById(id).get();
        model.addAttribute("university", university);
        List<City> cityList = citiesDao.findAll();
        model.addAttribute("cityList", cityList);
        return "universities/edit";
    }

    @GetMapping("/cancelUniversityEdit/{id}")
    public String cancelEditUniversity(@PathVariable int id, Model model) {
        University university = universitiesDao.findById(id).get();
        model.addAttribute("university", university);

        return "redirect:/viewUniversity/{id}";
    }

    @GetMapping("/universities")
    public String universities() {
        return "redirect:/UniversitiesSearch";
    }

    @GetMapping("/UniversitiesSearch")
    public String UniversitiesSearch(Model model) {
        List<University> universityList = universitiesDao.findAll();
        model.addAttribute("universityList", universityList);
        return "universities/search";
    }

    @GetMapping("/createNewUniversityButtonView")
    public String createNewUniversity() {
        return "universities/create";
    }

    @GetMapping("/deleteUniversity/{id}")
    public String deleteUniversity(@PathVariable int id) {
        University university = universitiesDao.findById(id).get();
        System.out.println("Видалено: " + university);
        universitiesDao.delete(university);
        return "redirect:/universities";
    }

    @GetMapping("/viewAllUniversities")
    public String viewAllUniversities() {
        return "redirect:/UniversitiesSearch";
    }


    @PostMapping("/successURL")
    public String successURL(Model model) {
        model.addAttribute("LoginSuccess", "Login Success");
        return "index";
    }

    @GetMapping("/LoginFailed")
    public String err(Model model) {
        model.addAttribute("LoginFailed", "Login Failed");
        return "index";
    }

    @PostMapping("/saveUser")
    public String user(User user,
                       Model model) {
        if (user.getUsername() != "" &&
                user.getPassword() != "" &&
                user.getPassword().length() > 1 &&
                user.getEmail() != "") {
            String encode = passwordEncoder.encode(user.getPassword());
            user.setPassword(encode);
            userService.save(user);
            System.out.println("Register success!");
            model.addAttribute("RegisterComplete", "Register Complete");
        } else {
            model.addAttribute("IncorrectUser", "Incorrect User");
            System.out.println("User Register Failed :(");
        }
        return "index";
    }

}
