package api;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface api_mapper {
	public int pdtest(Map<String, String> data); //map
	public List<api_dto> pdlist();
}
