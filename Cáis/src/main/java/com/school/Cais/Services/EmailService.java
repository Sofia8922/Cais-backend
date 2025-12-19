package com.school.Cais.Services;

import com.school.Cais.Miscellaneous.Constants;
import com.school.Cais.Miscellaneous.DummyData;
import com.school.Cais.Models.Account;
import com.school.Cais.Models.CartItem;
import org.springframework.stereotype.Service;

import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.MailtrapMail;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {
    public void sendPurchaseEmail(Account account) {
        if(DummyData.isStarting()) return;

        List<Constants.PurchasedItem> purchasedItems = new ArrayList<>();
        List<CartItem> cartItems = account.getCartItems();
        for(CartItem cartItem : cartItems) {
            purchasedItems.add(
                new Constants.PurchasedItem(
                    cartItem.getProduct().getName(),
                    cartItem.getQuantity()
                )
            );
        }

        sendEmail(
            account.getEmail(),
            Constants.PurchaseMail(account.getUsername(), purchasedItems));
    }

    private void sendEmail(String recepient, Constants.Email email) {
        final String TOKEN = "fb3ea2499818b3db2142fec5b2cb1d34";


        final MailtrapConfig config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);

        final MailtrapMail generatedMail = MailtrapMail.builder()
                .from(new Address("noreply@demomailtrap.co", "Cáis"))
                .to(List.of(new Address(recepient)))
                .subject(email.title())
                .html(email.content())
                .category("Integration Test")
                .build();

        try {
            System.out.println(client.send(generatedMail));
        } catch (Exception e) {
            System.out.println("Caught exception : " + e);
        }
    }
}


