package br.com.ucsal.olimpiadas.repository;

import java.util.List;

public interface Repositorio<T> {
	T salvar(T entidade);
	List<T> listarTodos();
	boolean existeId(long id);
}
