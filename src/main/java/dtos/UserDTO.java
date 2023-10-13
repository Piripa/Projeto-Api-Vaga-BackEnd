package dtos;

import java.math.BigDecimal;

import domain.user.TipoUsuario;

public record UserDTO(String primeiroNome, String segundoNome, String cpf, BigDecimal valor, String email, String password, TipoUsuario tipo) {

}
