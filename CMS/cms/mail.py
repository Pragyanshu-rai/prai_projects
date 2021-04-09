import smtplib

from email.message import EmailMessage

import random

def send_email(subject, body, to):
    # create EmailMessage object
    message = EmailMessage()
    #set the body of mail
    message.set_content(body)
    message['subject'] = subject
    message['to'] = to
    
    sender = "noreply.services.2001@gmail.com"
    password = "cxzifunjhbptvsml"
    
    message['from'] = sender
        
    server = smtplib.SMTP("smtp.gmail.com", 587)
    server.starttls()
    server.login(sender, password)
    # send mail
    server.send_message(message)
    
    server.quit()


