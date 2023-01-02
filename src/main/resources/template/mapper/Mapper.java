package ${package_mapper};
import ${package_model}.${Table};


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 林学文
 */
public interface ${Table}Mapper extends BaseMapper<${Table}> {

}
//继承BaseMapper可以省略xml的编写
