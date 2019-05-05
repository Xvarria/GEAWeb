package com.gea.web.form.base;

import com.gea.web.model.constant.FormAcciones;

public class BaseForm {

	private MessageAndRedirect messageAndRedirect = new MessageAndRedirect();
	private String accion = "";
	
	private final String actionView = FormAcciones.VER.toString();
	private final String actionDelete = FormAcciones.ELIMINAR.toString();
	private final String actionCreate = FormAcciones.CREAR.toString();
	private final String actionModify = FormAcciones.ACTUALIZAR.toString();
	private final String actionList = FormAcciones.LISTAR.toString();
		
	private LinkElement option1 = new LinkElement();
	private LinkElement option2 = new LinkElement();
	
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	public MessageAndRedirect getMessageAndRedirect() {
		return messageAndRedirect;
	}
	
	public void setMessageAndRedirect(MessageAndRedirect messageAndRedirect) {
		this.messageAndRedirect = messageAndRedirect;
	}
	
	//ReadonlyMethods
	public String getActionCreate() {
		return actionCreate;
	}
	
	public String getActionModify() {
		return actionModify;
	}
	
	public String getActionList() {
		return actionList;
	}
	
	public String getActionView() {
		return actionView;
	}

	public String getActionDelete() {
		return actionDelete;
	}
	
	public boolean isCreate(){
		return accion.equals(actionCreate);
	}
	
	public boolean isModify(){
		return accion.equals(actionModify);
	}
	
	public boolean isView(){
		return accion.equals(actionView);
	}
	
	public LinkElement getOption1() {
		return option1;
	}
	
	public void setOption1(LinkElement option1) {
		this.option1 = option1;
	}
	
	public LinkElement getOption2() {
		return option2;
	}
	
	public void setOption2(LinkElement option2) {
		this.option2 = option2;
	}
}
