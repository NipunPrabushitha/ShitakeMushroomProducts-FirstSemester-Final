package lk.SMP.model.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeTm {
    private String employeeId;
    private String name;
    private String contactNumber;
    private String fieldId;
    private String userId;
}
