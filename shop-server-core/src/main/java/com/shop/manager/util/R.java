package com.shop.manager.util;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 响应信息主体
 *
 * @param <T>
 * @author devin
 */
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class R<T> implements Serializable {
    private static final long serialVersionUID = -5537330587033784458L;
    
    private static final Integer SUCCESS = 1; 
    private static final Integer ERROR = 0; 

    @Getter
    @Setter
    private int code = 1;

    @Getter
    @Setter
    private String msg = "success";

    @Getter
    @Setter
    private T data;

    public R() {
        super();
    }

    public static R SUCCESS(Object o) {
        return R.builder().build().setData(o).setMsg("success").setCode(SUCCESS);
    }

    public static R ERROR(Object o) {
        return R.builder().build().setData(o).setMsg("error").setCode(ERROR);
    }

    public static R SUCCESS() {
        return R.builder().build().setData(null).setMsg("success").setCode(SUCCESS);
    }

    public static R ERROR() {
        return R.builder().build().setData(null).setMsg("error").setCode(ERROR);
    }


    public static R SUCCESS(Object o, String msg) {
        return R.builder().build().setData(o).setMsg(msg).setCode(SUCCESS);
    }

    public static R ERROR(Object o, String msg) {
        return R.builder().build().setData(o).setMsg(msg).setCode(ERROR);
    }

    public static R ERROR(Throwable e) {
        return R.builder().build().setData(null).setMsg(e.getMessage()).setCode(ERROR);
    }
}
