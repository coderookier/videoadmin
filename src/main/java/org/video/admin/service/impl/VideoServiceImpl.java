package org.video.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.video.admin.common.enums.BGMOperatorTypeEnum;
import org.video.admin.common.org.n3r.idworker.Sid;
import org.video.admin.common.utils.PagedResult;
import org.video.admin.mapper.BgmMapper;
import org.video.admin.mapper.VideosMapper;
import org.video.admin.pojo.Bgm;
import org.video.admin.pojo.BgmExample;
import org.video.admin.pojo.Videos;
import org.video.admin.service.VideoService;


@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideosMapper videosMapper;
		
	@Autowired
	private BgmMapper bgmMapper;
	
	@Autowired
	private Sid sid;
	
//	@Autowired
//	private ZKCurator zkCurator;
	
//	@Autowired
//	private UsersReportMapperCustom usersReportMapperCustom;
	
//	@Override
//	public PagedResult queryReportList(Integer page, Integer pageSize) {
//
//		PageHelper.startPage(page, pageSize);
//
//		List<Reports> reportsList = usersReportMapperCustom.selectAllVideoReport();
//
//		PageInfo<Reports> pageList = new PageInfo<Reports>(reportsList);
//
//		PagedResult grid = new PagedResult();
//		grid.setTotal(pageList.getPages());
//		grid.setRows(reportsList);
//		grid.setPage(page);
//		grid.setRecords(pageList.getTotal());
//
//		return grid;
//	}

	@Override
	public void updateVideoStatus(String videoId, Integer status) {
		
		Videos video = new Videos();
		video.setId(videoId);
		video.setStatus(status);
		videosMapper.updateByPrimaryKeySelective(video);
	}

	@Override
	public PagedResult queryBgmList(Integer page, Integer pageSize) {
		
		PageHelper.startPage(page, pageSize);
		
		BgmExample example = new BgmExample();
		List<Bgm> list = bgmMapper.selectByExample(example);
		
		PageInfo<Bgm> pageList = new PageInfo<>(list);
		
		PagedResult result = new PagedResult();
		result.setTotal(pageList.getPages());
		result.setRows(list);
		result.setPage(page);
		result.setRecords(pageList.getTotal());
		
		return result;
	}

	@Override
	public void addBgm(Bgm bgm) {
		String bgmId = sid.nextShort();
		bgm.setId(bgmId);
		System.out.println(bgm.getId());
		System.out.println(bgm.getAuthor());
		System.out.println(bgm.getName());
		System.out.println(bgm.getPath());
		bgmMapper.insert(bgm);

	//	zkCurator.sendBgmOperator(bgmId, JsonUtils.objectToJson(map));
	}
	
	@Override
	public void deleteBgm(String id) {
		Bgm bgm = bgmMapper.selectByPrimaryKey(id);
		
		bgmMapper.deleteByPrimaryKey(id);
		
		Map<String, String> map = new HashMap<>();
		map.put("operType", BGMOperatorTypeEnum.DELETE.type);
		map.put("path", bgm.getPath());
		
	//	zkCurator.sendBgmOperator(id, JsonUtils.objectToJson(map));
		
	}

}
