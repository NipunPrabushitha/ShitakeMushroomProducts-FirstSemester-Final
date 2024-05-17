package lk.SMP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetail {
    private String orderId;
    private String harvestId;
    private int qty;
    private double unitPrice;
    private double subItemTotal;
}
