package com.example.demovalidate.service.status;

import com.example.demovalidate.event.ProcessEvent;
import com.example.demovalidate.inter.status.AbnormalStatusProcessBase;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 项目名称：demo-validate
 * 类 名 称：AbnormalStatusProcessService
 * 类 描 述：TODO
 * 创建时间：2022/12/10 17:56
 * 创 建 人：panyong
 */
@Service
public class AbnormalStatusProcessService implements AbnormalStatusProcessBase {

    @Override
    public String releaseDoc(ProcessEvent event) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public String audit(ProcessEvent event) {
        return null;
    }

    @Override
    public String releaseAnnounce(ProcessEvent event) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public String edit(ProcessEvent event) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public String confirm(ProcessEvent event) {
        return null;
    }

    @Override
    public String approve(ProcessEvent event) {
        return null;
    }

    @Override
    public String sign(ProcessEvent event) {
        throw new UnsupportedOperationException("");
    }
}
