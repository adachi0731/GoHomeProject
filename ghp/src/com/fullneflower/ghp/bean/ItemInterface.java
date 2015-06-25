package com.fullneflower.ghp.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.ghp.exception.GhpException;
/**
 * 命令を実行するインターフェース(int)
 * @author 本多
 */
public interface ItemInterface {
	int execute(HttpServletRequest request, HttpServletResponse response)throws GhpException;
}
