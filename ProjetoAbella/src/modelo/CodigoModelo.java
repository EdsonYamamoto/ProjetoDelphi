package modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CodigoModelo")
public class CodigoModelo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	
	private int id;
	private String unit;
	private String pasName;
	private String procFunc;
	private int numLinha;
	private String metodo;
	
	
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPasName() {
		return pasName;
	}
	public void setPasName(String pasName) {
		this.pasName = pasName;
	}
	public String getProcFunc() {
		return procFunc;
	}
	public void setProcFunc(String procFunc) {
		this.procFunc = procFunc;
	}
	public int getNumLinha() {
		return numLinha;
	}
	public void setNumLinha(int numLinha) {
		this.numLinha = numLinha;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public CodigoModelo()
	{
		
	}
	public CodigoModelo(String procedure,int numLinha)
	{
		this.metodo = procedure;
		this.numLinha = numLinha;
	}
	
	@Override
	public String toString()
	{
		return this.pasName+"\t"+ this.unit + "\t"+this.procFunc+"\t"+this.numLinha+"\t"+this.metodo;
	}
}

