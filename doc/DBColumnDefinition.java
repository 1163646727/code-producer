package com.evada.inno.pm.code.generate.model;

/**
 * className: DBColumnDefinition <BR>
 * description: 数据库字段封装类<BR>
 * remark: <BR>
 * author: ChenQi <BR>
 * createDate: 2019-11-26 20:28 <BR>
 */
public class DBColumnDefinition {

    /** 数据库的字段名称，例如 id **/
    private String columnName;
    /** 数据库字段类型，例如 varchar **/
    private String columnType;
    /** 数据库字段首字母小写且去掉下划线字符串，例如create_time转成CreateTiem **/
    private String changeColumnName;
    /** 数据库字段注释,例如create_time的注解是'创建时间' **/
    private String columnComment;

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getChangeColumnName() {
        return changeColumnName;
    }

    public void setChangeColumnName(String changeColumnName) {
        this.changeColumnName = changeColumnName;
    }
}
