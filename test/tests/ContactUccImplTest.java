package tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import biz.ContactDto;
import mockObjects.MockContact;
import mockObjects.MockContactDao;
import mockObjects.MockDalServices;
import mockObjects.MockUnitOfWork;
import ucc.ContactUccImpl;

public class ContactUccImplTest {

  private MockDalServices mockDalServices;
  private MockContactDao mockContactDao1, mockContactDao2;
  private MockUnitOfWork mockUnitOfWork1, mockUnitOfWork2, mockUnitOfWork3;
  private ContactUccImpl ucc1, ucc2, ucc3;
  private ContactDto mockContactDto;

  /**
   * @throws Exception Exception If one is thrown during initialisation.
   */
  @Before
  public void setUp() throws Exception {
    // for test 1
    mockContactDto = new MockContact();
    mockContactDao1 = new MockContactDao();
    mockUnitOfWork1 = new MockUnitOfWork(Arrays.asList("addInsert"));
    mockDalServices = new MockDalServices();
    ucc1 = new ContactUccImpl(mockContactDao1, mockUnitOfWork1, mockDalServices);
    // for test 2
    mockUnitOfWork2 = new MockUnitOfWork();
    mockContactDao2 = new MockContactDao(Arrays.asList("getAllContacts"));
    ucc2 = new ContactUccImpl(mockContactDao2, mockUnitOfWork2, mockDalServices);
    // for test 3
    mockUnitOfWork3 = new MockUnitOfWork(Arrays.asList("addUpdate"));
    ucc3 = new ContactUccImpl(mockContactDao1, mockUnitOfWork3, mockDalServices);
  }

  /**
   * Checks that the method addContact calls the addInsert method on the unit of work.
   */
  @Test
  public void testAddContact() {
    ucc1.addContact(mockContactDto);
    assertEquals("addInsert should have been called on the unit of work", true,
        mockUnitOfWork1.validate());
  }

  /**
   * Checks that getAllContacts is called on the contact dao.
   */
  @Test
  public void testGetAllContacts() {
    ucc2.getAllContacts();
    assertEquals("getallContacts should have been called on the contact dao", true,
        mockContactDao2.validate());
  }

  /**
   * Checks that addUpdate is called on the unit of work.
   */
  @Test
  public void testSetContactActivity() {
    ucc3.setContactActivity(mockContactDto);
    assertEquals("addUpdate should have been called on the unit of work", true,
        mockUnitOfWork3.validate());
  }

  /**
   * Checks that addUpdate is called on the unit of work.
   */
  @Test
  public void testUpdateContact() {
    ucc3.updateContact(mockContactDto);
    assertEquals("addUpdate should have been called on the unit of work", true,
        mockUnitOfWork3.validate());
  }

}
