package com.davidbriard.suncalc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface DependsOnField {
    int[] fieldIds();
}
