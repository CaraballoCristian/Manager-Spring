package com.musa.project.configuration;

import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {

    private static final long CACHE_EVICT_DELAY_MS = 600_000; // 10 min

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager manager = new ConcurrentMapCacheManager();

        manager.setCacheNames(
                EnumSet.allOf(E_CacheName.class)
                    .stream()
                    .map(E_CacheName::getValue)
                    .toList()
        );

        return manager;
    }

    @AllArgsConstructor
    @Component
    public static class CacheEvictor{

        private final CacheManager cacheManager;

        @Scheduled(fixedDelay = CACHE_EVICT_DELAY_MS)
        public void evictCache() {
            cacheManager
                    .getCacheNames()
                    .forEach(cacheName -> {
                        Cache cache = cacheManager.getCache(cacheName);
                         if(cache != null) cache.clear();
                    });
        }

    }
}
