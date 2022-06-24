package com.zemoso.springbootproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private int id;

    @NotEmpty(message = "Item Name can't be empty")
    private String itemName;

    @NotNull(message = "Item Price can't be empty")
    @Range(min = 1)
    private Integer itemPrice;

    @NotEmpty
    private String availability;

    @NotEmpty
    private String itemType;

    @NotNull(message = "Specify the required time for preparation")
    @Range(min = 10, max = 30, message = "Please specify time between the range 10 to 30min")
    private Integer timeRequiredToPrepare;

}
