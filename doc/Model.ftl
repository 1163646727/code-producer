package ${package_name}.model;
import com.evada.inno.common.domain.BaseModel;
import com.evada.inno.common.listener.ICreateListenable;
import com.evada.inno.common.listener.IDeleteListenable;
import com.evada.inno.common.listener.IModifyListenable;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.Date;

/**
* className: ${table_name} <BR><!--table_name:数据表名称转成实体类格式的名称，例如：ay_test 变成 AyTest-->
* description: ${table_annotation}实体类<BR><!-- table_annotation:数据库表注释 -->
* remark: <BR>
* author: ${author} <BR>
* createDate: ${date} <BR>
*/
@Entity
@Table(name="${table_name_small}")<!--table_name_small:数据表名称，例如：ay_test-->
public class ${table_name} extends BaseModel{

<#if model_column?exists><!--model_column:参数集合-->
  <#list model_column as model>
    /** ${model.columnComment!} ${author}*/
    <!--根据字段的类型，创建对应的属性-->
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
<!--生成get、set方法-->
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
