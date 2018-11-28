本示例中主要讲解二种连接方式：
1、使用纯java方式连接redis
2、基于spring和redis连接步骤如下：
   1)配置applicationContext.xml文件，增加如下：
     xmlns:cache="http://www.springframework.org/schema/cache"
     http://www.springframework.org/schema/cache
     http://www.springframework.org/schema/cache/spring-cache-3.1.xsd
   2)在applicationContext.xml中配置redis管理redisCacheManager的B
