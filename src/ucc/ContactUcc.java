package ucc;

import java.util.List;

import biz.Contact;
import biz.ContactDto;

public interface ContactUcc {

  /**
   * @author Maniet.Antoine
   * @param contact The ContactDto instance which values must be added into the DB
   * @return The contact with his new ID, or null if an error occurred during the insert
   */
  Contact addContact(ContactDto contact);

  /**
   * @author Maniet.Antoine
   * @return The list of all contacts found in the database.
   */
  List<ContactDto> getAllContacts();

  /**
   * @author Damien.Meur
   * @param contact The ContactDto instance which value of activity (boolean) must be updated into
   *        the DB
   * @return The contact who has been updated or null if an error occurred during the update
   */
  Contact setContactActivity(ContactDto contact);

  /**
   * 
   * @author Sacre.Christopher.
   * @param contact the contact dto to modify.
   */
  ContactDto updateContact(ContactDto contact);
}
