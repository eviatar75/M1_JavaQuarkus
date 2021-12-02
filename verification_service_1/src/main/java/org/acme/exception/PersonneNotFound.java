package org.acme.exception;

public class PersonneNotFound extends Exception {
    public static class PersonneNotFoundException extends Throwable {
        public  PersonneNotFoundException(int idBien) {
            super("personne(s) non trouvés");
        }
    }
}
