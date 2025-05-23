package api;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/* CRUD
 * C : @PutMapping
 * D : @DeleteMapping
 * U : @PatchMapping
 *     jackson => ajax => JSON
 *     GSON 라이브러리
 *     예) /api/data.do/{data}
 * 
 * R : @GetMapping, @PostMapping => JSON 허락 예) /api/data.do
 */

@Controller
public class api_controller {

    private static final Logger logger = LoggerFactory.getLogger(api_controller.class);
		
	/*
	 * this.logger.info : 해당 메소드에서 실행된 값을 출력하는 역할
	 * this.logger.error : catch에서 사용하는 형태. error 발생시 출력되는 메시지
	 * this.logger.debug : 해당 코드가 정상적으로 작동하는 테스트 메세지를 출력
	 * this.logger.trace : 해당 코드에 문제가 발생시 좀 더 상세하게 문제사항을 출력
	 * this.logger.warn : 현재 코드에 대해서 향후 문제가 발생될 수 있는 원인에 대한 메세지를 출력
	 * this.logger.fatal : 치명적인 오류 발생시 출력되는 역할
	 */
	//js - Ajax(GET)
    PrintWriter pw = null;
    //문자열 + ok, no, error => GET (product)
    
    @Resource(name="api_dao")
    public api_dao dao;
    
    
	@GetMapping("/ajax/ajax1.do")
	public String ajax1(@RequestParam(name="product") String data, ServletResponse res) {
		//Front-end에서 보낸 nqme을 원시배열로 받을 경우 자동으로 배열로 변경처리
		//단, String으로 배열 자료형을 사용하지 않을 경우 split을 이용하여 값을 분리 시켜야 함
		this.logger.info(data);
		try {
			this.pw = res.getWriter();
			this.pw.print("ok");
		}catch (Exception e) {
			this.logger.error(e.toString());
		}
		return null;
	}
	
	//Array 형태로 전송시 GET 형태와 동일
	@PostMapping("/ajax/ajax2.do")
	public String ajax2(ServletResponse res, 
			@RequestParam(name="product") String pd[],
			@RequestParam(name="person") String person) {
		try {
			this.logger.info(pd[0]);
			this.logger.info(person);
			this.pw = res.getWriter();
			this.pw.print("ok");
		}catch (Exception e) {
			this.logger.error(e.toString());
		}
		return null;
	}
	
	/* @RequestHeader : Ajax에서만 사용하는 Headers의 값이며, 키에 맞는 데이터를 가져올 수 있음
	 * Front-end : @RequestHeader에 key, value값을 보낼 경우에만 사용함
	 * 
	 * @RequestBody : "content-type","applicaion/json"
	 */
	@PostMapping("/ajax/ajax3.do")
	public String ajax3(ServletResponse res,
			@RequestBody String pd
			//@RequestParam(name="pd") String pd
			//@RequestHeader(name="User") String user
			) {
		this.logger.info(pd);
		//this.logger.info(user);
		try {
			this.pw = res.getWriter();
			this.pw.print("ok");
		}catch (Exception e) {
			this.logger.error(e.toString());
		}
		return null;
	}
	
	//JSON.stringify : Front-end가 전송시 무조건 @RequestBody처리
	@PostMapping("/ajax/ajax4.do")
	public String ajax4(ServletResponse res,
			@RequestBody String pd
			) {

		try {
			this.logger.info(pd);
			JSONArray ja = new JSONArray(pd);
			int w=0;
			this.logger.info(String.valueOf(ja.length()));
			while(w < ja.length()) {
				JSONObject jo = (JSONObject)ja.get(w);
				String usernm = jo.get("pd1").toString();
				this.logger.info(usernm);
				w++;
			}

		}catch (Exception e) {
			this.logger.error(e.toString());
		}
		return null;
	}
	
	
	@PostMapping("/ajax/test.do")
	public String test(ServletResponse res,
		    @RequestParam(name="pd") String pd) {
		this.logger.info(pd);
		return null;
	}
	
	//JQery-Ajax (GET) => JSON.stringify
	@GetMapping("/ajax/ajax5.do")
	public String ajax5(@RequestParam("no") String no, ServletResponse res) {
		try {
			this.logger.info(no);
			JSONArray ja = new JSONArray(no);
			int w = 0;
			while(w < ja.length()) {
				this.logger.info(ja.getString(w));
				w++;
			}
			this.pw = res.getWriter();
			this.pw.write("ok");
		}catch (Exception e) {
			
		}
		return null;
	}
	
	//JQery-Ajax (POST) => JSON.stringify
	@PostMapping("/ajax/ajax6.do")
	public String ajax6(@RequestBody String all_data, ServletResponse res) {
		try {
			this.logger.info(all_data);
			//대표키가 있는 경우
			JSONObject jo = new JSONObject(all_data);
			this.logger.info(jo.getString("userdata"));
			
			
//			this.pw = res.getWriter();
//			JSONArray ja = new JSONArray(all_data);
//			int w = 0;
//			while(w < ja.length()) {
//				this.logger.info(ja.getString(w));
//				w++;
//			}
			this.pw = res.getWriter();
			this.pw.write("ok");
		}catch (Exception e) {
			
		}
		return null;
	}
	
