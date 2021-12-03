package org.acme.exception;

public class PrixLieuDitNotFound extends Exception{
    public static class PrixLieuDitNofFoundException extends Throwable{
        public PrixLieuDitNofFoundException(int codePostal){
            super("Prix non trouvé pour le code postal : "+codePostal);
        }
    }
}
