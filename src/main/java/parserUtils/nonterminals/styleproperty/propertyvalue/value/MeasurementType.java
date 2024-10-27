package parserUtils.nonterminals.styleproperty.propertyvalue.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MeasurementType {
    PX("PX"),
    EM("EM");

    private final String type;
}
