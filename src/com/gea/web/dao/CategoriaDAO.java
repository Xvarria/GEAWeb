package com.gea.web.dao;

import java.util.Collection;

import com.gea.web.model.Categoria;

public interface CategoriaDAO {
	
	public void save(Categoria categoria);
	
	public void update(Categoria categoria);
	
	public void delete(Categoria categoria);
	
	public Categoria getCategoriaById(int categoriaId);
	
	public Collection<Categoria> listCategorias();
}
