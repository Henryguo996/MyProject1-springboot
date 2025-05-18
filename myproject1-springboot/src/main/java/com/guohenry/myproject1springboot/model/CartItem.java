package com.guohenry.myproject1springboot.model;

public class CartItem {

    private Long id;
    private Integer userId;      // 新增
    private Long productId;
    private Integer num;
    private String deliveryId;

    /* -------- getters / setters -------- */

    public Long getId()             { return id; }
    public void setId(Long id)      { this.id = id; }

    public Integer getUserId()          { return userId; }
    public void setUserId(Integer uid)  { this.userId = uid; }

    public Long getProductId()      { return productId; }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getNum()         { return num; }
    public void setNum(Integer num) { this.num = num; }

    public String getDeliveryId()       { return deliveryId; }
    public void setDeliveryId(String d) { this.deliveryId = d; }
}
