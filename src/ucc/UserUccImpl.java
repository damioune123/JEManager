package ucc;

import biz.User;
import biz.UserDto;
import exceptions.InvalidInformationException;
import exceptions.UserNameAlreadyPickedException;
import persistence.DalServices;
import persistence.UnitOfWork;
import persistence.dao.UserDao;

public class UserUccImpl implements UserUcc {

  private UserDao userDao;
  private DalServices dal;
  private UnitOfWork unit;

  /**
   * @param userDao The userUCC's associated dao, needed for query methods.
   * @param dal An instance of dalServices needed for transactions.
   * @param unit an instance of unitOfWork needed to do insert, update and delete.
   */
  public UserUccImpl(UserDao userDao, DalServices dal, UnitOfWork unit) {
    this.userDao = userDao;
    this.dal = dal;
    this.unit = unit;
  }

  /**
   * {@inheritDoc}.
   */
  public UserDto login(UserDto user) {
    dal.startTransaction();
    User userDb = userDao.getUserByPseudo(user.getUsername());
    dal.commit();
    if (userDb == null) {
      return null;
    }
    if (!userDb.checkPassword(user.getPassword())) {
      return null;
    } else {
      return userDb;
    }
  }

  @Override
  public User register(UserDto user)
      throws UserNameAlreadyPickedException, InvalidInformationException {
    dal.startTransaction();
    User bizUser = (User) user;
    bizUser.checkNewUserInformation();
    bizUser.encryptPassword();
    User userDb = userDao.getUserByPseudo(user.getUsername());
    if (userDb != null) {
      dal.rollback();
      throw new UserNameAlreadyPickedException("Pseudo déjà pris !");
    }
    dal.commit();
    unit.startTransaction();
    unit.addInsert(user);
    unit.commit();
    dal.startTransaction();
    User newUserDb = userDao.getUserById(unit.getResult());
    dal.commit();
    return newUserDb;
  }

  @Override
  public UserDto getUserByUsername(String username) {
    dal.startTransaction();
    // Checking parameter
    if (username == null) {
      dal.rollback();
      throw new IllegalArgumentException();
    }
    // Getting the user via the Dao
    UserDto user = userDao.getUserByPseudo(username);
    // If no user is found, throwing a runtime exception
    dal.commit();
    if (user == null) {
      throw new IllegalArgumentException();
    }
    return user;
  }

  @Override
  public UserDto getUserById(int id) {
    dal.startTransaction();
    // Checking parameter
    if (id < 0) {
      dal.rollback();
      throw new IllegalArgumentException();
    }
    // Getting the user via the Dao
    UserDto userDto = userDao.getUserById(id);
    dal.commit();
    if (userDto == null) {
      throw new IllegalArgumentException();
    }
    return userDto;
  }
}
