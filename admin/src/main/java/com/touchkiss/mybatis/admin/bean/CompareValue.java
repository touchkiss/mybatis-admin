package com.touchkiss.mybatis.admin.bean;

import lombok.Data;

/**
 * @Author Touchkiss
 * @create: 2019-06-26 09:57
 */
@Data
public class CompareValue {
    private String compare;
    private String value;
    private CompareValue leftChild, rightChild;
}
