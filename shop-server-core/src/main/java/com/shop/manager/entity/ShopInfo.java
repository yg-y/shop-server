package com.shop.manager.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2019-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShopInfo implements Serializable {

    private static final long serialVersionUID = 1343572477192028114L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格【原价】
     */
    private BigDecimal price;

    /**
     * 折扣价
     */
    private BigDecimal discountPrice;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 分类ID
     */
    private Integer typeId;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 封面大图
     */
    private String titlePhoto;

    /**
     * 详情大图【最多6张，逗号分隔】
     */
    private String detailPhoto;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;


}
