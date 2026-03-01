# 凭证抽查管理系统（含 OCR 流程）

这是一个面向“凭证抽查与审计协同”的全栈 Web 项目。该项目最初完成于毕业阶段，现已整理为可用于求职展示的作品集版本。

## 0）项目动机
这个项目的想法来自我担任**审计助理实习生**时的真实工作内容。  
当时大量凭证核查工作重复、枯燥且耗时，我认为其中很大一部分可以自动化处理。因此我设计并实现了这个系统，希望减少重复劳动、提升核查一致性，并提高审计流程效率。

## 1）项目摘要
系统面向 **实习生、审计师、管理员** 三类角色，支持凭证抽查全流程协作。

核心能力：
- 凭证主数据管理与抽查任务管理
- 跨角色分配审计职责
- 上传凭证附件/图片并维护证据链
- 触发 OCR 识别相关流程
- 记录核查结果与流程状态

技术上采用 Java 后端（Spring Boot + MyBatis）、Spring Boot 托管静态前端页面（含 Vue 使用）以及 MySQL 关系型数据库。

## 2）系统架构（SDE 视角）
### 后端
- 框架：`Spring Boot 2.1.14.RELEASE`
- 语言：`Java 8`
- 持久层：`MyBatis` + `tk.mybatis Mapper` + `PageHelper`
- 分层方式：Controller / Service / DAO
- 核心模块：
  - `controller`：REST 与页面路由入口
  - `service`：业务逻辑编排
  - `dao`：SQL 映射与数据库访问
  - `domain`：实体模型（`Vouchers`、`CheckVouInfo`、`Auditor`、`Intern`、`User`）

### 前端
- 交付方式：Spring Boot 直接托管静态 HTML 页面
- 使用库：`Vue`、`Axios`、`jQuery`、`ECharts`
- 典型角色入口：
  - `indexI.html`（实习生）
  - `indexA.html`（审计师）
  - `indexM.html`（管理员）

### 数据库 / SQL
- 数据库：`MySQL`
- 主脚本：`database/sql/spot_check_voucher2.sql`、`database/sql/spot_check_voucher.sql`
- 核心表：
  - `vouchers`：凭证基础信息
  - `auditor_manage`：审计师侧任务管理与分配状态
  - `check_vou_info`：实习生侧抽查结果明细
  - `auditor`、`intern`：角色人员信息

## 3）分层请求流程
1. 前端发起 JSON / 表单请求
2. `Controller` 接收并校验参数
3. `Service` 执行业务规则与组合逻辑
4. `DAO` 通过 MyBatis 执行 SQL
5. 统一返回 `Result` / `PageResult`

## 4）功能范围
- 登录与会话（按角色）
- 凭证增删改查与分页检索
- 实习生信息与分配管理
- 审计师信息与分配管理
- 抽查记录增删改查与识别触发
- 附件上传与删除

## 5）仓库结构（已整理为英文目录）
```text
.
├── README.md
├── README.en.md
├── README.zh-CN.md
├── database/
│   └── sql/                     # 数据库脚本
├── docs/
│   └── design_assets/           # UML/流程/设计文档（已改为英文文件名）
├── project/
│   └── spot_check_voucher/      # 主应用
├── MLTest/                      # OCR 相关实验代码
└── Test2/                       # 历史 C++ 测试代码
```

## 6）主要后端接口（示例）
- `POST /LoginSuccess`：登录
- `GET /getUserInfo`：会话用户信息
- `/spotCheckVoucher/findv|searchv|addv|updatev|delv`：凭证管理
- `/spotCheckVoucher/find|search|add|update|del|identify`：抽查明细
- `/spotCheckVoucher/finda|searcha|adda|updatea|dela`：审计师管理
- `/spotCheckVoucher/findi|searchi|addi|updatei|deli`：实习生管理
- `/voucheck/fileupload`：上传凭证图片
- `/voucheck/delfile`：删除已上传文件

## 7）图示说明（英文文件名 + 中文解释）
说明：图内文字多数仍为中文；本次已将文件名改为英文，并补充中文解释，便于双语展示。

- `docs/design_assets/vouchers-main-business-overview.png`：凭证抽查核心业务总览
- `docs/design_assets/vouchers-main-business-sequence.png`：主流程时序图（用户到数据层）
- `docs/design_assets/vouchers-main-business-activity.png`：主业务活动图
- `docs/design_assets/vouchers-er-diagram.png`：核心数据表 ER 图
- `docs/design_assets/vouchers-component-diagram.png`：系统组件图
- `docs/design_assets/deployment-diagram.png`：部署视图
- `docs/design_assets/vouchers-ocr-api-use-case.png`：OCR 接口调用用例图
- `docs/design_assets/vouchers-user-upload-sequence.png`：用户上传图片时序图
- `docs/design_assets/vouchers-user-input-url-flow.png`：用户输入图片地址流程图
- `docs/design_assets/vouchers-user-click-identify-sequence.png`：点击识别按钮后的时序图

## 8）本次技术整理与改进
- 仓库结构与命名标准化（英文化）：
  - `design_file` -> `docs/design_assets`
  - `sql_code` -> `database/sql`
  - 原中文设计文件名重命名为英文
- 后端稳定性改进：
  - 修复 Session 超时单位问题（`30` 秒 -> `30 * 60` 秒）
  - 登录逻辑增加空值校验，降低空指针风险
  - 用户身份展示统一为英文角色映射（便于对外展示）
  - 用户信息查询减少重复 DAO 调用
  - 文件删除接口路径解析修复为环境无关方式

## 9）遗留约束与升级建议
该项目保留了“历史项目 + 增量改良”的形态，便于展示你的工程成长。

建议的下一步升级：
1. 升级到 `Spring Boot 3.x` + `Java 17`
2. 登录改造为 Spring Security + BCrypt
3. 前端迁移到 `Vue 3 + Vite + TypeScript`
4. 文件存储迁移到对象存储（S3/OSS/MinIO）
5. 增加集成测试与 CI 流程
6. 清理 `pom.xml` 重复依赖与潜在安全风险依赖

## 10）快速运行（历史环境）
### 环境要求
- JDK 8
- Maven 3.6+
- MySQL 8.x

### 启动
```bash
cd project/spot_check_voucher
# 先执行 database/sql/spot_check_voucher2.sql 建表
./mvnw spring-boot:run
```

默认端口：`8888`

登录页：
- `http://localhost:8888/login.html`
