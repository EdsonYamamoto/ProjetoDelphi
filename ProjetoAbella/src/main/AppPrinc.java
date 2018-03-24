package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//regex usados linguagens formais
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//import com.mysql.jdbc.util.PropertiesDocGenerator;

import java.lang.String;

import modelo.CodigoModelo;
import modelo.ConsultaModelo;
import modelo.ExceptionsModelo;
import modelo.IfModelo;
import modelo.Procura;



public class AppPrinc {

	//private String diretorio = "C:/XPCell/Fontes/fntXpCellProducaoSorocaba";
	private String diretorio = "C:/Users/Edson/Desktop/Teste"; //teste
	private String extensao = "pas";

	public String tipoArquivoSaida = ".txt";
	public CodigoModelo modelo;
	//Todas as palavras que podem ser procuradas
	public String palavraIf = "(IF|if|If|iF)";
	public String palavraElse = "(ELSE|Else|ELse|ELSe|else)";
	public String palavraWhile = "(While|while|WHILE)";
	public String palavraDo = "(DO|do)";
	public String palavraCometario1Linha = "([//])";	
	public String palavraCometarioMultiplasLinhaInicio = "([{])";	
	public String palavraCometarioMultiplasLinhaFim = "([}])";	
	public String palavraExceptionCreate = "(Exception.Create|exception.create)";
	public String palavraProcedure = "(procedure|Procedure|PROCEDURE)";
	public String palavraFunction = "(function|Function|FUNCTION)";
	public String palavraPonto = "([.])";
	public String palavraAbreParenteses = "([(])";
	public String palavraFechaParenteses = "([)])";
	public String palavraPontoVirgula = "([;])";
	public String palavraEnd = "(end|END|End)";
	public String palavraBegin = "(Begin|begin|BEGIN)";
	public String palavraConsulta = "(sql.add|SQL.ADD|Sql.Add|SQL.Add)";
	
	public String palavraProcura = "(IdTipoServico)";
		
	public List<CodigoModelo> listaCodigos = new ArrayList<CodigoModelo>();
	public CodigoModelo codigo = new CodigoModelo();
	
	public List<ExceptionsModelo> listaExceptions = new ArrayList<ExceptionsModelo>();
	public ExceptionsModelo exception = new ExceptionsModelo();
	
	public List<IfModelo> listaIfs = new ArrayList<IfModelo>();
	public IfModelo ifs = new IfModelo();
	
	public List<ConsultaModelo> listaConsultas = new ArrayList<ConsultaModelo>();
	public ConsultaModelo consultas = new ConsultaModelo();

	public List<Procura> listaProcura = new ArrayList<Procura>();
	public Procura procura = new Procura();
	
	// lista todos os IF's do codigo
	public boolean listarIf = false;
	// lista todos as exceptions do codigo 
	public boolean listarExcecoes = false;
	// lista todas as funções e procedures
	public boolean listarMetodos = true;
	// lista todos as consultas de data source do codigo 
	public boolean listarConsultas = false;
	//modo simplificado retorna o tipo de coisa encontrado na linha como por exemplo if's e else's
	public boolean modoSimplificado= false;
	//imprimi diretamente no console
	public boolean modoImprimir= true;
	//imprimi todo o codigo do modo imprimir num arquivo especifico
	public boolean escritaEmArquivo= false;
	//imprimi procura
	public boolean procuraEmArquivo= false;

	/*
	 * Atributos para funcionar o codigo
	 * */
	int numLinha=0;
	public boolean condicaoDeComentario = false;
	public int espacadorLinha = 0;
	/*
	 * */
	
