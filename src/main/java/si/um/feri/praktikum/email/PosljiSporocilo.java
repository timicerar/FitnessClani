package si.um.feri.praktikum.email;

import si.um.feri.praktikum.vao.Oseba;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PosljiSporocilo {
    public static void posljiPodatkeOsebe(Oseba oseba, int kateraMetoda) throws NamingException, JMSException {
        InitialContext ctx = new InitialContext();
        Queue queue = (Queue) ctx.lookup("jms/queue/test");
        QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("java:/ConnectionFactory");
        QueueConnection cnn = factory.createQueueConnection("guest", "guest");
        QueueSession session = cnn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
        QueueSender sender = session.createSender(queue);

        MapMessage m = session.createMapMessage();
        m.setString("ime", oseba.getIme());
        m.setString("priimek", oseba.getPriimek());
        m.setString("email", oseba.getEmail());
        m.setInt("kateraMetoda", kateraMetoda);

        sender.send(m, DeliveryMode.NON_PERSISTENT, 3, 2000);

        session.close();
    }
}
