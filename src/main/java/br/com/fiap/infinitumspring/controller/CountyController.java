package br.com.fiap.infinitumspring.controller;

import java.util.List;

import javax.validation.Valid;
import br.com.fiap.infinitumspring.model.County;
import br.com.fiap.infinitumspring.model.CountyConsulta;
import br.com.fiap.infinitumspring.model.CountyDespesa;
import br.com.fiap.infinitumspring.model.CountyReceita;
import br.com.fiap.infinitumspring.service.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/municipios")
public class CountyController {

    @Autowired
    private CountyService service;

    @GetMapping("/listar")
    public ModelAndView listar() {

        ModelAndView view = new ModelAndView("lista-municipios");

        List<County> counties = service.listCounty();

        view.addObject("county", new CountyConsulta());

        view.addObject("counties", counties);

        return view;
    }

    @PostMapping("/consulta")
    public ModelAndView consulta(CountyConsulta county) {
        ModelAndView view;

        if (county.getTipo().equals("receitas")) {
            view = new ModelAndView("receitas-municipio");
            List<CountyReceita> countyReceitas = service.getCountyReceitas(county.getFullName(), county.getAno(), county.getMes());
            view.addObject("counties", countyReceitas);
        } else {
            view = new ModelAndView("despesas-municipio");
            List<CountyDespesa> countiesDespesas = service.getCountyDespesas(county.getFullName(), county.getAno(), county.getMes());
            view.addObject("counties", countiesDespesas);
        }

        view.addObject("countyName", county.getName());
        return view;
    }

    @GetMapping("error")
    public ModelAndView error() {
        return new ModelAndView("error");
    }

}