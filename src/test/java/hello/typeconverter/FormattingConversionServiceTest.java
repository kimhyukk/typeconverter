package hello.typeconverter;


import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.formatter.MyNumberFormatter;
import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.*;

public class FormattingConversionServiceTest {


    @Test
    void formattingConversionService() {
        DefaultFormattingConversionService service = new DefaultFormattingConversionService();
        service.addFormatter(new MyNumberFormatter());
        service.addConverter(new IpPortToStringConverter());
        service.addConverter(new StringToIpPortConverter());


        IpPort ipPort = service.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));


        assertThat(service.convert(1000, String.class)).isEqualTo("1,000");
        assertThat(service.convert("1,000", Long.class)).isEqualTo(1000L);



    }
}
