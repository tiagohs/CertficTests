package br.com.oca.util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendingObjectOutputStream extends ObjectOutputStream {
	public AppendingObjectOutputStream(OutputStream out) throws IOException {
	    super(out);
	  }
	
	 @Override
	  protected void writeStreamHeader() throws IOException {
	    reset();
	  }

}
