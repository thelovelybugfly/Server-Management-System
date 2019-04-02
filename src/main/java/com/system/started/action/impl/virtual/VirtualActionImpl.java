package com.system.started.action.impl.virtual;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.started.action.impl.IVirtualAction;
import com.system.started.db.DBService;
import com.system.started.db.DBServiceConst;
import com.vlandc.oss.kernel.virtual.IVirtualService;
import com.vlandc.oss.model.action.EAction;
import com.vlandc.oss.model.engine.VirtualUserObject;
import com.vlandc.oss.model.result.EResultCode;
import com.vlandc.oss.model.result.Result;

@Component("virtualActionImpl")
public class VirtualActionImpl implements IVirtualAction {

	@Autowired
	private IVirtualService kernelVirtualService;
	@Autowired
	private DBService dbService;

	@Override
	public Result doExcutionAction(String loginId,String projectId, EAction action, HashMap<String, Object> actionParam) {
		VirtualUserObject virtualUserObject = getUserObjectByLoginId(loginId,projectId);
		if (virtualUserObject == null) {
			Result result = new Result();
			result.setResultCode(EResultCode.PARAM_VALIDATE_FAIL);
			result.setResultMsg("不能根据(" + loginId + ")查找到详细信息！");
			return result;
		}
		return kernelVirtualService.doExcutionAction(virtualUserObject, action, actionParam);
	}
	
	@Override
	public Result doExcutionAction(String regionName, String loginId, String projectId, EAction action, HashMap<String, Object> actionParam) {
		VirtualUserObject virtualUserObject = getUserObjectByLoginId(loginId,projectId);
		if (virtualUserObject == null) {
			Result result = new Result();
			result.setResultCode(EResultCode.PARAM_VALIDATE_FAIL);
			result.setResultMsg("不能根据(" + loginId + ")查找到详细信息！");
			return result;
		}
		return kernelVirtualService.doExcutionAction(regionName, virtualUserObject, action, actionParam);
	}

	@Override
	public Result doExcutionAction(String loginId, String projectId, EAction action, HashMap<String, Object> actionParam,Integer... resourceIdArray) {
		VirtualUserObject virtualUserObject = getUserObjectByLoginId(loginId,projectId);
		if (virtualUserObject == null) {
			Result result = new Result();
			result.setResultCode(EResultCode.PARAM_VALIDATE_FAIL);
			result.setResultMsg("不能根据(" + loginId + ")查找到详细信息！");
			return result;
		}
		return kernelVirtualService.doExcutionAction(virtualUserObject, action, actionParam, resourceIdArray);
	}
	
	@Override
	public Result doExcutionAction(String regionName, String loginId, String projectId, EAction action, HashMap<String, Object> actionParam, Integer... resourceIdArray) {
		VirtualUserObject virtualUserObject = getUserObjectByLoginId(loginId,projectId);
		if (virtualUserObject == null) {
			Result result = new Result();
			result.setResultCode(EResultCode.PARAM_VALIDATE_FAIL);
			result.setResultMsg("不能根据(" + loginId + ")查找到详细信息！");
			return result;
		}
		return kernelVirtualService.doExcutionAction(regionName, virtualUserObject, action, actionParam, resourceIdArray);
	}

	@Override
	public Result doExcutionAction(String loginId,String projectId, EAction action, HashMap<String, Object> actionParam,String... resourceUuidArray) {
		VirtualUserObject virtualUserObject = getUserObjectByLoginId(loginId,projectId);
		if (virtualUserObject == null) {
			Result result = new Result();
			result.setResultCode(EResultCode.PARAM_VALIDATE_FAIL);
			result.setResultMsg("不能根据(" + loginId + ")查找到详细信息！");
			return result;
		}
		return kernelVirtualService.doExcutionAction(virtualUserObject, action, actionParam, resourceUuidArray);
	}
	
	@Override
	public Result doExcutionAction(String regionName, String loginId,String projectId, EAction action, HashMap<String, Object> actionParam, String... resourceUuidArray) {
		VirtualUserObject virtualUserObject = getUserObjectByLoginId(loginId,projectId);
		if (virtualUserObject == null) {
			Result result = new Result();
			result.setResultCode(EResultCode.PARAM_VALIDATE_FAIL);
			result.setResultMsg("不能根据(" + loginId + ")查找到详细信息！");
			return result;
		}
		return kernelVirtualService.doExcutionAction(regionName, virtualUserObject, action, actionParam, resourceUuidArray);
	}

	private VirtualUserObject getUserObjectByLoginId(String loginId,String projectId) {
		VirtualUserObject userObject = getUserObjectByLoginId(loginId);
		if(userObject != null && projectId != null){
			userObject.setProjectUuid(projectId);
		}
		return userObject;
	}
	
	private VirtualUserObject getUserObjectByLoginId(String loginId) {
		HashMap<String, Object> parameter = new HashMap<>();
		parameter.put("loginId", loginId);
		List<HashMap<String, Object>> resultList = dbService.directSelect(DBServiceConst.SELECT_SYSTEM_USERS, parameter);
		if (resultList != null && resultList.size() > 0) {
			HashMap<String, Object> resultMap = resultList.get(0);
			
			VirtualUserObject userObject = new VirtualUserObject();
			userObject.setUserId(loginId);
			userObject.setUserUuid(String.valueOf(resultMap.get("virtualUuid")));
			userObject.setDepartment(String.valueOf(resultMap.get("department")));
			userObject.setProjectUuid(String.valueOf(resultMap.get("projectId")));
			userObject.setPassword(String.valueOf(resultMap.get("virtualPassword")));
			return userObject;
		}
		return null;
	}
}
