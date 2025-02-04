package  com.gea.web.controller;

import static com.gea.web.model.constant.GeaWebConstants.MEDIDOR_ERROR_PARAM;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;

import com.gea.web.form.base.BaseForm;
import com.gea.web.model.constant.FormAcciones;
import com.gea.web.model.exception.ControllerException;
import com.gea.web.service.PropertyMessageBO;

public class BasicController extends AbstractView {
	
	@Autowired
	protected PropertyMessageBO propertyMessageBO;
	
	private static final String COMMAND_NAME = "command";

	private static final String VIEW_ERROR_PAGE_MSG = "VIEW_ERROR_PAGE_MSG";
	private static final String GENERAL_ERROR_PARAMETER = "GENERAL_ERROR_PARAMETER";
	
	/**	  
	 * Redirects to the Error view and get the message from the error message key, in order to display it on the error view.
	 * @param messageKey
	 * @return
	 */
	protected ModelAndView redirectToErrorView(String messageKey){
		ModelAndView errorModelView = new ModelAndView();
		if (StringUtils.isEmpty(messageKey)){
			messageKey = GENERAL_ERROR_PARAMETER;
		}
		String errorView = VIEW_ERROR_PAGE_MSG;
		String errorMessage = this.propertyMessageBO.getMessageFromProperties(messageKey);
		errorModelView.addObject("message",errorMessage);
		errorModelView.setViewName(errorView);
		return errorModelView;
	}


	/**
	 * Set success Msg
	 * @param command
	 * @param actionType
	 * @param messageKey
	 */
	protected void setSuccessMsg(BaseForm command, FormAcciones actionType, String messageKey){
		String message = this.propertyMessageBO.getMessageFromProperties(messageKey);
		command.getMessageAndRedirect().setSuccessMessage(message);//Set success message
	}
	
	/**
	 * Redirect to the indicated view and loads the command
	 * @param viewName
	 * @param command
	 * @param actionType
	 * @return
	 */
	protected ModelAndView basicNextModelView(String viewName, BaseForm command, FormAcciones actionType){
		ModelAndView formModelView = new ModelAndView(viewName);		
		command.setAccion(actionType.toString());
		formModelView.clear();
		formModelView.setViewName(viewName);
		formModelView.addObject(COMMAND_NAME, command);
		return formModelView;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void renderMergedOutputModel(Map arg0, HttpServletRequest arg1, HttpServletResponse arg2) throws Exception {
		// Method overrided
	}
	
	/**
	 * Converts String parameter into numeric id
	 * @param medidorIdStr
	 * @return
	 * @throws ControllerException
	 */
	protected int getIdDesdeParametro(String medidorIdStr) throws ControllerException {
		try {
			return Integer.parseInt(medidorIdStr);
		} catch (NumberFormatException e) {
			throw new ControllerException(MEDIDOR_ERROR_PARAM);
		}
	}
}