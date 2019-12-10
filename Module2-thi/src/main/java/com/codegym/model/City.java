package com.codegym.model;


import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name="city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min=3, max=30)
    private String name;

    @NotNull
    @Min(0)
    private Long acreage;

    @NotNull
    @Min(0)
    private Long population;

    @NotNull
    @Min(0)
    private Long gdp;

    private String description;
    //Liên kết giữa 2 bảng
    @ManyToOne
    @JoinColumn(name="countryId")
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City(String  name, @NotNull @Min(0) Long acreage, @NotNull @Min(0) Long population, @NotNull @Min(0) Long gdp, String description, Country country) {
        this.name = name;
        this.acreage = acreage;
        this.population = population;
        this.gdp = gdp;
        this.description = description;
        this.country = country;
    }

    public City() {
    }

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

    public Long getAcreage() {
        return acreage;
    }

    public void setAcreage(Long acreage) {
        this.acreage = acreage;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Long getGdp() {
        return gdp;
    }

    public void setGdp(Long gdp) {
        this.gdp = gdp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
