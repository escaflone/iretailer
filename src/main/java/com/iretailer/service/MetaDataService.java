package com.iretailer.service;

import com.iretailer.dao.MetaDataMapper;
import com.iretailer.dao.UserMapper;
import com.iretailer.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wubin on 2017/6/23.
 */
@Service
public class MetaDataService {

    @Autowired
    private MetaDataMapper metaDataMapper;

}