package com.fullneflower.ghp.exception;
/**
 * 例外クラス
 * @author 森
 *
 */
public class GhpException extends Exception {


	public GhpException(){
		super();
	}
	public GhpException(String msg,Throwable e){
		super(msg,e);
	}
	public GhpException(String msg){
		super(msg);
	}
	public GhpException(Throwable e){
		super(e);
	}
}
