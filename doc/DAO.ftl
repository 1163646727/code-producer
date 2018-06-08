package ${package_name}.repository.mybatis;

import com.evada.de.common.annotation.mybatis.MyBatisRepository;
import ${package_name}.dto.${table_name}DTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 * 描述：${table_annotation}DAO 层
 * @author ${author}
 * @date ${date}
 */
@MyBatisRepository
public interface ${table_name}DAO {

    ${table_name}DTO findDTOById(@Param("id")String id);


    /**
    * 描述：查询${table_annotation}列表以及高级搜索(分页)
    * @param page  分页参数
    * @param ${table_name?uncap_first}DTO  ${table_annotation}DTO
    */
    Page<${table_name}DTO> find${table_name}Page(${table_name}DTO ${table_name?uncap_first}DTO, Pageable page);

}
