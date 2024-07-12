package org.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.List;

@Document(collection = "billing")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
public class Billing extends AuditingEntity{
    @Id
    @JsonProperty("billingId")
    String billingId;

    @JsonProperty("addressCity")
    String addressCity;

    @JsonProperty("addressDistrict")
    String addressDistrict;

    @JsonProperty("addressStreet")
    String addressStreet;

    @JsonProperty("bookerEmail")
    String bookerEmail;

    @JsonProperty("bookerName")
    String bookerName;

    @JsonProperty("bookerPhone")
    String bookerPhone;

    @JsonProperty("invoice")
    String invoice;

    @JsonProperty("note")
    String note;

    @JsonProperty("receiveDate")
    String receiveDate;

    @JsonProperty("receiveTime")
    String receiveTime;

    @JsonProperty("receiverName")
    String receiverName;

    @JsonProperty("receiverPhone")
    String receiverPhone;

    @JsonProperty("paymentType")
    String paymentType;

    @JsonProperty("selectedCakes")
    List<CakeBook> selectedCakes;

    @JsonProperty("selectedAccessories")
    List<AccessoryBook> selectedAccessories;

    @JsonProperty("totalAmount")
    BigInteger totalAmount;

    @JsonProperty("totalPrice")
    BigInteger totalPrice;

    @JsonProperty("status")
    String status = "processing";

    @Getter
    @Setter
    public static class CakeBook extends Cake {
        @JsonProperty("quantity")
        BigInteger quantity;

        @JsonProperty("cakeTotalPrice")
        BigInteger cakeTotalPrice;
    }
    @Getter
    @Setter
    public static class AccessoryBook extends Accessory {
        @JsonProperty("totalPrice")
        BigInteger totalPrice;

        @JsonProperty("quantity")
        BigInteger quantity;
    }
}
