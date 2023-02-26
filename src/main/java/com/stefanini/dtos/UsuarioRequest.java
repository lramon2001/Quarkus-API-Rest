package com.stefanini.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Convert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stefanini.utils.PasswordUtils;
import com.stefanini.utils.ValidDataNascimento;
public class UsuarioRequest {
   
    private Long id;

    
    @NotBlank(message = "O nome do usuário não pode estar em branco.")
    private String nome;

    
    @NotBlank
    @Size(min = 5, max = 20,message = "O login deve possuir entre 5 e 20 caracteres de tamanho.")
    private String login;

    
    @NotBlank
    @Size(min = 10,message = "O email deve possuir no mínimo 10 caraceteres.")
    @Email(message = "O email deve ser válido")
    private String email;

    
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$", message = "Senha inválida")
    @Convert(converter = PasswordUtils.class)
    private String senha;

    
    @ValidDataNascimento
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime dataCriacao;

    
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime dataAtualizacao;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getSenha() {
        return senha;
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }


    public LocalDate getDataNascimento() {
        return dataNascimento;
    }


    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }


    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }


    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }


    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }


    
}
