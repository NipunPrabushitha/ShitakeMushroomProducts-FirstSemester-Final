package lk.SMP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Salary {
    private String salaryId;
    private String amount;
    private Date paymentDate;
    private String employeeId;




}
