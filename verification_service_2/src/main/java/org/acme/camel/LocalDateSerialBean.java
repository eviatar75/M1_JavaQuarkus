package org.acme.camel;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class LocalDateSerialBean {
        public String serial(Object returned) throws Exception {
            ObjectMapper obj = new ObjectMapper();
            obj.findAndRegisterModules();
            return obj.writeValueAsString(returned);
        }

}

