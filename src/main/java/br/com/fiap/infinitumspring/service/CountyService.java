package br.com.fiap.infinitumspring.service;

import br.com.fiap.infinitumspring.model.County;
import br.com.fiap.infinitumspring.model.CountyDespesa;
import br.com.fiap.infinitumspring.model.CountyReceita;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CountyService {

    RestTemplate restTemplate = new RestTemplate();

    public List<County> listCounty() {


        ResponseEntity<List<County>> responseEntity = restTemplate.exchange("https://transparencia.tce.sp.gov.br/api/json/municipios",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<County>>() {
                });
        List<County> countyEntities = responseEntity.getBody();


        assert countyEntities != null;

        return countyEntities;
    }

    public List<CountyReceita> getCountyReceitas(String county, String year, String month) {
        String url = "https://transparencia.tce.sp.gov.br/api/json/receitas/" + county + "/" + year + "/" + month;
        ResponseEntity<List<CountyReceita>> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CountyReceita>>() {
                });
        return responseEntity.getBody();
    }

    public List<CountyDespesa> getCountyDespesas(String county, String year, String month) {
        String url = "https://transparencia.tce.sp.gov.br/api/json/despesas/" + county + "/" + year + "/" + month;
        ResponseEntity<List<CountyDespesa>> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CountyDespesa>>() {
                });
        return responseEntity.getBody();
    }
}
