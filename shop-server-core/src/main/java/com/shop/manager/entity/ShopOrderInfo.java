package com.shop.manager.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class ShopOrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(exist = false)
    private String ids;

    /**
     * 商品ID
     */
    private Integer shopInfoId;
    @TableField(exist = false)
    private String shopInfoIds;

    /**
     * 付款价格
     */
    private BigDecimal paymentPrice;

    /**
     * 购买用户
     */
    private Integer userId;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 购买数量
     */
    private Integer number;

    /**
     * 订单状态【0 待付款 1 已付款 2 退货中 3 已退货】
     */
    private Integer status;

    /**
     * 退货金额
     */
    private BigDecimal backPrice;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;


}
