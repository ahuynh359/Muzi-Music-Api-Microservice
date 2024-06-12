package com.ahuynh.user_service.service;

import com.ahuynh.user_service.configuration.CustomFeignClientConfiguration;
import com.ahuynh.user_service.model.rest.response.SongResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@FeignClient(value = "muzi-music-song",
        configuration = CustomFeignClientConfiguration.class,url = "http://localhost:8765/core-service")
public interface FeignClientSong {

    @GetMapping("/song/{id}")
    SongResponse getSongById(@PathVariable Long id);

    @GetMapping("/song/list")
    Set<SongResponse> getListSongById(@RequestParam(name = "id") Set<Long> id);


}