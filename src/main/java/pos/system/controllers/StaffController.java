package pos.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pos.system.dto.StaffDTO;
import pos.system.service.MainService;

@Controller
public class StaffController {

    private final MainService mainService;

    private Integer isMessage;
    private String email;

    @Autowired
    StaffController(MainService mainService) {
        this.mainService = mainService;
        isMessage = 0;
    }

    @GetMapping("/staffs")
    public String getStaffs(Model model) {
        model.addAttribute("title", "Персонал");
        model.addAttribute("staffs", mainService.findAllStaffByCompany());
        model.addAttribute("isMessage", isMessage);
        if (isMessage == -1)
            model.addAttribute("message", "Данный работник не может быть удален.");
        isMessage = 0;
        return "staffs";
    }

    @GetMapping("staff/create")
    public String getCreateStaff(Model model) {
        model.addAttribute("positions", mainService.findAllPositionByCompany());
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
            mainService.saveStaff(staffDTO);
            isMessage =  1;
        } catch (Exception e) {
            isMessage = -1;
            return "redirect:/staff/create";
        }
        return "redirect:/staff/create";
    }

    @GetMapping("staff/{id}/edit")
    public String getEditStaff(Model model, @PathVariable Long id) {
        model.addAttribute("positions", mainService.findAllPositionByCompany());
        model.addAttribute("staff", mainService.findStaffById(id));
        model.addAttribute("title", "Персонал");
        model.addAttribute("editing", true);
        model.addAttribute("isMessage", isMessage);
        if (isMessage == -1)
            model.addAttribute("message", "Персонал с таким email \"" + email + "\" уже существует");
        isMessage = 0;
        return "createStaff";
    }

    @PostMapping("staff/{id}/edit")
    public String postEditStaff(Model model, @PathVariable Long id, StaffDTO staffDTO) {
        try {
            mainService.saveStaffById(id, staffDTO);
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
            mainService.deleteStaff(id);
        } catch (Exception e) {
            isMessage = -1;
            email = mainService.findStaffById(id).getName();
        }
        return "redirect:/staffs";
    }
}
