package com.baristamatic.program.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "DRINK_ITEMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrinkEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DRINK_ID")
    private Integer drinkNumber;
	
	@Column(name = "DRINK_NAME")
	private String drinkName;
	
	@Column(name = "COFFEE")
	private Integer coffee;
	
	@Column(name = "DECAFE_COFEE")
	private Integer dcafe_coffee;
	
	@Column(name = "SUGAR")
	private Integer sugar;
	
	@Column(name = "CREAM")
	private Integer cream;
	
	@Column(name = "STEAMED_MILK")
	private Integer steamed_milk;
	
	@Column(name = "FOAMED_MILK")
	private Integer foamed_milk;
	
	@Column(name = "ESPRESSO")
	private Integer espresso;
	
	@Column(name = "COCOA")
	private Integer cocoa;
	
	@Column(name = "WHIPPED_CREAM")
	private Integer whipped_cream;

}
