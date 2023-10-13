package services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.user.TipoUsuario;
import domain.user.User;
import repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void validateTransaction(User remetente, BigDecimal valor) throws Exception {
		if(remetente.getTipo() != TipoUsuario.COMUNS ) {
			throw new Exception("Usuario do tipo Logista não está autorizado a realizara transação");
		}
		
		if(remetente.getValor().compareTo(valor) < 0) {
			throw new Exception("Saldo insuficiente");
		}

	}
	
	public User findUserById(Long id) throws Exception {
		return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuario não encontrado"));

	}
	
	public void saveUser(User user) {
		this.userRepository.save(user);
	}

}
