package pos.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pos.system.dto.PositionDTO;
import pos.system.service.MainService;

@Controller
public class PositionController {

    private final MainService mainService;

    private Integer isMessage;
    private String name;

    @Autowired
    PositionController(MainService mainService) {
        this.mainService = mainService;
        isMessage = 0;
    }

    @GetMapping("/positions")
    public String getPositions(Model model) {
        model.addAttribute("title", "Должности");
        model.addAttribute("positions", mainService.findAllPositionByCompany());
        model.addAttribute("isMessage", isMessage);
        if (isMessage == -1)
            model.addAttribute("message", "Должность \"" + name + "\" не может быть удалена, возможно она связа с каким-то работником. Удалите всех работников с текущей должностью, после этого удалите должность.");
        isMessage = 0;
        return "positions";
    }

    @GetMapping("position/create")
    public String getCreatePosition(Model model) {
        model.addAttribute("title", "Должности");
        model.addAttribute("editing", false);
        model.addAttribute("isMessage", isMessage);
        if (isMessage == 0)
            model.addAttribute("message", null);
        if (isMessage == -1)
            model.addAttribute("message", "Должность \"" + name + "\" уже существует");
        if (isMessage == 1)
            model.addAttribute("message", "Должность \"" + name + "\" успешно добавлена");
        isMessage = 0;
        return "createPosition";
    }

    @PostMapping("position/create")
    public String postCreatePosition(PositionDTO positionDTO) {
        name = positionDTO.getName();
        try{
            mainService.savePosition(positionDTO);
            isMessage =  1;
        } catch (Exception e) {
            isMessage = -1;
        }
        return "redirect:/position/create";
    }

    @GetMapping("position/{id}/edit")
    public String getEditPosition(Model model, @PathVariable Long id) {
        model.addAttribute("position", mainService.findPositionById(id));
        model.addAttribute("title", "Должности");
        model.addAttribute("editing", true);
        model.addAttribute("isMessage", isMessage);
        if (isMessage == -1)
            model.addAttribute("message", "Должность \"" + name + "\" уже существует");
        isMessage = 0;
        return "createPosition";
    }

    @PostMapping("position/{id}/edit")
    public String postEditPosition(@PathVariable Long id, PositionDTO positionDTO) {
        try {
            mainService.savePositionById(id, positionDTO);
            return "positions";
        } catch(Exception e) {
            isMessage = -1;
            name = positionDTO.getName();
            return "redirect:/position/" + id + "/edit";
        }
    }

    @PostMapping("position/{id}/delete")
    public String postDeletePosition(@PathVariable Long id) {
        try {
            mainService.deletePosition(id);
        } catch(Exception e) {
            isMessage = -1;
            name = mainService.findPositionById(id).getName();
        }
        return "redirect:/positions";
    }
}
