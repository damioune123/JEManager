package persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import biz.Company;
import biz.CompanyFactory;
import biz.Dto;
import biz.Event;
import biz.EventFactory;
import biz.Participation;
import biz.ParticipationFactory;
import exceptions.FatalException;
import exceptions.OptimisticException;
import persistence.DalBackEndServices;
import ucc.ParticipationUcc;
import util.Util;

public class ParticipationDaoImpl implements ParticipationDao {

  private DalBackEndServices dalBackendService;
  private ParticipationFactory participationFactory;
  private CompanyFactory companyFactory;
  private EventFactory eventFactory;

  /**
   * The constructor of this class.
   * 
   * @param dalServices the dalServices linking us to the DB
   * @param participationFactory the factory for participations
   * @param companyFactory the factory for companies
   * @param eventFactory the factory for events
   */
  public ParticipationDaoImpl(DalBackEndServices dalServices,
      ParticipationFactory participationFactory, CompanyFactory companyFactory,
      EventFactory eventFactory) {
    this.dalBackendService = dalServices;
    this.participationFactory = participationFactory;
    this.companyFactory = companyFactory;
    this.eventFactory = eventFactory;
  }

  @Override
  public List<Participation> getCurrentParticipations() {
    List<Participation> participations = new ArrayList<Participation>();
    try (PreparedStatement prepare =
        dalBackendService.prepareStatement("query.getCurrentParticipations")) {
      try (ResultSet result = prepare.executeQuery()) {
        while (result.next()) {
          Participation participation = (Participation) participationFactory.getParticipation();
          participation.setParticipationId(result.getInt(1));
          participation.setState(result.getString(2));
          participation.setVersion(result.getInt(3));
          Company company = (Company) companyFactory.getCompany();
          company.setCompanyId(result.getInt(4));
          company.setName(result.getString(5));
          company.setDateFirstContact(Util.stringToLocalDateTime(result.getString(6)));
          company.setStreet(result.getString(7));
          company.setNumber(result.getInt(8));
          company.setBox(result.getString(9));
          company.setPostalCode(result.getInt(10));
          company.setMunicipality(result.getString(11));
          Event event = (Event) eventFactory.getEvent();
          event.setEventId(result.getInt(12));
          event.setDate(Util.stringToLocalDateTime(result.getString(13)));
          participation.setCompany(company);
          participation.setEvent(event);
          participations.add(participation);
        }
      }
    } catch (SQLException sqle) {
      throw new FatalException(sqle);
    }
    return participations;
  }

  @Override
  public List<Participation> getAllParticipations() {
    List<Participation> participations = new ArrayList<Participation>();
    try (PreparedStatement prepare =
        dalBackendService.prepareStatement("query.getTrulyAllParticipations")) {
      try (ResultSet result = prepare.executeQuery()) {
        while (result.next()) {
          Participation participation = (Participation) participationFactory.getParticipation();
          participation.setState(result.getString(2));
          participation.setVersion(result.getInt(3));
          participation.setParticipationId(result.getInt(1));
          Company company = (Company) companyFactory.getCompany();
          company.setName(result.getString(5));
          company.setDateFirstContact(Util.stringToLocalDateTime(result.getString(6)));
          company.setStreet(result.getString(7));
          company.setCompanyId(result.getInt(4));
          company.setPostalCode(result.getInt(10));
          company.setNumber(result.getInt(8));
          company.setMunicipality(result.getString(11));
          company.setBox(result.getString(9));
          Event event = (Event) eventFactory.getEvent();
          event.setDate(Util.stringToLocalDateTime(result.getString(13)));
          event.setEventId(result.getInt(12));
          participation.setEvent(event);
          participation.setCompany(company);
          participations.add(participation);
        }
      }
    } catch (SQLException sqle) {
      throw new FatalException(sqle);
    }
    return participations;
  }

