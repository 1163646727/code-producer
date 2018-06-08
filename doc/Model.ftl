package ${package_name}.model;
import com.evada.inno.common.domain.BaseModel;
import com.evada.inno.common.listener.ICreateListenable;
import com.evada.inno.common.listener.IDeleteListenable;
import com.evada.inno.common.listener.IModifyListenable;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.Date;

/**
* 描述：${table_annotation}模型
* @author ${author}
* @date ${date}
*/
@Entity
@Table(name="${table_name_small}")
public class ${table_name} extends BaseModel{

    <#if model_column?exists>
    <#list model_column as model>
    /**
    *${model.columnComment!}
    */
    <#if (model.columnType = 'varchar' || model.columnType = 'text')>
    @Column(name = "${model.columnName}",columnDefinition = "VARCHAR")
    private String ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnType = 'timestamp' >
    @Column(name = "${model.columnName}",columnDefinition = "TIMESTAMP")
    private Date ${model.changeColumnName?uncap_first};

    </#if>

    <#if model.columnType = 'numeric' >
    @Column(name = "${model.columnName}",columnDefinition = "DECIMAL")
    private Float ${model.changeColumnName?uncap_first};

    </#if>
        </#list>
    </#if>

<#if model_column?exists>
<#list model_column as model>
<#if (model.columnType = 'varchar' || model.columnType = 'text')>
    public String get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(String ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
</#if>
<#if model.columnType = 'timestamp' >
    public Date get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Date ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
</#if>
    <#if model.columnType = 'numeric' >
    public Float get${model.changeColumnName}() {
    return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Float ${model.changeColumnName?uncap_first}) {
    this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
    </#if>
</#list>
</#if>

}
