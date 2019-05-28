package pos.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pos.system.dto.PositionDTO;
import pos.system.entities.Position;
import pos.system.service.CompanyService;
import pos.system.service.PositionService;

import java.util.ArrayList;

@Controller
public class PositionController {

    private final CompanyService companyService;
    private final PositionService positionService;

    private Integer isMessage;
    private String name;
    private String namePosition;

    @Autowired
    PositionController(CompanyService companyService, PositionService positionService) {
        this.companyService = companyService;
        this.positionService = positionService;
        isMessage = 0;
    }

    @GetMapping("/positions")
    public String getPositions(Model model) {
        ArrayList<PositionDTO> positionsDTO= new ArrayList<PositionDTO>();
        Iterable<Position> positions;
        positions = positionService.findAllByCompany(companyService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        for (Position position: positions) {
            positionsDTO.add(position.convertToDto());
        }
        model.addAttribute("title", "Должности");
        model.addAttribute("positions", positionsDTO);
        model.addAttribute("isMessage", isMessage);
        if (isMessage == -1)
            model.addAttribute("message", "Должность \"" + namePosition + "\" не может быть удалена, возможно она связа с каким-то работником. Удалите всех работников с текущей должностью, после этого удалите должность.");
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
            positionDTO.setCompany(companyService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
            positionService.save(positionDTO.convertToEntity());
            isMessage =  1;
        } catch (Exception e) {
            isMessage = -1;
            return "redirect:/position/create";
        }
        return "redirect:/position/create";
    }

    @GetMapping("position/{id}/edit")
    public String getEditPosition(Model model, @PathVariable Long id) {
        Position position = positionService.findById(id);
        PositionDTO positionDTO = position.convertToDto();
        model.addAttribute("position", positionDTO);
        model.addAttribute("title", "Должности");
        model.addAttribute("editing", true);
        model.addAttribute("isMessage", isMessage);
        if (isMessage == -1)
            model.addAttribute("message", "Должность \"" + name + "\" уже существует");
        isMessage = 0;
        return "createPosition";
    }

    @PostMapping("position/{id}/edit")
    public String postEditPosition(Model model, @PathVariable Long id, PositionDTO positionDTO) {
        Position position = positionService.findById(id);
        position.setName(positionDTO.getName());
        try {
            positionService.save(position);
            return "redirect:/positions";
        } catch(Exception e) {
            isMessage = -1;
            name = positionDTO.getName();
            return "redirect:/position/" + id + "/edit";
        }
    }

    @PostMapping("position/{id}/delete")
    public String postDeletePosition(@PathVariable Long id) {
        try {
            positionService.delete(id);
        } catch(Exception e) {
            isMessage = -1;
            namePosition = positionService.findById(id).getName();
        }
        return "redirect:/positions";
    }
}
