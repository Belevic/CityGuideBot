package org.example.dto.impl;

import lombok.Data;
import org.example.dto.BaseDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CityDto extends BaseDto {

    @NotBlank(message = "Please, provide city name, it can't be null or consists of spaces only!")
    @Pattern(regexp = "^[А-Я][а-я]{2,40}$", message = "City name must consist of letters only!")
    @Size(min = 2,max = 40,message = "City name length should be more than 1 and less than 40 symbols length!")
    private String name;

    @NotBlank(message = "Please, provide city info, it can't be null or consists of spaces only!")
    @Size(min = 1,max = 2000,message = "City info length should be more than 1 and less than 2000 symbols length!")
    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

