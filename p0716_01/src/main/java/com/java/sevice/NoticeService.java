package com.java.sevice;

import java.util.ArrayList;
import java.util.Map;

import com.java.dto.NoticeDto;

public interface NoticeService {

	ArrayList<NoticeDto> selectAll();

	Map<String, Object> selectOne(NoticeDto ndto);


}
