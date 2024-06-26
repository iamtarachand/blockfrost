package com.cardanoj.backend.api;

import com.cardanoj.coreapi.common.OrderEnum;
import com.cardanoj.coreapi.exception.ApiException;
import com.cardanoj.coreapi.model.Result;
import com.cardanoj.coreapi.model.Utxo;

import java.util.List;

public interface UtxoService {

    /**
     *
     * @param address
     * @param count
     * @param page
     * @return List of Utxos for a address
     * @throws ApiException
     */
    Result<List<Utxo>> getUtxos(String address, int count, int page) throws ApiException;

    /**
     *
     * @param address
     * @param count
     * @param page
     * @param order  asc or desc. Default is "asc"
     * @return
     * @throws ApiException
     */
    Result<List<Utxo>> getUtxos(String address, int count, int page, OrderEnum order) throws ApiException;

    /**
     * Fetch Utxos for a given address and asset
     * @param address Address
     * @param unit Asset unit
     * @param count Number of utxos to fetch
     * @param page Page number
     * @return List of Utxos
     * @throws ApiException If any error occurs
     */
    Result<List<Utxo>> getUtxos(String address, String unit, int count, int page) throws ApiException;

    /**
     * Fetch Utxos for a given address and asset
     * @param address Address
     * @param unit Asset unit
     * @param count Number of utxos to fetch
     * @param page Page number
     * @param order Order of the utxos
     * @return List of Utxos
     * @throws ApiException If any error occurs
     */
    Result<List<Utxo>> getUtxos(String address, String unit, int count, int page, OrderEnum order) throws ApiException;

    /**
     * Fetch Output for a given transaction hash and output index. The output may be spent or unspent.
     * @param txHash Transaction hash
     * @param outputIndex Output index
     * @return Utxo
     * @throws ApiException If any error occurs
     */
    Result<Utxo> getTxOutput(String txHash, int outputIndex) throws ApiException;
}
