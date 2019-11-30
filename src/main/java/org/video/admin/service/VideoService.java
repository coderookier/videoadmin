package org.video.admin.service;


import org.video.admin.common.utils.PagedResult;
import org.video.admin.pojo.Bgm;

public interface VideoService {

	public void addBgm(Bgm bgm);
	
	public PagedResult queryBgmList(Integer page, Integer pageSize);
	
	public void deleteBgm(String id);
	
	//public PagedResult queryReportList(Integer page, Integer pageSize);
	
	public void updateVideoStatus(String videoId, Integer status);
}
