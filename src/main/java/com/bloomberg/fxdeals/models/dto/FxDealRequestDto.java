package com.bloomberg.fxdeals.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class FxDealRequestDto {

    @JsonProperty(value = "id", required = true)
    @NotNull(message = "Please provide the Deal ID")
    private Integer id;

    @JsonProperty(value = "fromCurrencyIsoCode", required = true)
    @NotEmpty(message = "From currencyISO code cannot be empty")
    @NotNull(message = "Please provide the From currency ISO code")
    @Pattern(regexp = "^[A-Z]{3}$")
    private String fromCurrencyIsoCode;

    @JsonProperty(value = "toCurrencyIsoCode", required = true)
    @NotEmpty(message = "To currency ISO code cannot be empty")
    @NotNull(message = "Please provide the To currency ISO code")
    @Pattern(regexp = "^[A-Z]{3}$")
    private String toCurrencyIsoCode;

    @JsonProperty(value = "dealTimeStamp", required = true)
    @NotEmpty(message = "Deal time stamp cannot be empty")
    @NotNull(message = "Please provide the Deal time stamp")
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}$")
    private String dealTimeStamp;

    @JsonProperty(value = "dealAmount", required = true)
    @NotNull(message = "Deal amount cannot be empty")
    private Double dealAmount;

}
