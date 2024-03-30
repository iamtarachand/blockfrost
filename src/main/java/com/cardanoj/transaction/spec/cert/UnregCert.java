package com.cardanoj.transaction.spec.cert;

import co.nstant.in.cbor.model.Array;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.UnsignedInteger;
import com.cardanoj.common.cbor.CborSerializationUtil;
import com.cardanoj.exception.CborSerializationException;
import lombok.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnregCert implements Certificate {
    private final CertificateType type = CertificateType.UNREG_CERT;

    private StakeCredential stakeCredential;
    private BigInteger coin;

    @Override
    public Array serialize() throws CborSerializationException {
        Objects.requireNonNull(stakeCredential);
        Objects.requireNonNull(coin);

        Array certArray = new Array();
        certArray.add(new UnsignedInteger(type.getValue()));
        certArray.add(stakeCredential.serialize());
        certArray.add(new UnsignedInteger(coin));

        return certArray;
    }

    @SneakyThrows
    public static UnregCert deserialize(Array certArray) {
        List<DataItem> dataItemList = certArray.getDataItems();

        StakeCredential stakeCredential = StakeCredential.deserialize((Array) dataItemList.get(1));
        BigInteger coin = CborSerializationUtil.getBigInteger(dataItemList.get(2));

        return new UnregCert(stakeCredential, coin);
    }
}
