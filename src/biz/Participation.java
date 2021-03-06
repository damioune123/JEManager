package biz;

import exceptions.WrongStateException;

/**
 * This is the Participation class's interface as seen from a UCC. Once a UCC has been given a DTO,
 * he may cast said object into this type in order to access all business-related methods in
 * addition to the DTO's accessors.
 * 
 * @author Antoine.Maniet
 */
public interface Participation extends ParticipationDto, OptimisticLock {

  /**
   * @throws WrongStateException if the state isn't in a possible state.
   */
  public void checkState() throws WrongStateException;
}
