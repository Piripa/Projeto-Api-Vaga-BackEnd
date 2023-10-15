package com.demo.dtos;

import java.math.BigDecimal;

import com.demo.domain.user.TipoUsuario;

public record UserDTO(String primeiroNome, String segundoNome, String cpf, BigDecimal valor, String email, String password, TipoUsuario tipo) {

}
