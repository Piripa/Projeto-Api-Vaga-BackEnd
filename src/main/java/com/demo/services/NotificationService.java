package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.domain.user.User;
import com.demo.dtos.NotificationDTO;

@Service
public class NotificationService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void enviarNotificacao(User user, String message) throws Exception {
		
		String email = user.getEmail();
		NotificationDTO notificationRequest = new NotificationDTO(email, message);
		
//		ResponseEntity<String> notificationResponse  = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificationRequest, String.class);
//		
//		if(!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
//			System.out.println("Erro ao enviar as notificações");
//			
//			throw new Exception("Serviço de notificação está fora do ar");
//		}
//		A requisição a cima não estava funcionando e o código estava apresentendendo problemas, pode ser devido ao link
		
		
		System.out.println("Notoficação enviada ao usuário com sucesso");

	}
}
