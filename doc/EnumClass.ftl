package ${package_name}.enums;

/**
* 描述：${enum_annotation}类型枚举
* @author ${author}
* @date ${date}
*/
public enum ${enum_class_name}Enum {

<#if enum_list?exists>
    <#list enum_list as enum>
    ${enum.enumCode}("${enum.enumNumber}","${enum.enumName}")<#if enum_index == ((enum_list?size) - 1)>;<#else >,</#if>
    </#list>
</#if>


    private final String code;

    private final String name;

    ${enum_class_name}Enum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
