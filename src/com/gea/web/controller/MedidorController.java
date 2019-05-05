package com.gea.web.controller;

import static com.gea.web.model.constant.GeaWebConstants.MEDIDOR_ERROR_ELIMINAR;
import static com.gea.web.model.constant.GeaWebConstants.MEDIDOR_ERROR_CREAR;
import static com.gea.web.model.constant.GeaWebConstants.MEDIDOR_ERROR_ACTUALIZAR;
import static com.gea.web.model.constant.GeaWebConstants.MEDIDOR_ERROR_GET;
import static com.gea.web.model.constant.GeaWebConstants.MEDIDOR_ERROR_NOT_FOUND;
import static com.gea.web.model.constant.GeaWebConstants.MEDIDOR_ERROR_NO_EXISTE;
import static com.gea.web.model.constant.GeaWebConstants.MEDIDOR_SUCCESS_CREAR;
import static com.gea.web.model.constant.GeaWebConstants.MEDIDOR_SUCCESS_ELIMINAR;
import static com.gea.web.model.constant.GeaWebConstants.MEDIDOR_SUCCESS_ACTUALIZAR;
import static com.gea.web.model.constant.GeaWebConstants.MEDIDOR_ERROR_PARAM;
import static com.gea.web.model.constant.GeaWebConstants.VIEW_MEDIDOR_FORM;
import static com.gea.web.model.constant.GeaWebConstants.VIEW_MEDIDOR_LISTAR;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gea.web.form.MedidorForm;
import com.gea.web.model.Medidor;
import com.gea.web.model.exception.ControllerException;
import com.gea.web.model.exception.GeaWebException;
import com.gea.web.model.constant.FormAcciones;
import com.gea.web.service.MedidorBO;
import com.gea.web.validator.MedidorValidator;

@Controller
public class MedidorController extends BasicController {
	
	private static Log log = LogFactory.getLog(MedidorController.class);
	
	@Autowired
	private MedidorBO medidorBO;
	
	@Autowired
	private MedidorValidator medidorValidator;	
		
	@RequestMapping(value="/**/web/listarMedidor.do")
	public ModelAndView crearMedidor(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") MedidorForm command) throws Exception {
		log.info("### INICIAR crearMedidor.do ###");
		ModelAndView listModelView = this.nextModelView(VIEW_MEDIDOR_LISTAR, command, FormAcciones.LISTAR);
		log.info("### END crearMedidors.do ###");
		return listModelView;
	}
	
	@RequestMapping(value = "/**/web/eliminarMedidor.do")
	public ModelAndView eliminarMedidor(HttpServletRequest request, HttpServletResponse response,  @ModelAttribute("command") MedidorForm command, @RequestParam(value = "medidorId", required=true)String medidorIdStr) throws Exception {
		log.info("### INICIAR eliminarMedidor.do ###");
		command.setAccion(FormAcciones.ELIMINAR.toString());
		Medidor medidor = command.getMedidor();	
		boolean errorOnAction = true;
		String errorPropertyKey = "";

		if (medidor != null){
			try {
				errorOnAction = false;
				this.medidorBO.delete(medidor);
			} catch (Exception e) {
				log.error("Error trying to eliminar Medidor");
				errorPropertyKey = MEDIDOR_ERROR_ELIMINAR;
			}
		}else{
			errorPropertyKey = MEDIDOR_ERROR_NO_EXISTE;
		}
		
		if (errorOnAction){
			String errorMessage = this.propertyMessageBO.getMessageFromProperties(errorPropertyKey);
			command.getMessageAndRedirect().setErrorMessage(errorMessage);
		}else{
			String successMessage = this.propertyMessageBO.getMessageFromProperties(MEDIDOR_SUCCESS_ELIMINAR);
			command.getMessageAndRedirect().setSuccessMessage(successMessage);
		}
		//After the process shows the listar refreshed
		ModelAndView listModelView = this.nextModelView(VIEW_MEDIDOR_LISTAR, command, FormAcciones.ELIMINAR);
		log.info("### END eliminarMedidor.do ###");
		return listModelView;
	}			
	
	//Get method for Create Medidor
	@RequestMapping(value = "/**/web/crearMedidor.do", method = RequestMethod.GET)
	public ModelAndView crearMedidorGet(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") MedidorForm command) throws Exception{
		log.info("### INICIAR crearMedidorGet.do GET ###");
		ModelAndView formModelView = this.nextModelView(VIEW_MEDIDOR_FORM, command, FormAcciones.CREAR);
		log.info("### FINALIZAR crearMedidorGet.do GET ###");
		return formModelView;
	}

