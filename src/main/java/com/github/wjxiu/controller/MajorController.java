package com.github.wjxiu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.DepartmentDO;
import com.github.wjxiu.DO.MajorDO;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.R;
import com.github.wjxiu.service.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiu
 * @create 2024-01-10 13:57
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/major")
public class MajorController {
    final MajorService majorService;
    @GetMapping("/list")
    public R<PageInfo<MajorDO>> list( MajorDO majorDO,
                                             @RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "10") Integer pageSize) {
        return R.success(new PageInfo<>(majorService.pageList(majorDO,pageNum,pageSize)));
    }

    @GetMapping(value = {"/allName/{departmentName}","/allName"})
    public R getMajorNameByDepartmentName(@PathVariable(value = "departmentName",required = false) String departmentName){
        List<String> list = majorService.list(new LambdaQueryWrapper<MajorDO>().select(MajorDO::getMajorName).eq(departmentName!=null&& !departmentName.isEmpty(),MajorDO::getDepartmentName,departmentName)).stream().map(MajorDO::getMajorName).toList();
        return R.success(list);
    }
    @GetMapping("/{id}")
    public R getinfo(@PathVariable("id") Integer id){
        MajorDO MajorDO = majorService.getById(id);
        return R.success(MajorDO);
    }
    @PostMapping
    public R save(@Validated @RequestBody MajorDO MajorDO){
        boolean save = majorService.save(MajorDO);
        if (save)return R.success(MajorDO);
        throw new ClientException("新增专业失败");
    }
    //todo 修改关联的地方
    @PutMapping
    public R update(@Validated @RequestBody MajorDO MajorDO){
        boolean save = majorService.updateById(MajorDO);
        if (save)return R.success();
        throw new ClientException("修改专业失败");
    }
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable("ids") List<Integer> ids){
        boolean b = majorService.removeBatchByIds(ids);
        if (b)return R.success();
        throw new ClientException("删除专业失败");
    }
}
