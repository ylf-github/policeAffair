package com.gl.police.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2020-01-11
 * @desc:
 */
@Data
public class BaseResPage<T> {
    private Integer total;
    private Integer pageSize=10;
    private Integer pageNo=0;
    private List<T> result;
}
