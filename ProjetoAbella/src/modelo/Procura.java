package modelo;

public class Procura {
	private int numLinha;
	
	private String mensagem;

	public int getNumLinha() {
		return numLinha;
	}

	public void setNumLinha(int numLinha) {
		this.numLinha = numLinha;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString()
	{
		return this.numLinha+"\t"+ this.mensagem;
	}
}
