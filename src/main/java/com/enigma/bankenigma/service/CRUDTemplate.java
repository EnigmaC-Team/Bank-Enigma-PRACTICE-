package com.enigma.bankenigma.service;

public interface CRUDTemplate<T> {

    T create(T t);
    T checkAccount(String id);
    String deleteAccount(String id);
}
