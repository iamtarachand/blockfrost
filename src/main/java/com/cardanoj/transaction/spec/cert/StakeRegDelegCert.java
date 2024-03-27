package com.cardanoj.transaction.spec.cert;

import co.nstant.in.cbor.model.Array;
import co.nstant.in.cbor.model.ByteString;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.UnsignedInteger;
import com.bloxbean.cardano.client.exception.CborSerializationException;
import com.bloxbean.cardano.client.util.HexUtil;
import lombok.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import static com.bloxbean.cardano.client.common.cbor.CborSerializationUtil.getBigInteger;
import static com.bloxbean.cardano.client.common.cbor.CborSerializationUtil.toHex;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StakeRegDelegCert implements Certificate {
    private final CertificateType type = CertificateType.STAKE_REG_DELEG_CERT;

    private StakeCredential stakeCredential;
    private String poolKeyHash;
    private BigInteger coin;

    @Override
    public Array serialize() throws CborSerializationException {
        Objects.requireNonNull(stakeCredential);
        Objects.requireNonNull(poolKeyHash);
        Objects.requireNonNull(coin);

        Array certArray = new Array();
        certArray.add(new UnsignedInteger(type.getValue()));
        certArray.add(stakeCredential.serialize());
        certArray.add(new ByteString(HexUtil.decodeHexString(poolKeyHash)));
        certArray.add(new UnsignedInteger(coin));

        return certArray;
    }

    @SneakyThrows
    public static StakeRegDelegCert deserialize(Array certArray) {
        List<DataItem> dataItemList = certArray.getDataItems();

        StakeCredential stakeCredential = StakeCredential.deserialize((Array) dataItemList.get(1));
        String poolKeyHash = toHex(dataItemList.get(2));
        BigInteger coin = getBigInteger(dataItemList.get(3));

        return new StakeRegDelegCert(stakeCredential, poolKeyHash, coin);
    }
}