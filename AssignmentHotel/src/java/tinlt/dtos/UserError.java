/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinlt.dtos;

/**
 *
 * @author Ray Khum
 */
public class UserError {

    String emailError;
    String fullnameError;
    String passwordError;
    String confirmError;
    String roleIDError;

    public UserError() {
    }

    public UserError(String emailError, String fullnameError, String passwordError, String confirmError, String roleIDError) {
        this.emailError = emailError;
        this.fullnameError = fullnameError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.roleIDError = roleIDError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getFullnameError() {
        return fullnameError;
    }

    public void setFullnameError(String fullnameError) {
        this.fullnameError = fullnameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

}
