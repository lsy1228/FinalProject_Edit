package com.kh.iMMUTABLE.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LikeDto {
    private long likeId;
    private int productId;
    private long userId;
    private String productName;
    private int productPrice;
    private String productMainImg;
}
