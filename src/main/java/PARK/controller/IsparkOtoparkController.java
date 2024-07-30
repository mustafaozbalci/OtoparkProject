//package PARK.controller;
//
//import PARK.dto.IsparkOtoparkDto;
//import PARK.service.IsparkOtoparkService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/ispark")
//public class IsparkOtoparkController {
//
//    @Autowired
//    private IsparkOtoparkService isparkOtoparkService;
//
//    @GetMapping("/otoparks")
//    public ResponseEntity<List<IsparkOtoparkDto>> fetchOtoparks() {
//        List<IsparkOtoparkDto> otoparks = isparkOtoparkService.fetchOtoparks();
//        return ResponseEntity.ok(otoparks);
//    }
//}
