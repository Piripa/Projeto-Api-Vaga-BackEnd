package services;

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

import domain.transaction.Transaction;
import domain.user.User;
import dtos.TransactionDTO;
import repositories.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private RestTemplate restTemplate; //Spring disponbiliza para realizar comunicações HTTP
	
	public void criarTransaction(TransactionDTO transaction) throws Exception {
		
		User rementente = this.userService.findUserById(transaction.rementeteId());
		
		User destinatario = this.userService.findUserById(transaction.destinatarioId());
		
		userService.validateTransaction(rementente,transaction.valor());
		
		boolean estaAutorizado = this.authorizeTransaction(rementente, transaction.valor());
		
		if(!estaAutorizado) {
			throw new Exception("Transacao não autorizada");
		}
		
		Transaction newTransaction = new Transaction();
		newTransaction.setAmount(transaction.valor());
		newTransaction.setRemetente(rementente);
		newTransaction.setDestinatario(destinatario);
		newTransaction.setTime(LocalDateTime.now());
		
		rementente.setValor(rementente.getValor().subtract(transaction.valor()));
		destinatario.setValor(destinatario.getValor().add(transaction.valor()));
		
		this.transactionRepository.save(newTransaction);
		this.userService.saveUser(rementente);
		this.userService.saveUser(destinatario);

		
		
		
	}
	
	public boolean authorizeTransaction(User rementente, BigDecimal value) {
	     ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
	     
	     if(authorizationResponse.getStatusCode() == HttpStatus.OK ) {
	    	 String message = authorizationResponse.getBody().get("message");
	    	 return "Autorizado".equalsIgnoreCase(message);
	     }
	     else return false;
	}

}