	//cria o app principals
	public static void main(String[] args)
	{
		try {
			AppPrinc programa = new AppPrinc();	
			System.out.println(programa.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public AppPrinc() throws IOException {
		File file = new File(diretorio);
		File afile[] = file.listFiles();
		int i = 0;
		for (int j = afile.length; i < j; i++) {
			File arquivos = afile[i];
			if(arquivos.getName().endsWith(extensao))
			{
				reescreveCodigo(arquivos);
			}
			if(arquivos.length()==0)
				arquivos.delete();
			
			if(listarConsultas)
				listarConsultas();
			if(listarMetodos)
				listarMetodos();
			if(listarExcecoes)
				listarExcecoes();
			if(listarIf)
				listarIfs();
			if(procuraEmArquivo)
				procuraEmArquivo();
		}
	}
	
	private void procuraEmArquivo() {
		for (Procura p : listaProcura) {
			System.out.println(p);
		}
	}

	private void listarConsultas() {
		// TODO Auto-generated method stub
		for (ConsultaModelo c : listaConsultas) 
			System.out.println(c);
	}

	private void listarIfs() {
		// TODO Auto-generated method stub
		for (IfModelo i : listaIfs) {
			System.out.println(i);
		}
		
	}

	private void listarExcecoes() {
		// TODO Auto-generated method stub
		
		for (ExceptionsModelo e : listaExceptions) {
			/*
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExceptionsModelo");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
	        em.persist(e);
	        em.getTransaction().commit();

	        em.close();
	        emf.close();
	        */
			System.out.println(e);
		}
		
	}
	

	public void listarMetodos()
	{
		EntityManagerFactory emf;
		EntityManager        em;

		emf  = Persistence.createEntityManagerFactory("ProjetoAbella");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		for (CodigoModelo c : listaCodigos) 
		{
			
				em.persist(c);
		    System.out.println("persistiu!");
			System.out.println(c);
		}
		em.close();
	    em.getTransaction().commit();

		emf .close();
	}
	

	private void criarBancoDeMetodos(CodigoModelo modeloCodigo) {
		EntityManagerFactory emf;
		EntityManager        em;

		emf  = Persistence.createEntityManagerFactory("ProjetoAbella");
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
			em.persist(modeloCodigo);
		em.close();
	    em.getTransaction().commit();
	    System.out.println("persistiu!");
		emf .close();
	}
	
	public static String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }
	
	
	//codigo q reescreve todas o arquivo de texto
	private void reescreveCodigo(File arquivos) throws IOException {
		String nomeArquivo = arquivos.getName();
		String nomeArquivoSaida = nomeArquivo+tipoArquivoSaida;	//o nome do arquivo com a extensão
		String caminhoArquivo = arquivos.getAbsolutePath();
		String aux;
		numLinha=0;
		
		
		String espacoAux="";
		
		File arquivoSaida = new File(nomeArquivoSaida);	
	      /*ESCREVER*/
		FileWriter arquivoParaEscrever = new FileWriter (arquivoSaida);//arquivo para escrita
	
		BufferedReader br = null;
	
		try {
			br = new BufferedReader(new FileReader(caminhoArquivo));
			arquivoSaida.createNewFile ();//arquivo criado
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();
		}
		espacadorLinha = 0;
		BufferedWriter bufferEscrita= new BufferedWriter (arquivoParaEscrever);
		String espaco="\t";
		
		while(br.ready()){
			String escritaArquivo = br.readLine().trim();
			numLinha++;

			codigo.setPasName(arquivos.getName());
			aux = tipoString(escritaArquivo.trim(), br) ;
			if(aux.compareTo("")!=0&&!condicaoDeComentario&&modoImprimir)
			{
				espacoAux="";
				espacoAux =  repeat(espaco, espacadorLinha);
				//System.out.println(espacadorLinha);
				aux = numLinha+"\t"+espacoAux+aux;
				
				escritaArquivo = numLinha+"\t"+espacoAux+escritaArquivo;
				if(!modoSimplificado)
					System.out.println(escritaArquivo);
				else
					System.out.println(aux);
				
				if(escritaEmArquivo)
				{
					bufferEscrita.write (escritaArquivo);//Leia um arquivo e Escreva no outro
					bufferEscrita.newLine ();//pula uma linha no arquivoescrever (result);
				}
				
			}
			
		}			
		br.close();
		arquivoParaEscrever.close ();
	}
	
	
	
	
	
	
	
	
	/*
	 * Encontra palavras reservadas
	 * */
	private String tipoString(String str, BufferedReader br)
	{
		
		String[] palavras, metodoProcFuncDivisao;
		String tipo = "";
		
		palavras = str.split(" ");
		
		Pattern patternIf = Pattern.compile(palavraIf);
		Pattern patternElse = Pattern.compile(palavraElse);
		Pattern patternWhile = Pattern.compile(palavraWhile);
		Pattern patternDo = Pattern.compile(palavraDo);
		Pattern patternExceptionCreate = Pattern.compile(palavraExceptionCreate);
		Pattern patternComentario1Linha = Pattern.compile(palavraCometario1Linha);
		Pattern patternComentarioMultiplasLinhaInicio = Pattern.compile(palavraCometarioMultiplasLinhaInicio);
		Pattern patternComentarioMultiplasLinhaFim = Pattern.compile(palavraCometarioMultiplasLinhaFim);
		Pattern patternProcedure = Pattern.compile(palavraProcedure);
		Pattern patternFunction = Pattern.compile(palavraFunction);
		Pattern patternPonto = Pattern.compile(palavraPonto);
		Pattern patternPontoVirugula = Pattern.compile(palavraPontoVirgula);
		Pattern patternEnd = Pattern.compile(palavraEnd);
		Pattern patternBegin = Pattern.compile(palavraBegin);
		Pattern patternConsulta = Pattern.compile(palavraConsulta);
		Pattern patternProcura = Pattern.compile(palavraProcura);
		
		
		for(int i=0;i<palavras.length;i++)	
		{
	        Matcher matcherIf = patternIf.matcher(palavras[i]);
	        Matcher matcherElse = patternElse.matcher(palavras[i]);
	        Matcher matcherWhile = patternWhile.matcher(palavras[i]);
	        Matcher matcherDo = patternDo.matcher(palavras[i]);
	        Matcher matcherExceptionCreate = patternExceptionCreate.matcher(palavras[i]);
	        Matcher matcherComentario1Linha = patternComentario1Linha.matcher(palavras[i]);
	        Matcher matcherComentarioMultiplasLinhaInicio = patternComentarioMultiplasLinhaInicio.matcher(palavras[i]);
	        Matcher matcherComentarioMultiplasLinhaFim = patternComentarioMultiplasLinhaFim.matcher(palavras[i]);
	        Matcher matcherFunction = patternFunction.matcher(palavras[i]);
	        Matcher matcherProcedure = patternProcedure.matcher(palavras[i]);
	        Matcher matcherPontoVirgula = patternPontoVirugula.matcher(palavras[i]);
	        Matcher matcherEnd = patternEnd.matcher(palavras[i]);
	        Matcher matcherBegin = patternBegin.matcher(palavras[i]);
	        Matcher matcherConsulta = patternConsulta.matcher(palavras[i]);
	        Matcher matcherProcura = patternProcura.matcher(palavras[i]);
	        
	        
	        if(matcherProcura.find())
	        {
	        	procura.setNumLinha(numLinha);
	        	procura.setMensagem(str);
	        	listaProcura.add(procura);
	        	procura= new Procura();
	        }
	        
	        if(matcherConsulta.find())
	        {
	        	String[] splitConsulta;
	        	splitConsulta = str.split("add|Add|ADD|;");
	        	consultas.setNumLinha(numLinha);
	        	consultas.setConsulta(splitConsulta[1]);
	        	listaConsultas.add(consultas);
	        	consultas= new ConsultaModelo();
	        	
	        	tipo += "consulta";
	        }
	        
	        if(matcherIf.matches())
	        {
	        	String[] splitIf;
	        	splitIf = str.split("(if|IF|If|then|Then|THEN)");
	        	if(splitIf.length>1)
	        	{
		        	ifs.setNumLinha(numLinha);
		        	ifs.setCondicao(splitIf[1]);
		        	listaIfs.add(ifs);
		        	ifs = new IfModelo();
		        	
		        	//for (String s : splitIf) 
		        	//	System.out.println(numLinha+"\t"+s);

	        	}
		        tipo += "If ";
	        }
	        if(matcherElse.matches())
	        	tipo += "Else ";
	        
	        if(matcherWhile.matches())
	        	tipo += "While ";
	        if(matcherDo.matches())
	        	tipo += "Do ";
	        	
	        if(matcherExceptionCreate.find())
	        {
	        	String[] splitExcepection;
		    	splitExcepection = str.split("[(']|[')]");
		    	if(splitExcepection.length>2)
		    	{
			    	exception.setNumLinha(numLinha);
			    	exception.setMensagem(splitExcepection[2]);
			    	exception.setCodigoModelo(listaCodigos.get(listaCodigos.size()-1));
			    	listaExceptions.add(exception);
			    	exception = new ExceptionsModelo();
			    }
		    	else
		    	{
			    	exception.setNumLinha(numLinha);
			    	exception.setMensagem(splitExcepection[1] + ". (Mensagem gerada pelo sistema)");
			    	exception.setCodigoModelo(listaCodigos.get(listaCodigos.size()-1));
			    	listaExceptions.add(exception);
			    	exception = new ExceptionsModelo();
		    	}
	        	tipo += "ExceptionCreate ";
	        }

	        if(matcherComentario1Linha.find())
	        {
	        	//condicaoDeComentario = true;
	        	//tipo += "ComentarioDeLinha ";
	        }
	        
	        
	        if(matcherComentarioMultiplasLinhaInicio.find())
	        {
	        	condicaoDeComentario=true;
	        	tipo += "ComentarioVariasLinhas Inicia ";
	        }

	        if(matcherComentarioMultiplasLinhaFim.find())
	        {
	        	condicaoDeComentario=false;
	        	tipo += "ComentarioVariasLinhas FIM ";
	        }
	        
	        if(matcherPontoVirgula.find())
	        {
	        	condicaoDeComentario=false;
	        	//tipo += "Fim de comando FIM ";
	        }
	        
	        //
	        // Espacadores
	        //
	        if(matcherBegin.find())
	        	espacadorLinha++;
	        if(matcherEnd.find())
	        	if(espacadorLinha>0)
	        	espacadorLinha--;

	        //
	        // Matcher encontra metodos
	        // 
	        if(matcherFunction.matches())
	        {
	        	condicaoDeComentario=true;
	        	for(String p : palavras)
	        	{
			        Matcher matcherPonto = patternPonto.matcher(p);
			        if(matcherPonto.find())
			        {
			    		metodoProcFuncDivisao = p.split("[.]|[(]|[)]|[;]|[:]");
			    		if(metodoProcFuncDivisao.length!=1)
			    		{
				    		codigo.setUnit(metodoProcFuncDivisao[0]);
				    		codigo.setProcFunc(palavras[0]);
				    		codigo.setMetodo(metodoProcFuncDivisao[1]);
				        	codigo.setNumLinha(numLinha);
				        	
				        	
				        	listaCodigos.add(codigo);
				        	codigo = new CodigoModelo();
				        	
				        	tipo += "Função ";
			    		}
			        }
	        	}
	        }
	        if(matcherProcedure.matches())
	        {
	        	
	        	condicaoDeComentario=true;
	        	for (String p : palavras) {
			        Matcher matcherPonto = patternPonto.matcher(p);
			        if(matcherPonto.find())
			        {
			    		metodoProcFuncDivisao = p.split("[.]|[(]|[)]|[;]");
						if(metodoProcFuncDivisao.length!=1)
						{
							codigo.setUnit(metodoProcFuncDivisao[0]);
				        	
							codigo.setProcFunc(palavras[0]);
							codigo.setMetodo(metodoProcFuncDivisao[1]);
				        	
				        	codigo.setNumLinha(numLinha);
				        	listaCodigos.add(codigo);
				        	codigo = new CodigoModelo();
				        	tipo += "Procedure ";
						}
			        }

				}
	        }
	        	
	        
		}
		
		return tipo;
	}
}

