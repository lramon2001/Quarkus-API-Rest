package com.stefanini.utils;

import javax.persistence.Converter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PasswordUtils implements AttributeConverter<String,String>{
    private static final int MAX_LENGTH = 10;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute != null && attribute.length() > MAX_LENGTH) {
            attribute = attribute.substring(0, MAX_LENGTH);
        }
        return Base64.getEncoder().encodeToString(attribute.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return new String(Base64.getDecoder().decode(dbData), StandardCharsets.UTF_8);
    }
}
