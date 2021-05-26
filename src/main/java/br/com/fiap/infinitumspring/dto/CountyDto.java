package br.com.fiap.infinitumspring.dto;

import javax.validation.constraints.NotBlank;

import br.com.fiap.infinitumspring.entity.CountyEntity;

public class CountyDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String fullName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public CountyEntity toEntity() {
        CountyEntity entity = new CountyEntity();
        entity.setId(this.id);
        entity.setFullName(this.fullName);
        entity.setName(this.name);
        return entity;
    }

}