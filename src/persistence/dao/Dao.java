package persistence.dao;

import biz.Dto;

public interface Dao {

  /**
   * Used to update a Dto.
   * 
   * @param dto the dto to be updated.
   * @return the id of the updated object.
   */
  public int update(Dto dto);

  /**
   * Used to insert a Dto.
   * 
   * @param dto the dto to be inserted.
   * @return the id of the inserted object.
   */
  public int insert(Dto dto);

  /**
   * Used to delete a Dto.
   * 
   * @param dto the dto to be deleted.
   * @return the id of the deleted object.
   */
  public int delete(Dto dto);
}
