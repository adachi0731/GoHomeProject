package com.fullneflower.ghp.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.ghp.exception.GhpException;

public interface fullneflowerBean {
	String execute(HttpServletRequest request, HttpServletResponse response)throws GhpException;
}
