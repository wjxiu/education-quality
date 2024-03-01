package com.github.wjxiu.DTO.Resp;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.wjxiu.conf.MyLocalDateTimeDeSerializer;
import com.github.wjxiu.conf.MyLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xiu
 * @create 2024-03-02 0:05
 */
@Data
public class EvalMessageResp {
    List<EvalRateResp> list;
    String comment;
}
