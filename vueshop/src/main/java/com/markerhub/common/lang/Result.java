package com.markerhub.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

	//	结果状态（如200表示成功，400表示异常）
	private int code;
	//	结果消息
	private String msg;
	//	结果数据
	private Object data;

	public static Result success() {
		return success(null);
	}

	public static Result success(Object data) {
		Result result = new Result();
		result.setCode(200);
		result.setMsg("操作成功");
		result.setData(data);
		return result;
	}

	public static Result fail(String msg) {
		Result result = new Result();
		result.setCode(400);
		result.setMsg(msg);
		result.setData(null);
		return result;
	}

}
