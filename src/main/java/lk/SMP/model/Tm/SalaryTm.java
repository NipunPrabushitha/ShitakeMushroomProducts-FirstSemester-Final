package lk.SMP.model.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SalaryTm {
    private String salaryId;
    private String amount;
    private Date paymentDate;
    private String employee;
}
