package forms;


/**
 * This class is used to render, restrict and validate a login form
 * @author Giovanni Alberto García
 * @version 1.0
 */
public class LoginForm {

  private String emails;
  private String password;

  public void setEmails(String emails) {
      this.emails = emails;
  }

  public String getEmails() {
      return emails;
  }

  public void setPassword(String password) {
      this.password = password;
  }

  public String getPassword() {
      return password;
  }

}