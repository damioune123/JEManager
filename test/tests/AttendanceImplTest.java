package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import biz.Attendance;
import biz.objects.AttendanceImpl;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import mockObjects.MockContact;
import mockObjects.MockParticipation;

public class AttendanceImplTest {

  private Attendance attendance1;
  private Attendance attendance2;
  private MockContact mockContact = new MockContact();
  private MockParticipation mockParticipation = new MockParticipation();

  /**
   * @throws Exception if one is thrown during initialisation.
   */
  @Before
  public void setUp() throws Exception {
    attendance1 = new AttendanceImpl();

    attendance2 = new AttendanceImpl();
    attendance2.setAttendanceId(1);
    attendance2.setConfirmed(false);
    attendance2.setContact(mockContact);
    attendance2.setContactId(mockContact.getCompanyId());
    attendance2.setParticipation(mockParticipation);
    attendance2.setParticipationId(mockParticipation.getParticipationId());
  }

  /**
   * Testing all getters on a sample attendance.
   */
  @Test
  public void testGetters() {
    assertEquals("Attendance id should be 1", 1, attendance2.getAttendanceId());
    assertEquals("Contact should be " + mockContact, mockContact,
        attendance2.getContact());
    assertEquals("Contact id should be " + mockContact.getContactId(),
        mockContact.getContactId(), attendance2.getContactId());
    assertEquals("Participation should be " + mockParticipation, mockParticipation,
        attendance2.getParticipation());
    assertEquals("Participation id should be " + mockParticipation.getParticipationId(),
        mockParticipation.getParticipationId(), attendance2.getParticipationId());
    assertEquals("Attendance should not be confirmed", false, attendance2.isConfirmed());
  }

  /**
   * SetAttendanceId with correct value.
   */
  @Test
  public void testSetAttendanceId1() {
    attendance1.setAttendanceId(1);
    assertEquals("Attendance id should be 1", 1, attendance1.getAttendanceId());
  }

  /**
   * SetAttendanceId with incorrect value (negative integer).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetAttendanceId2() {
    attendance1.setAttendanceId(-1);
  }

  /**
   * SetConfirmed with correct value.
   */
  @Test
  public void testSetConfirmed() {
    attendance1.setConfirmed(true);
    assertEquals("Attendance should be confirmed", true, attendance1.isConfirmed());
  }

  /**
   * SetContact with correct value.
   */
  @Test
  public void testSetContact1() {
    attendance1.setContact(mockContact);
    assertEquals("Attendance's contact should be " + mockContact, mockContact,
        attendance1.getContact());
  }

  /**
   * SetContact with incorrect value (null).
   */
  @Test(expected = IllegalArgumentException.class)
  @SuppressFBWarnings("")
  public void testSetContact2() {
    attendance1.setContact(null);
  }

  /**
   * SetContactId with correct value.
   */
  @Test
  public void testSetContactId1() {
    attendance1.setContactId(1);
    assertEquals("Attendance's contact id should be 1", 1, attendance1.getContactId());
  }

  /**
   * SetContactId with incorrect value (negative integer).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetContactId2() {
    attendance1.setContactId(-1);
  }

  /**
   * SetParticipation with correct value.
   */
  @Test
  public void testSetParticipation() {
    attendance1.setParticipation(mockParticipation);
    assertEquals("Attendance's participation should be " + mockParticipation,
        mockParticipation, attendance1.getParticipation());
  }

  /**
   * SetParticipation with incorrect value (null).
   */
  @Test(expected = IllegalArgumentException.class)
  @SuppressFBWarnings("")
  public void testSetParticipation2() {
    attendance1.setParticipation(null);;
  }


  /**
   * SetParticipationId with correct value.
   */
  @Test
  public void testSetParticipationId1() {
    attendance1.setParticipationId(1);
    assertEquals("Attendance's Participation id should be 1", 1, attendance1.getParticipationId());
  }


  /**
   * SetParticipationId with incorrect value (negative integer).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetParticipationId2() {
    attendance1.setParticipationId(-1);
  }

}
