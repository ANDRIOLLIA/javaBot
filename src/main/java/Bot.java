import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

import static org.telegram.telegrambots.facilities.TelegramHttpClientBuilder.build;

public class Bot extends TelegramLongPollingBot {

    //Кнопка для запуска бота
    private InlineKeyboardButton buttonForStartTgBot = InlineKeyboardButton.builder()
            .text("Нажмите для запуска бота")
            .callbackData("start_bot")
            .build();

    //Клавиатура для кнопки для запуска теста
    private InlineKeyboardMarkup keyboardForStartTgBot = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(buttonForStartTgBot))
            .build();

    @Override
    public String getBotUsername() {
        return "@MatosyanTGBot";
    }

    @Override
    public String getBotToken() {
        return "8004012680:AAEfvyYY8R44wFfIGunrWkTFaowWxH5-zbE";
    }

    @Override
    public void onUpdateReceived(Update update) {
        forWorkWithText(update);
        forWorkWithButtons(update);
    }

    public void forWorkWithText(Update update){
        if(update.hasMessage()){
            String textMessage = update.getMessage().getText();
            String idUser = update.getMessage().getFrom().getId().toString();

            SendMessage sendMessage = SendMessage.builder()
                    .chatId(idUser)
                    .text("")
                    .build();

            if(textMessage.equals("/start")){
                sendMessage.setText("Вас прветствует тг-бот для поиска и формирования ИТ-команды");
                sendMessage.setReplyMarkup(keyboardForStartTgBot);
            }



            try{
                execute(sendMessage);
            }catch (Exception ex){
                ex.getMessage();
            }
        }
    }

    public void forWorkWithButtons(Update update){
        if(update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();

            EditMessageText editMessageText = EditMessageText.builder()
                    .chatId(chatId.toString())
                    .messageId(messageId)
                    .text("")
                    .build();

            if(callBackData.equals("start_bot")){
                editMessageText.setText("Напишите текст в следующем формате: "+
                        "\"Меня зовут \'Андрей\'.\n" +
                        "Я живу в городе \'Сочи\'.\n" +
                        "Моим основным ЯП является \'Java\'" +
                        "\"");
            }
            try{
                execute(editMessageText);
            }catch (Exception ex){
                ex.getMessage();
            }

        }

    }
}
