# Springboot+MP实现用户CRUD操作

## 一. 搭建项目架构

### 1. 统一版本

>```text
>Springboot版本：2.7.15
>JDK17
>Maven3.9.4
>IDEA2022.3
>MySQL服务器8.0版本
>```

### 2. 项目架构如图：

![1709864841150](C:\Users\ADMINI~1\AppData\Local\Temp\1709864841150.png)

##二.集成MP

### 1. 添加依赖

```xml
      <!--MP的依赖-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.3.2</version>
        </dependency>
 		<!--MySql连接驱动依赖-->
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!--lombok依赖-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!--MP代码生成器依赖-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.5.3.2</version>
        </dependency>
        <!--FreeMarker 模板依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-freemarker</artifactId>
        </dependency>
```

### 2. 配置文件配置

```pro
#项目根路径
server.servlet.context-path=/ums

#配置数据源
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=root
spring.datasource.password=ysd123

#MP配置
#配置起别名的包
mybatis-plus.type-aliases-package=com.example.ums.entity
#配置映射文件的路径
mybatis-plus.mapper-locations=mapper/**.xml
#打印sql日志
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl

#配置 Date 转为 字符串
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
#时间为东八区
spring.jackson.time-zone=GMT+8
#MP逻辑删除的全局配置
# 全局逻辑删除的实体属性名
mybatis-plus.global-config.db-config.logic-delete-field=deleted
# 逻辑已删除值(默认为 1)
mybatis-plus.global-config.db-config.logic-delete-value=1
# 逻辑未删除值(默认为 0)
mybatis-plus.global-config.db-config.logic-not-delete-value=0
```

## 三.完成增删改查操作

### 1. 准备用户表脚本

```sql
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `birthday` date DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '0：未删除 1：已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
```



### 2. 准备自动生成代码的工具类

```java
public class CodeGenerator {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/test";
        String username="root";
        String password="ysd123";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("qinkui") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                           // .fileOverride() // 覆盖已生成文件
                            //System.getProperty("user.dir") :表示当前项目的路径：
                            .outputDir(System.getProperty("user.dir")+"/src/main/java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.example") // 设置父包名
                            .moduleName("ums") // 设置父包模块名
                            //默认：实体类放到entity包; mapper接口放到 mapper包，service接口放到 service包下
                            // service接口实现类 service/impl 目录下;contorller控制器类放在controller包下
                            .serviceImpl("serviceImpl") //自定义 service接口实现类 存放在哪个包下
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir")+"/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                   /* builder.addInclude("t_student","city") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀*/
                    builder.addInclude("user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
                System.out.println("代码生成完毕！...");
    }
}
```

### 3.准备结果集工具类 

```java
public class ResultUtil {

    private Integer code;//状态码

    private Boolean status; //成功或者失败的标识：true,false

    private String msg;// 说明

    private Object data;// 结果数据
    /*
     * 构造参数私有化：外部不能直接通过 new 创建对象了
     * */
    private ResultUtil() {
    }

    private ResultUtil(Integer code, Boolean status, String msg, Object data) {
        this.code = code;
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回
     * */
    public static ResultUtil isSuccess(Object data){
        return new ResultUtil(20000,true,"操作成功",data);
    }

    /**
     * 成功返回,可以传递msg
     * */
    public static ResultUtil isSuccess(String msg,Object data){
        return new ResultUtil(20000,true,msg,data);
    }

    /**
     * 失败返回
     * */
    public static ResultUtil isFail(Integer code,String msg){
        return new ResultUtil(code,false,msg,null);
    }
}
```

### 4. 准备控制器

```java
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;
    ......
}
```

####4.1 查询所有的未被删除的用户信息

```java
 @RequestMapping("/getAllUser")
    public Object getAllUser(){
        List<User> users = userMapper.selectList(null);
        return ResultUtil.isSuccess(users);
    }
```

#### 4.2 添加新用户

```java
   @PostMapping("/addUser")
    public Object addUser(@RequestBody User user){
          int i =userMapper.insert(user);
          if(i==1){
              return ResultUtil.isSuccess("添加成功",null);
          }else{
              return ResultUtil.isFail(500,"添加失败");
          }
    }
```

#### 4.3 更新用户

```java
@PostMapping("/updateUser")
    public Object updateUser(@RequestBody User user){
        int i =userMapper.updateById(user);
        if(i==1){
            return ResultUtil.isSuccess("更新成功",null);
        }else{
            return ResultUtil.isFail(500,"更新失败");
        }
    }
```

#### 4.4 删除用户

```java
    @PostMapping("/delUser")
    public Object delUser(Integer userId){
        int i =userMapper.deleteById(userId);
        if(i==1){
            return ResultUtil.isSuccess("删除成功",null);
        }else{
            return ResultUtil.isFail(500,"删除失败");
        }
    }
```

#### 4.5 多条件分页查询

##### 4.5.1 准备条件查询类

```java
@Data
public class UserSearch {
    private String userName;//用户名关键字

    private Integer roleId;//角色

    private Integer pageNo;//当前页

    private Integer pageSize;//每页显示的条数
}
```

##### 4.5.2 编写分页插件配置类

```java
@Configuration
@MapperScan("com.example.ums.mapper")
public class MybatisPlusConfig {
    // 最新版
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
```

##### 4.5.3 多条件分页查询

```java
    @RequestMapping("/getAllUserByCon")
    public Object getAllUserByCon(@RequestBody UserSearch userSearch){
        Page<User> page=new Page<>(userSearch.getPageNo(),userSearch.getPageSize());
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        if(userSearch.getUserName()!=null&&!userSearch.getUserName().equals("")){
            wrapper.like("username",userSearch.getUserName());
        }
       if(userSearch.getRoleId()!=null){
           wrapper.eq("roleId",userSearch.getRoleId());
       }
        List<User> users = userMapper.selectList(page, wrapper);//多条件分页后的数据
        System.out.println("总记录数："+page.getTotal());
        return ResultUtil.isSuccess(users);
    }
```





