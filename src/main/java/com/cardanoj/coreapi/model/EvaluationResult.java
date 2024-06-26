package com.cardanoj.coreapi.model;

import com.cardanoj.plutus.spec.ExUnits;
import com.cardanoj.plutus.spec.RedeemerTag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EvaluationResult {
    private RedeemerTag redeemerTag;
    private int index;
    private ExUnits exUnits;
}
