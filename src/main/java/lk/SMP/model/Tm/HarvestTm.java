package lk.SMP.model.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HarvestTm {
    private String harvestId;
    private String cropType;
    private double quantity;
    private Date date;
    private String fieldId;
    private double unitPrice;
    private double waste;


}
