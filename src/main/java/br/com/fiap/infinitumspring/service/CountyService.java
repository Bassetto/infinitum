package br.com.fiap.infinitumspring.service;

import br.com.fiap.infinitumspring.dto.CountyDto;
import br.com.fiap.infinitumspring.entity.CountyEntity;
import br.com.fiap.infinitumspring.model.County;
import br.com.fiap.infinitumspring.model.CountyDespesa;
import br.com.fiap.infinitumspring.model.CountyReceita;
import br.com.fiap.infinitumspring.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountyService {

    @Autowired
    CountyRepository repository;

    RestTemplate restTemplate = new RestTemplate();

    public CountyDto saveCounty(CountyDto countyDto) {
        CountyEntity entity = countyDto.toEntity();
        entity = repository.save(entity);
        return toCountyDto(entity);
    }

    public List<County> listCounty() {


        ResponseEntity<List<County>> responseEntity = restTemplate.exchange("https://transparencia.tce.sp.gov.br/api/json/municipios",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<County>>() {
                });
        List<County> countyEntities = responseEntity.getBody();


//        List<CountyEntity> localEntities = repository.findAll();
//        assert countyEntities != null;
//        countyEntities.addAll(toCounty(localEntities));


        return countyEntities;
    }

    public List<CountyReceita> getCountyReceitas(String county, String year, String month) {
        String url = "https://transparencia.tce.sp.gov.br/api/json/receitas/" + county + "/" + year + "/" + month;
        ResponseEntity<List<CountyReceita>> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CountyReceita>>() {
                });
        List<CountyReceita> countyEntities = responseEntity.getBody();
        return responseEntity.getBody();
    }

    public List<CountyDespesa> getCountyDespesas(String county, String year, String month) {
        String url = "https://transparencia.tce.sp.gov.br/api/json/despesas/" + county + "/" + year + "/" + month;
        ResponseEntity<List<CountyDespesa>> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CountyDespesa>>() {
                });
        return responseEntity.getBody();
    }

    public CountyDto updateCounty(Long id) {

        Optional<CountyEntity> opt = repository.findById(id);
        CountyEntity entity;
        CountyDto countyDto = null;

        if (opt.isPresent()) {
            entity = opt.get();
            countyDto = toCountyDto(entity);
        }

        return countyDto;
    }

    public void removeCounty(Long id) {
        repository.deleteById(id);
    }

    private County toCounty(CountyEntity entity) {
        County county = new County();
        county.setMunicipio(entity.getName());
        county.setMunicipio_extenso(entity.getFullName());
        return county;
    }

    private List<County> toCounty(List<CountyEntity> entities) {
        List<County> counties = new ArrayList<>();

        for (CountyEntity entity: entities){
            County county = new County();
            county.setMunicipio(entity.getName());
            county.setMunicipio_extenso(entity.getFullName());
            counties.add(county);
        }
        return counties;
    }

    private CountyDto toCountyDto(CountyEntity entity) {
        CountyDto dto = new CountyDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setFullName(entity.getFullName());
        return dto;
    }

    private List<CountyDto> toCountyDto(List<CountyEntity> entities) {
        List<CountyDto> countyDtos = new ArrayList<>();

        for (CountyEntity entity : entities) {
            CountyDto dto = new CountyDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setFullName(entity.getFullName());

            countyDtos.add(dto);
        }
        return countyDtos;
    }
}
