package com.blair.BlairSpringGlorious.exceptions;

public class CannotDeleteAdminException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    public CannotDeleteAdminException() {
        super("Cannot delete \"admin\".");
    }

}
