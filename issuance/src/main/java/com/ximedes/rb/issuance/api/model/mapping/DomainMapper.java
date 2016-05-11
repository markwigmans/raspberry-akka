package com.ximedes.rb.issuance.api.model.mapping;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by mawi on 12/12/2015.
 */
@Component
public class DomainMapper extends ConfigurableMapper {

    private MapperFactory mapperFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void configure(final MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    @PostConstruct
    protected void init() {
        // register all converters
        applicationContext.getBeansOfType(Converter.class).values().stream()
                .forEach(mapperFactory.getConverterFactory()::registerConverter);
    }
}
