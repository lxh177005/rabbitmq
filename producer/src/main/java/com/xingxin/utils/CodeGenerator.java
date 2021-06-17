package com.xingxin.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {
    public static void main(String[] args) {
        //1、全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig
                .setActiveRecord(false)
                .setEnableCache(false)
                //这个位置的位置是自己项目的路径到java文件夹下
                .setOutputDir("D:\\codeGenerator")
                //覆盖生成的文件
                .setFileOverride(true)
                .setIdType(IdType.INPUT)
                .setServiceName("%sService")
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setAuthor("liuxh")
                .setOpen(false);
        //2、数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        //这套配置是Oracle的配置
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/rabbitmq?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                //数据库登录名
                .setUsername("root")
                //数据库密码
                .setPassword("root")
                .setDbType(DbType.MYSQL);

        //3、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setNaming(NamingStrategy.underline_to_camel)
//                TODO
                //需要导入的表的名称,支持多表，"，"隔开
                .setInclude("record_message")
                //需要导入表删除前缀（如：xxx_xx,删除完前缀是xx,只剩下表名）
                .setTablePrefix("");

        //4、包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        //这个需要改成自己项目的位置
        packageConfig.setParent("com.xingxin")
                //Dao层的文件
                .setMapper("dao")
                //service层的文件
                .setService("service")
                //controller层的文件
                .setController("controller")
                //实体类的文件
                .setEntity("entity")
                //xml的文件
                .setXml("mapper");

        //5、整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);
        //6、执行
        autoGenerator.execute();
        System.out.println("success");
    }
}