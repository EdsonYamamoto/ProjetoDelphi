package modelo;

public class ConsultaModelo {
	private int numLinha;
	private String consulta;
	public int getNumLinha() {
		return numLinha;
	}
	public void setNumLinha(int numLinha) {
		this.numLinha = numLinha;
	}
	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	@Override
	public String toString()
	{
		return this.numLinha+"\t"+ this.consulta;
	}
}
