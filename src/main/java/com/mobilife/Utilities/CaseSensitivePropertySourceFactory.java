package com.mobilife.Utilities;

import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;

public class CaseSensitivePropertySourceFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource (String name, EncodedResource resource) throws IOException {
        Resource propertiesResource = resource.getResource();
        Properties properties = PropertiesLoaderUtils.loadProperties(propertiesResource);

        return new PropertiesPropertySource(propertiesResource.getFilename(), properties);
    }
}
