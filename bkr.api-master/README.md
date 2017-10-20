# bkr.api 基于 springBoot spring-data-jpa queryDSL
# 默认数据库H2 用法参见：http://www.h2database.com/html/quickstart.html
1.将 bkr.api, bkr.base, bkr.core下载到同一目录下
2.安装 lombok (未安装过的场合)
    2.1。 找到本地maven仓库下 下载的lombok-1.16.18。jar
    2.2。 执行 java -jar lombok-1.16.18。jar 在安装界面中 选择使用的Ecplise的目录后安装
    2.2。重启Ecplise
    2.3。执行maven upadte
3.本地 启动
    3，1 选中bkr.api\src\main\java\bkr\BkrApplication.java 执行 run as Java Appliaction