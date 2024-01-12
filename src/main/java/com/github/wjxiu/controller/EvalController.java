package com.github.wjxiu.controller;

import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.EvalDO;
import com.github.wjxiu.DTO.Req.EvalSubmitReq;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.R;
import com.github.wjxiu.common.token.UserContext;
import com.github.wjxiu.service.EvalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiu
 * @create 2024-01-10 14:01
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/eval")
public class EvalController {
    final EvalService evalService;
    final
    @GetMapping("/list")
    public R<PageInfo<EvalDO>> list(String evalItem,Integer id, Integer pageNum, Integer pageSize) {
      List<EvalDO> list=  evalService.list(evalItem, id, pageNum, pageSize);
        return R.success(new PageInfo<EvalDO>(list));
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
    @PostMapping("/submit")
    public R submit(@RequestBody EvalSubmitReq data){
       Boolean res= evalService.submit(data);
        return R.success(res);
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
