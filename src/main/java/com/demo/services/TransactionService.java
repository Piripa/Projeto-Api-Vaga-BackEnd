package com.demo.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.demo.domain.transaction.Transaction;
import com.demo.domain.user.User;
import com.demo.dtos.TransactionDTO;
import com.demo.repositories.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private RestTemplate restTemplate; //Spring disponbiliza para realizar comunicações HTTP
	
	@Autowired
	private NotificationService notificationService;
	
	public Transaction criarTransaction(TransactionDTO transacao) throws Exception {
		
		User remetente = this.userService.findUserById(transacao.remetenteId());
		
		User destinatario = this.userService.findUserById(transacao.destinatarioId());
		
		userService.validateTransaction(remetente,transacao.valor());
		
		boolean estaAutorizado = this.authorizeTransaction(remetente, transacao.valor());
		
		if(!estaAutorizado) {
			throw new Exception("Transacao não autorizada");
		}
		
		Transaction newTransaction = new Transaction();
		newTransaction.setValor(transacao.valor());
		newTransaction.setRemetente(remetente);
		newTransaction.setDestinatario(destinatario);
		newTransaction.setTime(LocalDateTime.now());
		
		remetente.setValor(remetente.getValor().subtract(transacao.valor()));
		destinatario.setValor(destinatario.getValor().add(transacao.valor()));
		
		this.transactionRepository.save(newTransaction);
		this.userService.saveUser(remetente);
		this.userService.saveUser(destinatario);

		this.notificationService.enviarNotificacao(remetente, "Transação enviada com sucesso!!");
		
		this.notificationService.enviarNotificacao(destinatario, "Transação recebida com sucesso!!");

		
		return newTransaction;
		
		
	}
	
	public boolean authorizeTransaction(User rementente, BigDecimal value) {
	     //ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
	     
	     //if(authorizationResponse.getStatusCode() == HttpStatus.OK ) {
	    	 //String message = authorizationResponse.getBody().get("message"); *Tive que comentar e fazer essa gambiarra, pois o .get não estava funcionando e não conseguir resolver ao momento.
	    	 //return "Autorizado".equalsIgnoreCase(message);
	    	 return true;
	    // }
	    // else return false;
	}

}
