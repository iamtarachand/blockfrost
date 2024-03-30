package com.cardanoj.coinselection.impl;

import com.cardanoj.coreapi.UtxoSupplier;
import com.cardanoj.coreapi.common.OrderEnum;
import com.cardanoj.coreapi.exception.ApiException;
import com.cardanoj.coreapi.model.Utxo;
import com.cardanoj.coinselection.UtxoSelector;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Default implementation of UtxoSelector
 */
public class DefaultUtxoSelector implements UtxoSelector {
    private final UtxoSupplier utxoSupplier;

    public DefaultUtxoSelector(UtxoSupplier utxoSupplier) {
        this.utxoSupplier = utxoSupplier;
    }

    @Override
    public Optional<Utxo> findFirst(String address, Predicate<Utxo> predicate) throws ApiException {
        return findFirst(address, predicate, Collections.EMPTY_SET);
    }

    @Override
    public Optional<Utxo> findFirst(String address, Predicate<Utxo> predicate, Set<Utxo> excludeUtxos) throws ApiException {
        boolean canContinue = true;
        int i = 0; //Start with 0 here, for backend service counter start from 1 in DefaultUtxoSupplier

        while (canContinue) {
            List<Utxo> data = utxoSupplier.getPage(address, getUtxoFetchSize(),
                    i++, getUtxoFetchOrder());
            if (data == null || data.isEmpty())
                canContinue = false;
            else {
                Optional<Utxo> optional = data.stream()
                        .filter(predicate)
                        .filter(utxo -> !excludeUtxos.contains(utxo))
                        .findFirst();

                if (optional.isPresent())
                    return optional;
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Utxo> findAll(String address, Predicate<Utxo> predicate) throws ApiException {
        return findAll(address, predicate, Collections.EMPTY_SET);
    }

    @Override
    public List<Utxo> findAll(String address, Predicate<Utxo> predicate, Set<Utxo> excludeUtxos) throws ApiException {
        boolean canContinue = true;
        int i = 0;

        List<Utxo> utxoList = new ArrayList<>();
        while (canContinue) {
            List<Utxo> data = utxoSupplier.getPage(address, getUtxoFetchSize(),
                    i++, getUtxoFetchOrder());
            if (data == null || data.isEmpty())
                canContinue = false;
            else {
                List<Utxo> filterUtxos = data.stream().filter(predicate)
                        .filter(utxo -> !excludeUtxos.contains(utxo))
                        .collect(Collectors.toList());
                utxoList.addAll(filterUtxos);
            }
        }

        return utxoList;
    }

    protected OrderEnum getUtxoFetchOrder() {
        return OrderEnum.asc;
    }

    protected int getUtxoFetchSize() {
        return 100;
    }
}
