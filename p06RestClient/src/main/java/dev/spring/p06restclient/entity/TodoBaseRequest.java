package dev.spring.p06restclient.entity;


import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class TodoBaseRequest {
    private LocalDateTime localDate;
}
