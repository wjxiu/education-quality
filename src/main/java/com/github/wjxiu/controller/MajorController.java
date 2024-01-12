package com.github.wjxiu.controller;

import com.github.pagehelper.PageInfo;
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
