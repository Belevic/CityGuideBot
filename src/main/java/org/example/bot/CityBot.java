package org.example.bot;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.impl.City;
import org.example.repository.CityRepository;
import org.example.service.impl.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@PropertySource("classpath:application.properties")
public class CityBot extends TelegramLongPollingBot {

    @Autowired
    private CityService service;

    private static final int RECONNECT_PAUSE =10000;
    private static final String STARTUP_TEXT = "/start";

    @Value("${telegram.username}")
    private String userName;

    @Value("${telegram.token}")
    private String token;

    private static final String regexExpression = "[А-Я][а-я]{2,}";

    private static final String defaultMessage = "Привет пользователь. Я туристический бот по городам. " +
            "Введи название города, который ты бы хотел посетить и я проинформирую тебя о его " +
            "достопримечательностях. Учти, что оно должно начинаться с заглавной буквы " +
            "и содержать только буквы русского алфавита!";

    private static final String notMatch = "К сожалению введенное тобой название города не удолетворяет " +
            "корректному названию. Попробуй еще раз и вводи город с помощью букв русского языка и " +
            "с начальной заглавной буквой!";

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();
        if (inputText.startsWith(STARTUP_TEXT)) {
            sendMessage(chatId,defaultMessage);
        }
        else{
            if(match(inputText)){
                sendMessage(chatId,service.getResponseByCityName(inputText));
            }
            else{
                sendMessage(chatId,notMatch);
            }
        }
    }

    private boolean match(String cityName){
        Pattern pattern = Pattern.compile(regexExpression);
        Matcher matcher = pattern.matcher(cityName);
        return matcher.matches();
    }

    private void sendMessage(Long chatId,String sendMessage){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(sendMessage);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @PostConstruct
    public void botConnect() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            try {
                Thread.sleep(RECONNECT_PAUSE);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                return;
            }
            botConnect();
        }
    }
}
