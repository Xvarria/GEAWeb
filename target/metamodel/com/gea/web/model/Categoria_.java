package com.gea.web.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Categoria.class)
public abstract class Categoria_ {

	public static volatile SingularAttribute<Categoria, String> descripcion;
	public static volatile CollectionAttribute<Categoria, Subcategoria> subcategorias;
	public static volatile SingularAttribute<Categoria, String> codigo;
	public static volatile SingularAttribute<Categoria, Integer> categoriaId;

}

