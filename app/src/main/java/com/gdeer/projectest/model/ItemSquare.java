package com.gdeer.projectest.model;

/**
 * Created by Gdeer on 2015/12/27.
 */
public class ItemSquare {
    private String itemName;
    private Integer itemType;

    public ItemSquare() {
    }

    public ItemSquare(String itemName, Integer itemType) {
        this.itemName = itemName;
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }
}
