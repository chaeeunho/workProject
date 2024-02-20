package com.work.community.entity;

import java.util.List;

import com.work.community.dto.ItemDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {
	
	@Id                                                     // PK (Primary Key)
	@GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동순번
	private Integer ino;                                    // 아이템 번호
	
	@Column(nullable = false)                               // not null
	private String iname;                                   // 아이템명
	
	@Column(nullable = false)                               // not null
	private String icontent;                                // 아이템 설명
	
	@Column(nullable = false)                               // not null
	private String iprice;                                  // 아이템 가격
	
	@Column(length = 5000, nullable = false)                               // not null
	private String ilink;                                   // 아이템 판매 링크
	
	private String ifilename;                               // 아이템 사진명
	
	private String ifilepath; 
	
	// 하나의 아이템에 여러 카트 아이템이 존재(일대다 관계)
	// 아이템이 삭제되면 카트 아이템에서도 삭제
	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private List<CartItem> cartItems;
	   
	// dto -> entity 변환
	public static Item toSaveEntity(ItemDTO itemDTO) {
		Item item = Item.builder()
						.ino(itemDTO.getIno())
						.iname(itemDTO.getIname())
						.icontent(itemDTO.getIcontent())
						.iprice(itemDTO.getIprice())
						.ilink(itemDTO.getIlink())
						.ifilename(itemDTO.getIfilename())
						.ifilepath(itemDTO.getIfilepath())
						.build();
		return item;
	}
	
}
