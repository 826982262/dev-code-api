package ${package_entity};

<#if swagger==true>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
<#list typeSet as set>
import ${set};
</#list>
import java.io.Serializable;
<#if swagger==true>
@ApiModel(description = "${Table}",value = "${Table}")
</#if>
@Data
@TableName("${TableName}")
public class ${TableName} implements Serializable{

<#list models as model>
<#if swagger==true>
@ApiModelProperty(value = "${model.desc!""}",required = false)
</#if>
<#if model.id==true>

@TableId(type = IdType.AUTO)
@Id
<#if model.identity=='YES'>
@GeneratedValue(strategy = GenerationType.IDENTITY)
</#if>
</#if>
@TableField("${model.column}")
private ${model.simpleType} ${model.name};//${model.desc!""}

</#list>



}
