package persistence.dao;

import java.util.List;

import biz.Contact;

public interface ContactDao extends Dao {

  /**
   * @author Maniet.Antoine
   * @return A list of all the contacts found in the database.
   */
  List<Contact> getAllContacts();

  /**
   * @author Damien.Meur
   * @param idContact , the id of the contact to fetch from the database
   * @return the contact with the id in the parameter, null if an error occured
   */
  Contact getContactById(int idContact);
}
