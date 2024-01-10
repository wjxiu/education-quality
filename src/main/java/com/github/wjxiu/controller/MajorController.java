package com.github.wjxiu.controller;

import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.MajorDO;
import com.github.wjxiu.DTO.Req.TeacherPageReq;
import com.github.wjxiu.DTO.Resp.TeacherPageResp;
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
    @PostMapping("/list")
    public PageInfo<TeacherPageResp> list(@RequestBody TeacherPageReq teacher, Integer pageNum, Integer pageSize) {
        return null;
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
