package org.acme.camel;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class LocalDateSerialBean {
        public String serialDTO(Object dto) throws Exception {
            ObjectMapper obj = new ObjectMapper();
            obj.findAndRegisterModules();
            String jsondto = obj.writeValueAsString(dto);
            //System.out.println("\n"+"\n"+"LA CONVERSION DU DTO EN JSON POUR L'envoie A MARCHE ");
            return jsondto;
        }

}

