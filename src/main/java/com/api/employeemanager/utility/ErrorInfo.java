package com.api.employeemanager.utility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorInfo {
	private String errorMessage;
	private Integer errorCode;
}
