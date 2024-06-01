package com.page.page.service.impl;

import com.page.page.service.TelegramNotificationService;
import com.page.page.util.DataUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TelegramNotificationServiceImpl implements TelegramNotificationService {

    @Override
    public void sendTelegramNotificationToAdmin(DataUtil param) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String telegramApiUrl = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
            String url = String.format(telegramApiUrl, param.getString("botToken"), param.getString("groupID"), param.getString("message"));
            restTemplate.getForObject(url, String.class);
        } catch ( Exception e ) {
            // DO NOTHING
        }
    }
}
