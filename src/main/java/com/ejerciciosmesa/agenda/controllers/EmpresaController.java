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








import com.ejerciciosmesa.agenda.appdata.AppData;
import com.ejerciciosmesa.agenda.models.entities.Empresa;
import com.ejerciciosmesa.agenda.models.services.EmpresaService;




@Controller
@SessionAttributes("empresa")
@RequestMapping("/empresas")
public class EmpresaController {

	private final AppData appData;
	private final EmpresaService empresaService;
	
	
	
	
	
	
		
	public static final String OPGEN = "EMPRESAS"; 
	
	public EmpresaController(
										 
										 
									     EmpresaService empresaService,
									     AppData applicationData
		   
		   		 
			) {
		this.appData = applicationData;
		this.empresaService = empresaService;
		
		
		

		
	}

		
	
	@GetMapping({"","/","/list"})
	public String list(@RequestParam(name="page", defaultValue="0") int page, Model model) {
	
		fillApplicationData(model,"LIST");
		
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Empresa> pageEmpresa = empresaService.findAll(pageRequest); 
		PageRender<Empresa> paginator = new PageRender<>("/empresas/list",pageEmpresa,5);
		

		model.addAttribute("numempresa", empresaService.count());
		model.addAttribute("listempresa", pageEmpresa);
		model.addAttribute("paginator",paginator);
		
		return "empresas/list";
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		Empresa empresa = new Empresa();		
		model.addAttribute("empresa",empresa);
		
		fillApplicationData(model,"CREATE");
		
		return "empresas/form";
	}
	
	@GetMapping("/form/{id}")
	public String form(@PathVariable Long id, Model model, RedirectAttributes flash) {
		Empresa empresa = empresaService.findOne(id);
		if(empresa==null) {
			flash.addFlashAttribute("error","Data not found");
			return "redirect:/empresa/list";
		}
		
		model.addAttribute("empresa", empresa);
		
		fillApplicationData(model,"UPDATE");
		
		return "empresas/form";
	}
	
	
	@PostMapping("/form")
	@Secured("ROLE_ADMIN")
	public String form(@Valid Empresa empresa,  
			           BindingResult result, 
					   
					   Model model,
					   
					   RedirectAttributes flash,
					   SessionStatus status) {
		
		if(empresa.getId()==null)
			fillApplicationData(model,"CREATE");
		else
			fillApplicationData(model,"UPDATE");
		
		String msg = (empresa.getId()==null) ? "Creation successful" : "Update successful";
		
		if(result.hasErrors()) {
			return "empresas/form";
		}
		
		
		
		
		
		empresaService.save(empresa);
		status.setComplete();
		flash.addFlashAttribute("success",msg);
		return "redirect:/empresas/list";
	}
	
	
	
	

	@Secured("ROLE_ADMIN")
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes flash) {
		
		if(id>0) { 			
			Empresa empresa = empresaService.findOne(id);
			
			if(empresa != null) {
				
		/* Only if there is required relation with this entity */			
				
		
		/* Only if there is no required relation with this entity, or there is a
		 * required relation but no entity related at the moment, (checked before) */
				
		
		/* Relations revised, the entity can be removed */
				empresaService.remove(id);
			} else {
				flash.addFlashAttribute("error","Data not found");
				return "redirect:/empresas/list";
			}
			
			
						
			flash.addFlashAttribute("success","Deletion successful");
		}
		
		return "redirect:/empresas/list";
	}
	
	@GetMapping("/view/{id}")
	public String view(@PathVariable Long id, Model model, RedirectAttributes flash) {

		if (id > 0) {
			Empresa empresa = empresaService.findOne(id);

			if (empresa == null) {
				flash.addFlashAttribute("error", "Data not found");
				return "redirect:/empresas/list";
			}

			model.addAttribute("empresa", empresa);
			fillApplicationData(model, "VIEW");
			return "empresas/view";
			
		}

		return "redirect:/empresas/list";
	}
	
	
	@GetMapping("/viewimg/{id}/{imageField}")
	public String viewimg(@PathVariable Long id, @PathVariable String imageField, Model model, RedirectAttributes flash) {

		if (id > 0) {
			Empresa empresa = empresaService.findOne(id);

			if (empresa == null) {
				flash.addFlashAttribute("error", "Data not found");
				return "redirect:/empresas/list";
			}

			model.addAttribute("empresa", empresa);
			fillApplicationData(model, "VIEWIMG");
			model.addAttribute("backOption",true);
			model.addAttribute("imageField",imageField);
			
			return "empresas/viewimg";
			
		}

		return "redirect:/empresas/list";
	}
	
	
	
	
	private void fillApplicationData(Model model, String screen) {
		model.addAttribute("applicationData",appData);
		model.addAttribute("optionCode",OPGEN);
		model.addAttribute("screen",screen);
	}
	
		
}
