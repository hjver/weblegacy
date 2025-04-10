package api;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/*
@CrossOrigin(
		origins = {"localhost:8080", "http://172.30.1.95:8080", "http://abc.co.kr"},
		allowedHeaders = {"POST", "GET", "PUT"}
		)
*/
//@RestController => View를 사용하지 않음. PrintWriter + res.setContentType
//@Controller => View를 사용함(Model 사용)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class api_contoller2 {
	
	private static final Logger logger = LoggerFactory.getLogger(api_contoller2.class);
	PrintWriter pw = null;
	
	@Resource(name="api_dao")
	api_dao dao;
	
	@GetMapping("/ajax/api_select.do")
	public String api_select(@RequestParam("key") String key, Model m) {
		this.logger.info(key);
		try {
			if(key.equals("apink")) {
				//Oracle에서 Data를 DTO를 이용하여 배열로 가져오는 코드
				List<api_dto> all = dao.pdlist();
				this.logger.info(String.valueOf(all.size()));
				//Front-end에게 Data를 원하는 형태의 배열로 생서와여 회신하는 코드
				int w = 0;
				JSONArray ja = new JSONArray();
				while(w < all.size()) {
					JSONObject jo = new JSONObject();
					jo.put("midx", all.get(w).getMidx());
					jo.put("pd1", all.get(w).getPd1());
					jo.put("pd2", all.get(w).getPd2());
					jo.put("pd3", all.get(w).getPd3());
					jo.put("pd4", all.get(w).getPd4());
					jo.put("pd5", all.get(w).getPd5());
					ja.put(jo);
					w++;
				}
				JSONObject jo2 = new JSONObject();
				jo2.put("data_all", ja);
				this.logger.info(jo2.toString());
				m.addAttribute("msg",jo2);
				//Front-end에게 Data를 원하는 형태의 배열로 생서와여 회신하는 코드
			}
			else {
				m.addAttribute("msg","{data_all:error}"); //error 발생시 대표키에 저굥사례
			}
		}catch (Exception e) {
			m.addAttribute("msg","{data_all:key-error}"); //error 발생시 대표키에 저굥사례
		}finally {
		}
		return "WEB-INF/views/api_select"; //JSON 데이터를 jsp에 출력하는 방식 API
	}
}
