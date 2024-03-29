package com.github.wjxiu.controller;


import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.StuClassDO;
import com.github.wjxiu.DO.StudentCourseClassTeacherDO;
import com.github.wjxiu.DO.TeacherDO;
import com.github.wjxiu.DTO.Req.TeacherPageReq;
import com.github.wjxiu.DTO.Resp.TeacherPageResp;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.R;
import com.github.wjxiu.service.TeacherService;
import com.github.wjxiu.utils.PasswordUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2024-01-08
 */
@RestController
@Slf4j
@Validated
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/list")
    public R<PageInfo<TeacherPageResp>> list(TeacherPageReq teacher, Integer pageNum, Integer pageSize) {
        PageInfo<TeacherPageResp> res = teacherService.selectTeacherList(teacher, pageNum, pageSize);
        return R.success(res);
    }
    @GetMapping("/{id}")
    public R getinfo(@PathVariable("id") Integer id){
        TeacherDO teacherDO = teacherService.getById(id);
        teacherDO.setPassword("");
        return R.success(teacherDO);
    }

    /**
     * 获取教师所教授的全部班级
     * @param teacherIds
     * @return
     */
    @GetMapping("/getTeacherClasses/{teacherIds}")
    public R getTeacherClasses(@PathVariable Integer teacherIds){
       List<StuClassDO> list= teacherService.getTeacherClasses(teacherIds);
        return R.success(list);
    }
    /**
     * 获取教师所教授的全部课程
     * @param teacherIds
     * @return
     */
    @GetMapping("/getTeacherCourse/{teacherIds}")
    public R getTeacherCourse(@PathVariable Integer teacherIds){
        List<StuClassDO> list= teacherService.getTeacherCourse(teacherIds);
        return R.success(list);
    }

    /**
     * 获取教师教授班级的年份列表
     * @param teacherIds
     * @return
     */
    @GetMapping("/getTeacherYear/{teacherIds}")
    public R getTeacherYear(@PathVariable Integer teacherIds){
        List<Integer> list= teacherService.getTeacherYear(teacherIds);
        return R.success(list);
    }
    @PostMapping
    public R save(@Validated @RequestBody TeacherDO teacherDO){
        teacherDO.setPassword(PasswordUtil.hashPassword(teacherDO.getPassword()));
        boolean save = teacherService.save(teacherDO);
        if (save)return R.success(teacherDO);
        throw new ClientException("新增教师失败");
    }
    @PutMapping
    public R update(@Validated @RequestBody TeacherDO teacherDO){
        boolean save = teacherService.updateById(teacherDO);
        if (save)return R.success();
        throw new ClientException("修改教师失败");
    }
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable("ids") List<Integer> ids){
        boolean b = teacherService.removeBatchByIds(ids);
        if (b)return R.success();
        throw new ClientException("删除教师失败");
    }
    /**
     * 导出【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:teacher:export')")
//    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, TeacherDO teacher) {
//        List<Teacher> list = teacherService.selectTeacherList(teacher);
//        ExcelUtil<Teacher> util = new ExcelUtil<Teacher>(Teacher.class);
//        util.exportExcel(response, list, "【请填写功能名称】数据");
//    }
//
//    /**
//     * 获取【请填写功能名称】详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('system:teacher:query')")
//    @GetMapping(value = "/{id}")
//    public AjaxResult getInfo(@PathVariable("id") Long id) {
//        return success(teacherService.selectTeacherById(id));
//    }
//
//    /**
//     * 新增【请填写功能名称】
//     */
//    @PreAuthorize("@ss.hasPermi('system:teacher:add')")
//    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody Teacher teacher) {
//        return toAjax(teacherService.insertTeacher(teacher));
//    }
//
//    /**
//     * 修改【请填写功能名称】
//     */
//    @PreAuthorize("@ss.hasPermi('system:teacher:edit')")
//    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody Teacher teacher) {
//        return toAjax(teacherService.updateTeacher(teacher));
//    }
//
//    /**
//     * 删除【请填写功能名称】
//     */
//    @PreAuthorize("@ss.hasPermi('system:teacher:remove')")
//    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids) {
//        return toAjax(teacherService.deleteTeacherByIds(ids));
//    }
}
