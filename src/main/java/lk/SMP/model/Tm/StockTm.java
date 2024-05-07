package lk.SMP.model.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StockTm {
    private String productId;
    private double quantity;
    private double unitPrice;
    private String expireDate;
    private String name;
}
