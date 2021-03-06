package biz.objects;

import biz.AttendanceDto;
import biz.AttendanceFactory;

/**
 * This implementation of the factory is used to create new Real DTO objects.
 * 
 * @author Antoine.Maniet
 */
class AttendanceFactoryImpl implements AttendanceFactory {
  public AttendanceDto getAttendance() {
    return new AttendanceImpl();
  }
}
