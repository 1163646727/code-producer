package ${package_name}.service;
import com.evada.inno.core.service.IBaseService;
import ${package_name}.model.${table_name};
import ${package_name}.dto.${table_name}DTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
* 描述：${table_annotation} 服务实现层接口
* @author ${author}
* @date ${date}
*/
public interface I${table_name}Service extends IBaseService<${table_name},String> {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    ${table_name}DTO findDTOById(String id)throws Exception;

    ${table_name}DTO create${table_name}(${table_name}DTO ${table_name?uncap_first}DTO) throws Exception;

    ${table_name}DTO update${table_name}(${table_name}DTO ${table_name?uncap_first}DTO) throws Exception;

    Page<${table_name}DTO> find${table_name}Page(${table_name}DTO ${table_name?uncap_first}DTO, Pageable page) throws Exception;

}
