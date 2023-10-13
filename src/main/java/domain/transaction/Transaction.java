package domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal amount;
	
	
	@ManyToOne //Um usuario pode fazer varias transações, mas uma transação só envolve um usuario;
	@JoinColumn(name = "rementente_id")
	private User remetente;
	
	@ManyToOne
	@JoinColumn(name = "destinatario_id")
	private User destinatario;
	
	private LocalDateTime time;
	
	
	
}