	//각각의 다른 키로 POST 전송시 (JQuery) - DTO
	//Front-end에서 파라미터 형태로 문자열 기준으로 POST 전송시 Backend에서는 dto 활성화
	@PostMapping("/ajax/ajax7.do")
	public String ajax7(api_dto dto, ServletResponse res) {
		try {
			this.logger.info(dto.getPd1());
			this.pw = res.getWriter();
			this.pw.write("ok");
		}catch (Exception e) {
			
		}
		return null;
	}
	
	/*
	@PostMapping("/ajax/ajax7.do")
	public String ajax7(@RequestBody String all_data, ServletResponse res) {
		try {
			this.logger.info(all_data);
			JSONObject jo = new JSONObject(all_data);
			Iterator i = jo.keys(); //키이름을 순차적으로 가져오는 인터페이스
			while(i.hasNext()) {
				String a = i.next().toString();
				this.logger.info(a);
				this.logger.info(jo.getString(a));
			}
			this.logger.info(jo.getString("pd1"));
			this.pw = res.getWriter();
			this.pw.write("ok");
		}catch (Exception e) {
			
		}
		return null;
	}
	*/

	@PostMapping("/ajax/ajax8.do")
	public String ajax8(ServletResponse res,
			@RequestParam("fdata") List<String> fdata
			) {
		try {
			this.logger.info(fdata.toString());
			this.pw = res.getWriter();
			this.pw.write("ok");
		}catch (Exception e) {
			
		}
		
		return null;
	}
	
	//ECMA - Ajax(GET)
	@GetMapping("/ajax/ajax9.do")
	public String ajax9(ServletResponse res,
			@RequestParam(name="mid") String mid
			) {
		try {
			this.logger.info(mid);
			this.pw = res.getWriter();
			this.pw.write("ok");
		}catch (Exception e) {
			
		}
		
		return null;
	}
	
	//ECMA - Ajax(POST)
	@PostMapping("/ajax/ajax10.do")
	public String ajax10(ServletResponse res,
			@RequestParam(name="mid") String mid
			) {
		try {
			this.logger.info(mid);
			this.pw = res.getWriter();
			this.pw.write("ok");
		}catch (Exception e) {
			
		}
		
		return null;
	}
	
	//ECMA - POST통신 (배열방식) - array
	@PostMapping("/ajax/ajax11.do")
	/*//JSON.stringify => 전송시 @RequestBody
	public String ajax11(ServletResponse res,
			@RequestBody String data
			) {
	*/
	/*
	public String ajax11(ServletResponse res,
			@RequestParam(name="mid") String mid,
			@RequestParam(name="mname") String mname
			) {
	*/
	public String ajax11(ServletResponse res,
			@ModelAttribute api_dto dto
			) {
		try {
			this.logger.info(dto.getMid());
			this.logger.info(dto.getMname());
			this.logger.info(dto.getMage().toString());
			this.pw = res.getWriter();
			this.pw.write("ok");
		}catch (Exception e) {
			
		}
		
		return null;
	}
	
	/*@PathVariable : URL 파라미터 값을 가져오는 어노테이션 {data} 가상의 파라미터 값
	                  JSON.stringify을 처리하지 못함
	*/
	@PatchMapping("/ajax/ajax12.do/{data}")
	public String ajax12(ServletResponse res,
			@PathVariable(name="data") String data,
			@RequestBody String myinfo
			) {
		try {
			this.pw = res.getWriter();
			if(data.equals("patch_myinfo")) {
				this.logger.info(myinfo);
				this.pw.write("ok");			
			}
			
			
			//this.logger.info(mid);
			//String user[] = mid.split(",");
			//this.logger.info(user[1]);
			//this.logger.info(user[2]);
			
		}catch (Exception e) {
			
		}
		return null;
	}
	
	//@RequestParam("midx") List<String> midx : POST FormData()시에 List<String>
	//@RequestPart : MultipartFile
	//@RequestParam : name 또는 파라미터
	//@ResponseBody + @Mapping : method 선언시에 사용
	//@ResponseBody : 응답에 대한 결과값을 해당 메소드에 바로 출력할 때 사용
	//@RequestBody : 배열값을 처리
	@DeleteMapping("/ajax/ajax13/{key}")  //delete (FormData()는 안됨
	public String ajax13(HttpServletResponse res,
			@PathVariable(name="key") String key,
			@RequestBody String midx
			) {
		try {
			this.pw = res.getWriter();
			if(key.equals("a123456")) {
				this.logger.info(midx);
				this.pw.write("ok");
			}else {
				
			}
		}catch (Exception e) {
			
		}
		return null;
	}
	
	@PostMapping("/ajax/ajax14/{key}")  //insert (DTO 기본)
	public String ajax14(HttpServletResponse res,
			@PathVariable(name="key") String key,
			//@RequestParam(name="pd1") String pd1
			@RequestBody String data
			) {
		try {
			this.pw = res.getWriter();
			if(key.equals("a123456")) {
				this.logger.info(data);
				
				Map<String, String> mp = new HashMap<String, String>();
				JSONObject jo = new JSONObject(data);
				Iterator<String> keys = jo.keys();
				while(keys.hasNext()) {
					String keynm = keys.next(); //키명
					mp.put(keynm, jo.getString(keynm).toString());
				}
				this.logger.info(mp.toString());
				int result = this.dao.pdtest(mp);
				this.logger.info(String.valueOf(result));
				if(result > 0) {
					this.pw.write("ok");
				}
				else {
					this.pw.write("no");
				}
				
			}else {
				this.pw.write("key error");
			}
		}catch (Exception e) {
			
		}
		return null;
	}
}
