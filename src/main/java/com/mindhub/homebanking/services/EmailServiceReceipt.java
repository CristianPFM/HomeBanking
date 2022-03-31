package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EmailServiceReceipt {
    @Autowired
    private JavaMailSender javaMailSender;

    public void EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(Client client, String fromAccountNumber, String toAccountNumber, Double amount, String description) throws MessagingException {
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("Comprobante de transferencia - Cliente: " + client.getFirstName() + " " + client.getLastName());

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'a las' HH:mm:ss");
        Date fecha = new Date(System.currentTimeMillis());

        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <title></title>\n" +
                "  <!--[if mso]>\n" +
                "  <style>\n" +
                "    table {border-collapse:collapse;border-spacing:0;border:none;margin:0;}\n" +
                "    div, td {padding:0;}\n" +
                "    div {margin:0 !important;}\n" +
                "  </style>\n" +
                "  <noscript>\n" +
                "    <xml>\n" +
                "      <o:OfficeDocumentSettings>\n" +
                "        <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "      </o:OfficeDocumentSettings>\n" +
                "    </xml>\n" +
                "  </noscript>\n" +
                "  <![endif]-->\n" +
                "  <style>\n" +
                "    table,\n" +
                "    td,\n" +
                "    div,\n" +
                "    h1,\n" +
                "    p {\n" +
                "      font-family: Arial, sans-serif;\n" +
                "    }\n" +
                "\n" +
                "    @media screen and (max-width: 530px) {\n" +
                "      .unsub {\n" +
                "        display: block;\n" +
                "        padding: 8px;\n" +
                "        margin-top: 14px;\n" +
                "        border-radius: 6px;\n" +
                "        background-color: white;\n" +
                "        text-decoration: none !important;\n" +
                "        font-weight: bold;\n" +
                "      }\n" +
                "\n" +
                "      .col-lge {\n" +
                "        max-width: 100% !important;\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    @media screen and (min-width: 531px) {\n" +
                "      .col-sml {\n" +
                "        max-width: 27% !important;\n" +
                "      }\n" +
                "\n" +
                "      .col-lge {\n" +
                "        max-width: 73% !important;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"margin:0;padding:0;word-spacing:normal;background-color:#939297;\">\n" +
                "  <div role=\"article\" aria-roledescription=\"email\" lang=\"en\" style=\"text-size-adjust:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#939297;\">\n" +
                "    <table role=\"presentation\" style=\"width:100%;border:none;border-spacing:0;\">\n" +
                "      <tr>\n" +
                "        <td align=\"center\" style=\"padding:0;\">\n" +
                "          <!--[if mso]>\n" +
                "          <table role=\"presentation\" align=\"center\" style=\"width:600px;\">\n" +
                "          <tr>\n" +
                "          <td>\n" +
                "          <![endif]-->\n" +
                "          <table role=\"presentation\" style=\"width:94%;max-width:600px;border:none;border-spacing:0;text-align:left;font-family:Arial,sans-serif;font-size:16px;line-height:22px;color:#363636;margin-top:50px;margin-bottom:50px;\">\n" +
                "\n" +
                "            <tr>\n" +
                "              <td style=\"padding:30px;background-color:#ffffff;\">\n" +
                "                <div style=\"text-align:center;\">\n" +
                "                  <img src=\"https://raw.githubusercontent.com/CristianPFM/logoecobank/main/logoBank.png\" width=\"165\" alt=\"Logo\" style=\"width:165px;max-width:80%;height:auto;border:none;text-decoration:none;color:#ffffff;text-align:center;\">\n" +
                "                </div>\n" +
                "                <h1 style=\"margin-top:0;margin-bottom:16px;font-size:26px;line-height:32px;font-weight:bold;letter-spacing:-0.02em;text-align:center;\">Comprobante de transferencia</h1>\n" +
                "                <p style=\"margin:0;text-align:center;\">Hola <b style='text-transform:capitalize;'>" + client.getFirstName() + "</b>, recientemente se ha realizado una transferencia desde tu cuenta y se ha generado este comprobante de forma automática.</p><br>\n" +
                "                <p style=\"margin:0;text-align:center;\">Para mas información revisa la parte inferior de este comprobante.</p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"background-color:white;padding-left:50px;padding-top:30px;padding-right:50px;\">\n" +
                "                <div style=\"background-color:#E4FFE6;padding:20px;border-radius:10px;\">\n" +
                "                  <p style=\"color:black;\"><b>Cuenta de origen: </b>" + fromAccountNumber + "</p>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"background-color:white;padding-left:50px;padding-top:30px;padding-right:50px;\">\n" +
                "                <div style=\"background-color:#E4FFE6;padding:20px;border-radius:10px;\">\n" +
                "                  <p style=\"color:black;\"><b>Cuenta de destino: </b>" + toAccountNumber + "</p>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"background-color:white;padding-left:50px;padding-top:30px;padding-right:50px;\">\n" +
                "                <div style=\"background-color:#E4FFE6;padding:20px;border-radius:10px;\">\n" +
                "                  <p style=\"color:black;\"><b>Monto: </b>$" + String.format("%.0f", amount) + "</p>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"background-color:white;padding-left:50px;padding-top:30px;padding-right:50px;\">\n" +
                "                <div style=\"background-color:#E4FFE6;padding:20px;border-radius:10px;\">\n" +
                "                  <p style=\"color:black;\"><b>Descripción: </b>" + description + "</p>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"background-color:white;padding-left:50px;padding-top:30px;padding-right:50px;padding-bottom:30px;\">\n" +
                "                <div style=\"background-color:#E4FFE6;padding:20px;border-radius:10px;\">\n" +
                "                  <p style=\"color:black;\"><b>Fecha: </b>" + formatter.format(fecha) + "</p>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr style=\"text-align:center;\">\n" +
                "              <td style=\"padding:35px 30px 11px 30px;font-size:0;background-color:#ffffff;border-bottom:1px solid #f0f0f5;border-color:rgba(201,201,207,.35);text-align:center;\">\n" +
                "                <!--[if mso]>\n" +
                "                <table role=\"presentation\" width=\"100%\">\n" +
                "                <tr>\n" +
                "                <td style=\"width:145px;\" align=\"left\" valign=\"top\">\n" +
                "                <![endif]-->\n" +
                "                <div class=\"col-sml\" style=\"display:inline-block;width:100%;max-width:145px;vertical-align:top;text-align:left;font-family:Arial,sans-serif;font-size:14px;color:#363636;\">\n" +
                "                  <img src=\"https://www.clipartmax.com/png/full/301-3011315_icon-check-green-tick-transparent-background.png\" width=\"115\" alt=\"\" style=\"width:115px;max-width:80%;margin-bottom:20px;\">\n" +
                "                </div>\n" +
                "                <!--[if mso]>\n" +
                "                </td>\n" +
                "                <td style=\"width:395px;padding-bottom:20px;\" valign=\"top\">\n" +
                "                <![endif]-->\n" +
                "                <!--[if mso]>\n" +
                "                </td>\n" +
                "                </tr>\n" +
                "                </table>\n" +
                "                <![endif]-->\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"padding:30px;text-align:center;font-size:12px;background-color:#306A48;color:#cccccc;\">\n" +
                "                <p style=\"margin:0 0 8px 0;\"><a href=\"http://www.facebook.com/\" style=\"text-decoration:none;\"><img src=\"https://assets.codepen.io/210284/facebook_1.png\" width=\"40\" height=\"40\" alt=\"f\" style=\"display:inline-block;color:#cccccc;\"></a> <a href=\"http://www.twitter.com/\" style=\"text-decoration:none;\"><img src=\"https://assets.codepen.io/210284/twitter_1.png\" width=\"40\" height=\"40\" alt=\"t\" style=\"display:inline-block;color:#cccccc;\"></a></p>\n" +
                "                <p style=\"margin:0;font-size:14px;line-height:20px;\">&reg; Ecobank 2021<br><a class=\"unsub\" href=\"http://www.example.com/\" style=\"color:#cccccc;text-decoration:underline;\">Sitio de Ecobank</a></p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "          <!--[if mso]>\n" +
                "          </td>\n" +
                "          </tr>\n" +
                "          </table>\n" +
                "          <![endif]-->\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        helper.setText(html, true);
        helper.setTo(client.getEmail());
        javaMailSender.send(mimeMessage);
    }
}
