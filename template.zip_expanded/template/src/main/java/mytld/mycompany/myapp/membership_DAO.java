package mytld.mycompany.myapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("membership_DAO")
public class membership_DAO implements membership_mapper{

	@Resource(name="sqltemp")
	SqlSessionTemplate st;
	
	Map<String, String> mp = null;
	
	@Override
	public String id_row(String mid) {
		String result = st.selectOne("id_row", mid);
		return result;
	}
	
	@Override
	public int member_join(membership_DTO dto) {
		int result = st.insert("member_join",dto);
		return result;
	}
	
	@Override
	public List<membership_DTO> id_info(String mid, String mpass) {
		//Map 생성하는 이유 : mapper.xml에서 choose을 각 when(조건) 별로 query를 다르게 실행 시키기 위함
		this.mp = new HashMap<String, String>();
		if(mpass != "") {
			this.mp.put("part", "login");
			this.mp.put("mid", mid);
			this.mp.put("mpass", mpass);
		}
		else {
			this.mp.put("part", "myinfo");
			this.mp.put("mid", mid);
		}
		
		//해당 조건의 mapper값을 return 받음
		List<membership_DTO> all = this.st.selectList("id_info",this.mp);
		return all;
	}
}
