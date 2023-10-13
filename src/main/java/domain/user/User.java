package domain.user;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id") //chave primária
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String primeiroNome;
	
	private String segundoNome;
	
	@Column(unique = true) //não deve ter diferentes;
	private String cpf;
	
	@Column(unique = true) 
	private String email;
	
	private String password;
	
	private BigDecimal valor;
	
	
	@Enumerated(EnumType.STRING) // os valores representam algum valor daquela string
	private TipoUsuario tipo;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}


	public void setSegundoNome(String segundoNome) {
		this.segundoNome = segundoNome;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}


	public String getPrimeiroNome() {
		return primeiroNome;
	}


	public String getSegundoNome() {
		return segundoNome;
	}


	public String getCpf() {
		return cpf;
	}


	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return password;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public TipoUsuario getTipo() {
		return tipo;
	}
}
