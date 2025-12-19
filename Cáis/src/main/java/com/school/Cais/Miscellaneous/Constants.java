package com.school.Cais.Miscellaneous;

import java.util.List;

public class Constants {
    public static String Password = "thoushaltneverguessmwahahaha";
    public enum Role { ADMIN, CUSTOMER }
    public enum DeliveryStatus { PROCESSING, UNDERWAY, DELIVERED, CANCELLED }

    public static String Email = "cheeseshop666@proton.me";
    public record Email(String title, String content) {
        public Email {
            content = "<html><body>" + content + "</body></html>";
        }
    }
    public record PurchasedItem(String name, int quantity) {}
    public static Email PurchaseMail(String username, List<PurchasedItem> items) {
        StringBuilder itemString = new StringBuilder();
        for(PurchasedItem item : items)
            itemString.append("<b>" + item.quantity + " × " + item.name + "</b><br>");

        return new Email(
            "Thank you for your purchase!",
            "<h1>Dear " + username + ",</h1>" +
                    "Thank you for your purchase at Cáis!<br><br>" +
                    "You bought:<br>" +
                    itemString +
                    "<br>We hope to see you again soon!"
    ); }
}
