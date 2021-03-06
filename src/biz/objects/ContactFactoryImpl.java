package biz.objects;

import biz.ContactDto;
import biz.ContactFactory;

/**
 * This implementation of the factory is used to create new Real DTO objects.
 * 
 * @author Antoine.Maniet
 */
class ContactFactoryImpl implements ContactFactory {
  public ContactDto getContact() {
    return new ContactImpl();
  }
}
