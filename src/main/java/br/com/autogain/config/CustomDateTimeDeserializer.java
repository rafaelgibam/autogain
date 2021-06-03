package br.com.autogain.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.SneakyThrows;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateTimeDeserializer extends StdDeserializer<Date> {
    private static final long serialVersionUID = 1L;
    private static SimpleDateFormat simpleDateFormat;

    public CustomDateTimeDeserializer() {
        this(null);
    }

    public CustomDateTimeDeserializer(Class<Date> t) {
        super(t);
    }

    @Override
    @SneakyThrows
    public Date deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String date = parser.getText();

        return simpleDateFormat.parse(date);

    }
}