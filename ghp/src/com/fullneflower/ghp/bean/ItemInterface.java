package com.fullneflower.ghp.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.ghp.exception.GhpException;

public interface ItemInterface {
	int execute(HttpServletRequest request, HttpServletResponse response)throws GhpException;
}
