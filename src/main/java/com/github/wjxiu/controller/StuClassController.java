package com.github.wjxiu.controller;

import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.StuClassDO;
import com.github.wjxiu.DO.StudentDO;
import com.github.wjxiu.DTO.Req.AddStuClassStudentReq;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.R;
import com.github.wjxiu.service.StuClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiu
 * @create 2024-01-14 0:08
 */
@RestController
@RequiredArgsConstructor()
@Slf4j
@RequestMapping("/stuClass")
public class StuClassController {
    final StuClassService stuClassService;
    @GetMapping("/list")
    public R<PageInfo<StuClassDO>> list(StuClassDO stuClassDO,
                                     @RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("班级信息：{}",stuClassService.pageList(stuClassDO,pageNum,pageSize));
        return R.success(new PageInfo<>(stuClassService.pageList(stuClassDO,pageNum,pageSize)));
    }
    @GetMapping("/addStuClassStudent")
    public  R addStuClassStudent(AddStuClassStudentReq addStuClassStudentReq){
       boolean res= stuClassService.addStuClassStudent(addStuClassStudentReq);
       return R.success();
    }
    @GetMapping("/getStuClassStudent/{stuClassId}")
    public  R getStuClassStudent(@PathVariable Integer stuClassId,@RequestParam(required = false) Integer studentId,@RequestParam(required = false)String studentName){
        List<StudentDO> res= stuClassService.getStuClassStudent(stuClassId,studentId,studentName);
        return R.success(res);
    }
    @DeleteMapping("/deleteClassStudent/{stuClassId}/{StuIds}")
    public R deleteClassStudent(@PathVariable("stuClassId") Integer stuClassId, @PathVariable("StuIds")List<Integer> StuIds){
      boolean res=  stuClassService.deleteClassStudent(stuClassId,StuIds);
      return R.success(res);
    }

    @GetMapping("/{id}")
    public R getinfo(@PathVariable("id") Integer id){
        StuClassDO StuClassDO = stuClassService.getById(id);
        log.info(StuClassDO.toString());
        return R.success(StuClassDO);
    }
    @PostMapping
    public R save(@Validated @RequestBody StuClassDO stuClassDO){
        boolean save = stuClassService.save(stuClassDO);
        if (save)return R.success(stuClassDO);
        throw new ClientException("新增专业失败");
    }
    //todo 修改关联的地方
    @PutMapping
    public R update(@Validated @RequestBody StuClassDO stuClassDO){
        boolean save = stuClassService.updateById(stuClassDO);
        if (save)return R.success();
        throw new ClientException("修改专业失败");
    }
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable("ids") List<Integer> ids){
        boolean b = stuClassService.removeBatchByIds(ids);
        if (b)return R.success();
        throw new ClientException("删除专业失败");
    }
}
