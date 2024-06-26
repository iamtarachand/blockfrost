package com.cardanoj.plutus.spec;


import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.ByteString;
import com.cardanoj.common.cbor.CborSerializationUtil;
import com.cardanoj.exception.CborDeserializationException;
import com.cardanoj.util.HexUtil;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class PlutusV2Script extends PlutusScript {
    public PlutusV2Script() {
        this.type = "PlutusScriptV2";
    }

    //plutus_script = bytes ; New
    public static PlutusV2Script deserialize(ByteString plutusScriptDI) throws CborDeserializationException {
        if (plutusScriptDI != null) {
            PlutusV2Script plutusScript = new PlutusV2Script();
            byte[] bytes;
            try {
                bytes = CborSerializationUtil.serialize(plutusScriptDI);
            } catch (CborException e) {
                throw new CborDeserializationException("CBor deserialization error", e);
            }
            plutusScript.setCborHex(HexUtil.encodeHexString(bytes));
            return plutusScript;
        } else {
            return null;
        }
    }

    @Override
    public byte[] getScriptTypeBytes() {
        return new byte[]{(byte) getScriptType()};
    }

    @Override
    public int getScriptType() {
        return 2;
    }
}
