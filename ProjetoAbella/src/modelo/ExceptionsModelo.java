package modelo;

public class ExceptionsModelo {
	private int numLinha;
	private String mensagem;
	private CodigoModelo codigoModelo;
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
	public CodigoModelo getCodigoModelo() {
		return codigoModelo;
	}
	public void setCodigoModelo(CodigoModelo codigoModelo) {
		this.codigoModelo = codigoModelo;
	}
	@Override
	public String toString()
	{
		return this.codigoModelo+"\t"+ this.numLinha+"\t"+ this.mensagem;
	}
}
