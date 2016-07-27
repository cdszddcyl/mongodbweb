package com.mongodb.model;

import java.io.Serializable;

public class IdEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4168166642902777422L;
	
	private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}
