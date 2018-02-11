package harker.gutierrez.andres.senderrabbitmqspring;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MyRestController {

    @Value("${ip.cola.mensajes}")
    String ipColaMensajes;
    
    @Value("${nombre.cola.mensajes}")
    String nombreColaMensajes;
    
    @RequestMapping("/")
    public String index() {
        return "Hola Springboot AHG";
    }

    @RequestMapping("/sendMessage")
    public String sendMessage(@RequestParam("message") String message) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ipColaMensajes);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(nombreColaMensajes, false, false, false, null);
        channel.basicPublish("", nombreColaMensajes, null, message.getBytes());
        System.out.println(" Enviado '" + message + "'");
        channel.close();
        connection.close();

        return "[AHG] Se coloca un mensaje en la cola de RabbitMQ y el valor es: " + message;
    }

}
