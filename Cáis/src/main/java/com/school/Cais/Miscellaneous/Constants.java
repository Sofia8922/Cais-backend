package com.school.Cais.Miscellaneous;

import com.school.Cais.Models.CartItem;

import java.util.List;

public class Constants {
    public static String Password = "thoushaltneverguessmwahahaha";
    public enum Role { ADMIN, CUSTOMER }
    public enum DeliveryStatus { PROCESSING, UNDERWAY, DELIVERED, CANCELLED }

    public static String Email = "cheeseshop666@proton.me";
    public record Email(String title, String content) {};
    public record PurchasedItem(String name, int quantity) {};
    public static Email PurchaseMail(String username, List<PurchasedItem> items) {
        StringBuilder itemString = new StringBuilder();
        for(PurchasedItem item : items)
            itemString.append(item.quantity + " × " + item.name + "\n");

        return new Email(
            "Thank you for your purchase!",
            "Dear " + username + ",\n \n" +
                    "Thank you for your purchase at Cáis!\n\n" +
                    "You bought:\n" +
                    itemString +
                    "\nWe hope to see you again soon!"
    ); }
}
