# SQL-Generator - 数据生成器

> 作者：[Mredust](https://github.com/Mredust)




## 项目背景

解决自己开发项目时反复写 SQL 建表和造数据的麻烦



## 应用场景

1. 通过填写可视化表单的方式，快速生成建表语句、模拟数据和代码，告别重复工作！

2. 支持多种快捷导入方式。比如已经有现成的数据表，可以直接导入建表语句，一键生成模拟数据。

3. 支持多种生成模拟数据的规则。比如固定值、随机值、正则表达式、递增值，甚至还支持选择词库来生成特定范围内的随机值！

4. 支持词库、表设计。直接使用现成的库表和字段，一键生成或进行二次开发。

5. 可以直接使用现成的词库来建立字典表，或者作为研究用的数据集，并支持二次完善词库！



## 页面预览

![](https://raw.githubusercontent.com/Mredust/images/main/file/7a88cde3e1b716e9331b7d3223f2730.png)

![](https://raw.githubusercontent.com/Mredust/images/main/file/a2e5b590eebf93359758d1b2ca9c8e6.png)

![](https://raw.githubusercontent.com/Mredust/images/main/file/e9fc0675a956a78dc8b553df79e7847.png)

![](https://raw.githubusercontent.com/Mredust/images/main/file/f72769e8a3e283800f82d66d8dd5401.png)



## 模块设计

数据库设计含有敏感数据，估不设计用户模块以及建站，用户只需拉取代码，自行部署使用，如不想自行部署可以在线使用[鱼皮：SQL之父](http://sqlfather.yupi.icu/)

### 前端管理

- 可视化建表
- 快捷导入建表
  - 导入表
  - 导入配置
  - 导入建表 SQL
- 一键生成
  - SQL 建表、插入数据语句
  - 模拟数据
  - JSON 数据
- 多种模拟数据生成规则
  - 固定值
  - 随机值
    - 人名
    - 邮箱
    - 手机号
  - 定制词库
- 词库共享
  - 创建词库
  - 一键创建字典表
  - 根据词库生成模拟数据
- 表信息共享
  - 创建表信息
  - 一键复制建表语句
  - 一键导入表



### 管理后台

- 词库管理
- 表信息管理
- SQL生成管理



## 技术栈

### 前端

主要技术：

- React 18
- Umi 4.x
- Ant Design 4.x 组件库
- Ant Design Pro Components 高级组件
- TypeScript 类型控制
- Eslint 代码规范控制
- Prettier 美化代码

依赖库：

- monaco-editor 代码编辑器
- copy-to-clipboard 剪切板复制



### 后端

主要技术：

- Spring Boot 2.7.x
- MyBatis Plus 3.5.x
- MySQL 8.x

依赖库：

- Druid：SQL 解析
- datafaker：模拟数据
- Apache Commons Lang3：工具库
- Hutool：工具库
- Gson：Json 解析



## 快速启动

### 后端

1. 运行 sql 目录下的 create_table.sql 建表
2. 修改 application.yml 中的数据库地址为自己的
3. 安装完 Maven 依赖后，直接运行即可
4. 已经编写好了 Dockerfile，支持 Docker 镜像部署。



### 前端

安装依赖：

```bash
npm run install
```

运行：

```bash
npm run dev
```

打包：

```bash
npm run build
```





## 核心架构设计

核心设计理念：将各输入方式统一为明确的 Schema，并根据 Schema 生成各类内容。

系统分为以下几个核心模块，各模块职责分明：



### 统一生成

对多种生成规则定义门面，使用 GeneratorFacade对多个 Builder进行统一的创建和管理。

- 外观模式（GeneratorFacade）：统一生成

  - 对架构数据校验

  - 根据前端传递参数生成对应结果

  - 统一封装返回

  

### Schema 构造器

核心类：TableSchemaBuilder，作用是将不同的参数统一收敛为 TableSchema 对象。



### 多种生成类型

每种方言规则定义为一个 sql目录，使用 SQLDialectFactory对多个 方言进行统一的创建和管理。

- 创建者模式（SQLDialectFactory）：单例模式（懒汉式）+工厂模式

- 策略模式（SQLBuilder）：SQL构建器
  - 单例模式（饿汉式）
  - 根据不同SQL方言生成对应SQL语句



### 多种模拟数据生成规则

每种生成规则定义为一个 Generator，使用 DataGeneratorFactory对多个 Generator 实例进行统一的创建和管理。

- 创建者模式（DataGeneratorFactory）：单例模式（饿汉式）+工厂模式

- 策略模式（DataBuilder）

  - 单例模式（懒汉式）
  - 根据不同模拟数据类型调用不同的生成数据方法





## 致谢

源码借鉴：[程序员鱼皮](https://github.com/liyupi)