	//Post method for Create Medidor
	@RequestMapping(value = "/**/web/crearMedidor.do", method = RequestMethod.POST)
	public String crearMedidorPost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") MedidorForm command, BindingResult result) throws Exception {
		log.info("### INICIAR crearMedidorGet.do POST ###");
		String nextView = this.postMethodProcess(command, result);
		log.info("### FINALIZAR crearMedidorGet.do POST ###");
		return nextView;
	}
	
	//Get method for Modify Medidor
	@RequestMapping(value = "/**/web/modificarMedidor.do", method = RequestMethod.GET)
	public ModelAndView modificarMedidorGet(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") MedidorForm command, @RequestParam(value = "medidorId", required=true)String medidorIdStr) throws Exception{
		log.info("### INICIAR modificarMedidor.do GET ###");
		//Get the Medidor and populates the form
		Medidor medidor = this.getMedidorFormParamater(medidorIdStr);			
		command.setMedidor(medidor);
		ModelAndView formModelView = this.nextModelView(VIEW_MEDIDOR_FORM, command, FormAcciones.ACTUALIZAR);
		log.info("### FINALIZAR modificarMedidor.do GET ###");
		return formModelView;
	}
	
	//Post method for Modify Medidor
	@RequestMapping(value = "/**/web/modificarMedidor.do", method = RequestMethod.POST)
	public String modificarMedidorPost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") MedidorForm command, BindingResult result) throws Exception {
		log.info("### INICIAR modificarMedidor.do POST ###");
		String nextView = this.postMethodProcess(command, result);
		log.info("### FINALIZAR modificarMedidor.do POST ###");
		return nextView;
	}	
	
	/**
	 * Converts String parameter into numeric id
	 * @param medidorIdStr
	 * @return
	 * @throws ControllerException
	 */
	private int getIdDesdeParametro(String medidorIdStr) throws ControllerException {
		try {
			return Integer.parseInt(medidorIdStr);
		} catch (NumberFormatException e) {
			throw new ControllerException(MEDIDOR_ERROR_PARAM);
		}
	}
	
	/**
	 * Get Medidor for database and handle it if null
	 * @param medidorIdStr
	 * @return
	 * @throws ControllerException
	 */
	private Medidor getMedidorFormParamater(String medidorIdStr) throws ControllerException  {
		try {
			int id = this.getIdDesdeParametro(medidorIdStr);
			Medidor medidor = this.medidorBO.getMedidorById(id);
			if (medidor == null) {
				throw new ControllerException(MEDIDOR_ERROR_NOT_FOUND);
			}
			return medidor;
		} catch (GeaWebException e) {
			throw new ControllerException(MEDIDOR_ERROR_GET);
		}
	}
	
	/**
	 * Load drop down options
	 * @param command
	 */
	private void setDropDownValues(MedidorForm command){
		try {
			//TODO add code here to get dropdowns
		} catch (Exception e) {
			log.error("Error loading drop down values", e);
		}
	}
	
	
	/**
	 * Process the request according the action, validates the data and process the information into the database
	 * @param command
	 * @param result
	 * @return
	 */
	private String postMethodProcess(MedidorForm command, BindingResult result) {
		boolean errorOnPost=false;
		String nextView = VIEW_MEDIDOR_FORM;
		String message = "";
		command.getMessageAndRedirect().cleanMsgValue(); //Cleans message and redirect info
		this.setDropDownValues(command); //Loads the drop down lists
		this.medidorValidator.validate(command, result); //validates the form
		if (result.hasErrors()) {
			errorOnPost = true;
		} else {
			String logEntryOnError = "";
			String errorPropertyKey = "";
			String successPropertyKey = "";
			FormAcciones actionType = FormAcciones.getAccionByDesc(command.getAccion());
			try {				
				//Process insert
				if (actionType == FormAcciones.CREAR){
					logEntryOnError = "Error trying to insert new Medidor Page";
					errorPropertyKey = MEDIDOR_ERROR_CREAR;
					successPropertyKey = MEDIDOR_SUCCESS_CREAR;
					this.medidorBO.save(command.getMedidor());
				}		
				//Process update
				if (actionType == FormAcciones.ACTUALIZAR){
					logEntryOnError = "Error trying to update the Medidor Page";
					errorPropertyKey = MEDIDOR_ERROR_ACTUALIZAR;
					successPropertyKey = MEDIDOR_SUCCESS_ACTUALIZAR;
					this.medidorBO.update(command.getMedidor()); 
				}
			}catch(Exception e){
				log.error(logEntryOnError, e);
				errorOnPost = true;
				this.medidorValidator.setErrorAtException(result, errorPropertyKey);
			}

			if (!errorOnPost){
				message = this.propertyMessageBO.getMessageFromProperties(successPropertyKey);
				command.getMessageAndRedirect().setSuccessMessage(message);//Set success message
			}			
		}			
		return nextView;
	}
	
	/**
	 * Redirect to the indicated view and loads the command
	 * @param viewName
	 * @param command
	 * @param actionType
	 * @return
	 */
	private ModelAndView nextModelView(String viewName, MedidorForm command, FormAcciones actionType){
		ModelAndView formModelView = new ModelAndView(viewName);		
		command.setAccion(actionType.toString());
		
		//TODO when required...
		this.setDropDownValues(command);
		
		formModelView.setViewName(viewName);
		formModelView.addObject("command", command);
		return formModelView;
	}
}

