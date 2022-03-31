package com.mindhub.homebanking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailServiceContact {
    @Autowired
    private JavaMailSender javaMailSender;

    public void EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String fullName, String email, String subject, String text) throws MessagingException {
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("Solicitud de Contacto - " + fullName);

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
                "                <h1 style=\"margin-top:0;margin-bottom:16px;font-size:26px;line-height:32px;font-weight:bold;letter-spacing:-0.02em;text-align:center;\">Cristian necesita que lo contacten</h1>\n" +
                "                <p style=\"margin:0;text-align:center;\">El usuario <b>Cristian</b> <b>Ramirez</b> necesita que lo contacten y ha proporcionado la siguiente información en el formulario de contacto.</p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"padding:0;font-size:24px;line-height:28px;font-weight:bold;\">\n" +
                "                <a href=\"http://www.example.com/\" style=\"text-decoration:none;\"><img src=\"https://assets.codepen.io/210284/1200x800-2.png\" width=\"600\" alt=\"\" style=\"width:100%;height:auto;display:block;border:none;text-decoration:none;color:#363636;\"></a>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "              <td style=\"background-color:white;padding-left:50px;padding-top:30px;padding-right:50px;\">\n" +
                "                <div style=\"background-color:#E4FFE6;padding:20px;border-radius:10px;\">\n" +
                "                  <p style=\"color:black;\"><b>Nombre: </b>" + fullName + "</p>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"background-color:white;padding-left:50px;padding-top:30px;padding-right:50px;\">\n" +
                "                <div style=\"background-color:#E4FFE6;padding:20px;border-radius:10px;\">\n" +
                "                  <p style=\"color:black;\"><b>Correo: </b>" + email + "</p>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"background-color:white;padding-left:50px;padding-top:30px;padding-right:50px;\">\n" +
                "                <div style=\"background-color:#E4FFE6;padding:20px;border-radius:10px;\">\n" +
                "                  <p style=\"color:black;\"><b>Asunto: </b>" + subject + "</p>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"background-color:white;padding-left:50px;padding-top:30px;padding-right:50px;padding-bottom:30px;\">\n" +
                "                <div style=\"background-color:#E4FFE6;padding:20px;border-radius:10px;\">\n" +
                "                  <p style=\"color:black;\"><b>Consulta: </b>" + text + "</p>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "\n" +
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
        helper.setTo("cfuentes.ghd@gmail.com");
        javaMailSender.send(mimeMessage);
    }
}
