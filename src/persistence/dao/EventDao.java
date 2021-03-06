package persistence.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import biz.Company;
import biz.Contact;
import biz.Event;

public interface EventDao extends Dao {

  /**
   * This method is called in EventUccImpl.generateFullCsv
   * 
   * @param eventId the ID of the event we need information for
   * @return a map of the company and all the contacts attending to the event (with an Attendance
   *         set to confirmed).
   */
  HashMap<Company, ArrayList<Contact>> getMyParticipants(int eventId);

  /**
   * This method is called in EventUccImpl.generateCsvRows
   * 
   * @param participationId the ID of the participation we need information for
   * @return a map of the company and all the contacts attending to the event (with an Attendance
   *         set to confirmed).
   */
  HashMap<Company, ArrayList<Contact>> getParticipationDatas(int participationId);

  /**
   * this method send a entirely EventDao object if there is an event unclosed, null otherwise.
   * 
   * @return the filled eventDao having his closed boolean to false
   */
  Event getCurrentEvent();

  /**
   * 
   * @author Sacre.Christopher
   * @return All the events found in the database.
   */
  List<Event> selectAll();

  /**
   * Find an event with its id.
   * 
   * @param idEvent target event's id.
   * @return the event if there is a matching id or null otherwise.
   */
  Event findEventById(int idEvent);
}
