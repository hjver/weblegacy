package mytld.mycompany.myapp;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbcp.BasicDataSource;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.protobuf.DescriptorProtos.SourceCodeInfo.Location;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	BasicDataSource dbinfo;
	
	@Resource(name="membership_DAO")
	membership_DAO dao;
	
	@Resource(name="md5")
	md5 md5;
	
	HttpSession session = null;
	
	PrintWriter pw = null;

	//로그아웃
	@GetMapping("/ajax/logout.do")
	public String logout(HttpServletRequest req, Model m) {
		this.session = req.getSession();
		this.session.invalidate();
		
		m.addAttribute("msg", "alert('정상적으로 로그아웃 되엇습니다.'); location.href='../kakao2.jsp';");
		
		return "joinok";
	}
	
	//API Patch로 개인정보 값 수정
	@PatchMapping("/ajax/myinfo_modify.do/{key}")
	public String myinfo_modify(ServletResponse res,
			@PathVariable("key") String key,
			@RequestBody String datainfo
			) {
		try {
			this.pw =res.getWriter();
			if(key.equals("mykey")) {
				JSONObject jo = new JSONObject(datainfo);
				Map<String, String> userdata = new HashMap<String, String>();
				for(String k : jo.keySet()) {
					if(!jo.get(k).equals("")) {
						if(k.equals("MPASS")) {
							userdata.put(k, this.md5.md5_pass(jo.get(k).toString()));
						}
						else {
							userdata.put(k, jo.get(k).toString());
						}
					}
				}
				this.logger.info(userdata.toString());
				int result = this.dao.id_update(userdata);
				if(result > 0) {
					this.pw.write("ok");					
				}
				else {
					this.pw.write("no");
				}
			}
			else {
				this.pw.write("key_error");
			}
		}catch (Exception e) {
			this.logger.info(e.toString());
			this.pw.write("error");
		}finally {
			this.pw.close();
		}
		
		return null;
	}
	
	
	//로그인 사용자 정보 출력하는 페이지
	@GetMapping("/ajax/myinfo.do")
	public String myinfo(@SessionAttribute("MID") String MID, Model m) {
		
		List<membership_DTO> mydata = this.dao.id_info(MID,"");
		m.addAttribute("MID",MID);
		m.addAttribute("mydata",mydata);
		return "/myinfo";
	}
	
	//회원가입
	@PostMapping("/joinok.do")
	public String joinok(@ModelAttribute membership_DTO dto, Model m) {
		String pw = dto.getMPASS();
		//
		dto.setMPASS(this.md5.md5_pass(pw));
		try {
			int result = this.dao.member_join(dto);
			if(result > 0) {
				m.addAttribute("msg","alert('정상적으로 회원가입이 완료 되었습니다.');"
						+ "location.href='./kakao2.jsp';");
			}else{
				m.addAttribute("msg","alert('회원가입이 완료되지 않았습니다.');"
						+ "location.href='./join.jsp';");
			}
		}catch (Exception e) {
			this.logger.info(e.toString());
		}
		return null;
	}
		
	
	@PostMapping("/login_idck.do")
	public String login_idck(@RequestParam(name="id") String id,
			ServletResponse res
			) {
		
		try {
			this.pw = res.getWriter();
			//id검토시 오라클에서는 대소문자를 구분함
			//toUpperCase(), toLowerCase()
			//고객 아이디는 DB에 저장시 소문자로 저장하기 때문에 ID검토시에도 소문자로 변경
			String result = this.dao.id_row(id.toLowerCase());
			if(result.equals("0")) {
				this.pw.write("ok");
			}else {
				this.pw.write("no");
			}
		}catch (Exception e) {
			this.logger.info(e.toString());
		}finally {
			this.pw.close();
		}
		return null;
	}
	
	//@Post => 일반로그인, KaKao api  @Get => KaKao Script
	//일반로그인 + KaKao Script => @RequestMapping
	//일반로그인 + KaKao api => @Post
	/*
	 * ServletRequest, ServletResponse => session 불가능
	 * HttpServletRequest, HttpServletResponse => session 사용 가능
	 */
	@RequestMapping("/ajax/web_loginok.do")
	public String web_loginok(@RequestParam(name="code") String code,
			@RequestParam(name="mid", required=false) String mid,
			@RequestParam(name="mpass", required=false) String mpass,
			@RequestParam(name="kakao_id", required=false) String kakao_id,
			@RequestParam(name="kakao_nicknm", required=false) String kakao_nicknm,
			Model m,
			HttpServletRequest req
			) throws Exception{
		
		this.session = req.getSession();
		
		if(code.equals("1")) { //일반로그인 처리
			//this.logger.info(mid);
			//this.logger.info(mpass);
			String pw = this.md5.md5_pass(mpass);
			List<membership_DTO> all = this.dao.id_info(mid, pw);
			String msg = null;
			if(all.size() > 0) {
				//this.logger.info("로그인 확인");
				//해당 로그인시 아이디를 session으로 등록함
				this.session.setAttribute("MID", all.get(0).getMID());
				msg = "alert('로그인 하였습니다.'); location.href='./myinfo.do';";
			}else {
				//this.logger.info("미확인");
				msg = "alert('아이디 및 패스워드를 확인하세요.'); history.go(-1);";
			}
			m.addAttribute("msg", msg);
		}
		else if(code.equals("2")) {  //카카오 로그인 처리
			List<membership_DTO> all = this.dao.id_info(kakao_id, "");
			//this.logger.info(kakao_id);
			String msg = null;
			if(all.size() > 0) {
				//해당 로그인시 아이디를 session으로 등록함
				this.session.setAttribute("MID", all.get(0).getMID());
				msg = "alert('로그인 하였습니다.'); location.href='./myinfo.do';";
			}else {
				//sessionStorage를 이용하여 간편회원가입을 등록시키려고 함. ''필요
				msg = "alert('카카오 사용자로 로그인시 간편회원가입이 필요합니다.');"
						+ "sessionStorage.setItem('mid','"+ kakao_id +"');"
						+ "sessionStorage.setItem('mnick','"+ kakao_nicknm +"');"
						+ "location.href='../join.jsp';";
			}
			m.addAttribute("msg", msg);
		}
		
		return "joinok";
	}
	
	
	@RequestMapping(value = "/test.do", method = RequestMethod.GET)
	public String home() {
		try {
			Connection con = dbinfo.getConnection();
			String sql = "select count(*) as ctn from MEMBER";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			this.logger.info(rs.getString("ctn"));
			
			this.logger.info(con.toString());
			this.logger.info("테스트 진행중!!");
			
			con.close();
			
		}catch (Exception e) {
			this.logger.error(e.toString());
			this.logger.debug("오류발생");
		}
		
		return "/WEB-INF/views/home";
	}
	
}
