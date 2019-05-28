package pos.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pos.system.dto.PositionDTO;
import pos.system.dto.StaffDTO;
import pos.system.entities.Position;
import pos.system.entities.Staff;
import pos.system.service.CompanyService;
import pos.system.service.PositionService;
import pos.system.service.StaffService;

import java.util.ArrayList;

@Controller
public class StaffController {

    private final StaffService staffService;
    private final PositionService positionService;
    private final CompanyService companyService;

    private Integer isMessage;
    private String email;

    @Autowired
    StaffController(StaffService staffService, PositionService positionService, CompanyService companyService) {
        this.staffService = staffService;
        this.positionService = positionService;
        this. companyService = companyService;
        isMessage = 0;
    }

    @GetMapping("/staffs")
    public String getStaffs(Model model) {
        ArrayList<StaffDTO> staffsDTO = new ArrayList<StaffDTO>();
        Iterable<Staff> staffs;
        staffs = staffService.findAllByCompany(companyService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        for (Staff staff: staffs) {
            staffsDTO.add(staff.convertToDTO());
        }
        model.addAttribute("title", "Персонал");
        model.addAttribute("staffs", staffsDTO);
        model.addAttribute("isMessage", isMessage);
        if (isMessage == -1)
            model.addAttribute("message", "Данный работник не может быть удален.");
        isMessage = 0;
        return "staffs";
    }

    @GetMapping("staff/create")
    public String getCreateStaff(Model model) {
        ArrayList<PositionDTO> positionsDTO = new ArrayList<PositionDTO>();
        Iterable<Position> positions;
        positions = positionService.findAllByCompany(companyService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        for (Position position: positions) {
            positionsDTO.add(position.convertToDTO());
        }
        model.addAttribute("positions", positionsDTO);
        model.addAttribute("title", "Персонал");
        model.addAttribute("editing", false);
        model.addAttribute("isMessage", isMessage);
        if (isMessage == 0)
            model.addAttribute("message", null);
        if (isMessage == -1)
            model.addAttribute("message", "Персонал с таким email \"" + email + "\" уже существует");
        if (isMessage == 1)
            model.addAttribute("message", "Работник успешно добавлен");
        isMessage = 0;
        return "createStaff";
    }

    @PostMapping("staff/create")
    public String postCreateStaff(Model model, StaffDTO staffDTO) {
        email = staffDTO.getEmail();
        try {
            staffService.save(staffDTO.convertToEntity(positionService.findByName(staffDTO.getPosition()), companyService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())));
            isMessage =  1;
        } catch (Exception e) {
            isMessage = -1;
            return "redirect:/staff/create";
        }
        return "redirect:/staff/create";
    }

    @GetMapping("staff/{id}/edit")
    public String getEditStaff(Model model, @PathVariable Long id) {
        ArrayList<PositionDTO> positionsDTO = new ArrayList<PositionDTO>();
        Iterable<Position> positions;
        positions = positionService.findAllByCompany(companyService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        for (Position position: positions) {
            positionsDTO.add(position.convertToDTO());
        }
        model.addAttribute("positions", positionsDTO);
        Staff staff = staffService.findById(id);
        StaffDTO staffDTO = staff.convertToDTO();
        model.addAttribute("staff", staffDTO);
        model.addAttribute("title", "Персонал");
        model.addAttribute("editing", true);
        model.addAttribute("isMessage", isMessage);
        if (isMessage == -1)
            model.addAttribute("message", "Должность \"" + email + "\" уже существует");
        isMessage = 0;
        return "createStaff";
    }

    @PostMapping("staff/{id}/edit")
    public String postEditStaff(Model model, @PathVariable Long id, StaffDTO staffDTO) {
        Staff staff = staffService.findById(id);
        staff.setName(staffDTO.getName());
        staff.setSurname(staffDTO.getSurname());
        staff.setEmail(staffDTO.getEmail());
        staff.setPassword(staffDTO.getPassword());
        staff.setPosition(positionService.findByName(staffDTO.getPosition()));
        try {
            staffService.save(staff);
            return "redirect:/staffs";
        } catch (Exception e) {
            isMessage = -1;
            email = staffDTO.getEmail();
            return "redirect:/staff/" + id + "/edit";
        }
    }

    @PostMapping("staff/{id}/delete")
    public String postDeleteStaff(@PathVariable Long id) {
        try {
            staffService.delete(id);
        } catch (Exception e) {
            isMessage = -1;
            email = staffService.findById(id).getName();
        }
        return "redirect:/staffs";
    }
}
