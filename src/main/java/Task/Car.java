package Task;

import Task.Annatation.MyColumn;
import Task.Annatation.MyTable;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@MyTable("Car")
public class Car {

    @MyColumn("id")
    private Integer id;

    @MyColumn("name")
    private String name;

    @MyColumn("color")
    private String color;

    @MyColumn("price")
    private Integer price;

}
