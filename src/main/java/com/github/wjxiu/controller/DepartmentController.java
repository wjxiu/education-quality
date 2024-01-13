package com.github.wjxiu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.DepartmentDO;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.R;
import com.github.wjxiu.mapper.DepartmentMapper;
import com.github.wjxiu.mapper.MajorMapper;
import com.github.wjxiu.service.DepartmentService;
import com.github.wjxiu.service.MajorService;
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
    final MajorService majorService;
    @GetMapping("/list")
    public R<PageInfo<DepartmentDO>> list(@RequestParam(value = "id",required = false) Integer id,
                                             @RequestParam(value = "name",required = false)String name,
                                             @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize) {
        return R.success(new PageInfo<>(departmentService.pagelist(id,name,pageNum,pageSize)));
    }
    @GetMapping(value = {"/allName","/allName/{majorName}"})
    public R getallName(@PathVariable(required = false) String majorName){
        List<String> list=  departmentService.getallName(majorName);
        return R.success(list);
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
