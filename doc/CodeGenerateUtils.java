package com.evada.inno.pm.code.generate.util;
import com.evada.inno.pm.code.generate.model.DBColumnDefinition;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 描述：代码生成器
 * Created by Ay on 2017/5/1.
 */
public class CodeGenerateUtils {

    /** 作者 **/
    private final String AUTHOR = "Ay";
    private final String CURRENT_DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    /** 数据表名称 **/
    private final String TABLE_NAME = "ay_menu";
    private final String PACKAGE_NAME = "com.evada.pm.process.manage";
    /** 数据库表注释 **/
    private final String TABLE_ANNOTATION = "用户类";
    /** 代码生成的位置 **/
    private final String DISK_PATH = "D://";
    /** ay_test 变成 AyTest **/
    private final String CHANGE_TABLE_NAME = replaceUnderLineAndUpperCase(TABLE_NAME);

    /**
     * 描述：生成数据库连接
     */
    public Connection getConnection() throws Exception{
        //数据库连接路径
        final String URL = "jdbc:postgresql://localhost:5432/postgres";
        //用户名
        final String USER = "postgres";
        //密码
        final String PASSWORD = "root";
        //驱动名称
        final String DRIVER = "org.postgresql.Driver";
        //生成数据库连接
        Class.forName(DRIVER);
        Connection connection= DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    /**
     * 描述：代码生成器的执行入口
     * @param args
     */
    public static void main(String[] args) throws Exception{
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        codeGenerateUtils.generate();
    }

    /**
     * 描述：代码生成器的核心方法
     */
    public void generate() throws Exception{
        try {
            //1.生成数据库连接
            Connection connection = getConnection();
            //2.获取数据库的元数据：描述数据的数据
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            //3.获取表的字段结果集
            ResultSet resultSet = databaseMetaData.getColumns(null,"%", TABLE_NAME,"%");
            //4.生成Model文件
            generateModelFile(resultSet);
            //5.生成Dao文件
            generateDaoFile(resultSet);
            //6.生成Repository文件
            generateRepositoryFile(resultSet);
            //7.生成服务层接口文件
            generateServiceInterfaceFile(resultSet);
            //8.生成服务实现层文件
            generateServiceImplFile(resultSet);
            //9.生成Controller层文件
            generateControllerFile(resultSet);
            //10.生成DTO文件
            generateDTOFile(resultSet);
            //11.生成Mapper文件
            generateMapperFile(resultSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{

        }
    }

    private void generateModelFile(ResultSet resultSet) throws Exception{
        //文件的后缀
        final String suffix = ".java";
        //文件生成的路径
        final String path = DISK_PATH + CHANGE_TABLE_NAME + suffix;
        //文件的模板
        final String templateName = "Model.ftl";
        File mapperFile = new File(path);
        //数据库字段封装的列表
        List<DBColumnDefinition> columnDefinitionList = new ArrayList<>();
        DBColumnDefinition columnDefinition = null;
        while(resultSet.next()){
            //id字段略过
            if(resultSet.getString("COLUMN_NAME").equals("id")) continue;
            columnDefinition = new DBColumnDefinition();
            //设置字段名称
            columnDefinition.setColumnName(resultSet.getString("COLUMN_NAME"));
            //设置字段类别
            columnDefinition.setColumnType(resultSet.getString("TYPE_NAME"));
            //设置转化过的字段名称
            columnDefinition.setChangeColumnName(replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
            //设置字段的备注
            columnDefinition.setColumnComment(resultSet.getString("REMARKS"));
            columnDefinitionList.add(columnDefinition);
        }
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("model_column", columnDefinitionList);
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateDTOFile(ResultSet resultSet) throws Exception{
        final String suffix = "DTO.java";
        final String path = "D://" + CHANGE_TABLE_NAME + suffix;
        final String templateName = "DTO.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateControllerFile(ResultSet resultSet) throws Exception{
        final String suffix = "Controller.java";
        final String path = DISK_PATH + CHANGE_TABLE_NAME + suffix;
        final String templateName = "Controller.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateServiceImplFile(ResultSet resultSet) throws Exception{
        final String suffix = "ServiceImpl.java";
        final String path = DISK_PATH + CHANGE_TABLE_NAME + suffix;
        final String templateName = "ServiceImpl.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateServiceInterfaceFile(ResultSet resultSet) throws Exception{
        final String prefix = "I";
        final String suffix = "Service.java";
        final String path = DISK_PATH + prefix + CHANGE_TABLE_NAME + suffix;
        final String templateName = "ServiceInterface.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateRepositoryFile(ResultSet resultSet) throws Exception{
        final String suffix = "Repository.java";
        final String path = DISK_PATH + CHANGE_TABLE_NAME + suffix;
        final String templateName = "Repository.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateDaoFile(ResultSet resultSet) throws Exception{
        final String suffix = "DAO.java";
        final String path = DISK_PATH + CHANGE_TABLE_NAME + suffix;
        final String templateName = "DAO.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);

    }

    private void generateMapperFile(ResultSet resultSet) throws Exception{
        final String suffix = "Mapper.xml";
        final String path = DISK_PATH + CHANGE_TABLE_NAME + suffix;
        final String templateName = "Mapper.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);

    }

    private void generateFileByTemplate(final String templateName,File file,Map<String,Object> dataMap) throws Exception{
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        //ay_test
        dataMap.put("table_name_small", TABLE_NAME);
        //AyTest
        dataMap.put("table_name", CHANGE_TABLE_NAME);
        //Ay
        dataMap.put("author",AUTHOR);
        //当前日期
        dataMap.put("date",CURRENT_DATE);
        //包名
        dataMap.put("package_name", PACKAGE_NAME);
        //表注释
        dataMap.put("table_annotation", TABLE_ANNOTATION);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);
    }

    /**
     * 描述：字符去掉下划线和转化字母的大小写
     */
    public String replaceUnderLineAndUpperCase(String str){
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while(count!=0){
            int num = sb.indexOf("_",count);
            count = num + 1;
            if(num != -1){
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count , count + 1,ia + "");
            }
        }
        String result = sb.toString().replaceAll("_","");
        return StringUtils.capitalize(result);
    }

}









