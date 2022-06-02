//package com.kss.userdevicemanagement.scheduler;
//
//import com.kss.userdevicemanagement.entity.redis.UserDeviceCacheEntity;
//import com.kss.userdevicemanagement.repository.UserDeviceRepository;
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
//public class CacheUserDeviceScheduler {
//    @Autowired
//    EntityManager entityManager;
//
//    @Autowired
//    UserDeviceRepository userDeviceRepository;
//
//
//    @Autowired
//    RedisTemplate redisTemplate;
//
//    @Scheduled(cron = "2 * * * * *")
//    @SchedulerLock(name = "PushUserDeviceScheduler", lockAtLeastFor = "14s", lockAtMostFor = "30s")
//    public void push() throws InterruptedException {
//
//        System.out.println("PushUserDeviceScheduler Runn ===== ");
//        LocalDateTime now = LocalDateTime.now();
//
//        Query query = entityManager.createNativeQuery("select ud.user_id, GROUP_CONCAT(di.device_id SEPARATOR ';'), GROUP_CONCAT(di.token SEPARATOR ';') from user_device ud inner join device_info di on ud.device_info_id = di.id and ud.status = true group by user_id");
//        List<Object[]> resultList = query.getResultList();
//        List<UserDeviceCacheEntity> userDeviceCacheEntities = new ArrayList<>();
//        resultList.forEach(objects -> {
//            String userId = (String) objects[0];
//            String strListDevice = (String) objects[1];
//            String[] splitDeviceIds = strListDevice.split(";");
//            List<String> listDeviceId = Arrays.asList(splitDeviceIds);
//
//            String strToken = (String) objects[2];
//            String[] splitToken = strToken.split(";");
//            List<String> listToken = Arrays.asList(splitToken);
//            List<UserDeviceCacheEntity.DeviceInfo> deviceInfos = new ArrayList<>();
//            for (int i = 0; i < listDeviceId.size(); i++) {
//                UserDeviceCacheEntity.DeviceInfo deviceInfo = new UserDeviceCacheEntity.DeviceInfo(listDeviceId.get(i), listToken.get(i));
//                deviceInfos.add(deviceInfo);
//            }
//            UserDeviceCacheEntity userDeviceCacheEntity = UserDeviceCacheEntity.builder().userId(userId).devices(deviceInfos).build();
//            userDeviceCacheEntities.add(userDeviceCacheEntity);
//
////            UserDeviceCacheEntity build = UserDeviceCacheEntity.builder().userId(userId).devices(listUser).build();
////            userTopicCacheRepository.save(build);
//        });
//        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
//            RedisConnection redisConnection = connection;
//            for (UserDeviceCacheEntity element : userDeviceCacheEntities) {
//                redisConnection.set(("user_device" + element.getUserId()).getBytes(), ConvertUtils.convertTobyte(element.getDevices()));
//            }
////            userDeviceCacheEntities.forEach(id -> {
////                redisConnection.get(("user_device" + id.getUserId()).getBytes());
////            });
//            return null;
//        });
//        LocalDateTime end = LocalDateTime.now();
//        long milisecion = ChronoUnit.MILLIS.between(now, end);
//        System.out.println("cost user_device : " + milisecion);
//
//        log.info(" send command success");
////        list.forEach(deviceInfos -> deviceInfos.forEach(System.out::println));
//
//
////        all.forEach(userTopicCacheEntity -> System.out.println(userTopicCacheEntity));
//    }
//
//    private void printfKey() {
//        Set<byte[]> keys = redisTemplate.getConnectionFactory().getConnection().keys("user_device:*".getBytes());
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
//
//}
