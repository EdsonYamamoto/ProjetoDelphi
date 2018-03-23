package modelo;

public class IfModelo {
	private int numLinha;
	private String condicao;
	public int getNumLinha() {
		return numLinha;
	}
	public void setNumLinha(int numLinha) {
		this.numLinha = numLinha;
	}
	public String getCondicao() {
		return condicao;
	}
	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	@Override
	public String toString()
	{
		return this.numLinha+"\t"+ this.condicao;
	}
}
