package br.com.jgb.jogoGourmet;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um tipo de prato
 * 
 * @author Jandrei
 *
 */
public class TipoPrato {

	private String nome;
	private List<Prato> pratos = new ArrayList<Prato>();
	private TipoPrato tipoPrato;

	public TipoPrato(String nome) {
		super();
		this.nome = nome;
	}

	public TipoPrato(String nome, Prato prato) {
		super();
		this.nome = nome;
		this.pratos.add(prato);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Prato> getPratos() {
		return pratos;
	}

	public void setPratos(List<Prato> pratos) {
		this.pratos = pratos;
	}

	public TipoPrato getTipoPrato() {
		return tipoPrato;
	}

	public void setTipoPrato(TipoPrato tipoPrato) {
		this.tipoPrato = tipoPrato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((pratos == null) ? 0 : pratos.hashCode());
		result = prime * result + ((tipoPrato == null) ? 0 : tipoPrato.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoPrato other = (TipoPrato) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pratos == null) {
			if (other.pratos != null)
				return false;
		} else if (!pratos.equals(other.pratos))
			return false;
		if (tipoPrato == null) {
			if (other.tipoPrato != null)
				return false;
		} else if (!tipoPrato.equals(other.tipoPrato))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoPrato [nome=" + nome + ", pratos=" + pratos + ", tipoPrato=" + tipoPrato + "]";
	}
}