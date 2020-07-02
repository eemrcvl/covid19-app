package com.Covid19.Tracker.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@NoArgsConstructor
@ToString
public abstract class BaseEntity implements Serializable {
}
