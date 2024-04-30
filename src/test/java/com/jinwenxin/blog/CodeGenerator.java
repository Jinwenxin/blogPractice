//package com.jinwenxin.blog;
//
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//
//public class CodeGenerator {
//    public static void main(String[] args) {
//        AutoGenerator generator = new AutoGenerator(
//                // 设置数据源
//                new DataSourceConfig.Builder("jdbc:mysql://localhost:3306/vueblog?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC", "root", "jinwenxin123!").build()
//        );
//
//        generator.global(
//                // 全局配置
//                new GlobalConfig.Builder()
//                        /*
//                         * 输出路径
//                         * System.getProperty("user.dir") 得到的是这个项目的目录
//                         * /src/main/kotlin 是你代码的存放目录，如果你是多模块项目，记得加上你的模块名
//                         * 比如 service-oa-parent
//                         *              |- service-oa
//                         *              |- model
//                         * 你想在 service-oa 中生成，那么应该填入: System.getProperty("user.dir") + "/service-oa/src/main/kotlin"
//                         */
//                        .outputDir(System.getProperty("user.dir") + "/src/main/java")
//                        // 作者
//                        .author("jinwenxin")
//                        // 设置生成完毕后是否展开你 idea 的目录，不影响结果
//                        .disableOpenDir().build()
//        );
//        // 包信息配置
//        generator.packageInfo(
//                new PackageConfig.Builder()
//                        /*
//                         * 假定下列代码的目录结构为:
//                         * com.goxiaogle.auth
//                         *  |- controller
//                         *  |- service
//                         *      |- impl
//                         *  |- mapper
//                         *  则 com.goxiaogle 为父包，auth 为模块名
//                         */
//                        // 设置父包
//                        .parent("com.jinwenxin")
//                        // 设置模块名
//                        //.moduleName("auth")
//                        // 以下四个可以去掉，如果你的分包命名和他一样
//                        // 设置 Controller 层包名，默认就是 controller
//                        .controller("controller")
//                        // 设置 Service 层包名，默认就是 service
//                        .service("service")
//                        // 设置 Mapper 层包名，默认就是 mapper
//                        .mapper("mapper")
//                        // 设置 Entity 包名，默认就是 entity
//                        .entity("entity").build()
//        );
//        // 策略配置
//        generator.strategy(
//                new StrategyConfig.Builder()
//                        // 设置要生成代码的数据库表名，可以设置多个，如 addInclude(a, b, c)
//                        .addInclude("m_user","m_blog").addTablePrefix("m_")
//                        .serviceBuilder()
//                        // 设置生成的 service 接口命名方式，默认是 IXxxService，这里改成 XxxService
//                        // serviceBuilder() 方法建议在 build 后使用，此处偷懒直接用了
//                        .serviceBuilder().formatServiceFileName("%sService").build()
//                // 设置其它几层的内容
//                 .entityBuilder()
//                 .controllerBuilder()
//                 .mapperBuilder().build()
//        );
//        // 执行
//        generator.execute();
//    }
//}
