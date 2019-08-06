package com.touchkiss.mybatis.admin.bean;

/**
 * @Author Touchkiss
 * @create: 2019-06-26 09:57
 */
public class CompareValue {
    private String compare;
    private String value;
    private CompareValue leftChild, rightChild;

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CompareValue getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(CompareValue leftChild) {
        this.leftChild = leftChild;
    }

    public CompareValue getRightChild() {
        return rightChild;
    }

    public void setRightChild(CompareValue rightChild) {
        this.rightChild = rightChild;
    }
}
