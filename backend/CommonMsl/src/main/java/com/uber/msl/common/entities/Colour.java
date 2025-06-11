package com.uber.msl.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Colour extends  BaseModel{

    @Column(nullable = false,unique = true)
    private String name;
}
