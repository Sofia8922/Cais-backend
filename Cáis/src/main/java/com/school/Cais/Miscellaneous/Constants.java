package com.school.Cais.Miscellaneous;

import java.util.List;

public class Constants {
    public static String Password = "thoushaltneverguessmwahahaha";
    public static String UserEmail = "cheeseshop666@proton.me";
    public enum Role { ADMIN, CUSTOMER }
    public enum DeliveryStatus { PROCESSING, UNDERWAY, DELIVERED, CANCELLED }

    //-----------------------------------
    public static String CáisEmail = "noreply@demomailtrap.co";
    public static String CáisEmailName = "Cáis";
    public record PurchasedItem(String name, int quantity, String imageLink) {}
    public record Email(String title, String content) {
        public Email {
            content = "<html><body>" + content + "</body></html>";
        }
    }

    public static Email PurchaseMail(String username, List<PurchasedItem> items) {
        StringBuilder itemString = new StringBuilder();
        for(PurchasedItem item : items)
            itemString.append("<b style=\"display: flex; align-items: center;\">" +
                    "<img src=\"" + item.imageLink + "\" width=\"25\" height=\"25\" style=\"border-radius: 50%; margin-right: 5px;\">" +
                    " " + item.quantity +
                    " × " + item.name +
                    "</b><br>");

        return new Email(
            "Thank you for your purchase!",
            "<h3>Dear " + username + ",</h3>" +
                    "Thank you for your purchase at Cáis!<br><br>" +
                    "You bought:<br>" +
                    itemString +
                    "<br><h3>We hope to see you again soon!</h3>"
    ); }
}
