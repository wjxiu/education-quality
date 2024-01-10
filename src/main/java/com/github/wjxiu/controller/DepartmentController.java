package com.github.wjxiu.controller;

import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.DepartmentDO;
import com.github.wjxiu.DTO.Req.TeacherPageReq;
import com.github.wjxiu.DTO.Resp.TeacherPageResp;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.R;
import com.github.wjxiu.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiu
 * @create 2024-01-10 13:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {
    final DepartmentService departmentService;
    @PostMapping("/list")
    public PageInfo<TeacherPageResp> list(@RequestBody TeacherPageReq teacher, Integer pageNum, Integer pageSize) {
        return null;
    }
    @GetMapping("/{id}")
    public R getinfo(@PathVariable("id") Integer id){
        DepartmentDO DepartmentDO = departmentService.getById(id);
        return R.success(DepartmentDO);
    }
    @PostMapping
    public R save(@Validated @RequestBody DepartmentDO DepartmentDO){
        boolean save = departmentService.save(DepartmentDO);
        if (save)return R.success(DepartmentDO);
        throw new ClientException("新增学院失败");
    }
    @PutMapping
    public R update(@Validated @RequestBody DepartmentDO DepartmentDO){
        boolean save = departmentService.updateById(DepartmentDO);
        if (save)return R.success();
        throw new ClientException("修改学院失败");
    }
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable("ids") List<Integer> ids){
        boolean b = departmentService.removeBatchByIds(ids);
        if (b)return R.success();
        throw new ClientException("删除学院失败");
    }
}