  @Override
  public List<Participation> getParticipationForState(Event eventParam, String type) {
    String query = null;
    switch (type) {
      case ParticipationUcc.ALL:
        query = "query.getAllParticipations";
        break;
      case ParticipationUcc.CONFIRMED:
        query = "query.getConfirmedParticipations";
        break;
      case ParticipationUcc.INVITED:
        query = "query.getInvitedParticipations";
        break;
      case ParticipationUcc.REFUSED:
        query = "query.getRefusedParticipations";
        break;
      case ParticipationUcc.CANCELLED:
        query = "query.getCancelledParticipations";
        break;
      case ParticipationUcc.INVOICED:
        query = "query.getInvoicedParticipations";
        break;
      case ParticipationUcc.PAID:
        query = "query.getPaidParticipations";
        break;
      default:
    }
    if (query == null) {
      throw new IllegalArgumentException("Attention la query ne peut être nulle");
    }
    List<Participation> participations = new ArrayList<Participation>();
    try (PreparedStatement prepare = dalBackendService.prepareStatement(query)) {
      prepare.setInt(1, eventParam.getEventId());
      try (ResultSet result = prepare.executeQuery()) {
        while (result.next()) {
          Participation participation = (Participation) participationFactory.getParticipation();
          participation.setState(result.getString(2));
          participation.setParticipationId(result.getInt(1));
          participation.setVersion(result.getInt(3));
          Company company = (Company) companyFactory.getCompany();
          company.setName(result.getString(5));
          company.setCompanyId(result.getInt(4));
          company.setDateFirstContact(Util.stringToLocalDateTime(result.getString(6)));
          company.setStreet(result.getString(7));
          company.setBox(result.getString(9));
          company.setNumber(result.getInt(8));
          company.setMunicipality(result.getString(11));
          company.setPostalCode(result.getInt(10));
          Event event = (Event) eventFactory.getEvent();
          event.setDate(Util.stringToLocalDateTime(result.getString(13)));
          event.setEventId(result.getInt(12));
          participation.setCompany(company);
          participation.setEvent(event);
          participations.add(participation);
        }
      }
    } catch (SQLException sqe) {
      sqe.printStackTrace();
      throw new FatalException();
    }
    return participations;
  }

  @Override
  public int countParticipations(Event event, String type) {
    int count = -1;
    String query = null;
    switch (type) {
      case ParticipationUcc.CONFIRMED:
        query = "query.countConfirmedParticipations";
        break;
      case ParticipationUcc.INVITED:
        query = "query.countInvitedParticipations";
        break;
      case ParticipationUcc.REFUSED:
        query = "query.countRefusedParticipations";
        break;
      case ParticipationUcc.CANCELLED:
        query = "query.countCancelledParticipations";
        break;
      case ParticipationUcc.INVOICED:
        query = "query.countInvoicedParticipations";
        break;
      case ParticipationUcc.PAID:
        query = "query.countPaidParticipations";
        break;
      default:
    }
    if (query == null) {
      throw new IllegalArgumentException("Attention la query ne peut être nulle");
    }
    try (PreparedStatement prepare = dalBackendService.prepareStatement(query)) {
      prepare.setInt(1, event.getEventId());
      try (ResultSet result = prepare.executeQuery()) {
        while (result.next()) {
          count = result.getInt(1);
          return count;
        }
      } catch (SQLException sqle) {
        throw new FatalException(sqle);
      }
    } catch (SQLException sqle2) {
      throw new FatalException(sqle2);
    }
    return count;
  }

  @Override
  public int update(Dto dto) {
    Participation participation = (Participation) dto;
    int compteur = 0;
    try (PreparedStatement prepare = dalBackendService.prepareStatement("query.updateState")) {
      prepare.setString(1, participation.getState());
      prepare.setInt(2, participation.getVersion() + 1);
      prepare.setInt(3, participation.getParticipationId());
      prepare.setInt(4, participation.getVersion());
      try (ResultSet result = prepare.executeQuery()) {
        while (result.next()) {
          compteur++;
        }
      } catch (SQLException sqle) {
        throw new FatalException(sqle);
      }
    } catch (SQLException sqle2) {
      throw new FatalException(sqle2);
    }
    if (compteur == 0) {
      throw new OptimisticException("Optimistic lock");
    }
    return participation.getParticipationId();
  }

  @Override
  public int insert(Dto dto) {
    Participation participation = (Participation) dto;
    int id = -1;
    try (PreparedStatement prepare = dalBackendService.prepareStatement("query.addParticipation")) {
      prepare.setInt(1, participation.getCompany().getCompanyId());
      prepare.setInt(2, participation.getEvent().getEventId());
      prepare.setString(3, participation.getState());
      try (ResultSet result = prepare.executeQuery()) {
        while (result.next()) {
          id = result.getInt(1);
          return id;
        }
      } catch (SQLException sqle) {
        throw new FatalException(sqle);
      }
    } catch (SQLException sqle2) {
      throw new FatalException(sqle2);
    }
    return id;
  }

  @Override
  public int delete(Dto dto) {
    throw new UnsupportedOperationException();
  }
}
