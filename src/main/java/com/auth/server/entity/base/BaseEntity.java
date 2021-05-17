package com.auth.server.entity.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    @CreationTimestamp
    @Column(name= "created_date", nullable = false, updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name= "updated_date", nullable = false, updatable = false)
    private Date updatedDate;
}
