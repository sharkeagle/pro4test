package com.project4test.project4test.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortEnum {
    ASC(true, "升序"),
    DESC(false, "降序"),;


    private final boolean code;
    private final String msg;
}
