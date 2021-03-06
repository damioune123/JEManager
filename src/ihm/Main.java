package ihm;

import javax.servlet.http.HttpServlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import biz.AttendanceFactory;
import biz.CompanyFactory;
import biz.ContactFactory;
import biz.EventFactory;
import biz.ParticipationFactory;
import biz.UserFactory;
import ucc.AttendanceUcc;
import ucc.CompanyUcc;
import ucc.ContactUcc;
import ucc.EventUcc;
import ucc.ParticipationUcc;
import ucc.UserUcc;

@SuppressWarnings("serial")
public class Main extends HttpServlet {

  /**
   * Common Main used to start the server.
   * 
   * @author Sacre.Christopher
   * @param args : the common main's args.
   * @throws Exception : the exception thrown by the server's launch.
   */
  public static void main(String[] args) throws Exception {
    PluginProperties props = setEnvironment(args);
    UserUcc userUcc = (UserUcc) props.getPluginFor(UserUcc.class);
    UserFactory userFactory = (UserFactory) props.getPluginFor(UserFactory.class);
    EventUcc eventUcc = (EventUcc) props.getPluginFor(EventUcc.class);
    EventFactory eventFactory = (EventFactory) props.getPluginFor(EventFactory.class);
    CompanyUcc companyUcc = (CompanyUcc) props.getPluginFor(CompanyUcc.class);
    CompanyFactory companyFactory = (CompanyFactory) props.getPluginFor(CompanyFactory.class);
    ContactUcc contactUcc = (ContactUcc) props.getPluginFor(ContactUcc.class);
    ContactFactory contactFactory = (ContactFactory) props.getPluginFor(ContactFactory.class);
    ParticipationUcc participationUcc =
        (ParticipationUcc) props.getPluginFor(ParticipationUcc.class);
    ParticipationFactory participationFactory =
        (ParticipationFactory) props.getPluginFor(ParticipationFactory.class);
    AttendanceUcc attendanceUcc = (AttendanceUcc) props.getPluginFor(AttendanceUcc.class);
    AttendanceFactory attendanceFactory =
        (AttendanceFactory) props.getPluginFor(AttendanceFactory.class);

    WebAppContext context = new WebAppContext();
    context.setResourceBase("www");
    AppServlet app = new AppServlet(userUcc, userFactory, eventUcc, eventFactory, companyUcc,
        companyFactory, contactUcc, contactFactory, participationUcc, participationFactory,
        attendanceUcc, attendanceFactory);
    context.addServlet(new ServletHolder(app), "/Login");
    app.setSecret(props.getProperty("jwt"));
    context.addServlet(new ServletHolder(new DefaultServlet()), "/");
    Server server = new Server(8060);
    server.setHandler(context);
    server.start();
  }

  /**
   * Used to set the environment using the programs args.
   * 
   * @author Sacre.Christopher.
   * @param args : the main's args.
   */
  private static PluginProperties setEnvironment(String[] args) {
    if (args.length == 0) {
      return new PluginProperties("src/ihm/config/prod.properties");
    } else {
      return new PluginProperties("src/ihm/config/" + args[0] + ".properties");
    }
  }
}
