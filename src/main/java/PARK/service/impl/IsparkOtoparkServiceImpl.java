//package PARK.service.impl;
//
//import PARK.dto.IsparkOtoparkDto;
//import PARK.service.IsparkOtoparkService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class IsparkOtoparkServiceImpl implements IsparkOtoparkService {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    private static final String ISPARK_API_URL = "https://api.ispark.com.tr/otoparks"; // Replace with actual API URL
//
//    @Override
//    public List<IsparkOtoparkDto> fetchOtoparks() {
//        IsparkOtoparkDto[] otoparks = restTemplate.getForObject(ISPARK_API_URL, IsparkOtoparkDto[].class);
//        return Arrays.asList(otoparks);
//    }
//}
