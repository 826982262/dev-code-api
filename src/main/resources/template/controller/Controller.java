package ${package_controller};

//import com.nev.demo.model.UserRecord;
import ${package_service}.${Table}Service;
import ${package_model}.${Table}Record;

<#if swagger==true>import io.swagger.annotations.*;</#if>

import com.github.pagehelper.Page;
import com.nev.base.entity.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

<#if swagger==true>@Api(value = "${Table}Controller")</#if>

@RestController
@RequestMapping("/api/curd/${table}")
public class UserController {

    @Resource
    private ${Table}Service ${table}Service;

    /***
     * 新增${Table}数据
     * @param ${table}
     * @return
     */
    @ApiOperation(value = "${Table}添加",notes = "添加${Table}方法详情",tags = {"${Table}Controller"})
    @PostMapping
    @ApiOperation(value = "新建 User",response = UserRecord.class)
    public Result create(@RequestBody  <#if swagger==true>@ApiParam(name = "${Table}对象",value = "传入JSON数据",required = true)</#if> ${Table}Record ${table}Record)){

        return Result.SUCCESS(${table}Service.add(${table}Record));
    }

    /***
     * ${Table}多条件分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping("")
    @ApiOperation(value = "${Table}分页查询",notes = "分页查询${Table}方法详情",tags = {"${Table}Controller"})

    public Result select(@RequestBody(required = false) ${Table}Record ${table}Record),
                         @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize){
        Page page = new Page();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        return  Result.SUCCESS(${table}Service.selectList(${table}Record, page));
    }
    /***
     * 修改${Table}数据
     * @param ${table}
     * @param id
     * @return
     */
    @PutMapping
    @ApiOperation(value = "更新${Table}",response = ${Table}Record.class)
    public Result update(@RequestBody ${Table}Record entity){
        return Result.SUCCESS(${table}Service.update(entity));
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    <#if swagger==true>
    @ApiOperation(value = "删除 ${Table}",response = ${Table}Record.class)
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "${keyType}")
    </#if>
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        return Result.SUCCESS(${table}Service.deleteById(id));
    }

}

