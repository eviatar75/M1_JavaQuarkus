package org.acme.gateway;

import org.acme.DTO.ContratDeVenteBrokerDTO;

public interface gatewayActeDeVente {

    void sendActeDeVente(ContratDeVenteBrokerDTO contrat);

}
