package org.example.dto;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Data
public abstract class BaseDto implements Serializable {

    @Id
    @PositiveOrZero
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

