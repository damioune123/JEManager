package biz.objects;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.BCrypt;

import exceptions.InvalidInformationException;
import util.Util;

public class UserImpl implements biz.User {

  private int userId;
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private int version = 0;

  /**
   * When inserting a new user in the database, the date will be set to the current date and time
   * using setRegistrationDate(LocalDateTime.now()). In the database, the date is then stored as a
   * String. When selecting a user from the database, the date will be converted to LocalDateTime
   * again using LocalDateTime.parse().
   * 
   * @author Maniet.Alexandre
   */
  private LocalDateTime registrationDate;

  /**
   * To encrypt before sending to the DB.
   */
  private String password;

  /**
   * Default value is false.
   */
  private boolean manager;

  /**
   * This constructor creates a RealUser instance. Setting specific values to said instance's fields
   * is done only through the setters. This will usually be done in a UCC (inserting data in db) or
   * DAO (extracting data from db).
   * 
   * @author Maniet.Alexandre
   */
  public UserImpl() {}

  @Override
  public int getUserId() {
    return userId;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public LocalDateTime getRegistrationDate() {
    return registrationDate;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isManager() {
    return manager;
  }

  @Override
  public void setUserId(int userId) {
    Util.checkPositiveInteger(userId);
    this.userId = userId;
  }

  @Override
  public void setUsername(String username) {
    Util.checkString(username);
    this.username = username;
  }

  @Override
  public void setFirstName(String firstName) {
    Util.checkString(firstName);
    this.firstName = firstName;
  }

  @Override
  public void setLastName(String lastName) {
    Util.checkString(lastName);
    this.lastName = lastName;
  }

  @Override
  public void setEmail(String email) {
    Util.checkString(email);
    this.email = email;
  }

  @Override
  public void setRegistrationDate(LocalDateTime registrationDate) {
    Util.checkLocalDateTime(registrationDate);
    this.registrationDate = registrationDate;
  }

  @Override
  public void setPassword(String password) {
    Util.checkString(password);
    this.password = password;
  }

  @Override
  public void setManager(boolean manager) {
    this.manager = manager;
  }

  @Override
  public void setVersion(int version) {
    this.version = version;
  }

  @Override
  public int getVersion() {
    return this.version;
  }

  @Override
  public void encryptPassword() {
    password = BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public boolean checkPassword(String plainText) {
    return BCrypt.checkpw(plainText, this.password);
  }

  @Override
  public void checkNewUserInformation() throws InvalidInformationException {
    if (registrationDate == null || firstName == null || lastName == null || email == null
        || firstName.equals("") || lastName.equals("") || email.equals("")) {
      throw new InvalidInformationException("Informations incomplètes");
    }
    LocalDateTime now = LocalDateTime.now();
    if (registrationDate.isAfter(now)) {
      throw new InvalidInformationException("Problème date inscription");
    }
    Pattern p1 = Pattern.compile(".+@.+\\.[a-z]+");
    Matcher m1 = p1.matcher(email);
    boolean matchFound = m1.matches();
    if (!matchFound) {
      throw new InvalidInformationException("Email non valide");
    }
    Pattern p2 = Pattern.compile("\\d+");
    Matcher m2 = p2.matcher(password);
    if (password.length() < 6 || password.toLowerCase().equals(password)
        || password.toUpperCase().equals(password) || !m2.find()) {
      throw new InvalidInformationException(
          "Le mdp doit faire 6+ caratères, contenir minuscule, majuscule et chiffre");
    }
  }

  @Override
  public String getId() {
    return this.getClass().getName() + this.userId;
  }
}
