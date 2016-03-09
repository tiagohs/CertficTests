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
 * A Classe AppendingObjectOutputStream é uma classe auxiliar na criação do
 * Arquivo de persistência da Aplicação, nesse caso, um arquivo simples .bat.
 * Ela é chamada quando já existe um arquivo .bat com o mesmo nome no sistema,
 * então é criado uma instância onde se referencia esse arquivo já criado, para
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
	 *             se houver qualquer erro na leitura ou criação, essa excessão
	 *             é lançada.
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
