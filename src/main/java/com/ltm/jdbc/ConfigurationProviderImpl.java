package com.ltm.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationProviderImpl implements ConfigurationProvider {
    @Override
    public Properties getCongiguration() {
        Properties props = new Properties();
        InputStream inStream = null;
        try {
            inStream = new FileInputStream(
                    new File("configuration/jdbc-config.properties"));
            props.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
