package sg.edu.nus.iss.vttp5_ssf_day18l.controller;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5_ssf_day18l.constant.Constant;
import sg.edu.nus.iss.vttp5_ssf_day18l.model.Person;
import sg.edu.nus.iss.vttp5_ssf_day18l.repository.MapRepo;


@Controller
@RequestMapping(path = "/persons")
public class PersonController {

    @Autowired
    MapRepo mapRepo;

    @GetMapping("/home")
    public String personHome() {
        return "personhome";
    }

    @GetMapping("/create")
    public String createPerson(Model model) {
        Person p = new Person();

        model.addAttribute("person", p);
        return "personcreate";
    }

    // day 18 - slide 8
    @GetMapping("/create2")
    public ModelAndView createPerson2() {
        Person p = new Person();
        
        ModelAndView mav = new ModelAndView();

        mav.setViewName("personcreate");
        mav.addObject("person", p);
        
        return mav;
    }

    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("person") Person entity, BindingResult results, Model model) {

        System.out.println(entity.toString());
        System.out.println(entity.getDob());

        if (results.hasErrors())
            return "personcreate";

        // serialise to JsonObject, then save the Jsonobject as a string using Map
        JsonObject jObject = Json.createObjectBuilder()
        .add("id", entity.getId().toString())
        .add("fullName", entity.getFullName())
        .add("email", entity.getEmail())
        .add("phone", entity.getPhone())
        .add("dob", entity.getDob().toString()).build();
        mapRepo.create(Constant.personKey, entity.getId().toString(), jObject.toString());
        
        return "redirect:/persons/list";
    }

    @GetMapping("/list")
    public String personList(Model model) throws ParseException {
        Map<Object, Object> personsObject = mapRepo.getEntries(Constant.personKey);

        List<Person> persons = new ArrayList<>();

        for(Map.Entry<Object, Object> entry: personsObject.entrySet()) {
            String stringValue = entry.getValue().toString();
            JsonReader jReader = Json.createReader(new StringReader(stringValue));
            JsonObject jObject = jReader.readObject();

            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zz yyyy");
            Date myDob = sdf.parse(jObject.getString("dob"));

            persons.add(new Person(Integer.parseInt(jObject.getString("id")), jObject.getString("fullName"), jObject.getString("email"), jObject.getString("phone"), myDob));
        }

        model.addAttribute("persons", persons);
        return "personlist";
    }
}
    
