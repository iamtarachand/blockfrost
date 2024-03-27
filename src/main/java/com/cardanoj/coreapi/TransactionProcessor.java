package com.cardanoj.coreapi;

import com.bloxbean.cardano.client.api.exception.ApiException;
import com.bloxbean.cardano.client.api.model.Result;

/**
 * Implement this interface to provide transaction submission capability.
 */
public interface TransactionProcessor extends TransactionEvaluator {

    Result<String> submitTransaction(byte[] cborData) throws ApiException;
}
