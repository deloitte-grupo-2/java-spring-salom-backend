package com.salomdoces.security;

import com.salomdoces.model.Cliente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// Classe implementará UserDetails
public class ClienteDetailsImpl implements UserDetails {

    // Implementando a constante serial (tornando o objeto único)
    private static final long serialVersionUID = 1L;

    // Atributos da classe
    private String username;
    private String password;

    // Construtor padrão
    public ClienteDetailsImpl() { }

    // Construtor de inicialização
    public ClienteDetailsImpl(Cliente user) {
        this.username = user.getEmail();
        this.password = user.getSenha();
    }

    // Getters e Setters dos atributos
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Métodos de UserDetails implementados pela IDE
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
