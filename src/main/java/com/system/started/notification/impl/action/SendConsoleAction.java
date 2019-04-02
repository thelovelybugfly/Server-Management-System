package com.system.started.notification.impl.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.started.db.DBService;
import com.system.started.db.DBServiceConst;

@Component("sendConsoleAction")
public class SendConsoleAction implements IEventNotificationAction {

	@Autowired
	private DBService dbService;
	
	@Override
	public void dealEventNotification(HashMap<String, Object> paramMap) {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		String description = (String) paramMap.get("description");
		parameters.put("eventId", paramMap.get("eventId"));
		parameters.put("notificationType", "SEND_CONSOLE");
		parameters.put("notificationContent", "告警内容：" + description + "，已通知到控制台！");
		
		dbService.insert(DBServiceConst.INSERT_ZABBIX_EVENT_NOTIFICATION, parameters);
	}
}
