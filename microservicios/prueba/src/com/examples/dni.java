package com.examples;



public class dni{
	
	public boolean esDni(String nif) {
		nif = nif.toUpperCase();
		if (!nif.matches("^\\d{1,8}[A-Z]$"))
			return false;
		return "TRWAGMYFPDXBNJZSQVHLCKE".charAt(Integer.parseInt(nif.substring(0, nif.length() - 1)) % 23) == nif
				.charAt(nif.length() - 1);
	}
	

	/*
	  public class dni {
	public boolean esNif(String nif) {
		boolean verificado = false;
		Pattern patron = Pattern.compile("/^\\d{1,8}[A-Za-z]$/");
		Matcher mat = patron.matcher(nif);
	    if (mat.matches()) {
	    	verificado = true;
	    }
		
		return verificado;
	}
	 * */
	
}
