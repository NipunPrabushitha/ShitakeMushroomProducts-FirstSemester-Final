package lk.SMP.model.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerTm {
    private String id;
    private String name;
    private String contactNumber;
    private String address;

}
