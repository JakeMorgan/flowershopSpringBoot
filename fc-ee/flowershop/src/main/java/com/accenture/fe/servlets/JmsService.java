package com.accenture.fe.servlets;

import com.accenture.XMLConverter;
import com.accenture.be.access.UserAccessService;
import com.accenture.be.business.UserBusinessServiceImpl;
import com.accenture.be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.*;
import java.io.IOException;

@Service
public class JmsService {
    private static final Logger LOG = LoggerFactory.getLogger(JmsService.class);
    @Autowired
    private UserAccessService userAccessService;
    @Autowired
    private UserBusinessServiceImpl userBusinessService;
    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private Queue receiveUserDiscountXmlQueue;
    @Autowired
    private Queue sendNewUserXmlQueue;
    @Autowired
    private XMLConverter xmlConverter;
    Session session;

    @PostConstruct
    public void init(){
        try{
            Connection connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(receiveUserDiscountXmlQueue);
            connection.start();
            consumer.setMessageListener(
                    message -> {
                        try{
                            String text =((TextMessage) message).getText();
                            User u = (User) xmlConverter.convertFromXMLStringToObject(text);
                            User user = userAccessService.get(u.getUserName()).get();
                            user.setDiscount(u.getDiscount());
                            userAccessService.update(user);
                        }catch(IOException | JMSException e){
                            LOG.error("Error receiving", e);
                        }
                    }
            );
        }catch(JMSException e){
            LOG.error("JMS error", e);
        }
    }

    public void newUser(User user){
        TextMessage message;
        try{
            message = session.createTextMessage(xmlConverter.convertFromObjectToXMLString(user));
            MessageProducer producer = session.createProducer(sendNewUserXmlQueue);
            producer.send(message);
            producer.close();
        } catch (JMSException | IOException e) {
            LOG.error("Error send message JMS", e);
        }
    }
}
