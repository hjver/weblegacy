package mytld.mycompany.myapp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface membership_mapper {
	public String id_row(String mid);
	public int member_join(membership_DTO dto);
	public List<membership_DTO> id_info(String mid, String mpass);
	public int id_update(Map<String, String> map); //개인정보 수정
}
