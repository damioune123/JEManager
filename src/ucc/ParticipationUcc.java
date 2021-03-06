package ucc;

import java.util.List;

import biz.EventDto;
import biz.ParticipationDto;
import exceptions.WrongStateException;

public interface ParticipationUcc {

  public static final String CONFIRMED = "confirmed";
  public static final String INVITED = "invited";
  public static final String REFUSED = "refused";
  public static final String CANCELLED = "cancelled";
  public static final String INVOICED = "invoiced";
  public static final String PAID = "paid";
  public static final String ALL = "all";

  /**
   * @author Antoine.Maniet
   * @return a list of the current event's participations
   */
  List<ParticipationDto> getCurrentParticipations();

  /**
   * @author Antoine.Maniet
   * @return a list of all participations
   */
  List<ParticipationDto> getAllParticipations();

  /**
   * @author Maniet.Antoine
   * @param participation The ParticipationDto instance which values must be added into the DB
   * @return The participation with his new ID, or null if an error occurred during the insert
   */
  ParticipationDto addParticipation(ParticipationDto participation);

  /**
   * count the participations in the invited state for a given event.
   * 
   * @author Sacre.Christopher
   * @param event The event to count the invited participations.
   * @return the number of invited participations.
   */
  int countInvitedParticipation(EventDto event);

  /**
   * This update the states of the participations.
   * 
   * @author Damien.Meur
   * @param participations list of the participations for which the state is going to be modified
   * 
   */
  int editStates(List<ParticipationDto> participations) throws WrongStateException;

  /**
   * 
   * @author Sacre.Christopher
   * @param event the event to get the participations.
   * @return the number of participations in the confirmed state.
   */
  int countConfirmedParticipation(EventDto event);

  /**
   * 
   * @author Sacre.Christopher
   * @param event the event to get the participations.
   * @return the number of participations in the refused state.
   */
  int countRefusedParticipation(EventDto event);

  /**
   * 
   * @author Sacre.Christopher
   * @param event the event to get the participations.
   * @return the number of participations in the cancelled state.
   */
  int countCancelledParticipation(EventDto event);

  /**
   * 
   * @author Sacre.Christopher
   * @param event the event to get the participations.
   * @return the number of participations in the invoiced state.
   */
  int countInvoicedParticipation(EventDto event);

  /**
   * 
   * @author Sacre.Christopher
   * @param event the event to get the participations.
   * @return the number of participations in the paid state.
   */
  int countPaidParticipation(EventDto event);

  /**
   * 
   * @author Sacre.Christopher
   * @param event the event to get the participations.
   * @return the list of participations related to the event.
   */
  List<ParticipationDto> getAllPartcipationFor(EventDto event);

  /**
   * @author Sacre.Christopher
   * @param event the event to get the participations.
   * @return the list of participations in the invited state related to the event.
   */
  List<ParticipationDto> getInvitedParticipationsFor(EventDto event);

  /**
   * @author Sacre.Christopher
   * @param event the event to get the participations.
   * @return the list of participations in the confirmed state related to the event.
   */
  List<ParticipationDto> getConfirmedParticipationsFor(EventDto event);

  /**
   * @author Sacre.Christopher
   * @param event the event to get the participations.
   * @return the list of participations in the refused state related to the event.
   */
  List<ParticipationDto> getRefusedParticipationsFor(EventDto event);

  /**
   * @author Sacre.Christopher
   * @param event the event to get the participations.
   * @return the list of participations in the cancelled state related to the event.
   */
  List<ParticipationDto> getCancelledParticipationsFor(EventDto event);

  /**
   * @author Sacre.Christopher
   * @param event the event to get the participations.
   * @return the list of participations in the invoiced state related to the event.
   */
  List<ParticipationDto> getInvoicedParticipationsFor(EventDto event);

  /**
   * @author Sacre.Christopher
   * @param event the event to get the participations.
   * @return the list of participations in the paid state related to the event.
   */
  List<ParticipationDto> getPaidParticipationsFor(EventDto event);
}
