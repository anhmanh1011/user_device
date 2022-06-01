//package com.kss.userdevicemanagement.scheduler;
//
//import com.kss.userdevicemanagement.entity.redis.UserTopicCacheEntity;
//import com.kss.userdevicemanagement.repository.redis.UserTopicCacheRepository;
//import com.kss.userdevicemanagement.utils.ConvertUtils;
//import lombok.extern.slf4j.Slf4j;
//import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.*;
//
//@Component
//@Slf4j
//public class CacheUserTopicScheduler {
//
//    @Autowired
//    EntityManager entityManager;
//
//    @Autowired
//    UserTopicCacheRepository userTopicCacheRepository;
//
//    @Autowired
//    RedisTemplate redisTemplate;
//
//    @Scheduled(cron = "3 * * * * *")
//    @SchedulerLock(name = "PushUserTopicScheduler", lockAtLeastFor = "14s", lockAtMostFor = "30s")
//    public void push() throws InterruptedException {
//        System.out.println("Job Runn  PushUserTopicScheduler ===== ");
//        LocalDateTime now = LocalDateTime.now();
//        Query query = entityManager.createNativeQuery("select topic_name , GROUP_CONCAT(user_id SEPARATOR ';') from user_topic group by topic_name");
//        List<Object[]> resultList = query.getResultList();
//        List<UserTopicCacheEntity> userTopicCacheEntities = new ArrayList<>();
//        resultList.forEach(objects -> {
//            String topicName = (String) objects[0];
//            String strListUser = (String) objects[1];
//            String[] split = strListUser.split(";");
//            List<String> listUser = Arrays.asList(split);
//            UserTopicCacheEntity build = UserTopicCacheEntity.builder().topicName(topicName).users(listUser).build();
//            userTopicCacheEntities.add(build);
//        });
//        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
//            RedisConnection redisConnection = connection;
//            for (UserTopicCacheEntity element : userTopicCacheEntities) {
//                redisConnection.set(("user_device" + element.getTopicName()).getBytes(), ConvertUtils.convertTobyte(element.getUsers()));
//            }
////            userDeviceCacheEntities.forEach(id -> {
////                redisConnection.get(("user_device" + id.getUserId()).getBytes());
////            });
//            return null;
//        });
//        log.info(" send command success");
//        LocalDateTime end = LocalDateTime.now();
//        long milisecion = ChronoUnit.MILLIS.between(now, end);
//
//        System.out.println("cost user_topic : " + milisecion);
//
//    }
//
//    private void printfKey() {
//        Set<byte[]> keys = redisTemplate.getConnectionFactory().getConnection().keys("user_topic:*".getBytes());
//
//        Iterator<byte[]> it = keys.iterator();
//
//        while (it.hasNext()) {
//
//            byte[] data = (byte[]) it.next();
//
//            System.out.println(new String(data, 0, data.length));
//        }
//    }
//
//}
