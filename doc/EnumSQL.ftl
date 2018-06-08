-- ${enum_annotation}枚举字典
<#if enum_list?exists>
    <#list enum_list as enum>
INSERT INTO "public"."sys_dict" ("id", "name", "code", "category_code", "parent_code", "sort_order", "is_updatable", "status")
VALUES ('${enum.enumUuid}', '${enum.enumName}', '${enum.enumCode}', '${enum.enumCategoryCode}', NULL, '${enum.enumSortOrder}', NULL, '1');
    </#list>
</#if>

-- ${enum_annotation}枚举字典分类
<#if enum_dict_catagory?exists>
INSERT INTO "public"."sys_dict_category" ("id", "name", "code", "parent_code", "sort_order", "description", "is_updatable", "status")
VALUES ('${enum_dict_catagory.enumUuid}', '${enum_dict_catagory.enumCategoryName}', '${enum_dict_catagory.enumCategoryCode}', '', '0', NULL, '0', '1');
</#if>




