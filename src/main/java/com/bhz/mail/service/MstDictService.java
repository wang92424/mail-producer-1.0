package com.bhz.mail.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhz.mail.config.database.ReadOnlyConnection;
import com.bhz.mail.entity.MstDict;
import com.bhz.mail.mapper.MstDictMapper;
import com.github.pagehelper.PageHelper;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * @author MrWang
 * @since 2018年3月8日 下午11:02:17
 */
@Service
public class MstDictService {
	
    @Autowired
    private MstDictMapper mstDictMapper;

    @ReadOnlyConnection
    public List<MstDict> findAll(Integer page, Integer rows){
        if (page != null && page != null) {
            PageHelper.startPage(page, rows);
        }
        return mstDictMapper.selectAll();
    }
    
    public List<MstDict> findByStatus(Map<String, Object> params){
    	return mstDictMapper.findByStatus(params);
    }

}
