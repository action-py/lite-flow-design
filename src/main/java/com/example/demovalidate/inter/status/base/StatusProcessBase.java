package com.example.demovalidate.inter.status.base;

import com.example.demovalidate.event.ProcessEvent;

public interface StatusProcessBase {
     //发布阶段
     String releaseDoc(ProcessEvent event);
     String audit(ProcessEvent event);
     String releaseAnnounce(ProcessEvent event);

     //签约阶段
     String edit(ProcessEvent event);
     String confirm(ProcessEvent event);
     String approve(ProcessEvent event);
     String sign(ProcessEvent event);


}
