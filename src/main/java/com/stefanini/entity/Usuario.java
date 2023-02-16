package com.stefanini.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.stefanini.utils.PasswordUtils;
import com.stefanini.utils.ValidDataNascimento;

@Entity
@Table(name ="usuario")
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "usuario_nome", nullable = false, length = 50)
    @NotBlank(message = "O nome do usuário não pode estar em branco.")
    private String nome;

    @Column(name ="usuario_login", nullable = false,length = 20,unique = true)
    @NotBlank
    @Size(min = 5, max = 20,message = "O login deve possuir entre 5 e 20 caracteres de tamanho.")
    private String login;

    @Column(name = "usuario_email",nullable = false)
    @NotBlank
    @Size(min = 10,message = "O email deve possuir no mínimo 10 caraceteres.")
    @Email(message = "O email deve ser válido")
    private String email;

    @Column(name = "usuario_senha", nullable = false)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$", message = "Senha inválida")
    @Convert(converter = PasswordUtils.class)
    private String senha;

    @Column(name ="usuario_data_nascimento",nullable = false)
    @ValidDataNascimento
    private LocalDate dataNascimento;

    @Column(name = "usuario_data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "usuario_data_atualizacao",updatable = true)
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
