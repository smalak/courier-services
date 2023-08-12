package com.sezer.worker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sezer.common.dto.Store;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Slf4j
public class PreperationService {

    @Value("classpath:data/stores.json")
    private Resource resourceFile;

    private final ObjectMapper objectMapper;

    private List<Store> stores;

    public PreperationService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void onInit() throws IOException {
        String content = resourceFile.getContentAsString(StandardCharsets.UTF_8);
        List<Store> storeList = objectMapper.readValue(content, new TypeReference<List<Store>>(){});
        stores = storeList;
    }

    public List<Store> getStores() {
        return stores;
    }
}
