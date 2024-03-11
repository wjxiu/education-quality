package com.github.wjxiu.controller;

import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.StudentEvalDO;
import com.github.wjxiu.DTO.Resp.EvalRateResp;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.R;
import com.github.wjxiu.service.StudentEvalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author xiu
 * @create 2024-01-16 19:26
 */
@RestController
@RequestMapping("stuEval")
@RequiredArgsConstructor@Slf4j
public class StudentEvalController {
    final StudentEvalService studentEvalService;
    @GetMapping({"/list"})
    public R getStudentRate( EvalRateResp param,Integer pageNum,Integer pageSize){
        PageInfo<EvalRateResp> evalRateRespPageInfo = new PageInfo<>(studentEvalService.getStudentRatePage(param,pageNum,pageSize));
        return R.success(evalRateRespPageInfo);
    }
    @GetMapping({"/getOneStudentRate"})
    public R getStudentRate(@RequestParam("studentId") Integer studentId,
                            @RequestParam("pageNum") Integer pageNum,
                            @RequestParam("pageSize") Integer pageSize){
        EvalRateResp evalRateResp = new EvalRateResp();
        evalRateResp.setStudentId(studentId);
        PageInfo<EvalRateResp> evalRateRespPageInfo = new PageInfo<>(studentEvalService.getStudentRatePage(evalRateResp,pageNum,pageSize));
        return R.success(evalRateRespPageInfo);
    }
    @GetMapping("/{id}")
    public R getinfo(@PathVariable("id") Integer id){
        EvalRateResp evalRateResp = new EvalRateResp();
        evalRateResp.setStudentId(id);
        List<EvalRateResp> studentRate = studentEvalService.getStudentRatePage(evalRateResp,1,1);
        if (studentRate.isEmpty()) return R.success(new EvalRateResp());
        return R.success(studentRate.get(0));
    }
    @PostMapping
    public R save(@Validated @RequestBody EvalRateResp EvalRateResp){
        StudentEvalDO studentEvalDO = new StudentEvalDO();
        BeanUtils.copyProperties(EvalRateResp,studentEvalDO);
        boolean save = studentEvalService.save(studentEvalDO);
        if (save)return R.success(EvalRateResp);
        throw new ClientException("新增专业失败");
    }
    //todo 修改关联的地方
    @PutMapping
    public R update(@Validated @RequestBody EvalRateResp evalRateResp){
        StudentEvalDO studentEvalDO = new StudentEvalDO();
        studentEvalDO.setId(evalRateResp.getId());
        //studentEvalDO.setStuId(evalRateResp.getStudentId());
        studentEvalDO.setRate(evalRateResp.getRate());
        log.info(studentEvalDO.toString());
        boolean save = studentEvalService.updateById(studentEvalDO);
        if (save)return R.success();
        throw new ClientException("修改专业失败");
    }
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable("ids") List<Integer> ids){
        boolean b = studentEvalService.removeBatchByIds(ids);
        if (b)return R.success();
        throw new ClientException("删除专业失败");
    }
}
