package ${package_service};
import ${package_model}.${Table}Record;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

public interface ${Table}Service {
    /***
     * 新增${Table}
     * @param ${table}
     */
    ${Table}Record add(${Table}Record ${table}Record );

    /***
     * 删除${Table}
     * @param id
     */
    Integer deleteById(Long id);

    /***
     * ${Table}多条件分页查询
     * @param ${table}
     * @param page
     * @param size
     * @return
     */
    PageInfo selectList(${Table}Record ${table}Record, Page page);
    /***
     * 修改${Table}数据
     * @param ${table}
     */
    ${Table}Record update(${Table}Record ${table}Record);

//    Page<User> page( Page<User> page,Object data);
}
