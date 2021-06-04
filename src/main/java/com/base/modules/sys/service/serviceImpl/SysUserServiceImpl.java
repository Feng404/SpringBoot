package com.base.modules.sys.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.modules.sys.entity.SysUserEntity;
import com.base.modules.sys.mapper.SysUserMapper;
import com.base.modules.sys.service.SysUserService;
import com.base.utils.Query;
import com.base.utils.SendMailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ClassName  SysUserServiceImpl
 * Description    用户serviceImpl
 * Author F
 * Date   2021/6/3 16:51
 * Version    1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {
    @Autowired
    private SendMailUtils sendMailUtils;

    @Override
    public Page queryAllUserByPage(Map<String, Object> params) {
        Page<SysUserEntity> page = new Query<SysUserEntity>(params).getPage();
        List<SysUserEntity> userList = baseMapper.queryAllUserByPage(page, params);
        return page.setRecords(userList);
    }

    @Override
    public void sendMailWithAttachment() throws Exception {
        String targetAddress = "fenglaixiang@weichai.com";
        String subject = "justForTest";
        String innerMessage = "<p>你好: </p> <p  style='color: green; font-size: medium;'>&ensp;&ensp;&ensp;&ensp;无需回复, 测试中</p>";
        List<String> attachmentList = new LinkedList<>();
        // 附件位置
        attachmentList.add("/home/zhaoyj/projectLocation/attachment/001.png");
        sendMailUtils.sendMailWithAttachment(targetAddress, subject, innerMessage, attachmentList);
    }

}
