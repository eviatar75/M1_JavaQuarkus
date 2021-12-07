package org.acme.camel;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class LocalDateSerialBean {
        public String serialDTO(Object dto) throws Exception {
            ObjectMapper obj = new ObjectMapper();
            obj.findAndRegisterModules();
            String jsondto = obj.writeValueAsString(dto);
            System.out.println("\n"+"\n"+"LA CONVERSION DU DTO1 EN JSON A MARCHE ");
            return jsondto;
        }

}

