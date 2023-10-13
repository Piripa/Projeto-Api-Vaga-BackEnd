package services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import domain.user.TipoUsuario;
import domain.user.User;
import dtos.UserDTO;
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

	public User criarUser(UserDTO data) {
		User newUser = new User(data);
		this.saveUser(newUser);
		return newUser;
	}

	public List<User> getAllUsers() {
		
		return this.userRepository.findAll();
	}

}
