package com.github.wjxiu.controller;

import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.EvalDO;
import com.github.wjxiu.DTO.Req.EvalSubmitReq;
import com.github.wjxiu.DTO.Req.TeacherPageReq;
import com.github.wjxiu.DTO.Resp.EvalRateReq;
import com.github.wjxiu.DTO.Resp.TeacherPageResp;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.R;
import com.github.wjxiu.common.token.UserContext;
import com.github.wjxiu.service.EvalService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiu
 * @create 2024-01-10 14:01
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/eval")
public class EvalController {
    final EvalService evalService;
    final
    @PostMapping("/list")
    public PageInfo<TeacherPageResp> list(@RequestBody TeacherPageReq teacher, Integer pageNum, Integer pageSize) {
        return null;
    }

    /**
     * 查询学生对教师的评价
     * @param teacherId
     * @return
     */
    @GetMapping("/stu/{teacherId}")
    public R getTeacherEvalByStuidAndTeacherId(@PathVariable("teacherId")  Integer teacherId){
        Integer stuId = UserContext.getUserId();
        return R.success(evalService.getTeacherEvalByStuidAndTeacherId(stuId,teacherId));
    }
    @GetMapping("/{id}")
    public R getinfo(@PathVariable("id") Integer id){
        EvalDO EvalDO = evalService.getById(id);
        return R.success(EvalDO);
    }
    @PostMapping("submit")
    public R submit(EvalSubmitReq submitReq){
       Boolean res= evalService.submit(submitReq);
        return null;
    }
    @PostMapping
    public R save(@Validated @RequestBody EvalDO EvalDO){
        boolean save = evalService.save(EvalDO);
        if (save)return R.success(EvalDO);
        throw new ClientException("新增评价失败");
    }
    @PutMapping
    public R update(@Validated @RequestBody EvalDO EvalDO){
        boolean save = evalService.updateById(EvalDO);
        if (save)return R.success();
        throw new ClientException("修改评价失败");
    }
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable("ids") List<Integer> ids){
        boolean b = evalService.removeBatchByIds(ids);
        if (b)return R.success();
        throw new ClientException("删除评价失败");
    }
}
