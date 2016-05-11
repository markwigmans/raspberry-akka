package com.ximedes.rb.cards.api.model.mapping;

import com.ximedes.rb.cards.api.model.mapping.converter.CardConverter;
import ma.glasnost.orika.Converter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

/**
 * Created by mawi on 12/12/2015.
 */
@Component
public class DomainMapper extends ConfigurableMapper {

    @Override
    protected void configure(final MapperFactory mapperFactory) {
        mapperFactory.getConverterFactory().registerConverter(new CardConverter());
    }
}
