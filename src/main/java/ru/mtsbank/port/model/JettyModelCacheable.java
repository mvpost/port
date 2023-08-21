package ru.mtsbank.port.model;

import org.springframework.cache.annotation.Cacheable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mtsbank.port.mapper.JettyMapper;
import ru.mtsbank.port.repository.JettyRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JettyModelCacheable {
    private final JettyRepository repository;
    private final JettyMapper mapper;

    @Cacheable("jetties")
    public List<JettyModel> fetch() {
        return mapper.mapModel(repository.findAll());
    }
}
