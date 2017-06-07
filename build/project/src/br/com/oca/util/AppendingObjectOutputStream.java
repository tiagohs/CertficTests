/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * 
 * A Classe AppendingObjectOutputStream � uma classe auxiliar na cria��o do
 * Arquivo de persist�ncia da Aplica��o, nesse caso, um arquivo simples .bat.
 * Ela � chamada quando j� existe um arquivo .bat com o mesmo nome no sistema,
 * ent�o � criado uma inst�ncia onde se referencia esse arquivo j� criado, para
 * sobreescrever o mesmo no final.
 * 
 * Classe <code>AppendingObjectOutputStream</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public class AppendingObjectOutputStream extends ObjectOutputStream {
	/**
	 * 
	 * Instancia um novo AppendingObjectOutputStream.
	 * 
	 * @param out
	 *            a referencia a um FileOutputStream, por exemplo, para se criar
	 *            o arquivo .bat.
	 * @throws IOException
	 *             se houver qualquer erro na leitura ou cria��o, essa excess�o
	 *             � lan�ada.
	 */
	public AppendingObjectOutputStream(OutputStream out) throws IOException {
		super(out);
	}

	/* (non-Javadoc)
	 * @see java.io.ObjectOutputStream#writeStreamHeader()
	 */
	@Override
	protected void writeStreamHeader() throws IOException {
		reset();
	}

}
