package com.cardanoj.coreapi;

import com.bloxbean.cardano.client.api.model.ProtocolParams;

/**
 * Implement this interface to provide ProtocolParams
 */
@FunctionalInterface
public interface ProtocolParamsSupplier {
    ProtocolParams getProtocolParams();
}
