import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * 纯java方式连接redis
 */
public class RedisTest {
    public static void main(String[] args) {
        //setStr();//字符串保存
        getStr();//字符串获取
        //lpush();//list保存
        //lrange();//list获取
        //hmset();//map保存
        //hmget();//map获取
    }

    /**
     * 保存字符串类型的数据
     */
    public static void setStr() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        //使用字符串string存值
        jedis.set("city", "南京");
    }
    /**
     * 取字符串类型的数据
     */
    public static void getStr() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        //取字符串string存值
        String city = jedis.get("city");
        System.out.println(city);
    }

    /**
     * 保存list类型的数据
     */
    public static void lpush() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        //使用字符串list存值
        jedis.lpush("city1", "南京");
        jedis.lpush("city1", "上海");
        jedis.lpush("city1", "苏州");
        jedis.lpush("city1", "北京");
        jedis.lpush("city1", "南通");
    }
    /**
     * 取list类型的数据
     */
    public static void lrange() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        //list集合取值,这里注意的是,100的位置是结束的角标,如果大了没事,小了的话就会缺
        List<String> arr = jedis.lrange("city1", 0, 100);
        System.out.println(arr.size());
        for (String string : arr) {
            System.out.println(string);
        }
    }

    /**
     * 存入Map的值
     * map类型存值又叫Redis hash ,是一个string类型的field和value的映射表
     */
    public static void hmset(){
        //连接本地的jedis服务器
        Jedis jedis=new Jedis("localhost");

        //Redis hash 是一个string类型的field和value的映射表，hash特别适合用于存储对象。
        //这里要求的是map必须是key和value都是string类型的
        Map<String, String> map=new HashMap<String, String>();
        map.put("name", "小明");
        map.put("age", "13");
        map.put("sex", "男");
        map.put("height", "174");

        //调用jedis的hmset(存入hash map)的方法将map的键值对存进去
        jedis.hmset("people", map);
    }

    /**
     * 取Map的值
     */
    public static void hmget(){
        //连接本地的jedis服务器
        Jedis jedis=new Jedis("localhost");

        //新建一个string类型的数组,用于存当时存入redis的map的key值
        String[] arr=new String[4];
        arr[0]="name";
        arr[1]="age";
        arr[2]="sex";
        arr[3]="height";
        //利用jedis的hmget方法,从数据库中依次取出对应的map的key值
        List<String> list = jedis.hmget("people",arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println("存入键值对为:"+arr[i]+"--"+list.get(i));
        }
    }

    /**
     * set保存
     */
    public static void sadd(){
        //连接本地的jedis服务器
        Jedis jedis=new Jedis("localhost");

        //使用list存入数据
        List<String> list=new ArrayList<String>();
        list.add("北京");
        list.add("南京");
        list.add("上海");
        list.add("北京");
        list.add("北京");
        list.add("上海");
        list.add("苏州");
        list.add("南京");
        //打印源数据
        System.out.println("源数据为"+list);

        //因为jedis的sadd的方法,存入的是一个数组对象或者多数据,所有将集合对象转换成数组对象
        String[] arr=new String[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=list.get(i);
        }
        //调用sadd方法存入数据库
        jedis.sadd("city3", arr);
    }
    /**
     * set取出
     */
    public static void smembers(){
        //连接本地的jedis服务器
        Jedis jedis=new Jedis("localhost");

        //调用jedis的smembers方法,获取所有的set集合
        Set<String> smembers = jedis.smembers("city3");

        System.out.println(smembers);
    }

    /**
     * 存入sortset的值
     */
    public static void zadd(){
        //连接本地的jedis服务器
        Jedis jedis=new Jedis("localhost");

        Map<String, Double> map=new HashMap<String, Double>();
        map.put("北京", 1.0);
        map.put("北京", 2.0);
        map.put("南京", 3.0);
        map.put("上海", 4.0);
        map.put("上海", 5.0);
        map.put("南京", 6.0);

        //调用jedis的zadd方法存入
        jedis.zadd("city4", map);
    }

    public static void zrevrange(){
        //连接本地的jedis服务器
        Jedis jedis=new Jedis("localhost");

        //索引在0,到10之间的,分数由高到底的取出所有的集合
        Set<String> zrevrange = jedis.zrevrange("city4", 0, 10);
        System.out.println(zrevrange);
    }
}
