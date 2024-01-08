package com.Notifications.controller;
import com.Notifications.dto.TipNotifikacijeCreateDto;
import com.Notifications.dto.TipNotifikacijeDto;
import com.Notifications.security.CheckSecurity;
import com.Notifications.service.TipNotifikacijeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/notification-types")
public class NotificationTypeController {
    private final TipNotifikacijeService tipNotifikacijeService;

    public NotificationTypeController(TipNotifikacijeService tipNotifikacijeService) {
        this.tipNotifikacijeService = tipNotifikacijeService;
    }

    @GetMapping("/getAllNotiTypes")
    //@CheckSecurity(roles={"ROLE_ADMIN"})
    public ResponseEntity<Page<TipNotifikacijeDto>> getAllNotificationTypes(Pageable pageable) {
        return new ResponseEntity<>(tipNotifikacijeService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles={"ROLE_ADMIN"})
    public ResponseEntity<TipNotifikacijeDto> addNotificationType(@RequestBody TipNotifikacijeCreateDto tipNotifikacijeCreateDto) {
        return new ResponseEntity<>(tipNotifikacijeService.add(tipNotifikacijeCreateDto), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    @CheckSecurity(roles={"ROLE_ADMIN"})
    public ResponseEntity<TipNotifikacijeDto> updateNotificationType(@RequestBody TipNotifikacijeDto tipNotifikacijeDto) {
        return new ResponseEntity<>(tipNotifikacijeService.update(tipNotifikacijeDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
//@CheckSecurity(roles={"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteNotificationType(@PathVariable Long id) {
        tipNotifikacijeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
