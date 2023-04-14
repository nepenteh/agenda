package com.ejerciciosmesa.agenda.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ejerciciosmesa.agenda.util.paginator.PageRender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import org.springframework.web.bind.support.SessionStatus;



import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import com.ejerciciosmesa.agenda.models.services.UploadService;





import com.ejerciciosmesa.agenda.appdata.AppData;
import com.ejerciciosmesa.agenda.models.entities.Persona;
import com.ejerciciosmesa.agenda.models.services.PersonaService;




@Controller
@SessionAttributes("persona")
@RequestMapping("/personas")
public class PersonaController {

	private final AppData appData;
	private final PersonaService personaService;
	
	
	
	
	
	private final UploadService uploadService;

		
	public static final String OPGEN = "PERSONAS"; 
	
	public PersonaController(UploadService uploadService,
										 
										 
									     PersonaService personaService,
									     AppData applicationData
		   
		   		 
			) {
		this.appData = applicationData;
		this.personaService = personaService;
		
		
		

		this.uploadService = uploadService;

	}

		
	
	@GetMapping({"","/","/list"})
	public String list(@RequestParam(name="page", defaultValue="0") int page, Model model) {
	
		fillApplicationData(model,"LIST");
		
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Persona> pagePersona = personaService.findAll(pageRequest); 
		PageRender<Persona> paginator = new PageRender<>("/personas/list",pagePersona,5);
		

		model.addAttribute("numpersona", personaService.count());
		model.addAttribute("listpersona", pagePersona);
		model.addAttribute("paginator",paginator);
		
		return "personas/list";
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		Persona persona = new Persona();		
		model.addAttribute("persona",persona);
		
		fillApplicationData(model,"CREATE");
		
		return "personas/form";
	}
	
	@GetMapping("/form/{id}")
	public String form(@PathVariable Long id, Model model, RedirectAttributes flash) {
		Persona persona = personaService.findOne(id);
		if(persona==null) {
			flash.addFlashAttribute("error","Data not found");
			return "redirect:/persona/list";
		}
		
		model.addAttribute("persona", persona);
		
		fillApplicationData(model,"UPDATE");
		
		return "personas/form";
	}
	
	
	@PostMapping("/form")
	@Secured("ROLE_ADMIN")
	public String form(@Valid Persona persona,  
			           BindingResult result, 
					   
					   Model model,
					   @RequestAttribute("file") MultipartFile foto_formname,
@RequestParam("fotoImageText") String fotoImageText,
@RequestParam("fotoImageTextOld") String fotoImageTextOld,

					   RedirectAttributes flash,
					   SessionStatus status) {
		
		if(persona.getId()==null)
			fillApplicationData(model,"CREATE");
		else
			fillApplicationData(model,"UPDATE");
		
		String msg = (persona.getId()==null) ? "Creation successful" : "Update successful";
		
		if(result.hasErrors()) {
			return "personas/form";
		}
		
		if(!foto_formname.isEmpty())
	AddUpdateImageFoto(foto_formname,persona);
else {
	if(fotoImageText.isEmpty() && !(fotoImageTextOld.isEmpty())) {
		uploadService.delete(fotoImageTextOld);
		persona.setFoto(null);
	}
}



		
		
		
		personaService.save(persona);
		status.setComplete();
		flash.addFlashAttribute("success",msg);
		return "redirect:/personas/list";
	}
	
	
	private void AddUpdateImageFoto(MultipartFile image, Persona persona) {
					
			if(persona.getId()!=null &&
			   persona.getId()>0 && 
			   persona.getFoto()!=null &&
			   persona.getFoto().length() > 0) {
			
				uploadService.delete(persona.getFoto());
			}
			
			String uniqueName = null;
			try {
				uniqueName = uploadService.copy(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			persona.setFoto(uniqueName);
		
	}

	

	@Secured("ROLE_ADMIN")
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes flash) {
		
		if(id>0) { 			
			Persona persona = personaService.findOne(id);
			
			if(persona != null) {
				
		/* Only if there is required relation with this entity */			
				
		
		/* Only if there is no required relation with this entity, or there is a
		 * required relation but no entity related at the moment, (checked before) */
				
		
		/* Relations revised, the entity can be removed */
				personaService.remove(id);
			} else {
				flash.addFlashAttribute("error","Data not found");
				return "redirect:/personas/list";
			}
			
			if(persona.getFoto()!=null)
	uploadService.delete(persona.getFoto());

						
			flash.addFlashAttribute("success","Deletion successful");
		}
		
		return "redirect:/personas/list";
	}
	
	@GetMapping("/view/{id}")
	public String view(@PathVariable Long id, Model model, RedirectAttributes flash) {

		if (id > 0) {
			Persona persona = personaService.findOne(id);

			if (persona == null) {
				flash.addFlashAttribute("error", "Data not found");
				return "redirect:/personas/list";
			}

			model.addAttribute("persona", persona);
			fillApplicationData(model, "VIEW");
			return "personas/view";
			
		}

		return "redirect:/personas/list";
	}
	
	
	@GetMapping("/viewimg/{id}/{imageField}")
	public String viewimg(@PathVariable Long id, @PathVariable String imageField, Model model, RedirectAttributes flash) {

		if (id > 0) {
			Persona persona = personaService.findOne(id);

			if (persona == null) {
				flash.addFlashAttribute("error", "Data not found");
				return "redirect:/personas/list";
			}

			model.addAttribute("persona", persona);
			fillApplicationData(model, "VIEWIMG");
			model.addAttribute("backOption",true);
			model.addAttribute("imageField",imageField);
			
			return "personas/viewimg";
			
		}

		return "redirect:/personas/list";
	}
	
	
	
	
	private void fillApplicationData(Model model, String screen) {
		model.addAttribute("applicationData",appData);
		model.addAttribute("optionCode",OPGEN);
		model.addAttribute("screen",screen);
	}
	
		
}
