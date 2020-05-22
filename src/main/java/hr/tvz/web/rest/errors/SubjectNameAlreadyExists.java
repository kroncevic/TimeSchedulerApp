package hr.tvz.web.rest.errors;

public class SubjectNameAlreadyExists extends BadRequestAlertException {
	
    private static final long serialVersionUID = 1L;

    public SubjectNameAlreadyExists() {
        super(ErrorConstants.NAME_ALREADY_EXISTS, "Name already exists!", "pageManagement", "nameexists");
    }

}
