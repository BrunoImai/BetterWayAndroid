package com.example.betterwayfinal.Activity.model;

import java.util.List;

public interface ICordenadasDAO {

    boolean salvar(Recycler recycler);
    boolean atualizar(Recycler recycler);
    boolean deletar(Recycler recycler);
    List<Recycler> listar();

}
