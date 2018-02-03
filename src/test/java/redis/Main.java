package redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class Main {
	Jedis jedis =new Jedis("127.0.0.1");
	@Test
	public void testString() {
		
		
		
		jedis.set("hello","world");
		System.out.println(jedis.get("hello"));
		//-----添加数据---------- 
        jedis.set("name","meepo");//向key-->name中放入了value-->meepo 
        System.out.println(jedis.get("name"));//执行结果：meepo 
 
        //-----修改数据----------- 
        //1、在原来基础上修改 
        jedis.append("name","dota");   //很直观，类似map 将dota append到已经有的value之后 
        System.out.println(jedis.get("name"));//执行结果:meepodota 
 
        //2、直接覆盖原来的数据 
        jedis.set("name","poofu"); 
        System.out.println(jedis.get("name"));//执行结果：poofu 
 
        //删除key对应的记录 
        jedis.del("name"); 
        System.out.println(jedis.get("name"));//执行结果：null 
 
        /**
         * mset相当于
         * jedis.set("name","meepo");
         * jedis.set("dota","poofu");
         */ 
        jedis.mset("name","meepo","dota","poofu"); 
        System.out.println(jedis.mget("name","dota")); 
        
        Jedis jedis2 =new Jedis("127.0.0.1");
        System.out.println(jedis2.get("hello"));
	}
	
	   /**
     * jedis操作List
     */ 
    @Test 
    public void testList(){ 
        //开始前，先移除所有的内容 
    	jedis.del("java framework"); 
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("java framework",0,-1)); 
       //先向key java framework中存放三条数据 
       jedis.lpush("java framework","spring"); 
       jedis.lpush("java framework","struts"); 
       jedis.lpush("java framework","hibernate"); 
       //再取出所有数据jedis.lrange是按范围取出， 
       // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有 
       System.out.println(jedis.lrange("java framework",0,-1)); 
    } 
	
}
