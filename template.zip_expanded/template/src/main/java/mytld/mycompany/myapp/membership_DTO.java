package mytld.mycompany.myapp;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository("membership_DTO")
@Data
public class membership_DTO {
	Integer MIDX,MCODE;
	String MID,MNAME,MNICK,MPASS,MEMAIL,MHP,MJOIN,MDATE;
}
