package co.edu.unipiloto.laboratorio_javamail;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail extends AsyncTask<String, Void, Void> {

    private Session sesion;
    private String EMAIL_ORIGEN;
    private String CONTRASENA_ORIGEN;

    public JavaMail(Context context) {
        this.EMAIL_ORIGEN = context.getString(R.string.correo);
        this.CONTRASENA_ORIGEN = context.getString(R.string.contrasena);
    }

    /*Metodo encargado de crear un hilo y enviar el correo electronico al destinatario
     * strings[0] = emailDestino
     * strings[1] = asunto
     * strings[2] = mensaje
     * Dominio de la fuente del correo:
     *   // Gmail -> smtp.gmail.com, hotmail ->  smtp.live.com, outlook -> smtp.Office365.com
     * Puerto por el cual se envian los correos
     *   // Gmail -> 465,  Hotmail -> 25, 465 (con autentificación) outlook -> 587
     * */
    @Override
    protected Void doInBackground(String... strings) {
        String emailDestino = strings[0];
        String asunto = strings[1];
        String mensaje = strings[2];
        EMAIL_ORIGEN = strings[3];
        CONTRASENA_ORIGEN = strings[4];

        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.port", "465");

        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");

        sesion = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_ORIGEN, CONTRASENA_ORIGEN);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(sesion);
        try {
            mimeMessage.setFrom(new InternetAddress(EMAIL_ORIGEN));
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(emailDestino)));
            mimeMessage.setSubject(asunto);
            mimeMessage.setText(mensaje);
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("Error en el envio");
        }
        return null;
    }


}