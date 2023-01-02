package ${package_service_impl};

import ${package_model}.${Table};
import ${package_model}.${Table}Record;
import ${package_service}.${Table}Service;
import ${package_mapper}.${Table}Mapper;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nev.base.entity.CommonCode;
import com.nev.base.exception.ExceptionCast;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ${Table}ServiceImpl implements ${Table}Service {


    @Resource
    ${Table}Mapper ${table}Mapper;

    @Override
    public ${Table}Record add(${Table}Record ${table}Record) {
        if (${table}Mapper.insert(${table}Record) == 0) {
            ExceptionCast.cast(CommonCode.FAIL);
        }

        return  ${table}Record;

    }

    @Override
    public Integer deleteById(Long id) {
        Integer result =  ${table}Mapper.deleteById(id);
        if (result == 0) {
            ExceptionCast.cast(CommonCode.FAIL);
        }
        return result;
    }

    @Override
    public PageInfo selectList(${Table}Record ${table}Record, Page page) {

        PageHelper.startPage(page.getPageNum(), page.getPageSize());
            QueryWrapper<${Table}> queryWrapper = new QueryWrapper<>();
            if (ObjectUtil.isNotNull(${table}Record) ) {
            <#list models as model>
                queryWrapper.like(${table}Record.get${model.upperName}()!=null,"${model.column}",${table}Record.get${model.upperName}());
            </#list>
            }

        List list = ${table}Mapper.selectList(queryWrapper);
        return new PageInfo(list);
    }



    @Override
    public ${Table}Record update(${Table}Record ${table}Record) {
        if (${table}Mapper.updateById(${table}Record) == 0) {
            ExceptionCast.cast(CommonCode.FAIL);
        }

        return ${table}Record;
    }


}
